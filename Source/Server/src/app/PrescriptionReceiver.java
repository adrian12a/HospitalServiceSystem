package app;

/**
 * Klasa obslugujaca wiadomosci od klienta zawierajace obiekty klasy Prescription.
 */
public class PrescriptionReceiver implements Receiver {

    /**
     * Obsluz wiadomosc.
     * @param o         Wiadomosc
     * @param userId    Identyfikator uzytkownika od ktorego odebrano wiadomosc
     * @return true w przypadku powodzenia
     */
    @Override
    public Object receive(Object o, int userId) {
        insertPrescription((Prescription) o);
        return true;
    }

    /**
     * Sprwadzenie czy pacjent znajduje sie juz w bazie (jesli nie dodanie pacjenta do bazy) oraz sprawdzenie jego identyfikatora.
     * @param p Recepta
     * @return  Identyfikator pacjenta
     */
    int checkPatient(Prescription p) {
        String input = "SELECT id FROM Pacjenci WHERE imię = '" + p.patient.name + "' AND nazwisko = '" + p.patient.surname + "' AND telefon = '" + p.patient.phoneNumber + "';";
        String result = Main.getDatabase().selectOne(input,"id");
        int id;
        if(result == null) {
            id = Main.getDatabase().getNextId("Pacjenci");
            input = "INSERT INTO Pacjenci VALUES (" + id + ",'" + p.patient.name + "','" + p.patient.surname + "','" + p.patient.email + "','" + p.patient.phoneNumber + "');";
            Main.getDatabase().query(input);
        }
        else {
            id = Integer.parseInt(result);
        }
        return id;
    }

    /**
     * Dodanie recepty do bazy danych.
     * @param p Recepta
     */
    void insertPrescription(Prescription p) {
        int patientId = checkPatient(p);
        int id = Main.getDatabase().getNextId("Recepty");
        for(Medicine medicine:p.medicines) {
            String input = "INSERT INTO Recepty VALUES (" + id + "," + patientId + ",'" + p.date.toString() + "','" + medicine.name + "','" + medicine.amount + "');";
            Main.getDatabase().query(input);
            id++;
        }
    }
}
