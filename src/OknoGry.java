import javax.swing.*;
import java.awt.*;

public class OknoGry {
    private JFrame frame;
    private Plansza plansza;
    private JLabel punktyLabel;
    private JLabel czasLabel;
    private JLabel poziomLabel;
    private int aktualnyPoziom = 0;
    private int maksymalnyDostepnyPoziom = 3;
    String currentDir = System.getProperty("user.dir");
    Color kolor = new Color(0, 102, 0);
    public OknoGry() {
        frame = new JFrame("Eko-Gierka Gra o Segregacji Odpadów");
        frame.setSize(1280, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.setUndecorated(true);
        dodajElementy();
    }

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

        ekranRozgrywki.setLayout(new GridBagLayout());  // Ustawienie GridBagLayout dla precyzyjnej kontroli położenia

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

        JPanel stopka = utworzStopke();
        frame.add(stopka, BorderLayout.SOUTH);
    }
    public void pokazMenu() {
        Object[] options = {"START", "POMOC", "WYBIERZ POZIOM", "KONIEC GRY"};
        int wybor = JOptionPane.showOptionDialog(frame, "Wybierz opcję:", "MENU", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Obsługa wybranej opcji
        switch (wybor) {
            case 0:
                // Obsługa opcji START
                rozpocznijPoziom(0);
                break;
            case 1:
                // Obsługa opcji POMOC
                JOptionPane.showMessageDialog(frame, "Pomoc dotycząca gry");
                break;
            case 2:
                // Wybierz poziom
                wybierzPoziom();
                break;
            case 3:
                // Obsługa opcji KONIEC GRY
                System.exit(0);
                break;
            default:
                break;
        }
    }
    private void wybierzPoziom() {

        Object[] options = {"Poziom 1", "Poziom 2", "Poziom 3"};
        int wyborPoziomu = JOptionPane.showOptionDialog(frame, "Wybierz poziom:", "WYBOR POZIOMU", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (wyborPoziomu) {
            case 0:
                rozpocznijDowolnyPoziom(0);
                break;
            case 1:
                rozpocznijDowolnyPoziom(1);
                break;
            case 2:
                rozpocznijDowolnyPoziom(2);
            default:
                break;
        }
    }
    public void rozpocznijPoziom(int poziom) {

        if (aktualnyPoziom < maksymalnyDostepnyPoziom){
            plansza = new Plansza(czasLabel, poziomLabel, this, poziom);
            frame.getContentPane().removeAll();
            frame.add(plansza, BorderLayout.CENTER);

            JPanel stopka = utworzStopke();
            frame.add(stopka, BorderLayout.SOUTH);

            frame.validate();
            frame.repaint();
            plansza.rozpocznijRozgrywke();
            aktualizujPoziomLabel(poziom + 1);
        }else {
            JOptionPane.showMessageDialog(frame, "Gratulacje! Udało się przejść wszystkie poziomy!");
            pokazMenu();
        }
        aktualnyPoziom++;
    }
    public void rozpocznijDowolnyPoziom(int poziom) {
        plansza = new Plansza(czasLabel, poziomLabel, this, poziom);
        frame.getContentPane().removeAll();
        frame.add(plansza, BorderLayout.CENTER);

        JPanel stopka = utworzStopke();
        frame.add(stopka, BorderLayout.SOUTH);

        frame.validate();
        frame.repaint();
        plansza.rozpocznijRozgrywke();
        aktualizujPoziomLabel(poziom + 1);
    }
    private JLabel utworzElement(int szerokosc, int wysokosc, String tekst) {
        JLabel element = new JLabel(tekst);
        element.setPreferredSize(new Dimension(szerokosc, wysokosc));
        element.setOpaque(true);
        element.setBackground(kolor);
        element.setHorizontalAlignment(SwingConstants.CENTER);
        element.setVerticalAlignment(SwingConstants.CENTER);
        element.setFont(new Font("Arial", Font.BOLD, 35));
        return element;
    }
    private static JButton utworzPrzycisk(int szerokosc, int wysokosc, String sciezkaDoObrazka) {
        JButton przyciskMenu = new JButton();
        przyciskMenu.setPreferredSize(new Dimension(szerokosc, wysokosc));

        przyciskMenu.setOpaque(false);
        przyciskMenu.setContentAreaFilled(false);
        przyciskMenu.setBorderPainted(false);

        ImageIcon icon = new ImageIcon(sciezkaDoObrazka);
        przyciskMenu.setIcon(icon);

        return przyciskMenu;
    }
    public JPanel utworzStopke() {
        // Stopka
        JPanel stopka = new JPanel();
        stopka.setPreferredSize(new Dimension(1220, 130));
        stopka.setBackground(Color.GRAY);

        poziomLabel = utworzElement(280, 120, "Poziom: ");
        punktyLabel = utworzElement(280, 120, "Punkty: 0");
        czasLabel = utworzElement(280, 120, "Czas: ");

        stopka.add(poziomLabel);
        stopka.add(punktyLabel);
        stopka.add(czasLabel);
        // Przycisk MENU
        JButton menuButton = utworzPrzycisk(280, 120, currentDir + "\\obrazki\\MENU.png");
        menuButton.addActionListener(e -> {
            pokazMenu();
        });
        stopka.add(menuButton);
        return stopka;

    }
    public void aktualizujPunktyLabel(int punkty) {
        punktyLabel.setText("Punkty: " + punkty);
    }

    public void aktualizujCzasLabel(int czasGry) {
        czasLabel.setText("Czas: " + czasGry + " s");
    }

    public void aktualizujPoziomLabel(int poziom) {
        poziomLabel.setText("Poziom: " + poziom);

    }

    public void uruchom() {
        frame.setVisible(true);
    }


}
