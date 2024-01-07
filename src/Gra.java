/*
 * Języki Programowania Wysokiego Poziomu - projekt
 *
 * Kierunek: Elektronika i Telekomunikacja Semestr V
 *
 * Autor: Jakub Cichocki nr indeksu: 188819
 *
 */

/**
 * Klasa Gra reprezentuje główną klasę gry.
 */
public class Gra {
    /**
     * Metoda main uruchamia grę
     */
    public static void main(String[] args) {
        // Tworzone jest nowe okno gry
        OknoGry graOkno = new OknoGry();

        // Uruchomienie okna gry
        graOkno.uruchom();
    }
}