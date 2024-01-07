import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa reprezentująca menu gry.
 */
public class Menu extends JPanel {
    private OknoGry oknoGry;  // Referencja do obiektu klasy OknoGry
    private JFrame menuFrame; // Referencja do ramki menu
    String currentDir = System.getProperty("user.dir"); // Ścieżka do aktualnego katalogu użytkownika
    Color kolor = new Color(212, 224, 240); // Kolor tła panelu menu

    /**
     * Konstruktor klasy Menu.
     *
     * @param oknoGry   Referencja do obiektu klasy OknoGry.
     * @param menuFrame Referencja do ramki menu.
     */
    public Menu(OknoGry oknoGry, JFrame menuFrame) {
        // Przypisanie referencji do obiektów
        this.oknoGry = oknoGry;
        this.menuFrame = menuFrame;

        setLayout(new GridLayout(5, 1));// Ustawienie układu panelu menu
        setBackground(kolor); // Ustawienie koloru tła panelu menu

        // Dodanie napisu graficznego do panelu menu
        JLabel napisGraficzny = new JLabel(new ImageIcon(currentDir + "\\obrazki\\WybierzOpcje.png"));
        add(napisGraficzny);

        // Utworzenie i dodanie przycisku START do panelu menu, powoduje rozpoczęcie rozgrywki od poziomu 1
        JButton start = utworzPrzycisk(currentDir + "\\obrazki\\START.png");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.rozpocznijPoziom(0);
                menuFrame.dispose();
            }
        });

        // Utworzenie i dodanie przycisku POMOC do panelu menu, który powoduje wyświetlenie pomocy
        JButton pomoc = utworzPrzycisk(currentDir + "\\obrazki\\POMOC.png");
        pomoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.wyswietlPomoc();
                menuFrame.dispose();
            }
        });

        // Utworzenie i dodanie przycisku WYBIERZ POZIOM do panelu menu, który wyświetla okno wyboru poziomu
        JButton wybierzPoziom = utworzPrzycisk(currentDir + "\\obrazki\\Wybierz_Poziom.png");
        wybierzPoziom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.wyswietlWybierzPoziom();
                menuFrame.dispose();
            }
        });

        // Utworzenie i dodanie przycisku KONIEC GRY do panelu menu, który powoduje zamknięcie gry
        JButton koniecGry = utworzPrzycisk(currentDir + "\\obrazki\\Koniec_Gry.png");
        koniecGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Dodanie przycisków do panelu menu
        add(start);
        add(pomoc);
        add(wybierzPoziom);
        add(koniecGry);
    }

    /**
     * Metoda służąca do utworzenia przycisku na podstawie ścieżki do obrazka.
     *
     * @param sciezkaDoObrazka Ścieżka do pliku z obrazkiem, który będzie używany jako ikona przycisku.
     * @return Nowy przycisk utworzony z zadaną ikoną.
     */
    private static JButton utworzPrzycisk(String sciezkaDoObrazka) {
        JButton przycisk = new JButton();

        // Konfiguracja przycisku
        przycisk.setOpaque(false);
        przycisk.setContentAreaFilled(false);
        przycisk.setBorderPainted(false);

        // Ustawienie ikony na przycisku
        ImageIcon icon = new ImageIcon(sciezkaDoObrazka);
        przycisk.setIcon(icon);

        return przycisk;
    }

}
