import java.awt.*;

public class Odpad {
    private Point poczatkoweMiejsce;
    private Point pozycja;
    private int szerokosc;
    private int wysokosc;
    private Image obrazek;
    private String typ;

    public Odpad(Point poczatkoweMiejsce, int szerokosc, int wysokosc, Image obrazek, String typ) {
        this.poczatkoweMiejsce = poczatkoweMiejsce;
        this.pozycja = poczatkoweMiejsce;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.obrazek = obrazek;
        this.typ = typ;
    }

    public String getTyp() {
        return typ;
    }

    public void rysuj(Graphics g) {
        g.drawImage(obrazek, pozycja.x, pozycja.y, szerokosc, wysokosc, null);
    }

    public boolean zawieraPunkt(Point punkt) {
        Rectangle obszarOdpadu = new Rectangle(pozycja.x, pozycja.y, szerokosc, wysokosc);
        return obszarOdpadu.contains(punkt);
    }

    public void przesun(Point nowaPozycja) {
        pozycja = nowaPozycja;
    }
    public void ustawNaPoczatkoweMiejsce() {
        pozycja = poczatkoweMiejsce;
    }


}
