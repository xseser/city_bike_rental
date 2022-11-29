package Zbior;
import java.io.*;
import java.util.*;
import pl.Rower.Rower;
import java.util.List;
import javax.swing.*;

public class zbior_rowerow {
    ArrayList<Rower> zbior = new ArrayList<Rower>();

    public void dodaj_rower(Rower rower) {
        int pomocnicza = 0;
        if (zbior.size() == 0) {
            zbior.add(rower);
            JOptionPane.showMessageDialog(null, "Rower dodany poomyślnie!");
            zapis_do_pliku("zapis.txt"); //po dodaniu roweru, automatycznie zapisuje to do pliku.
        } else {
            for (Rower value : zbior) {
                if (rower.zwroc_ID() == value.zwroc_ID()) {
                    pomocnicza++;
                }
            }
            if (pomocnicza == 0) {
                zbior.add(rower);
                JOptionPane.showMessageDialog(null, "Rower dodany poomyślnie!");
                zapis_do_pliku("zapis.txt");
            }
            else{
                JOptionPane.showMessageDialog(null, "Rower o podanym numerze już istnieje!");
            }
        }
    }

    public void ShowAll() {
        System.out.println("WSZYSTKIE ROWERY: ");
        if (zbior.size() == 0) {
            System.out.println("NIE MA ROWEROW!");
        }
        for (Rower rower : zbior) {
            rower.show_bike();
        }
    }

    public void delete_bike(int number) {
        try {
            //System.out.println("PODAJ NUMER ROWERU, KTÓRY CHCESZ USUNĄĆ: ");
            int pomocnicza = 0; //pomocnicza niewiadoma, jeżeli wystąpi tyle razy co size listy, to wtedy oznacza to, że danego roweru nie ma w liście!
            //Scanner input = new Scanner(System.in);
            //int number = input.nextInt();
            for (int i = 0; i < zbior.size(); i++) {
                if (zbior.get(i).zwroc_ID() == number) {
                    zbior.remove(i);
                    zapis_do_pliku("zapis.txt");
                    JOptionPane.showMessageDialog(null, "Pomyślnie usunięto rower!");
                    return;
                } else {
                    pomocnicza++;
                }
            }
            if (pomocnicza == zbior.size()) {
                JOptionPane.showMessageDialog(null, "Nie ma roweru o podanym numerze!");
            }
        }catch (NumberFormatException | InputMismatchException exception){
            System.out.println("NIE PRAWIDLOWY FORMAT DANYCH!");
        }
    }

    public void wypozycz_rower(int number) {
        try {
            ///System.out.println("PODAJ NUMER ROWERU, KTÓRY CHCESZ WYPOZYCZYC: ");
            int pomocnicza = 0; //pomocnicza niewiadoma, jeżeli wystąpi tyle razy co size listy, to wtedy oznacza to, że danego roweru nie ma w liście!
            //Scanner input = new Scanner(System.in);
            //int number = input.nextInt();

            for (Rower rower : zbior) {
                if (number == rower.zwroc_ID()) {
                    if (!rower.Sprawdz_czy_wolny()) {
                        JOptionPane.showMessageDialog(null, "ROWER ZOSTAŁ WYNAJĘTY!");
                        System.out.println(rower.Sprawdz_czy_wolny());
                        rower.wynajem_zwrot();
                        System.out.println(rower.Sprawdz_czy_wolny());
                        zapis_do_pliku("zapis.txt");
                    } else {
                        JOptionPane.showMessageDialog(null, "Podany rower jest już wynajety, nie mozesz go wynajac!");
                    }
                } else {
                    pomocnicza++;
                }
            }
            if (pomocnicza == zbior.size()) {
                JOptionPane.showMessageDialog(null, "NIE MA ROWERU O PODANYM NUMERZE! ");
            }
        }catch (NumberFormatException | InputMismatchException exception){
            JOptionPane.showMessageDialog(null, "BŁĄD DANYCH");
        }
    }

    public void oddaj_rower(int number) {
        try {
            //System.out.println("PODAJ NUMER ROWERU, KTÓRY CHCESZ ODDAC: ");
            int pomocnicza = 0; //pomocnicza niewiadoma, jeżeli wystąpi tyle razy co size listy, to wtedy oznacza to, że danego roweru nie ma w liście!
            //Scanner input = new Scanner(System.in);
            //int number = input.nextInt();
            for (int i = 0; i < zbior.size(); i++) {
                if (number == zbior.get(i).zwroc_ID()) {
                    if (zbior.get(i).Sprawdz_czy_wolny()) {
                        JOptionPane.showMessageDialog(null, "Rower o numerze " +number+ " został oddany poomyślnie!");
                        zbior.get(i).wynajem_zwrot();
                        //zapis_do_pliku();
                    } else {
                        JOptionPane.showMessageDialog(null, "Rower o numerze "+number+" nie jest wypożyczony");
                    }
                } else {
                    pomocnicza++;
                }
            }
            if (pomocnicza == zbior.size()) {
                JOptionPane.showMessageDialog(null, "Nie ma roweru o podanym numerze!");
            }
        }catch (NumberFormatException | InputMismatchException exception){
            JOptionPane.showMessageDialog(null, "Błąd danych");
        }
    }

    public void sortowanie_po_numerach() {   // zastosowane jest tu zwykle sortowanie bombelkowe
        //System.out.println("ROWERY POSORTOWANE W KOLEJNOSCI NUMEROWEJ: ");
        int n = 0;
        n = zbior.size();
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                if (zbior.get(i).zwroc_ID() > zbior.get(i + 1).zwroc_ID()) {
                    Collections.swap(zbior, i, i + 1);
                }
            }
            n = n - 1;
        }
        //ShowAll();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sort_numer.txt"));
            for (Rower rower : zbior) {
                bw.write(rower.pomocznicza_plik2() + "\n");
            }
            bw.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "BŁĄD DANYCH");
        }
    }

    public void sortowanie_po_cenie() {
        //System.out.println("ROWERY POSORTOWANE OD NAJNIZSZEJ CENY: ");
        int n = 0;
        n = zbior.size();
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                if (zbior.get(i).zwroc_CENE() > zbior.get(i + 1).zwroc_CENE()) {
                    Collections.swap(zbior, i, i + 1);
                }
            }
            n--;
        }
        //ShowAll();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sort_cena.txt"));
            //"C://Users//mierz//OneDrive//Pulpit//programowanie obiektowe//obiekt_pokazanie_posortowanych_po_cenie.txt"
            for (Rower rower : zbior) {
                bw.write(rower.pomocznicza_plik2() + "\n");
            }
            bw.close();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "BŁĄD DANYCH");
        }
    }

    public void pokazanie_wolnych_rowerow() {
        ArrayList<Rower> Wolne_rowery = new ArrayList<Rower>();
        //System.out.println("ROWERY, KTORE SA WOLNE: ");
        for (int i=0;i<zbior.size();i++) {
            if (!zbior.get(i).Sprawdz_czy_wolny()) {
                Wolne_rowery.add(zbior.get(i));
            }
        }
        //ShowAll();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sort_wolne.txt"));
            for (int i=0;i<Wolne_rowery.size();i++) {
                bw.write(Wolne_rowery.get(i).pomocznicza_plik2() + "\n");
            }
            bw.close();
        } catch (Exception ignored) {
        }
    }

    public int[] odczytanie_z_pliku(String x) { //x to sciezka do pliku

        try {
            File f = new File(x);
            Scanner s = new Scanner(f);
            int ctr = 0;
            while (s.hasNextInt()) {
                ctr++;
                s.nextInt();
            }
            int[] arr = new int[ctr];
            Scanner s1 = new Scanner(f);
            for(int i =0; i<arr.length;i++){
                arr[i] = s1.nextInt();
            }
            return arr;
        } catch (Exception exception) {
            System.out.println("Zła ścieżka pliku!");
        }

        return null;
    }

    public ArrayList<Rower> dodanie_rowerow_z_pliku(int[] arr){
        ArrayList<Integer> TAB = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(i%4==3){
                TAB.add(arr[i]);
                //System.out.println(arr[i]);
            }
        }
        int i =0;
        for(int j=0;j< arr.length;j++) {
            if (j % 4 == 0) {
                    if (TAB.get(i) == 0) {
                        if(zbior.size()==0) {
                            zbior.add(new Rower(arr[j], arr[j + 1], arr[j + 2], false));
                        }
                        else{
                            int pomocnicza = 0;
                            for (Rower value : zbior) {
                                if (arr[j] == value.zwroc_ID()) {
                                    pomocnicza++;
                                }
                            }
                            if (pomocnicza == 0) {
                                zbior.add(new Rower(arr[j], arr[j + 1], arr[j + 2], false));
                            }
                        }
                    }

                    else {
                        if (zbior.size() == 0) {
                            zbior.add(new Rower(arr[j], arr[j + 1], arr[j + 2], true));
                        }
                        else{
                            int pomocnicza = 0;
                            for (Rower value : zbior) {
                                if (arr[j] == value.zwroc_ID()) {
                                    pomocnicza++;
                                }
                            }
                            if (pomocnicza == 0) {
                                zbior.add(new Rower(arr[j], arr[j + 1], arr[j + 2], true));
                            }
                        }
                    }
                    i++;
            }
        }
        return zbior;

    }

    public boolean zapis_do_pliku(String x){  //funkcja pozwala na zapis do pliku w takiej formie aby to pozniej jeszcze raz otworzyc
        try {
            sortowanie_po_cenie();
            sortowanie_po_numerach();
            pokazanie_wolnych_rowerow();
            for(int i=0;i< zbior.size()-1;i++){
                for(int j=i+1;j<zbior.size();j++){
                    if(zbior.get(i).zwroc_ID()==zbior.get(j).zwroc_ID()){
                        JOptionPane.showMessageDialog(null, "BŁĄD W PLIKU!\n" + "2 ROWERRY O TAKIM SAMYM ID!");
                        return false;
                    }
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(x));
            for (Rower rower : zbior) {
                bw.write(rower.pomocznicza_plik2() + "\n");
            }
            bw.close();
        } catch (Exception exception) {
            System.out.println("Zla sciezka pliku");
        }
        return true;
    }

    public void usuwanie_calosci(){
        for(int i=0;i< zbior.size();i++){
            zbior.remove(i);
        }
    }
}



