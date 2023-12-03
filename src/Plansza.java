import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Plansza extends JPanel {
    private Image tlo;
    private List<Odpad> odpady;
    private List<Pojemnik> pojemniki;
    private Odpad aktualniePrzesuwanyOdpad;

    public Plansza() {
        // Wczytanie tła
        tlo = new ImageIcon("D:\\Studia\\Semestr 5\\JPWP projekt\\EkoGierka\\tlo1.jpg").getImage();

        // Inicjalizzacja listy odpadów i pojemników
        odpady = new ArrayList<>();
        pojemniki = new ArrayList<>();

        // Dodaj odpady
        dodajOdpady();

        // Dodaj pojemniki
        dodajPojemniki();

        dodajObslugeMyszy();
    }

    private void dodajObslugeMyszy() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                for (Odpad odpad : odpady) {
                    if (odpad.zawieraPunkt(e.getPoint())) {
                        aktualniePrzesuwanyOdpad = odpad;
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                aktualniePrzesuwanyOdpad = null;
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (aktualniePrzesuwanyOdpad != null) {
                    aktualniePrzesuwanyOdpad.przesun(e.getPoint());
                    repaint();
                }
            }
        });

    }

    private void dodajOdpady() {
        odpady.add(new Odpad(new Point(100, 100), 20, 20, Color.BLUE));
        odpady.add(new Odpad(new Point(200, 300), 30, 40, Color.BLUE));
        odpady.add(new Odpad(new Point(400, 500), 30, 40, Color.BLUE));
        odpady.add(new Odpad(new Point(450, 500), 25, 60, Color.BLUE));
        odpady.add(new Odpad(new Point(500, 500), 30, 40, Color.BLUE));
        odpady.add(new Odpad(new Point(600, 500), 30, 40, Color.BLUE));
        odpady.add(new Odpad(new Point(700, 300), 30, 40, Color.BLUE));
    }

    private void dodajPojemniki() {

        Color[] kolory = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK};
        String[] podpisy = {"METAL", "PAPIER", "SZKŁO", "PLASTIK", "BIO", "RESZTA"};

        int x = 50; // Początkowa pozycja X dla pierwszego pojemnika

        for (int i = 0; i < 6; i++) {
            Rectangle pojemnikProstokat = new Rectangle(x, 700, 145, 165);
            Color kolor = kolory[i];
            String podpis = podpisy[i];

            Pojemnik nowyPojemnik = new Pojemnik(pojemnikProstokat, kolor, podpis);
            pojemniki.add(nowyPojemnik);

            x += 200; // Odległość między pojemnikami
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rysuj tło
        g.drawImage(tlo, 0, 0, 1280, 820, this);

        // Rysuj odpady
        for (Odpad odpad : odpady) {
            odpad.rysuj(g);
        }

        // Rysuj pojemniki
        for (Pojemnik pojemnik : pojemniki) {
            pojemnik.rysuj(g);
        }
    }

}
