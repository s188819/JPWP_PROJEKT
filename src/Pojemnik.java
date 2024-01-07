import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca pojemnik na odpady w grze.
 */
public class Pojemnik {
    private Rectangle prostokat;// Prostokąt reprezentujący obszar pojemnika
    private Color kolor;// Kolor pojemnika
    private String podpis;// Podpis pojemnika
    private List<Odpad> odpadyWewnatrz; // Lista odpadów znajdujących się wewnątrz pojemnika
    private List<String> akceptowaneTypy;// Lista akceptowanych typów odpadów

    /**
     * Konstruktor klasy Pojemnik.
     *
     * @param prostokat       Prostokąt reprezentujący obszar pojemnika.
     * @param kolor           Kolor pojemnika.
     * @param podpis          Podpis pojemnika.
     * @param akceptowaneTypy Lista akceptowanych typów odpadów.
     */
    public Pojemnik(Rectangle prostokat, Color kolor, String podpis, List<String> akceptowaneTypy) {
        this.prostokat = prostokat;
        this.kolor = kolor;
        this.podpis = podpis;
        this.odpadyWewnatrz = new ArrayList<>();
        this.akceptowaneTypy = akceptowaneTypy;
    }

    /**
     * Rysuje pojemnik na ekranie.
     *
     * @param g Obiekt Graphics do rysowania.
     */
    public void rysuj(Graphics g) {
        // Rysuje prostokąt reprezentujący obszar pojemnika w danym kolorze
        g.setColor(kolor);
        g.fillRect(prostokat.x, prostokat.y, prostokat.width, prostokat.height);

        // Ustawia kolor tekstu na czarny i ustawia czcionkę
        g.setColor(Color.BLACK);
        g.setFont(new Font("Dialog", Font.BOLD, 25));

        // Wysrodkowanie napisu w pojemniku
        int x = prostokat.x + prostokat.width / 2 - g.getFontMetrics().stringWidth(podpis) / 2;
        int y = prostokat.y + prostokat.height / 2 + g.getFontMetrics().getHeight() / 4;
        // Rysuje napis w pojemniku
        g.drawString(podpis, x, y);
    }

    /**
     * Sprawdza, czy dany punkt znajduje się w obszarze pojemnika.
     *
     * @param punkt Punkt do sprawdzenia.
     * @return true, jeśli punkt znajduje się w obszarze pojemnika, w przeciwnym razie false.
     */
    public boolean zawieraPunkt(Point punkt) {
        // Sprawdza, czy dany punkt znajduje się w obszarze prostokąta pojemnika
        return prostokat.contains(punkt);
    }

    /**
     * Sprawdza, czy pojemnik akceptuje dany typ odpadu.
     *
     * @param typ Typ odpadu do sprawdzenia.
     * @return true, jeśli pojemnik akceptuje dany typ odpadu, w przeciwnym razie false.
     */
    public boolean akceptujeTyp(String typ) {
        // Sprawdza, czy dany typ odpadu jest na liście akceptowanych typów
        return akceptowaneTypy.contains(typ);
    }

    /**
     * Dodaje odpad do pojemnika i ustawia go na początkowym miejscu.
     *
     * @param odpad Odpad do dodania do pojemnika.
     */
    public void dodajOdpad(Odpad odpad) {
        // Dodaje odpad do listy odpadów wewnątrz pojemnika
        odpadyWewnatrz.add(odpad);
        // Ustawia odpad na początkowym miejscu, jeśli nie pasuje do pojemnika
        odpad.ustawNaPoczatkoweMiejsce();
    }
}
