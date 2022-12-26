package percy.obronsie;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Menu extends JFrame {
    JPanel tekst;
    JRysowanie rysowanie;
    JLabel powitanie;
    JLabel wybierz;
    JButton latwy;
    JButton sredni;
    JButton trudny;
    int iloscElementow;
    Poziom poziom;

    Menu() {
        this.setTitle("Obroń się!");
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1280, 1024);
        this.tekst = new JPanel();
        this.tekst.setLocation(0, 0);
        this.tekst.setSize(150, 1024);
        this.tekst.setBackground(new Color(0,0,255));
        this.rysowanie = new JRysowanie();
        this.rysowanie.setLocation(150, 0);
        this.rysowanie.setSize(1280-150, 1024);
        this.add(this.tekst);
        this.add(this.rysowanie);
        this.powitanie = new JLabel("Witaj w Obroń się!");
        this.tekst.add(this.powitanie);
        this.wybierz = new JLabel("Wybierz poziom:");
        this.latwy = new JButton("Łatwy");
        this.sredni = new JButton("Średni");
        this.trudny = new JButton("Trudny");
        this.tekst.add(this.wybierz);
        this.tekst.add(this.latwy);
        this.tekst.add(this.sredni);
        this.tekst.add(this.trudny);
        this.setVisible(true);
        this.iloscElementow = 2;
        this.poziom = new Poziom2();
        this.latwy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                iloscElementow = 3;
                poziom = new Poziom3();
            }
        });
        this.sredni.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                iloscElementow = 2;
                poziom = new Poziom2();
            }
        });
        this.trudny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                iloscElementow = 1;
                poziom = new Poziom1();
            }
        });
    }
}