import javax.swing.*;
import java.awt.*;

/**
 * Klasa OknoGry reprezentuje główne okno gry.
 */
public class OknoGry {
    private JFrame frame; //Głowne okno aplikacji
    private Plansza plansza; // Plansza gry
    private JLabel punktyLabel; // Etykieta wyświetlająca liczbę punktów
    private JLabel czasLabel; // Etykieta wyświetlająca czas gry
    private JLabel poziomLabel; // Etykieta wyświetlająca aktualny poziom gry
    private int aktualnyPoziom = 0; // Aktualny poziom, na którym znajduje się gracz
    private int maksymalnyDostepnyPoziom = 3; // Maksymalny dostępny poziom w grze
    String currentDir = System.getProperty("user.dir"); // Katalog bieżący aplikacji
    Color kolor = new Color(0, 102, 0); // Kolor używany w interfejsie graficznym
    Color kolor2 = new Color(212, 224, 240); // Inny kolor używany w interfejsie graficznym

    /**
     * Konstruktor klasy OknoGry.
     * Inicjalizuje główne okno gry.
     */
    public OknoGry() {
        frame = new JFrame("Eko-Gierka Gra o Segregacji Odpadów"); // Utworzenie nowej ramki z nazwą
        frame.setSize(1280, 1024); // Ustawienie rozmiaru JFrame na 1280x1024 pikseli
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ustawienie operacji zamknięcia aplikacji po zamknięciu okna
        frame.setResizable(false); // Ustawienie, aby ramka nie była rozszerzalna przez użytkownika
        frame.setUndecorated(true); //Ustawienie ramki bez widocznego paska u góry
        dodajElementy(); // Wywołanie metody dodającej elementy do JFrame
    }

    /**
     * Metoda dodająca elementy do głównego okna gry.
     * Tworzy ekran rozgrywki, wyświetla powitalny napis i stopkę gry.
     */
    public void dodajElementy() {
        // Ekran rozgrywki
        JPanel ekranRozgrywki = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image obrazek = new ImageIcon(currentDir + "\\obrazki\\TloStartowe.jpg").getImage();
                g.drawImage(obrazek, 0, 0, this);
            }
        };

        ekranRozgrywki.setLayout(new GridBagLayout());

        // Powitalny napis, dostoswanie tekstu, czcionki,oraz położenia
        JLabel napisWitamy = new JLabel("<html>Witamy w grze<br/>EkoGierka - Gra o segregacji odpadów <br/>Aby rozpocząć kliknij <br/>MENU</html>");
        napisWitamy.setFont(new Font("Arial Black", Font.BOLD, 45));

        napisWitamy.setForeground(kolor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(500, 0, 0, 0);
        ekranRozgrywki.add(napisWitamy, gbc);

        ekranRozgrywki.setPreferredSize(new Dimension(1220, 820));
        frame.add(ekranRozgrywki, BorderLayout.CENTER);

        // Stopka gry
        JPanel stopka = utworzStopke();
        frame.add(stopka, BorderLayout.SOUTH);
    }

    /**
     * Metoda wyświetlająca menu gry.
     * Tworzy nowe okno menu, ustawia jego parametry, dodaje panel menu, i ustawia widoczność okna.
     */
    public void wyswietlMenu() {
        // Utworzenie nowego obiektu ramki dla menu
        JFrame menuFrame = new JFrame(); // Ustawienie rozmiaru ramki menu
        menuFrame.setSize(550, 400);
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setUndecorated(true);

        // Ustalenie pozycji ramki menu na środku głównej ramki gry
        int centerX = frame.getX() + (frame.getWidth() - menuFrame.getWidth()) / 2;
        int centerY = frame.getY() + (frame.getHeight() - menuFrame.getHeight()) / 2;
        menuFrame.setLocation(centerX, centerY);

        // Utworzenie panelu menu i dodanie go do ramki menu
        JPanel MenuPanel = new Menu(this, menuFrame);
        menuFrame.add(MenuPanel);

        // Ustawienie widoczności ramki menu
        menuFrame.setVisible(true);
    }

    /**
     * Metoda wyświetlająca ekran wyboru poziomu gry.
     * Tworzy nowe okno dla wyboru poziomu, ustawia jego parametry, dodaje panel do wyboru poziomu, i ustawia widoczność okna.
     */
    public void wyswietlWybierzPoziom() {
        // Utworzenie nowego obiektu ramki dla wyboru poziomu
        JFrame poziomFrame = new JFrame();
        poziomFrame.setSize(550, 400);// Ustawienie rozmiaru ramki dla wyboru poziomu
        poziomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        poziomFrame.setResizable(false);
        poziomFrame.setUndecorated(true);

        // Ustalenie pozycji ramki wyboru poziomu na środku głównej ramki gry
        int centerX = frame.getX() + (frame.getWidth() - poziomFrame.getWidth()) / 2;
        int centerY = frame.getY() + (frame.getHeight() - poziomFrame.getHeight()) / 2;
        poziomFrame.setLocation(centerX, centerY);

        // Utworzenie panelu do wyboru poziomu i dodanie go do ramki wyboru poziomu
        JPanel PoziomPanel = new WybierzPoziom(this, poziomFrame);
        poziomFrame.add(PoziomPanel);

        // Ustawienie widoczności ramki wyboru poziomu
        poziomFrame.setVisible(true);
    }

    /**
     * Metoda wyświetlająca ekran pomocy gry.
     * Tworzy panel pomocy zawierający opis zasad i instrukcji gry, a następnie wyświetla go w głównym oknie.
     */
    public void wyswietlPomoc() {
        // Utworzenie panelu pomocy
        JPanel panelPomocy = new JPanel(new BorderLayout());
        // Utworzenie obszaru tekstowego z informacją o grze
        JTextArea tekstPomocy = new JTextArea(
                " Witaj w Eko-Gierce - grze o segregacji odpadów!\n\n"
                        + " - Gra polega na zebraniu i posegregowaniu wszystkich odpadów znajdujących się na planszy do odpowiednich pojemników.\n\n"
                        + " - Aby posegregować odpad należy najechać na niego kursorem myszy, kliknąć,a następnie przytrzymując lewy przycisk myszy przesunąć go do odpowiedniego pojemnika. \n\n"
                        + " - Gra posiada 3 poziomy trudności.\n\n"
                        + " - Każdy kolejny poziom posiada coraz mniej czasu na uprzątnięcie planszy.\n\n"
                        + " - W każdym poziomie zliczane są punky, są one dodawane (10 pkt) za prawidłowe wyrzucenie " +
                        " odpadku oraz odejmowane za upuszczenie lub złe wyrzucenie (-15 pkt).\n\n"
                        + " - Dodatkowo dodawane są punkty za pozostały czas (1 pozostała sekunda to 1 dodatkowy punkt).\n\n"
                        + " - Aby rozpocząć kliknij MENU oraz opcję START lub Wybierz dowolny poziom\n\n"
        );
        tekstPomocy.setEditable(false);
        tekstPomocy.setLineWrap(true);
        tekstPomocy.setWrapStyleWord(true);
        tekstPomocy.setFont(new Font("Arial", Font.PLAIN, 22));
        tekstPomocy.setBackground(kolor2);

        // Dodanie obszaru tekstowego do panelu pomocy
        panelPomocy.add(tekstPomocy, BorderLayout.CENTER);

        // Utworzenie stopki dla panelu pomocy
        JPanel stopkaPomocy = utworzStopke();

        // Dodanie stopki do panelu pomocy
        panelPomocy.add(stopkaPomocy, BorderLayout.SOUTH);

        // Usunięcie wszystkich komponentów z głównego kontenera okna gry
        frame.getContentPane().removeAll();

        // Dodanie panelu pomocy do głównego okna gry
        frame.add(panelPomocy, BorderLayout.CENTER);

        frame.validate();
        frame.repaint();
    }

    /**
     * Rozpoczyna nowy poziom gry na podstawie przekazanego numeru poziomu.
     * Tworzy nową planszę, aktualizuje interfejs użytkownika i rozpoczyna rozgrywkę.
     *
     * @param poziom Numer poziomu, który ma być rozpoczęty.
     */
    public void rozpocznijPoziom(int poziom) {
        this.aktualnyPoziom = poziom;

        // Sprawdzenie, czy istnieje dostępny poziom
        if (aktualnyPoziom < maksymalnyDostepnyPoziom) {
            // Utworzenie nowej planszy na podstawie przekazanych parametrów oraz dostoswanie okna go aktualnego poziomu
            plansza = new Plansza(czasLabel, poziomLabel, this, poziom);
            frame.getContentPane().removeAll();
            frame.add(plansza, BorderLayout.CENTER);
            JPanel stopka = utworzStopke();
            frame.add(stopka, BorderLayout.SOUTH);
            frame.validate();
            frame.repaint();

            plansza.rozpocznijRozgrywke(); // Rozpoczęcie rozgrywki na planszy
            aktualizujPoziomLabel(poziom + 1); // Aktualizacja etykiety poziomu
            aktualnyPoziom++; // Inkrementacja numeru aktualnego poziomu
        } else {
            // Informacja o ukończeniu wszystkich poziomów
            JOptionPane.showMessageDialog(frame, "Gratulacje! Udało Ci się przejść wszystkie poziomy!");
            // Wyświetlenie menu po zakończeniu gry
            wyswietlMenu();
        }
    }

    /**
     * Metoda służąca do utworzenia nowej etykiety (JLabel) z określonymi parametrami.
     *
     * @param szerokosc Szerokość etykiety.
     * @param wysokosc  Wysokość etykiety.
     * @param tekst     Tekst wyświetlany na etykiecie.
     * @return Nowa etykieta utworzona z zadanymi parametrami.
     */
    private JLabel utworzElement(int szerokosc, int wysokosc, String tekst) {
        JLabel element = new JLabel(tekst); // Utworzenie nowej etykiety
        element.setPreferredSize(new Dimension(szerokosc, wysokosc)); // Ustawienie preferowanej wielkości etykiety
        // Ustawienie tła etykiety
        element.setOpaque(true);
        element.setBackground(kolor);
        // Ustawienie poziomego i pionowego wyśrodkowania tekstu
        element.setHorizontalAlignment(SwingConstants.CENTER);
        element.setVerticalAlignment(SwingConstants.CENTER);
        element.setFont(new Font("Arial", Font.BOLD, 35)); // Ustawienie czcionki etykiety
        return element; // Zwrócenie utworzonej etykiety
    }

    /**
     * Metoda służąca do utworzenia nowego przycisku (JButton) z określonymi parametrami.
     *
     * @param szerokosc        Szerokość przycisku.
     * @param wysokosc         Wysokość przycisku.
     * @param sciezkaDoObrazka Ścieżka do pliku z obrazkiem, który będzie używany jako ikona przycisku.
     * @return Nowy przycisk utworzony z zadanymi parametrami.
     */
    private static JButton utworzPrzycisk(int szerokosc, int wysokosc, String sciezkaDoObrazka) {
        JButton przyciskMenu = new JButton();// Utworzenie nowego przycisku
        przyciskMenu.setPreferredSize(new Dimension(szerokosc, wysokosc));// Ustawienie preferowanej wielkości przycisku
        przyciskMenu.setOpaque(false); // Ustawienie przezroczystości tła przycisku
        przyciskMenu.setContentAreaFilled(false); // Ustawienie, aby przycisk nie wypełniał tła
        przyciskMenu.setBorderPainted(false);// Ustawienie, aby nie rysować ramki przycisku

        ImageIcon icon = new ImageIcon(sciezkaDoObrazka);// Utworzenie obiektu ImageIcon na podstawie ścieżki do obrazka
        przyciskMenu.setIcon(icon);// Ustawienie ikony przycisku

        return przyciskMenu;// Zwrócenie utworzonego przycisku
    }

    /**
     * Metoda służąca do utworzenia stopki gry (JPanel) z etykietami poziomu, punktów, czasu oraz przyciskiem MENU.
     *
     * @return Nowy panel stopki utworzony z zadanymi komponentami.
     */
    public JPanel utworzStopke() {
        JPanel stopka = new JPanel(); // Utworzenie nowego panelu stopki
        stopka.setPreferredSize(new Dimension(1220, 130));// Ustawienie preferowanej wielkości panelu stopki
        stopka.setBackground(Color.GRAY);// Ustawienie koloru tła panelu stopki na szary

        poziomLabel = utworzElement(280, 120, "Poziom: "); // Utworzenie etykiety poziomu
        punktyLabel = utworzElement(280, 120, "Punkty: 0"); // Utworzenie etykiety punktów
        czasLabel = utworzElement(280, 120, "Czas: "); // Utworzenie etykiety czasu

        // Dodanie etykiet do panelu stopki
        stopka.add(poziomLabel);
        stopka.add(punktyLabel);
        stopka.add(czasLabel);

        // Utworzenie przycisku MENU i dodanie ActionListenera do obsługi zdarzenia
        JButton menuButton = utworzPrzycisk(280, 120, currentDir + "\\obrazki\\MENU.png");
        menuButton.addActionListener(e -> {
            wyswietlMenu();
        });
        stopka.add(menuButton);// Dodanie przycisku MENU do panelu stopki
        return stopka;// Zwrócenie utworzonego panelu stopki
    }

    /**
     * Aktualizuje etykietę punktów w stopce gry.
     *
     * @param punkty Aktualna liczba punktów, która ma być wyświetlona na etykiecie.
     */
    public void aktualizujPunktyLabel(int punkty) {
        punktyLabel.setText("Punkty: " + punkty);// Ustawienie tekstu etykiety punktów na podstawie aktualnej liczby punktów
    }

    /**
     * Aktualizuje etykietę czasu w stopce gry.
     *
     * @param czasGry Aktualny czas gry, który ma być wyświetlony na etykiecie.
     */
    public void aktualizujCzasLabel(int czasGry) {
        czasLabel.setText("Czas: " + czasGry + " s");// Ustawienie tekstu etykiety czasu na podstawie aktualnego czasu gry
    }

    /**
     * Aktualizuje etykietę poziomu w stopce gry.
     *
     * @param poziom Numer aktualnego poziomu, który ma być wyświetlony na etykiecie.
     */
    public void aktualizujPoziomLabel(int poziom) {
        poziomLabel.setText("Poziom: " + poziom);// Ustawienie tekstu etykiety poziomu na podstawie aktualnego numeru poziomu

    }

    /**
     * Wyświetla główne okno gry, ustawiając je jako widoczne.
     */
    public void uruchom() {
        frame.setVisible(true); // Ustawienie głównego okna gry jako widoczne
    }

}
