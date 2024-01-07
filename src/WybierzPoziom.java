import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa reprezentująca panel do wyboru poziomu gry.
 */
public class WybierzPoziom extends JPanel {
    private OknoGry oknoGry; // Referencja do obiektu klasy OknoGry
    private JFrame poziomFrame; // Referencja do ramki wyboru poziomu
    String currentDir = System.getProperty("user.dir"); // Ścieżka do aktualnego katalogu użytkownika
    Color kolor = new Color(212, 224, 240); // Kolor tła panelu wyboru poziomu

    /**
     * Konstruktor klasy WybierzPoziom.
     *
     * @param oknoGry     Referencja do obiektu klasy OknoGry.
     * @param poziomFrame Referencja do ramki wyboru poziomu.
     */
    public WybierzPoziom(OknoGry oknoGry, JFrame poziomFrame) {
        // Przypisanie referencji do obiektów
        this.oknoGry = oknoGry;
        this.poziomFrame = poziomFrame;

        setLayout(new GridLayout(4, 1)); // Ustawienie układu panelu wyboru poziomu
        setBackground(kolor);// Ustawienie koloru tła panelu wyboru poziomu

        // Dodanie napisu graficznego do panelu wyboru poziomu
        JLabel napisGraficzny = new JLabel(new ImageIcon(currentDir + "\\obrazki\\WybierzPoziom.png"));
        add(napisGraficzny);

        // Utworzenie i dodanie przycisku dla poziomu 1 do panelu wyboru poziomu, który powoduje rozpoczęcie poziomu 1
        JButton poziom1 = utworzPrzycisk(currentDir + "\\obrazki\\Poziom1.png");
        poziom1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.rozpocznijPoziom(0);
                poziomFrame.dispose();
            }
        });

        // Utworzenie i dodanie przycisku dla poziomu 2 do panelu wyboru poziomu, który powoduje rozpoczęcie poziomu 2
        JButton poziom2 = utworzPrzycisk(currentDir + "\\obrazki\\Poziom2.png");
        poziom2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.rozpocznijPoziom(1);
                poziomFrame.dispose();
            }
        });

        // Utworzenie i dodanie przycisku dla poziomu 3 do panelu wyboru poziomu, który powoduje rozpoczęcie poziomu 3
        JButton poziom3 = utworzPrzycisk(currentDir + "\\obrazki\\Poziom3.png");
        poziom3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oknoGry.rozpocznijPoziom(2);
                poziomFrame.dispose();
            }
        });

        // Dodanie przycisków do panelu wyboru poziomu
        add(poziom1);
        add(poziom2);
        add(poziom3);
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
