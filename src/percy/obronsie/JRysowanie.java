package percy.obronsie;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.awt.Color;
/*
 * wprowadza element blokujący - interakcja uzytkownika
 */
class JRysowanie extends JPanel {
    Poziom poziom;
    boolean pozwolRysowac;
    ArrayList<Belka> belka;
    Menu menu;
    int iloscBelek;
    int iloscWzBelek;
    Watek watek;
    JRysowanie(Menu menu) {
        super();
        this.menu = menu;
        this.watek = new Watek(this);
        this.poziom = new Poziom();
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX());           //lewy przycisk myszy rysowania belek
                System.out.println(e.getY());           //prawy przycisk myszy rysowania belek
            }
            public void mousePressed(MouseEvent e) {
                if (poziom.maxIloscBelek == 0) {
                    return;
                }
                Belka nowaBelka = new Belka();
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (poziom.maxIloscBelek > iloscBelek) {
                        iloscBelek++;
                    } else {
                        return;
                    }
                }
                else if (SwingUtilities.isRightMouseButton(e)) {
                    if (poziom.maxIloscWzBelek > iloscWzBelek) {
                        nowaBelka.wytrzymalosc = 2;
                        iloscWzBelek++;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
                belka.add(nowaBelka);
                pozwolRysowac = true;
            }
            public void mouseReleased(MouseEvent e) {
                if (poziom.maxIloscBelek == 0) {
                    return;
                }
                pozwolRysowac = false;
            }
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged (MouseEvent e) {
                if (!pozwolRysowac || poziom.maxIloscBelek == 0) {
                    return;
                }
                Kulka kulka = new Kulka();
                if (e.getY() > poziom.bron[1]-kulka.dly) {
                    return;
                }
                int najdalszy = 0;
                for (int i = 0; i<poziom.kulki.length;i++) {
                    if (poziom.kulki[i][1]+kulka.dly*2 > najdalszy) {
                        najdalszy = poziom.kulki[i][1]+kulka.dly*2;
                    }
                }
                if (najdalszy > e.getY()) {
                    return;
                }
                Belka nowaBelka = belka.get(belka.size()-1);
                ArrayList<Integer> punkty = new ArrayList<Integer>();
                punkty.add(e.getX());
                punkty.add(e.getY());
                nowaBelka.punkty.add(punkty);
                repaint();
            }
            public void mouseMoved (MouseEvent e) { }
        });
    }
    
    /** 
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (poziom.maxIloscBelek == 0) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        Kulka kulka = new Kulka();
        int iloscKulek = poziom.kulki.length;
        g2.setColor(new Color(10,150,15));
        for (int i = 0; i<iloscKulek; i++) {
            if (poziom.kulki[i][0] < 0 || poziom.kulki[i][1] < 0) {
                continue;
            }
            g2.fillRect(poziom.kulki[i][0], poziom.kulki[i][1], kulka.dlx, kulka.dly);
        }
        g2.setColor(new Color(25,110,220));
        g2.fillRect(poziom.bron[0], poziom.bron[1], 30, 30);
        for (int i = 0; i<belka.size(); i++) {
            if (belka.get(i).wytrzymalosc < 1) {
                continue;
            }
            if (belka.get(i).wytrzymalosc == 1) {
                g2.setColor(new Color(0,255,0));
            } else {
                g2.setColor(new Color(0,0,255));
            }
            for (int j = 0; j<belka.get(i).punkty.size(); j++) {
                g2.fillRect(belka.get(i).punkty.get(j).get(0)-2,belka.get(i).punkty.get(j).get(1)-2, 5, 5);
            }
        }
    }
    
    /** 
     * @param poz
     * @param dlx
     * @param dly
     * @param przesuniecie
     */
    void ustawPoziom(int poz, int dlx,int dly,int przesuniecie) {
        switch(poz) {
            case 1:
                this.poziom = new Poziom1(dlx,dly,przesuniecie);
                break;
            case 2:
                this.poziom = new Poziom2(dlx,dly,przesuniecie);
                break;
            case 3:
                this.poziom = new Poziom3(dlx,dly,przesuniecie);
                break;
            default:
                return;
        }
        this.belka = new ArrayList<Belka>();
        this.iloscBelek = 0;
        this.iloscWzBelek = 0;
        this.watek.stop();
        this.repaint();
    }
    void start() {
        this.watek = new Watek(this);
        this.watek.start();
    }
}
