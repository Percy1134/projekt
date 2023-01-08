package percy.obronsie;
import java.lang.Thread;
import java.lang.Math;
import javax.swing.JOptionPane;
class Watek extends Thread {
    JRysowanie rys;
    double resztax;
    double resztay;
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
        while (true) {
            try {
                Thread.sleep(1);
            } catch (Exception e) {}
            for (int i = 0; i<this.rys.poziom.kulki.length; i++) {
                this.rys.repaint();
                dx = this.rys.poziom.bron[0] - this.rys.poziom.kulki[i][0];
                dy = this.rys.poziom.bron[1] - this.rys.poziom.kulki[i][1];
                dl = Math.sqrt(dx*dx+dy*dy);
                resztax = dx/dl+resztax;
                resztay = dy/dl+resztay;
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
                while (Math.abs(resztax) >=1) {
                    this.rys.poziom.kulki[i][0] += kierx;
                    if (dx < 0) {
                        resztax -= -1;
                    } else {
                        resztax -= 1;
                    }
                }
                while (Math.abs(resztay) >=1) {
                    this.rys.poziom.kulki[i][1] += kiery;
                    if (dy < 0) {
                        resztay -= -1;
                    } else {
                        resztay -= 1;
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
        }
    }
}
