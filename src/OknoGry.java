import javax.swing.*;
import java.awt.*;

public class OknoGry {
    private JFrame frame;
    private Plansza planszaPoziomPierwszy;
    public OknoGry() {
        frame = new JFrame("Eko-Gierka Gra o Segregacji Odpadów");
        frame.setSize(1280, 1024);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        dodajElementy();
    }

    private void dodajElementy() {
        // Ekran rozgrywki
        JPanel ekranRozgrywki = new JPanel();
        ekranRozgrywki.setPreferredSize(new Dimension(1220, 820));
        frame.add(ekranRozgrywki,BorderLayout.CENTER);

        JPanel stopka = utworzStopke();

        frame.add(stopka, BorderLayout.SOUTH);

    }
    public JPanel utworzStopke(){
        // Stopka
        JPanel stopka = new JPanel();
        stopka.setPreferredSize(new Dimension(1220, 130));
        stopka.setBackground(Color.GRAY);

        JLabel czasLabel = utworzElement(280, 120, "Czas: ");
        JLabel poziomLabel = utworzElement(280, 120, "Poziom: ");
        JLabel punktyLabel = utworzElement(280, 120, "Punkty: ");

        stopka.add(czasLabel);
        stopka.add(poziomLabel);
        stopka.add(punktyLabel);

        // Przycisk MENU
        JButton menuButton = utworzPrzycisk(280, 120, "MENU");
        menuButton.addActionListener(e -> {
            pokazMenu();
        });

        stopka.add(menuButton);

        return stopka;
    };

    private void pokazMenu() {
        Object[] options = {"START", "POMOC", "KONIEC GRY"};
        int choice = JOptionPane.showOptionDialog(frame, "Wybierz opcję:", "MENU", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Obsługa wybranej opcji
        switch (choice) {
            case 0:
                // Obsługa opcji START
                rozpocznijPoziomPierwszy();

                break;
            case 1:
                // Obsługa opcji POMOC
                JOptionPane.showMessageDialog(frame, "Pomoc dotycząca gry");
                break;
            case 2:
                // Obsługa opcji KONIEC GRY
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void rozpocznijPoziomPierwszy() {
        planszaPoziomPierwszy = new Plansza();
        frame.getContentPane().removeAll();
        frame.add(planszaPoziomPierwszy, BorderLayout.CENTER);
        JPanel stopka = utworzStopke();

        frame.add(stopka, BorderLayout.SOUTH);
        frame.validate();
        frame.repaint();
    }

    private JLabel utworzElement(int szerokosc, int wysokosc, String tekst) {
        JLabel element = new JLabel(tekst);
        element.setPreferredSize(new Dimension(szerokosc, wysokosc));
        element.setOpaque(true);
        element.setBackground(Color.green);
        element.setHorizontalAlignment(SwingConstants.CENTER);
        element.setVerticalAlignment(SwingConstants.CENTER);
        element.setFont(new Font("Arial",Font.BOLD,25));
        return element;
    }

    private JButton utworzPrzycisk(int szerokosc, int wysokosc, String tekst) {
        JButton przyciskMenu = new JButton(tekst);
        przyciskMenu.setPreferredSize(new Dimension(szerokosc, wysokosc));
        przyciskMenu.setBackground(Color.green);
        przyciskMenu.setHorizontalAlignment(SwingConstants.CENTER);
        przyciskMenu.setVerticalAlignment(SwingConstants.CENTER);
        przyciskMenu.setFont(new Font("Arial",Font.BOLD,25));
        return przyciskMenu;
    }

    public void uruchom() {frame.setVisible(true);}


}
