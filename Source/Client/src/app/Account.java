package app;

import java.io.Serializable;

/**
 * Klasa ktorej pojedynczy obiekt reprezentuje konto uzytkownika. Obiekt tej klasy moze byc przesylany do serwera w celu logowanie lub rejestracji klienta.
 */
public class Account implements Serializable {
    /**
     * Imie uzytkownika
     */
    String name;
    /**
     * Nazwisko uzytkownika
     */
    String surname;
    /**
     * Login uzytkownika
     */
    String login;
    /**
     * Haslo uzytkownika
     */
    String password;
    /**
     * Email uzytkownika
     */
    String email;
    /**
     * Typ konta (np. Lekarz, Pielegniarka)
     */
    String type;

    /**
     * true - zarejestruj sie, false - zaloguj sie
     */
    private boolean register;

    /**
     * Konstruktor konta do logowania.
     * @param login     Login uzytkownika
     * @param password  Haslo uzytkownika
     */
    public Account(String login, String password) {
        this.login = login;
        this.password = password;
        register = false;
    }

    /**
     * Konstruktor konta do rejestracji.
     * @param name      Imie uzytkownika
     * @param surname   Nazwisko uzytkownika
     * @param login     Login uzytkownika
     * @param password  Haslo uzytkownika
     * @param email     Email uzytkownika
     * @param type      Typ konta (np. Lekarz, Pielegniarka)
     */
    public Account(String name, String surname, String login, String password, String email, String type) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.type = type;
        register = true;
    }

    /**
     * Sprawdzenie czy konto jest przeznaczone do rejestracji.
     * @return true - zarejestruj sie, false - zaloguj sie
     */
    public boolean wantRegister() {
        return register;
    }
}
