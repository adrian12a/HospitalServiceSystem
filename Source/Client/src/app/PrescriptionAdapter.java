package app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 * Klasa adaptujaca obiekty klasy Prescrtiption tak, aby mozna ja bylo wyswietlic w formie tabeli (wzorzec Adapter).
 */
public class PrescriptionAdapter extends RecursiveTreeObject<PrescriptionAdapter> {
    /**
     * Id recepty.
     */
    String id;
    /**
     * Data wystawienia recepty.
     */
    String date;
    /**
     * Nazwa leku.
     */
    String medicineName;
    /**
     * Liczba sztuk przepisanego leku.
     */
    String medicineAmount;
    /**
     * Pacjent.
     */
    Patient patient;

    /**
     * Konstruktor.
     * @param id                Id recepty
     * @param patientName       Imie pacjenta
     * @param patientSurname    Nazwisko pacjenta
     * @param date              Data wystawienia recepty
     * @param medicineName      Nazwa leku
     * @param medicineAmount    Liczba sztuk przepisanego leku
     */
    PrescriptionAdapter(String id, String patientName, String patientSurname, String date, String medicineName, String medicineAmount) {
        this.id = id;
        this.date = date;
        this.medicineName = medicineName;
        this.medicineAmount = medicineAmount;
        patient = new Patient(patientName, patientSurname, "" , "");
    }

    /**
     * Pobranie id recepty.
     * @return Id recepty
     */
    public String getId() {
        return id;
    }

    /**
     * Pobranie daty wystawienia recepty.
     * @return Data wystawienia recepty
     */
    public String getDate() {
        return date;
    }

    /**
     * Pobranie nazwy leku.
     * @return Nazwa leku
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Pobranie ilosci sztuk przepisanego leku.
     * @return Ilosc sztuk leku
     */
    public String getMedicineAmount() {
        return medicineAmount;
    }

    /**
     * Pobranie imienia pacjenta.
     * @return Imie pacjenta
     */
    public String getPatientName() {
        return patient.name;
    }

    /**
     * Pobranie nazwiska pacjenta.
     * @return Nazwisko pacjenta
     */
    public String getPatientSurname() {
        return patient.surname;
    }
}
