import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class Plansza extends JPanel {

    private Image tlo;
    final List<Odpad> odpady;
    final List<Pojemnik> pojemniki;
    private Odpad aktualniePrzesuwanyOdpad;
    private int iloscPozostalychOdpadow;
    final JLabel czasLabel;
    private JLabel poziomLabel;
    private OknoGry oknoGry;
    private Timer timer;
    private int czasGry;
    private int czasKoncowy;
    private int czasPoczatkowy;
    private int punkty;
    private int punktyZaCzas;
    private int aktualnyPoziom;
    private boolean istniejaKolejnePoziomy = true;

    String currentDir = System.getProperty("user.dir");
    Image obrazekMetal = new ImageIcon(currentDir + "\\obrazki\\metal1.png").getImage();
    Image obrazekMetal2 = new ImageIcon(currentDir + "\\obrazki\\metal2.png").getImage();
    Image obrazekMetal3 = new ImageIcon(currentDir + "\\obrazki\\metal3.png").getImage();
    Image obrazekPlastik = new ImageIcon(currentDir + "\\obrazki\\plastik.png").getImage();
    Image obrazekPlastik2 = new ImageIcon(currentDir + "\\obrazki\\plastik2.png").getImage();
    Image obrazekPlastik3 = new ImageIcon(currentDir + "\\obrazki\\plastik3.png").getImage();
    Image obrazekPlastik4 = new ImageIcon(currentDir + "\\obrazki\\plastik4.png").getImage();
    Image obrazekSzklo1 = new ImageIcon(currentDir + "\\obrazki\\szklo1.png").getImage();
    Image obrazekSzklo2 = new ImageIcon(currentDir + "\\obrazki\\szklo2.png").getImage();
    Image obrazekPapier1 = new ImageIcon(currentDir + "\\obrazki\\papier1.png").getImage();
    Image obrazekPapier2 = new ImageIcon(currentDir + "\\obrazki\\papier2.png").getImage();
    Image obrazekPapier3 = new ImageIcon(currentDir + "\\obrazki\\papier3.png").getImage();
    Image obrazekPapier4 = new ImageIcon(currentDir + "\\obrazki\\papier4.png").getImage();
    Image obrazekBio1 = new ImageIcon(currentDir + "\\obrazki\\bio1.png").getImage();
    Image obrazekBio2 = new ImageIcon(currentDir + "\\obrazki\\bio2.png").getImage();
    public Plansza(JLabel czasLabel, JLabel poziomLabel, OknoGry oknoGry, int poziom) {
        // Inicjalizzacja listy odpadów i pojemników
        odpady = new ArrayList<>();
        pojemniki = new ArrayList<>();

        // Dodaj pojemniki
        dodajPojemniki();
        this.czasLabel = czasLabel;
        this.oknoGry = oknoGry;
        this.poziomLabel = poziomLabel;
        this.aktualnyPoziom = poziom;
        //wybor poziomu
        switch (poziom) {
            case 0:
                //poziom1
                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo1.jpg").getImage();
                dodajOdpadyPoziom1();
                // Ustawienie czasu gry na 60 sekund
                czasGry = 60;
                czasPoczatkowy = 60;
                // Inicjalizacja timera
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aktualizujCzasGry();
                    }
                });
                break;
            case 1:
                //poziom2
                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo2.jpg").getImage();
                dodajOdpadyPoziom2();
                // Ustawienie czasu gry na 30 sekund
                czasGry = 30;
                czasPoczatkowy = 30;
                // Inicjalizacja timera
                timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aktualizujCzasGry();
                    }
                });
                break;
            case 2:
                //poziom3

                tlo = new ImageIcon(currentDir + "\\obrazki\\tlo3.jpg").getImage();
                dodajOdpadyPoziom3();
                // Ustawienie czasu gry na 20 sekund
                czasGry = 20;
                czasPoczatkowy = 20;
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
                if (aktualniePrzesuwanyOdpad != null) {
                    boolean odpadWyrzucony = false;

                    for (Pojemnik pojemnik : pojemniki) {
                        if (pojemnik.zawieraPunkt(e.getPoint()) && pojemnik.akceptujeTyp(aktualniePrzesuwanyOdpad.getTyp())) {
                            pojemnik.dodajOdpad(aktualniePrzesuwanyOdpad);
                            odpady.remove(aktualniePrzesuwanyOdpad);
                            odpadWyrzucony = true;

                            // Zwiększ punkty za prawidłowe wyrzucenie
                            zwiekszPunkty(10);

                            iloscPozostalychOdpadow--;
                            if (iloscPozostalychOdpadow == 0) {
                                zakonczGre();
                            }
                            break;
                        }
                    }

                    if (!odpadWyrzucony) {
                        // Jeżeli odpad nie został wyrzucony do żadnego pojemnika, to wróć na początkowe miejsce
                        aktualniePrzesuwanyOdpad.ustawNaPoczatkoweMiejsce();

                        // Odejmij punkty za złe wyrzucenie lub upuszczenie
                        zwiekszPunkty(-15);
                    }

                    repaint();
                    aktualniePrzesuwanyOdpad = null;
                }
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

    private void zwiekszPunkty(int iloscPunktow) {
        punkty += iloscPunktow;
        oknoGry.aktualizujPunktyLabel(punkty);
    }

    private void aktualizujCzasGry() {
        czasGry--;

        if (czasGry <= 0) {
            zakonczGre();
        }
        oknoGry.aktualizujCzasLabel(czasGry);
    }
    private void dodajOdpadyPoziom1() {
        odpady.add(new Odpad(new Point(100, 100), 30, 60, obrazekMetal, "metal"));
        odpady.add(new Odpad(new Point(200, 150), 50, 60, obrazekMetal2, "metal"));
        odpady.add(new Odpad(new Point(300, 250), 50, 60, obrazekPlastik, "plastik"));
        iloscPozostalychOdpadow = odpady.size();
    }

    private void dodajOdpadyPoziom2() {
        odpady.add(new Odpad(new Point(100, 100), 30, 60, obrazekMetal, "metal"));
        odpady.add(new Odpad(new Point(200, 150), 50, 60, obrazekMetal2, "metal"));
        odpady.add(new Odpad(new Point(300, 250), 50, 60, obrazekPlastik, "plastik"));
        iloscPozostalychOdpadow = odpady.size();
    }

    private void dodajOdpadyPoziom3() {
        odpady.add(new Odpad(new Point(400, 570), 30, 60, obrazekMetal, "metal"));
        //odpady.add(new Odpad(new Point(857, 647), 50, 60, obrazekMetal2, "metal"));
        //odpady.add(new Odpad(new Point(56, 604), 50, 60, obrazekPlastik, "plastik"));
        //odpady.add(new Odpad(new Point(1169, 599), 55, 80, obrazekPlastik2, "plastik"));
        //odpady.add(new Odpad(new Point(720, 476), 50, 60, obrazekPlastik4, "plastik"));
        //odpady.add(new Odpad(new Point(501, 481), 50, 70, obrazekSzklo1, "szklo"));
        //odpady.add(new Odpad(new Point(119, 561), 50, 70, obrazekBio1, "bio"));
        //odpady.add(new Odpad(new Point(540, 485), 100, 100, obrazekPapier2, "papier"));
        //odpady.add(new Odpad(new Point(597, 607), 90, 90, obrazekPapier1, "papier"));
        //odpady.add(new Odpad(new Point(1132, 510), 80, 80, obrazekMetal3, "metal"));
        //odpady.add(new Odpad(new Point(703, 457), 80, 80, obrazekPapier4, "papier"));
        //odpady.add(new Odpad(new Point(121, 502), 80, 80, obrazekSzklo2, "szklo"));
        iloscPozostalychOdpadow = odpady.size();
    }

    private void dodajPojemniki() {
        List<String> akceptowaneTypyMetal = List.of("metal");
        List<String> akceptowaneTypyPapier = List.of("papier");
        List<String> akceptowaneTypySzklo = List.of("szklo");
        List<String> akceptowaneTypyPlastik = List.of("plastik");
        List<String> akceptowaneTypyBio = List.of("bio");
        List<String> akceptowaneTypyReszta = List.of("reszta");

        Color kolorBrązowy = new Color(139, 69, 19);
        Color kolorCzarny = new Color(50, 50, 50);

        Color[] kolory = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, kolorBrązowy, kolorCzarny};
        String[] podpisy = {"METAL", "PAPIER", "SZKŁO", "PLASTIK", "BIO", "ZMIESZANE"};
        List<List<String>> akceptowaneTypyPojemnikow = List.of(
                akceptowaneTypyMetal,
                akceptowaneTypyPapier,
                akceptowaneTypySzklo,
                akceptowaneTypyPlastik,
                akceptowaneTypyBio,
                akceptowaneTypyReszta
        );

        int x = 50;

        for (int i = 0; i < 6; i++) {
            Rectangle pojemnikProstokat = new Rectangle(x, 700, 145, 165);
            Color kolor = kolory[i];
            String podpis = podpisy[i];
            List<String> akceptowaneTypyPojemnika = akceptowaneTypyPojemnikow.get(i);

            Pojemnik nowyPojemnik = new Pojemnik(pojemnikProstokat, kolor, podpis, akceptowaneTypyPojemnika);
            pojemniki.add(nowyPojemnik);

            x += 200;
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

    public void rozpocznijRozgrywke() {
        timer.start();
        oknoGry.aktualizujCzasLabel(czasGry);
        czasKoncowy = 30 - czasGry;
        poziomLabel.setText("Poziom: " + poziomLabel);
    }

    public void zakonczGre() {
        timer.stop();
        punktyZaCzas = czasGry;
        czasKoncowy = czasPoczatkowy - czasGry;
        zwiekszPunkty(punktyZaCzas);

        if (czyWszystkieOdpadyPosegregowane()) {
            if (istniejaKolejnePoziomy) {
                JOptionPane.showMessageDialog(this, "Gratulacje! Poziom ukończony! Czas gry: " + czasKoncowy + " sekundy. Zdobyte punkty: " + punkty);
                oknoGry.rozpocznijPoziom(aktualnyPoziom + 1);
            } else {
                JOptionPane.showMessageDialog(this, "Gratulacje! Udało się przejść wszystkie poziomy!");
                oknoGry.pokazMenu();
            }
        } else {
            int opcja = JOptionPane.showConfirmDialog(this, "Nie wszystkie odpady zostały posegregowane. Czy chcesz zacząć od nowa?", "Koniec gry", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcja == JOptionPane.YES_OPTION) {
                oknoGry.rozpocznijPoziom(aktualnyPoziom);
            } else {
                JOptionPane.showMessageDialog(this, "Koniec gry! Czas gry: " + czasKoncowy + " sekundy. Zdobyte punkty: " + punkty);
                oknoGry.pokazMenu();
            }
        }
    }
    private boolean czyWszystkieOdpadyPosegregowane() {
        return iloscPozostalychOdpadow == 0;
    }

}