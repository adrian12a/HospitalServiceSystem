package app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 * Klasa adaptujaca obiekty klasy Patient tak, aby mozna ja bylo wyswietlic w formie tabeli (wzorzec Adapter).
 */
public class PatientAdapter extends RecursiveTreeObject<PatientAdapter> {
    /**
     * Id pacjenta.
     */
    private String id;
    /**
     * Obiekt klasy Patient do adaptacji.
     */
    private Patient patient;

    /**
     * Konstruktor.
     * @param id            Id pacjenta
     * @param name          Imie pacjenta
     * @param surname       Nazwisko pacjenta
     * @param email         E-mail pacjenta
     * @param phoneNumber   Numer telefonu pacjenta
     */
    public PatientAdapter(String id, String name, String surname, String email, String phoneNumber) {
        this.id = id;
        patient = new Patient(name, surname, email, phoneNumber);
    }

    /**
     * Pobranie id pacjenta.
     * @return Id pacjenta
     */
    public String getId() {
        return id;
    }

    /**
     * Pobranie imienia pacjenta.
     * @return Imie pacjenta
     */
    public String getName() {
        return patient.name;
    }

    /**
     * Pobranie nazwiska pacjenta.
     * @return Nazwisko pacjenta
     */
    public String getSurname() {
        return patient.surname;
    }

    /**
     * Pobranie e-maila pacjenta.
     * @return E-mail pacjenta
     */
    public String getEmail() {
        return patient.email;
    }

    /**
     * Pobranie numeru telefonu pacjenta.
     * @return Numer telefonu pacjenta
     */
    public String getPhoneNumber() {
        return patient.phoneNumber;
    }
}
