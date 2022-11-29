package Zbior;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wypozyczalnia_rowerow extends JFrame {

    zbior_rowerow r1 = new zbior_rowerow();
    dodawanie_rowerow_okno OKNO_dodowania = new dodawanie_rowerow_okno();
    Wypozyczalnia_usun_rower OKNO_usuwania = new Wypozyczalnia_usun_rower();
    Wypozyczalnia_wypozyczenie OKNO_WYPOZYCZENIA = new Wypozyczalnia_wypozyczenie();
    Wypozyczalnia_oddanie_z_wypozyczenia OKNO_ODDANIA = new Wypozyczalnia_oddanie_z_wypozyczenia();
    private JPanel mainPanel;
    private JPanel dodatkowy_panel;
    private JTable table_with_bikes;
    private JButton button_dodaj;
    private JButton button_usun;
    private JButton button_wyswietl_random;
    private JButton button_wypozyczenie;
    private JButton button_oddanie;
    private JButton button_wyswietlanie_wolnych;
    private JButton Button_sortowanie_cenowe;
    private JButton Button_wyswietlanie_numery;
    private JLabel Dodanie_roweru_miejskiego;
    private JLabel Usun_rower;
    private JLabel Display_bikes_random;
    private JLabel Wypozyczanie;
    private JLabel Oddanie;
    private JLabel wyswietlanie_wolnych;
    private JLabel sortowanie_cenowe;
    private JLabel wyswietlanie_numery;
    private JButton button_off_program;
    JLabel label;
    private final DefaultTableModel model = new DefaultTableModel();

    public Wypozyczalnia_rowerow() {
        setupFrame();
        setupLOGO();
        button_wyswietl_random.addActionListener(e -> DELETE());
        button_wyswietlanie_wolnych.addActionListener((e -> POKAZANIE_WOLNYCH()));
        button_dodaj.addActionListener((e -> DODANIE()));
        button_usun.addActionListener((e -> USUWANIE()));
        button_wypozyczenie.addActionListener((e -> WYPOZYCZENIE()));
        button_oddanie.addActionListener((e -> ODDANIE()));
        Button_sortowanie_cenowe.addActionListener((e -> CENOWE()));
        Button_wyswietlanie_numery.addActionListener((e -> WYSWIETLANIE_NUMEROWE()));
        button_off_program.addActionListener(e -> KONIEC_PROGRAMU());
    }

    public void setupFrame() {
        setContentPane(mainPanel);
        setTitle("WYPOZYCZALNIA ROWEROW");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 800));
        setResizable(false);
        setVisible(true);
        table_with_bikes.setModel(model);
        model.setColumnCount(5);

    }


    public void setupLOGO() {
        ImageIcon image = new ImageIcon("logo.jpg");
        setIconImage(image.getImage());
        dodaj_do_tabelki("zapis.txt");
    }  //donne

    public void DELETE() {
        usuwanie_tabelki();
    } //done

    public void POKAZANIE_WOLNYCH() {
        usuwanie_tabelki();
        r1.dodanie_rowerow_z_pliku(r1.odczytanie_z_pliku("zapis.txt"));
        r1.pokazanie_wolnych_rowerow();
        r1.usuwanie_calosci();
        dodaj_do_tabelki("sort_wolne.txt");
    }  //done

    public void DODANIE() {
        usuwanie_tabelki();
        OKNO_dodowania.window();
        dodaj_do_tabelki("zapis.txt");
    } ///DONE!

    public void USUWANIE() {
        usuwanie_tabelki();
        OKNO_usuwania.window();
        dodaj_do_tabelki("zapis.txt");
    } //done

    public void WYPOZYCZENIE() {
        usuwanie_tabelki();
        OKNO_WYPOZYCZENIA.window();
        dodaj_do_tabelki("zapis.txt");
    } //done

    public void ODDANIE() {
        usuwanie_tabelki();
        OKNO_ODDANIA.window();
        dodaj_do_tabelki("zapis.txt");
    }

    public void CENOWE() {
        usuwanie_tabelki();
        r1.dodanie_rowerow_z_pliku(r1.odczytanie_z_pliku("zapis.txt"));
        r1.sortowanie_po_cenie();
        r1.usuwanie_calosci();
        dodaj_do_tabelki("sort_cena.txt");
    } ///DONE!

    public void WYSWIETLANIE_NUMEROWE() {
        usuwanie_tabelki();
        r1.dodanie_rowerow_z_pliku(r1.odczytanie_z_pliku("zapis.txt"));
        r1.sortowanie_po_numerach();
        r1.usuwanie_calosci();
        dodaj_do_tabelki("sort_numer.txt");
    }   ///  DONE!

    private void KONIEC_PROGRAMU() {
        System.exit(1);
    } ///  DONE!

    public void dodaj_do_tabelki(String x) {
        String stan = "";
        int id;
        int cena;
        int rozmiar;
        boolean waga;
        File f = new File(x);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int ctr = 0;
        while (true) {
            assert s != null;
            if (!s.hasNextInt()) break;
            ctr++;
            s.nextInt();
        }
        int[] arr = new int[ctr];
        Scanner s1 = null;
        try {
            s1 = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < arr.length; i++) {
            assert s1 != null;
            arr[i] = s1.nextInt();
        }

        for (int i = 0; i < arr.length; i++)
            if (i % 4 == 0) {
                id = arr[i];
                rozmiar = arr[i + 1];
                cena = arr[i + 2];
                if (arr[i + 3] == 0) {
                    waga = false;
                    stan = "wolny";
                }
                if (arr[i+3]==1){
                    stan = "zajęty";
                }
                dodaj_rower_do_tabeli(id, rozmiar, cena, stan);
            }

    }   // done

    private void dodaj_rower_do_tabeli(int id, int rozmiar, int cena, String waga) {
        Object[] row = {"ROWER", id, rozmiar, cena, waga};
        model.addRow(row);
    }  //done

    private void usuwanie_tabelki() {
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    } //done

}
