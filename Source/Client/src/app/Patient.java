package app;

import java.io.Serializable;

/**
 * Klasa ktorej pojedynczy obiekt reprezentuje pacjenta.
 */
public class Patient implements Serializable {
    /**
     * Imie pacjenta.
     */
    String name;
    /**
     * Nazwisko pacjenta.
     */
    String surname;
    /**
     * E-mail pacjenta.
     */
    String email;
    /**
     * Numer telefonu pacjenta.
     */
    String phoneNumber;

    /**
     * Konstruktor
     * @param name          Imie pacjenta
     * @param surname       Nazwisko pacjenta
     * @param email         E-mail pacjenta
     * @param phoneNumber   Numer telefonu pacjenta
     */
    public Patient(String name, String surname, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}