import java.awt.*;

public class Odpad {
    private Point pozycja;
    private int szerokosc;
    private int wysokosc;
    private Color kolor;

    public Odpad(Point pozycja, int szerokosc, int wysokosc, Color kolor) {
        this.pozycja = pozycja;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.kolor = kolor;
    }

    public void rysuj(Graphics g) {
        g.setColor(kolor);
        g.fillRect(pozycja.x, pozycja.y, szerokosc, wysokosc);
    }

    public boolean zawieraPunkt(Point punkt) {
        Rectangle obszarOdpadu = new Rectangle(pozycja.x, pozycja.y, szerokosc, wysokosc);
        return obszarOdpadu.contains(punkt);
    }

    public void przesun(Point nowaPozycja) {
        pozycja = nowaPozycja;
    }
}
