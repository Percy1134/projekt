package percy.obronsie;
import java.lang.Thread;
import java.lang.Math;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/*
 * cala reszta
 * pozycjonowanie
 * wektor
 * zmniejsza wytrzymalosc belki po trafieniu
 * 
 */
class Watek extends Thread {
    JRysowanie rys;
    double resztax[];
    double resztay[];
    Watek(JRysowanie rys) {
        super();
        this.rys = rys;
    }
    public void run() {
        double dx;
        double dy;
        double dl;
        double kierx;
        double kiery;
        Kulka kulka = new Kulka();
        this.resztax = new double[this.rys.poziom.kulki.length];
        this.resztay = new double[this.rys.poziom.kulki.length];
        while (true) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
            for (int i = 0; i<this.rys.poziom.kulki.length; i++) {
                if (this.rys.poziom.kulki[i][0] < 0 || this.rys.poziom.kulki[i][1] < 0) {
                    continue;
                }
                dx = this.rys.poziom.bron[0] - this.rys.poziom.kulki[i][0];
                dy = this.rys.poziom.bron[1] - this.rys.poziom.kulki[i][1];
                dl = Math.sqrt(dx*dx+dy*dy);
                resztax[i] += dx/dl;
                resztay[i] += dy/dl;
                if (dx < 0) {
                    kierx = -1;
                } else {
                    kierx = 1;
                }
                if (dy < 0) {
                    kiery = -1;
                } else {
                    kiery = 1;
                }
                while (Math.abs(resztax[i]) >=1) {
                    this.rys.poziom.kulki[i][0] += kierx;
                    if (dx < 0) {
                        resztax[i] -= -1;
                    } else {
                        resztax[i] -= 1;
                    }
                }
                while (Math.abs(resztay[i]) >=1) {
                    this.rys.poziom.kulki[i][1] += kiery;
                    if (dy < 0) {
                        resztay[i] -= -1;
                    } else {
                        resztay[i] -= 1;
                    }
                }
                for (int j = 0; j<this.rys.belka.size(); j++) {
                    Belka belka = this.rys.belka.get(j);
                    if (belka.wytrzymalosc < 1) {
                        continue;
                    }
                    boolean check = false;
                    ArrayList<ArrayList<Integer>> punkty = belka.punkty;
                    for (int k = 0; k<punkty.size(); k++) {
                        ArrayList<Integer> punkt = punkty.get(k);
                        for (int l = 0; l<kulka.dly; l++) {
                            for (int h = 0; h<kulka.dlx; h++) {
                                if ((this.rys.poziom.kulki[i][0]+h == punkt.get(0)) && (this.rys.poziom.kulki[i][1]+l == punkt.get(1))) {
                                    belka.wytrzymalosc--;
                                    this.rys.poziom.kulki[i][0] = -1;
                                    this.rys.poziom.kulki[i][1] = -1;
                                }
                                if (belka.wytrzymalosc < 1) {
                                    check = true;
                                }
                                if (check) {
                                    break;
                                }
                            }
                            if (check) {
                                break;
                            }
                        }
                        if (check) {
                            break;
                        }
                    }
                }
                if ((this.rys.poziom.kulki[i][0] >= this.rys.poziom.bron[0] && this.rys.poziom.kulki[i][0] <= this.rys.poziom.bron[0]+30) && (this.rys.poziom.kulki[i][1] >= this.rys.poziom.bron[1] && this.rys.poziom.kulki[i][1] <= this.rys.poziom.bron[1]+30) || 
                    (this.rys.poziom.kulki[i][0] >= this.rys.poziom.bron[0] && this.rys.poziom.kulki[i][0] <= this.rys.poziom.bron[0]+30) && (this.rys.poziom.kulki[i][1]+kulka.dly >= this.rys.poziom.bron[1] && this.rys.poziom.kulki[i][1]+kulka.dly <= this.rys.poziom.bron[1]+30) ||
                    (this.rys.poziom.kulki[i][0]+kulka.dlx >= this.rys.poziom.bron[0] && this.rys.poziom.kulki[i][0]+kulka.dlx <= this.rys.poziom.bron[0]+30) && (this.rys.poziom.kulki[i][1] >= this.rys.poziom.bron[1] && this.rys.poziom.kulki[i][1] <= this.rys.poziom.bron[1]+30) ||
                    (this.rys.poziom.kulki[i][0]+kulka.dlx >= this.rys.poziom.bron[0] && this.rys.poziom.kulki[i][0]+kulka.dlx <= this.rys.poziom.bron[0]+30) && (this.rys.poziom.kulki[i][1]+kulka.dly >= this.rys.poziom.bron[1] && this.rys.poziom.kulki[i][1]+kulka.dly <= this.rys.poziom.bron[1]+30)) {
                    JOptionPane.showMessageDialog(null, "Trafiono cel, przegrałeś");
                    return;
                }
            }
            this.rys.repaint();
        }
    }
}
