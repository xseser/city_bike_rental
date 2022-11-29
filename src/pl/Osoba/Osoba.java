package pl.Osoba;

public class Osoba {
    String login;
    String haslo;
    int wiek;

    public Osoba(String login, String haslo, int wiek){
        this.haslo=haslo;
        this.login=login;
        this.wiek=wiek;
    }

    public String zwroc_haslo(){
        return haslo;
    }

    public String zwroc_login(){
        return login;
    }

    public int zwroc_wiek(){
        return wiek;
    }
}

