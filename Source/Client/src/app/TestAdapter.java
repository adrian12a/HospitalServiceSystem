package app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 * Klasa adaptujaca obiekty klasy Test tak, aby mozna ja bylo wyswietlic w formie tabeli (wzorzec Adapter).
 */
public class TestAdapter extends RecursiveTreeObject<TestAdapter> {
    /**
     * Id badania.
     */
    private String id;
    /**
     * Imie lekarza.
     */
    private String doctorName;
    /**
     * Nazwisko lekarza.
     */
    private String doctorSurname;
    /**
     * Data przeprowadzenia badania.
     */
    private String date;
    /**
     * Pacjent.
     */
    private Patient  patient;
    /**
     * Badanie.
     */
    private Test test;

    /**
     * Konstruktor.
     * @param id                Id badania
     * @param doctorName        Imie lekarza
     * @param doctorSurname     Nazwisko lekarza
     * @param patientName       Imie pacjenta
     * @param patientSurname    Nazwisko pacjenta
     * @param type              Typ badania
     * @param date              Data przeprowadzenia badania
     * @param description       Opis badania
     */
    public TestAdapter(String id, String doctorName, String doctorSurname, String patientName, String patientSurname, String type, String date, String description) {
        this.id = id;
        this.doctorName =  doctorName;
        this.doctorSurname = doctorSurname;
        this.date = date;
        patient = new Patient(patientName, patientSurname, "" , "");
        test = new Test(patient,description,type);
    }

    /**
     * Pobranie id pacjenta.
     * @return Id pacjenta
     */
    public String getId() {
        return id;
    }

    /**
     * Pobranie imienia doktora.
     * @return Imie doktora
     */
    public String getDoctorName() {
        return doctorName;
    }

    /**
     * Pobranie nazwiska doktora.
     * @return Nazwisko doktora
     */
    public String getDoctorSurname() {
        return doctorSurname;
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

    /**
     * Pobranie typu badania.
     * @return Typ badania
     */
    public String getType() {
        return test.type;
    }

    /**
     * Pobranie opisu badania.
     * @return Opis badania
     */
    public String getDescription() {
        return test.description;
    }

    /**
     * Pobranie daty przeprowadzenia badania.
     * @return Data przeprowadzenia badania
     */
    public String getDate() {
        return date;
    }
}
