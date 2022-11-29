package pl.zbior_osob;
import pl.Osoba.Osoba;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class zbior_osob {
    ArrayList<Osoba> zbior = new ArrayList<>();

    public boolean weryfikacja_konta(Osoba osoba) {
        if (osoba.zwroc_haslo().length() < 8) {
            JOptionPane.showMessageDialog(null, "Hasło musi mieć conajmniej 8 znaków!");
            return false;
        }
        if (osoba.zwroc_wiek() < 16) {
            JOptionPane.showMessageDialog(null, "Wiek osoby musi wynosić conajmniej 16 lat!");
            return false;
        }
        for (int i = 0; i < zbior.size(); i++) {
            if (osoba.zwroc_login() == zbior.get(i).zwroc_login()) {
                JOptionPane.showMessageDialog(null, "Konto o podanym loginie już istnieje");
                return false;
            } else {
                return true;
            }
        }
        return true;
    }


    public void dodaj_konto(Osoba osoba) {
        if (weryfikacja_konta(osoba)) {
            zbior.add(osoba);
        }
    }

}
