import java.awt.*;

/**
 * Klasa reprezentująca obiekt odpadu w grze.
 */
public class Odpad {
    private Point poczatkoweMiejsce; // Początkowe miejsce odpadu
    private Point pozycja; // Aktualna pozycja odpadu
    private int szerokosc; // Szerokość odpadu
    private int wysokosc; // Wysokość odpadu
    private Image obrazek;// Obrazek odpadu
    private String typ;// Typ odpadu

    /**
     * Konstruktor klasy Odpad.
     *
     * @param poczatkoweMiejsce Początkowe miejsce odpadu.
     * @param szerokosc         Szerokość odpadu.
     * @param wysokosc          Wysokość odpadu.
     * @param obrazek           Obrazek odpadu.
     * @param typ               Typ odpadu.
     */
    public Odpad(Point poczatkoweMiejsce, int szerokosc, int wysokosc, Image obrazek, String typ) {
        this.poczatkoweMiejsce = poczatkoweMiejsce;
        this.pozycja = poczatkoweMiejsce;
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.obrazek = obrazek;
        this.typ = typ;
    }

    /**
     * Pobiera typ odpadu.
     *
     * @return Typ odpadu.
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Rysuje odpad na ekranie.
     *
     * @param g Obiekt Graphics do rysowania.
     */
    public void rysuj(Graphics g) {
        // Rysuje obrazek odpadu na aktualnej pozycji
        g.drawImage(obrazek, pozycja.x, pozycja.y, szerokosc, wysokosc, null);
    }

    /**
     * Sprawdza, czy dany punkt znajduje się w obszarze odpadu.
     *
     * @param punkt Punkt do sprawdzenia.
     * @return true, jeśli punkt znajduje się w obszarze odpadu, w przeciwnym razie false.
     */
    public boolean zawieraPunkt(Point punkt) {
        // Tworzy prostokąt reprezentujący obszar odpadu i sprawdza, czy punkt znajduje się w nim
        Rectangle obszarOdpadu = new Rectangle(pozycja.x, pozycja.y, szerokosc, wysokosc);
        return obszarOdpadu.contains(punkt);
    }

    /**
     * Przesuwa odpad do nowej pozycji.
     *
     * @param nowaPozycja Nowa pozycja odpadu.
     */
    public void przesun(Point nowaPozycja) {
        // Ustawia nową pozycję odpadu
        pozycja = nowaPozycja;
    }

    /**
     * Ustawia odpad na początkowym miejscu.
     */
    public void ustawNaPoczatkoweMiejsce() {
        // Ustawia pozycję odpadu na początkowym miejscu
        pozycja = poczatkoweMiejsce;
    }
}
