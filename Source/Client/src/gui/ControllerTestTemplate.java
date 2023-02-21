package gui;

import app.Log;
import app.Main;
import app.Patient;
import app.Test;

/**
 * Absstrakcyjny kontroler gui - klasa szablonowa tworzenia badania.
 */
public abstract class ControllerTestTemplate extends Controller {
    /**
     * Pacjent.
     */
    Patient patient;
    /**
     * Badanie.
     */
    Test test;

    /**
     * Stworzenie badania i przeslanie do serwera (Wzorzec Metoda szablonowa).
     * @return true - w przypadku powodzenia, false - w przypadku niepowodzenia
     */
    final boolean acceptTest() {
        makePatient();
        makeTest(patient);
        return sendMessage(test);
    }

    /**
     * Tworzenie pacjenta (nalezy zaimplementowac w klasie dziedziczacej).
     */
    abstract void makePatient();

    /**
     * Tworzenie badania  (nalezy zaimplementowac w klasie dziedziczacej).
     * @param patient Pacjent
     */
    abstract void makeTest(Patient patient);

    /**
     * Wysylanie wiadomosci z wynikami badan do serwera.
     * @param test Badanie
     * @return Wiadomosc zwrotna (true - w przypadku powodzenia, false - w przypadku niepowodzenia)
     */
    boolean sendMessage(Test test) {
        Log.info("Wysylanie wynikow badania do serwera");
        Main.getServer().sendMessage(test);
        return (boolean) Main.getServer().receiveMessage();
    }
}