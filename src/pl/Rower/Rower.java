package pl.Rower;

import javax.swing.*;

public class Rower {

    int ID;
    int rozmiar;
    int cena;
    boolean czy_wolny;

    public Rower(int ID, int rozmiar, int cena, boolean czy_wolny) {
        this.ID = ID;
        this.rozmiar = rozmiar;
        this.cena = cena;
        this.czy_wolny = czy_wolny;
    }

    public int zwroc_ID() {
        return ID;
    }

    public int zwroc_CENE(){
        return cena;
    }

    public void show_bike(){
        if(czy_wolny) {
            System.out.println("ROWER O ID: " + ID + ", ROZMIARZE: " + rozmiar + ", CENIE: " + cena + " " + "ROWER ZAJETY!");
        }
        else{
            System.out.println("ROWER O ID: " + ID + ", ROZMIARZE: " + rozmiar + ", CENIE: " + cena + " " + "ROWER WOLNY!");
        }
    }

    public String pomocnicza_plik(){ //funkcja pomaga w zapisie danych do pliku
        return("ROWER O ID: " + ID + ", ROZMIARZE: " + rozmiar + ", CENIE: " + cena + " " + czy_wolny);
    }

    public String pomocznicza_plik2(){ //funckcja pomaga w zapisie danych do pliku, ale tak by program mógł je ponownnie odczytać
        int x=0;
        if(!czy_wolny){
            x=0;
        }
        else{
            x=1;
        }
        return(ID+" "+rozmiar+" "+cena+" "+x);
    }

    public boolean Sprawdz_czy_wolny(){
        return czy_wolny;
    }

    public boolean wynajem_zwrot(){ //funkcja zmienia stan wynajmu podanego roweru.
        if(!czy_wolny){
            czy_wolny = true;
        }
        else{
            czy_wolny = false;
        }
        return czy_wolny;
    }

}


