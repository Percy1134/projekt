package percy.obronsie;

class Poziom1 extends Poziom {
    Poziom1(int dlx, int dly, int przesuniecie) {
        super();
        this.maxIloscBelek = 3;
        this.maxIloscWzBelek = 1;
        this.bron = new int[2];
        this.kulki = new int[1][2];
        this.bron[0] = (int)((dlx-przesuniecie)/2);
        this.bron[1] = (int)(dly*0.9);
        this.kulki[0][0] = (int)((dlx-przesuniecie)/2);
        this.kulki[0][1] = (int)(dly*0.10);
    }
}
