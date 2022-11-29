package pl.zbior.rowerow;
import java.util.Scanner;

import Zbior.zbior_rowerow;
import pl.Rower.Rower;

public class Panel {
    public static int pobierz_numer(){
        System.out.println("PODAJ NUMER DO WYKONANIA: ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        System.out.println("wybrales numer" + " " +number);
        return number;
    }

    public static void lista(){
        System.out.println("0. ZAKONCZ PROGRAM");
        System.out.println("1. DODAJ ROWER");
    }

    public static void panel_sterowania(int number){
        lista();
        pobierz_numer();
        switch(number){
            case 0:
                return;

            case 1:
                //zbior_rowerow.dodaj_rower();
        }
    }
}
