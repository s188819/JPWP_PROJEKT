import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca planszę gry.
 */
public class Plansza extends JPanel {

    private Image tlo;// Obrazek tła
    final List<Odpad> odpady;// Lista odpadów na planszy
    final List<Pojemnik> pojemniki;// Lista pojemników na planszy
    private Odpad aktualniePrzesuwanyOdpad; // Aktualnie przesuwany odpad
    private int iloscPozostalychOdpadow; // Ilość pozostałych odpadów do posegregowania
    final JLabel czasLabel;// Etykieta z czasem gry
    private JLabel poziomLabel;// Etykieta z poziomem gry
    private OknoGry oknoGry;// Referencja do głównego okna gry
    private Timer timer;// Timer do odmierzania czasu gry
    private int czasGry;// Czas trwania gry w sekundach
    private int czasPoczatkowy;// Czas początkowy gr
    private int czasKoncowy;// Czas końcowy gry
    private int punkty;// Punkty zdobyte w grze
    private int punktyZaCzas;// Punkty zdobyte za czas
    private int aktualnyPoziom;// Aktualny poziom gry
    private boolean istniejaKolejnePoziomy = true;// Flaga informująca, czy istnieją kolejne poziomy do przejścia

    String currentDir = System.getProperty("user.dir");// Ścieżka do katalogu aktualnej pracy

    // Obiekty obrazków odpadów
    Image obrazekMetal = new ImageIcon(currentDir + "\\obrazki\\metal1.png").getImage();
    Image obrazekMetal2 = new ImageIcon(currentDir + "\\obrazki\\metal2.png").getImage();
    Image obrazekMetal3 = new ImageIcon(currentDir + "\\obrazki\\metal3.png").getImage();
    Image obrazekMetal4 = new ImageIcon(currentDir + "\\obrazki\\metal4.png").getImage();
    Image obrazekMetal5 = new ImageIcon(currentDir + "\\obrazki\\metal5.png").getImage();
    Image obrazekMetal6 = new ImageIcon(currentDir + "\\obrazki\\metal6.png").getImage();
    Image obrazekMetal7 = new ImageIcon(currentDir + "\\obrazki\\metal7.png").getImage();
    Image obrazekMetal8 = new ImageIcon(currentDir + "\\obrazki\\metal8.png").getImage();
    Image obrazekPlastik = new ImageIcon(currentDir + "\\obrazki\\plastik.png").getImage();
    Image obrazekPlastik2 = new ImageIcon(currentDir + "\\obrazki\\plastik2.png").getImage();
    Image obrazekPlastik3 = new ImageIcon(currentDir + "\\obrazki\\plastik3.png").getImage();
    Image obrazekPlastik4 = new ImageIcon(currentDir + "\\obrazki\\plastik4.png").getImage();
    Image obrazekPlastik5 = new ImageIcon(currentDir + "\\obrazki\\plastikowaTorba.png").getImage();
    Image obrazekPlastik7 = new ImageIcon(currentDir + "\\obrazki\\plastik7.png").getImage();
    Image obrazekPlastik8 = new ImageIcon(currentDir + "\\obrazki\\plastik8.png").getImage();
    Image obrazekSzklo1 = new ImageIcon(currentDir + "\\obrazki\\szklo1.png").getImage();
    Image obrazekSzklo2 = new ImageIcon(currentDir + "\\obrazki\\szklo2.png").getImage();
    Image obrazekSzklo3 = new ImageIcon(currentDir + "\\obrazki\\szklo3.png").getImage();
    Image obrazekPapier1 = new ImageIcon(currentDir + "\\obrazki\\papier1.png").getImage();
    Image obrazekPapier2 = new ImageIcon(currentDir + "\\obrazki\\papier2.png").getImage();
    Image obrazekPapier3 = new ImageIcon(currentDir + "\\obrazki\\papier3.png").getImage();
    Image obrazekPapier5 = new ImageIcon(currentDir + "\\obrazki\\papier5.png").getImage();
    Image obrazekBio1 = new ImageIcon(currentDir + "\\obrazki\\bio1.png").getImage();
    Image obrazekBio2 = new ImageIcon(currentDir + "\\obrazki\\bio2.png").getImage();
    Image obrazekInne1 = new ImageIcon(currentDir + "\\obrazki\\inne1.png").getImage();
    Image obrazekInne2 = new ImageIcon(currentDir + "\\obrazki\\inne2.png").getImage();

    /**
     * Konstruktor klasy Plansza.
     *
     * @param czasLabel   Etykieta z czasem gry.
     * @param poziomLabel Etykieta z poziomem gry.
     * @param oknoGry     Referencja do głównego okna gry.
     * @param poziom      Numer aktualnego poziomu.
     */
    public Plansza(JLabel czasLabel, JLabel poziomLabel, OknoGry oknoGry, int poziom) {
        // Inicjalizzacja listy odpadów i pojemników
        odpady = new ArrayList<>();
        pojemniki = new ArrayList<>();

        // Dodanie pojemników
        dodajPojemniki();
        this.czasLabel = czasLabel;
        this.oknoGry = oknoGry;
        this.poziomLabel = poziomLabel;
        this.aktualnyPoziom = poziom;

        // Inicjalizacja tła planszy,odpadów oraz czasu gry na podstawie aktualnego poziomu
        switch (poziom) {
            case 0:
                // Poziom 1
                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo1.jpg").getImage();
                dodajOdpadyPoziom1();
                czasGry = 60; // Ustawienie czasu gry na 60 sekund
                czasPoczatkowy = 60;// Ustawienie czasu początkowego na 60 sekund
                // Inicjalizacja timera
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aktualizujCzasGry();
                    }
                });
                break;
            case 1:
                // Poziom 2
                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo2.jpg").getImage();
                dodajOdpadyPoziom2();

                czasGry = 30;// Ustawienie czasu gry na 30 sekund
                czasPoczatkowy = 30;// Ustawienie czasu początkowego na 30 sekund
                // Inicjalizacja timera
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aktualizujCzasGry();
                    }
                });
                break;
            case 2:
                // Poziom 3
                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo3.jpg").getImage();
                dodajOdpadyPoziom3();
                // Ustawienie czasu gry na 20 sekund
                czasGry = 20;// Ustawienie czasu gry na 20 sekund
                czasPoczatkowy = 20;// Ustawienie czasu początkowego na 20 sekund
                // Inicjalizacja timera
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aktualizujCzasGry();
                    }
                });
                break;
            default:
                break;
        }
        // Dodaj obsługę myszy dla planszy
        dodajObslugeMyszy();
    }

    /**
     * Dodaje obsługę myszy dla interakcji w grze.
     */
    private void dodajObslugeMyszy() {
        // Dodanie obsługi zdarzeń dla naciśnięcia i zwolnienia myszy
        addMouseListener(new MouseAdapter() {
            /**
             * Obsługuje zdarzenie naciśnięcia myszy.
             *
             * @param e Zdarzenie myszy
             */
            @Override
            public void mousePressed(MouseEvent e) {
                for (Odpad odpad : odpady) {
                    // Sprawdzenie, czy któryś z odpadów zawiera punkt naciśnięcia myszy
                    if (odpad.zawieraPunkt(e.getPoint())) {
                        aktualniePrzesuwanyOdpad = odpad;
                        break;
                    }
                }
            }

            /**
             * Obsługuje zdarzenie zwolnienia myszy.
             *
             * @param e Zdarzenie myszy
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if (aktualniePrzesuwanyOdpad != null) {
                    boolean odpadWyrzucony = false;

                    for (Pojemnik pojemnik : pojemniki) {
                        // Sprawdzenie, czy mysz została zwolniona nad jakimś pojemnikiem
                        if (pojemnik.zawieraPunkt(e.getPoint()) && pojemnik.akceptujeTyp(aktualniePrzesuwanyOdpad.getTyp())) {
                            // Dodanie odpadu do pojemnika
                            pojemnik.dodajOdpad(aktualniePrzesuwanyOdpad);
                            odpady.remove(aktualniePrzesuwanyOdpad);
                            odpadWyrzucony = true;

                            // Zwiększenie punktów za prawidłowe wyrzucenie
                            zwiekszPunkty(10);
                            // Zmniejszenie liczby pozostałych odpadów
                            iloscPozostalychOdpadow--;
                            if (iloscPozostalychOdpadow == 0) {
                                zakonczGre();
                            }
                            break;
                        }
                    }

                    if (!odpadWyrzucony) {
                        //Powrót odpadu na początkowe miejse, jeżeli odpad nie został wyfzucony do żadnego z pojemników
                        aktualniePrzesuwanyOdpad.ustawNaPoczatkoweMiejsce();

                        // Odjęcie punktów za złe wyrzucenie lub upuszczenie
                        zwiekszPunkty(-15);
                    }
                    // Odświeżenie widoku
                    repaint();
                    aktualniePrzesuwanyOdpad = null;
                }
            }
        });
        // Dodanie obsługi zdarzenia przeciągania myszy
        addMouseMotionListener(new MouseAdapter() {
            /**
             * Obsługuje zdarzenie przeciągania myszy.
             *
             * @param e Zdarzenie myszy
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                if (aktualniePrzesuwanyOdpad != null) {
                    // Przesunięcie aktualnie przesuwanego odpadu w miejsce myszy
                    aktualniePrzesuwanyOdpad.przesun(e.getPoint());
                    // Odświeżenie widoku
                    repaint();
                }
            }
        });
    }

    /**
     * Zwiększa liczbę punktów o określoną ilość i aktualizuje etykietę punktów w oknie gry.
     *
     * @param iloscPunktow Ilość punktów do dodania (może być także ujemna dla zmniejszenia punktów)
     */
    private void zwiekszPunkty(int iloscPunktow) {
        punkty += iloscPunktow;// Dodanie punktów do aktualnej liczby punktów
        oknoGry.aktualizujPunktyLabel(punkty);// Aktualizowanie etykiety punktów w oknie gry
    }

    /**
     * Aktualizuje czas gry, zmniejszając go o jednostkę, oraz sprawdza czy czas nie minął, a następnie
     * aktualizuje etykietę czasu w oknie gry.
     */
    private void aktualizujCzasGry() {
        czasGry--;// Zmniejszenie czasu gry
        // Sprawdzenie, czy czas gry nie minął
        if (czasGry <= 0) {
            zakonczGre();// Jeżeli czas minął, zakończenie gry
        }
        oknoGry.aktualizujCzasLabel(czasGry); // Aktualizowanie etykiety czasu w oknie gry
    }

    /**
     * Dodaje odpady do gry na pierwszym poziomie, inicjując różne obiekty klasy Odpad z określonymi parametrami.
     */
    private void dodajOdpadyPoziom1() {
        odpady.add(new Odpad(new Point(43, 500), 30, 60, obrazekMetal, "metal"));
        odpady.add(new Odpad(new Point(1220, 490), 50, 60, obrazekMetal2, "metal"));
        odpady.add(new Odpad(new Point(63, 590), 50, 60, obrazekPlastik, "plastik"));
        odpady.add(new Odpad(new Point(823, 503), 100, 100, obrazekPapier5, "papier"));
        odpady.add(new Odpad(new Point(269, 513), 50, 60, obrazekSzklo3, "szklo"));
        odpady.add(new Odpad(new Point(1002, 506), 80, 80, obrazekSzklo1, "szklo"));
        odpady.add(new Odpad(new Point(664, 568), 80, 80, obrazekPlastik5, "plastik"));
        odpady.add(new Odpad(new Point(950, 603), 70, 70, obrazekBio1, "bio"));
        odpady.add(new Odpad(new Point(340, 603), 70, 70, obrazekBio2, "bio"));
        odpady.add(new Odpad(new Point(521, 528), 70, 70, obrazekPlastik2, "plastik"));
        odpady.add(new Odpad(new Point(780, 603), 70, 70, obrazekSzklo2, "szklo"));
        odpady.add(new Odpad(new Point(174, 500), 70, 70, obrazekPlastik3, "plastik"));
        odpady.add(new Odpad(new Point(740, 486), 70, 70, obrazekPapier1, "papier"));
        odpady.add(new Odpad(new Point(1165, 551), 70, 70, obrazekMetal3, "metal"));
        odpady.add(new Odpad(new Point(522, 626), 50, 60, obrazekMetal6, "metal"));
        odpady.add(new Odpad(new Point(422, 506), 100, 100, obrazekInne1, "reszta"));
        odpady.add(new Odpad(new Point(375, 486), 70, 70, obrazekMetal7, "metal"));
        odpady.add(new Odpad(new Point(660, 504), 30, 60, obrazekMetal, "metal"));
        // Ustawienie liczby pozostałych odpadów na ilość dodanych odpadów
        iloscPozostalychOdpadow = odpady.size();
    }

    /**
     * Dodaje odpady do gry na drugim poziomie, inicjując różne obiekty klasy Odpad z określonymi parametrami.
     */
    private void dodajOdpadyPoziom2() {
        odpady.add(new Odpad(new Point(555, 600), 30, 60, obrazekMetal, "metal"));
        odpady.add(new Odpad(new Point(1200, 670), 50, 60, obrazekMetal2, "metal"));
        odpady.add(new Odpad(new Point(145, 452), 50, 60, obrazekMetal4, "metal"));
        odpady.add(new Odpad(new Point(1100, 555), 50, 60, obrazekPlastik, "plastik"));
        odpady.add(new Odpad(new Point(900, 503), 50, 60, obrazekPlastik5, "plastik"));
        odpady.add(new Odpad(new Point(524, 459), 50, 60, obrazekSzklo2, "szklo"));
        odpady.add(new Odpad(new Point(275, 600), 50, 60, obrazekBio2, "bio"));
        odpady.add(new Odpad(new Point(1125, 455), 80, 80, obrazekPapier3, "papier"));
        odpady.add(new Odpad(new Point(40, 538), 80, 70, obrazekPlastik7, "plastik"));
        odpady.add(new Odpad(new Point(350, 450), 160, 120, obrazekMetal5, "metal"));
        odpady.add(new Odpad(new Point(162, 600), 70, 70, obrazekPlastik8, "plastik"));
        odpady.add(new Odpad(new Point(793, 503), 80, 80, obrazekMetal7, "metal"));
        odpady.add(new Odpad(new Point(750, 512), 70, 70, obrazekSzklo3, "szklo"));
        odpady.add(new Odpad(new Point(801, 446), 70, 70, obrazekBio1, "bio"));
        odpady.add(new Odpad(new Point(678, 449), 80, 80, obrazekInne2, "reszta"));
        odpady.add(new Odpad(new Point(1024, 477), 100, 100, obrazekPapier2, "papier"));
        // Ustawienie liczby pozostałych odpadów na ilość dodanych odpadów
        iloscPozostalychOdpadow = odpady.size();
    }

    /**
     * Dodaje odpady do gry na trzecim poziomie, inicjując różne obiekty klasy Odpad z określonymi parametrami.
     */
    private void dodajOdpadyPoziom3() {
        odpady.add(new Odpad(new Point(400, 570), 30, 60, obrazekMetal, "metal"));
        odpady.add(new Odpad(new Point(847, 647), 50, 60, obrazekMetal2, "metal"));
        odpady.add(new Odpad(new Point(56, 604), 50, 60, obrazekPlastik, "plastik"));
        odpady.add(new Odpad(new Point(1169, 599), 55, 80, obrazekPlastik2, "plastik"));
        odpady.add(new Odpad(new Point(824, 591), 50, 70, obrazekPlastik4, "plastik"));
        odpady.add(new Odpad(new Point(501, 481), 50, 70, obrazekSzklo1, "szklo"));
        odpady.add(new Odpad(new Point(119, 561), 50, 70, obrazekBio1, "bio"));
        odpady.add(new Odpad(new Point(540, 485), 100, 100, obrazekPapier2, "papier"));
        odpady.add(new Odpad(new Point(597, 607), 90, 90, obrazekPapier1, "papier"));
        odpady.add(new Odpad(new Point(1132, 510), 80, 80, obrazekMetal3, "metal"));
        odpady.add(new Odpad(new Point(703, 457), 50, 60, obrazekMetal8, "metal"));
        odpady.add(new Odpad(new Point(320, 539), 80, 80, obrazekSzklo2, "szklo"));
        odpady.add(new Odpad(new Point(703, 550), 50, 60, obrazekPlastik5, "plastik"));
        odpady.add(new Odpad(new Point(249, 600), 80, 90, obrazekPlastik8, "plastik"));
        odpady.add(new Odpad(new Point(990, 601), 80, 90, obrazekInne2, "reszta"));
        // Ustawienie liczby pozostałych odpadów na ilość dodanych odpadów
        iloscPozostalychOdpadow = odpady.size();
    }

    /**
     * Dodaje pojemniki do gry, zdefiniowane z odpowiednimi parametrami, kolorami i akceptowanymi typami odpadów.
     */
    private void dodajPojemniki() {
        // Definiowanie listy akceptowanych typów dla każdego pojemnika
        List<String> akceptowaneTypyMetal = List.of("metal");
        List<String> akceptowaneTypyPapier = List.of("papier");
        List<String> akceptowaneTypySzklo = List.of("szklo");
        List<String> akceptowaneTypyPlastik = List.of("plastik");
        List<String> akceptowaneTypyBio = List.of("bio");
        List<String> akceptowaneTypyReszta = List.of("reszta");

        // Definiowanie specjalnych kolory dla niektórych pojemników
        Color kolorBrązowy = new Color(139, 69, 19);
        Color kolorCzarny = new Color(50, 50, 50);

        // Tablice zawierające kolory, podpisy i akceptowane typy dla poszczególnych pojemników
        Color[] kolory = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, kolorBrązowy, kolorCzarny};
        String[] podpisy = {"METAL", "PAPIER", "SZKŁO", "PLASTIK", "BIO", "RESZTA"};
        List<List<String>> akceptowaneTypyPojemnikow = List.of(
                akceptowaneTypyMetal,
                akceptowaneTypyPapier,
                akceptowaneTypySzklo,
                akceptowaneTypyPlastik,
                akceptowaneTypyBio,
                akceptowaneTypyReszta
        );

        int x = 50;// Początkowa pozycja X dla pierwszego pojemnika

        // Pętla tworząca i dodająca pojemniki do listy
        for (int i = 0; i < 6; i++) {
            // Utworzenie prostokąta reprezentującego obszar pojemnika
            Rectangle pojemnikProstokat = new Rectangle(x, 700, 145, 165);
            // Pobranie koloru, podpisu i akceptowanych typów dla aktualnego pojemnika
            Color kolor = kolory[i];
            String podpis = podpisy[i];
            List<String> akceptowaneTypyPojemnika = akceptowaneTypyPojemnikow.get(i);

            // Utworzenie nowego pojemnika i dodanie go do listy pojemników
            Pojemnik nowyPojemnik = new Pojemnik(pojemnikProstokat, kolor, podpis, akceptowaneTypyPojemnika);
            pojemniki.add(nowyPojemnik);
            // Zwiększenie pozycji X dla następnego pojemnika
            x += 200;
        }
    }

    /**
     * Metoda paintComponent z klasy nadrzędnej, odpowiedzialna za rysowanie komponentu gry.
     *
     * @param g Obiekt Graphics używany do rysowania.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Wywołanie metody paintComponent z klasy nadrzędnej w celu prawidłowego rysowania komponentu
        super.paintComponent(g);

        // Rysowanie tła
        g.drawImage(tlo, 0, 0, 1280, 820, this);

        // Rysowanie odpadów
        for (Odpad odpad : odpady) {
            odpad.rysuj(g);
        }

        // Rysowanie pojemników
        for (Pojemnik pojemnik : pojemniki) {
            pojemnik.rysuj(g);
        }
    }

    /**
     * Rozpoczyna nową rozgrywkę, uruchamiając timer, aktualizując czas gry na etykiecie oraz ustawiając informacje o poziomie.
     */
    public void rozpocznijRozgrywke() {
        timer.start(); // Uruchomienie timera odpowiadającego za aktualizację czasu gry
        oknoGry.aktualizujCzasLabel(czasGry); // Aktualizacja etykiety z czasem gry na oknie gry
        czasKoncowy = 30 - czasGry;// Obliczenie i ustawienie czasu końcowego rozgrywki
        poziomLabel.setText("Poziom: " + poziomLabel);// Ustawienie informacji o poziomie gry na etykiecie
    }

    /**
     * Metoda wywoływana po zakończeniu gry, zatrzymuje timer, oblicza punkty za czas,
     * sprawdza, czy wszystkie odpady zostały posegregowane, i podejmuje odpowiednie działania w zależności od rezultatu.
     */
    public void zakonczGre() {
        timer.stop();// Zatrzymanie timera
        punktyZaCzas = czasGry;// Obliczenie punktów za czas
        czasKoncowy = czasPoczatkowy - czasGry;// Obliczenie czasu końcowego gry
        zwiekszPunkty(punktyZaCzas);// Zwiększenie punktów o uzyskane punkty za czas
        // Sprawdzenie, czy wszystkie odpady zostały posegregowane
        if (czyWszystkieOdpadyPosegregowane()) {
            // Sprawdzenie, czy istnieją kolejne poziomy do przejścia
            if (istniejaKolejnePoziomy) {
                // Wyświetlenie gratulacji i przechodzenie do następnego poziomu
                JOptionPane.showMessageDialog(this, "Gratulacje! Poziom ukończony! Czas gry: " + czasKoncowy + " sekundy. Zdobyte punkty: " + punkty);
                oknoGry.rozpocznijPoziom(aktualnyPoziom + 1);
            } else {
                // Wyświetlenie gratulacji za ukończenie wszystkich poziomów
                JOptionPane.showMessageDialog(this, "Gratulacje! Udało się przejść wszystkie poziomy!");
                oknoGry.wyswietlMenu();
            }
        } else {
            // Wyświetlenie komunikatu o nieposegregowanych odpadach
            int opcja = JOptionPane.showConfirmDialog(this, "Nie wszystkie odpady zostały posegregowane. Czy chcesz zacząć od nowa?", "Koniec gry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcja == JOptionPane.YES_OPTION) {
                // Rozpoczęcie ponownie aktualnego poziomu
                oknoGry.rozpocznijPoziom(aktualnyPoziom);
            } else {
                // Wyświetlenie podsumowania gry i wyświetlenie menu
                JOptionPane.showMessageDialog(this, "Koniec gry! Czas gry: " + czasKoncowy + " sekundy. Zdobyte punkty: " + punkty);
                oknoGry.wyswietlMenu();
            }
        }
    }

    /**
     * Sprawdza, czy wszystkie odpady zostały posegregowane.
     *
     * @return true, jeśli wszystkie odpady zostały posegregowane, false w przeciwnym razie.
     */
    private boolean czyWszystkieOdpadyPosegregowane() {
        // Zwraca true, jeśli ilość pozostałych odpadów wynosi 0
        return iloscPozostalychOdpadow == 0;
    }

}