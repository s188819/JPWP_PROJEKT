import java.awt.*;

public class Pojemnik {
    private Rectangle prostokat;
    private Color kolor;
    private String podpis;

    public Pojemnik(Rectangle prostokat, Color kolor, String podpis) {
        this.prostokat = prostokat;
        this.kolor = kolor;
        this.podpis = podpis;
    }

    public void rysuj(Graphics g) {
        g.setColor(kolor);
        g.fillRect(prostokat.x, prostokat.y, prostokat.width, prostokat.height);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        // Wysrodkowanie napisu
        int x = prostokat.x + prostokat.width / 2 - g.getFontMetrics().stringWidth(podpis) / 2;
        int y = prostokat.y + prostokat.height / 2 + g.getFontMetrics().getHeight() / 4;

        g.drawString(podpis, x, y);
    }
}
