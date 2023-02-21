package app;

/**
 * Klasa obslugujaca wiadomosci od klienta zawierajace obiekty klasy Test.
 */
public class TestReceiver implements Receiver {

    /**
     * Obsluz wiadomosc.
     * @param o         Wiadomosc
     * @param userId    Identyfikator uzytkownika od ktorego odebrano wiadomosc
     * @return true w przypadku powodzenia
     */
    @Override
    public Object receive(Object o, int userId) {
        int patientId = checkPatient((Test)o);
        insertTest((Test)o, userId, patientId);
        return true;
    }

    /**
     * Sprwadzenie czy pacjent znajduje sie juz w bazie (jesli nie dodanie pacjenta do bazy) oraz sprawdzenie jego identyfikatora.
     * @param t Badanie
     * @return  Identyfikator pacjenta
     */
   int checkPatient(Test t) {
        String input = "SELECT id FROM Pacjenci WHERE imię = '" + t.patient.name + "' AND nazwisko = '" + t.patient.surname + "' AND telefon = '" + t.patient.phoneNumber + "';";
        String result = Main.getDatabase().selectOne(input,"id");
        int id;
        if(result == null) {
            id = Main.getDatabase().getNextId("Pacjenci");
            input = "INSERT INTO Pacjenci VALUES (" + id + ",'" + t.patient.name + "','" + t.patient.surname + "','" + t.patient.email + "','" + t.patient.phoneNumber + "');";
            Main.getDatabase().query(input);
        }
        else {
            id = Integer.parseInt(result);
        }
        return id;
    }

    /**
     * Dodanie badania do bazy danych.
     * @param t             Badanie
     * @param userId        Identyfikator uzytkownika (pracownika, ktory przeprowadzil badanie)
     * @param patientId     Identyfikator pacjenta
     */
    void insertTest(Test t,int userId, int patientId) {
        int id = Main.getDatabase().getNextId("Badania");
        String input = "SELECT id FROM TypyBadań WHERE nazwa = '" + t.type + "';";
        String result = Main.getDatabase().selectOne(input,"id");
        int testTypeId = Integer.parseInt(result);
        input = "INSERT INTO Badania VALUES (" + id + "," + userId + "," + patientId + "," + testTypeId + ",'" + t.date.toString() + "','" + t.description + "');";
        Main.getDatabase().query(input);
    }
}
