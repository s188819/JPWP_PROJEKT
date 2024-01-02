import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pojemnik {
    private Rectangle prostokat;
    private Color kolor;
    private String podpis;
    private List<Odpad> odpadyWewnatrz;
    private List<String> akceptowaneTypy;

    public Pojemnik(Rectangle prostokat, Color kolor, String podpis, List<String> akceptowaneTypy) {
        this.prostokat = prostokat;
        this.kolor = kolor;
        this.podpis = podpis;
        this.odpadyWewnatrz = new ArrayList<>();
        this.akceptowaneTypy = akceptowaneTypy;
    }

    public void rysuj(Graphics g) {
        g.setColor(kolor);
        g.fillRect(prostokat.x, prostokat.y, prostokat.width, prostokat.height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Dialog", Font.BOLD, 25));

        // Wysrodkowanie napisu
        int x = prostokat.x + prostokat.width / 2 - g.getFontMetrics().stringWidth(podpis) / 2;
        int y = prostokat.y + prostokat.height / 2 + g.getFontMetrics().getHeight() / 4;

        g.drawString(podpis, x, y);
    }

    public boolean zawieraPunkt(Point punkt) {
        return prostokat.contains(punkt);
    }

    public boolean akceptujeTyp(String typ) {
        return akceptowaneTypy.contains(typ);
    }

    public void dodajOdpad(Odpad odpad) {
        odpadyWewnatrz.add(odpad);
        odpad.ustawNaPoczatkoweMiejsce();
    }
}
