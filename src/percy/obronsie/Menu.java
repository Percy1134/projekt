package percy.obronsie;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * tworzy funkcjonalny panel boczny
 */
class Menu extends JFrame {
    JPanel tekst;
    JRysowanie rysowanie;
    JLabel powitanie;
    JLabel wybierz;
    JButton latwy;
    JButton sredni;
    JButton trudny;
    JButton start;
    int dlx;
    int dly;
    int przesuniecie;
    Menu() {
        this.dlx = 1280;
        this.dly = 800;
        this.przesuniecie = 150;
        this.setTitle("Obroń się!");        //tytul okna
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(this.dlx, this.dly);
        this.tekst = new JPanel();
        this.tekst.setLocation(0, 0);       //lokalizacja panelu
        this.tekst.setSize(this.przesuniecie, this.dly);
        this.tekst.setBackground(new Color(198,198,0));     //kolory panelu
        this.rysowanie = new JRysowanie(this);
        this.rysowanie.setLocation(150, 0);
        this.rysowanie.setSize(this.dlx-this.przesuniecie, this.dly);
        this.add(this.tekst);
        this.add(this.rysowanie);
        this.powitanie = new JLabel("Witaj w Obroń się!");
        this.tekst.add(this.powitanie);
        this.wybierz = new JLabel("Wybierz poziom:");
        this.latwy = new JButton("Łatwy");
        this.sredni = new JButton("Średni");
        this.trudny = new JButton("Trudny");
        this.start = new JButton("Start");
        latwy.setBackground(Color.blue);
        latwy.setForeground(Color.RED);
        sredni.setBackground(Color.blue);
        sredni.setForeground(Color.RED);
        trudny.setBackground(Color.blue);
        trudny.setForeground(Color.RED);
        start.setBackground(Color.blue);
        start.setForeground(Color.RED);
        this.tekst.add(this.wybierz);
        this.tekst.add(this.latwy);
        this.tekst.add(this.sredni);
        this.tekst.add(this.trudny);
        this.tekst.add(this.start);
        this.setVisible(true);
        this.latwy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                rysowanie.ustawPoziom(1,dlx,dly,przesuniecie);
            }
        });
        this.sredni.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                rysowanie.ustawPoziom(2,dlx,dly,przesuniecie);
            }
        });
        this.trudny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                rysowanie.ustawPoziom(3,dlx,dly,przesuniecie);
            }
        });
        this.start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                rysowanie.start();
            }
        });
    }
}
