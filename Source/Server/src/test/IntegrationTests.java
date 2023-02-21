package test;

import app.*;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Testy integracyjne aplikacji serwera
 */
public class IntegrationTests {
    /**
     * Polaczenie z baza danych
     */
    DatabaseConnection database = Main.getDatabase();

    /**
     * Testowanie obslugi obiektu klasy Account
     */
    @Test
    public void testAccountReceiver() {
        Receiver receiver = new AccountReceiver();
        int id = database.getNextId("Pracownicy");
        Account account = new Account("test","test","test","test","test","Lekarz");
        assertEquals(id,receiver.receive(account,1));

        int result = Integer.parseInt(database.selectOne("SELECT id FROM Pracownicy WHERE imię = 'test' and nazwisko = 'test';","id"));
        assertEquals(id,result);
    }

    /**
     * Testowanie obslugi obiektu klasy Archive
     */
    @Test
    public void testArchiveReceiver() {
        Receiver receiver = new ArchiveReceiver();

        Archive archive = (Archive) receiver.receive(new Archive("Employee"),1);
        int counter = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM pracownicy","COUNT(*)"));
        assertEquals(counter,archive.records);
        assertEquals(counter * 5,archive.fields.size());

        archive = (Archive) receiver.receive(new Archive("Patient"),1);
        counter = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM pacjenci","COUNT(*)"));
        assertEquals(counter,archive.records);
        assertEquals(counter * 5,archive.fields.size());
    }

    /**
     * Testowanie obslugi obiektu klasy Data
     */
    @Test
    public void testDataReceiver() {
        List<String> desiredAnswer = new ArrayList<>();
        desiredAnswer.add("Badanie wzroku");
        desiredAnswer.add("Pomiar ciśnienia");
        Receiver receiver = new DataReceiver();
        Data data = new Data("nazwa","TypyBadań","Uprawnienia","Lekarz",true);
        List<String> result = (List<String>) receiver.receive(data,1);
        assertArrayEquals(result.toArray(),desiredAnswer.toArray());
    }

    /**
     * Testowanie obslugi obiektu klasy Test
     */
    @Test
    public void testTestReceiver() {
        Patient patient = new Patient("test","test","test","test");
        app.Test test = new app.Test(patient,"-1.999","Badanie wzroku");
        Receiver receiver = new TestReceiver();
        int id = database.getNextId("Badania");
        assertEquals(true,receiver.receive(test,1));

        int idAfter = database.getNextId("Badania");
        assertEquals(id + 1,idAfter);

        int testId = Integer.parseInt(database.selectOne("SELECT id FROM Badania WHERE opis = '-1.999';","id"));
        assertEquals(id,testId);
    }

    /**
     * Testowanie obslugi obiektu klasy Prescription
     */
    @Test
    public void testPrescriptionReceiver() {
        Patient patient = new Patient("test","test","test","test");
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("test",1));
        medicines.add(new Medicine("test",2));
        medicines.add(new Medicine("test",3));
        Prescription prescription = new Prescription(patient,medicines);
        Receiver receiver = new PrescriptionReceiver();
        int id = database.getNextId("Recepty");
        assertEquals(true,receiver.receive(prescription,1));

        int idAfter = database.getNextId("Recepty");
        assertEquals(id + 3,idAfter);

        int testId1 = Integer.parseInt(database.selectOne("SELECT id FROM Recepty WHERE nazwaLeku = 'test' AND ilośćLeku = 1;","id"));
        int testId2 = Integer.parseInt(database.selectOne("SELECT id FROM Recepty WHERE nazwaLeku = 'test' AND ilośćLeku = 2;","id"));
        int testId3 = Integer.parseInt(database.selectOne("SELECT id FROM Recepty WHERE nazwaLeku = 'test' AND ilośćLeku = 3;","id"));
        assertEquals(id,testId1);
        assertEquals(id + 1,testId2);
        assertEquals(id + 2,testId3);
    }

    /**
     * Czyszczenie bazy danych po wykonaniu testow
     */
    @After
    public void clear() {
        database.query("DELETE FROM Badania WHERE opis LIKE '%-1.999%';");
        database.query("DELETE FROM Recepty WHERE nazwaLeku = 'test';");
        database.query("DELETE FROM Pacjenci WHERE imię = 'test';");
        database.query("DELETE FROM Pracownicy WHERE imię = 'test';");
    }
}
