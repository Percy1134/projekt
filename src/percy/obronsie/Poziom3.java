package percy.obronsie;
/*
 * ustalanie max ilosci belek i pozycji kulek
 */
class Poziom3 extends Poziom {
    Poziom3(int dlx, int dly, int przesuniecie) {
        super();
        this.maxIloscBelek = 1;
        this.maxIloscWzBelek = 1;
        this.bron = new int[2];
        this.kulki = new int[3][2];
        this.bron[0] = (int)((dlx-przesuniecie)/2);
        this.bron[1] = (int)(dly*0.9);
        this.kulki[0][0] = (int)((dlx-przesuniecie)/2-300);
        this.kulki[0][1] = (int)(dly*0.10);
        this.kulki[1][0] = (int)((dlx-przesuniecie)/2);
        this.kulki[1][1] = (int)(dly*0.10);
        this.kulki[2][0] = (int)((dlx-przesuniecie)/2+300);
        this.kulki[2][1] = (int)(dly*0.10);
    }
}
