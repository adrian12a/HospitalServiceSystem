package test;

import app.*;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testy integracyjne aplikacji klienta
 */
public class IntegrationTests {

    /**
     * Testowanie rejestracji i logowania konta uzytkownika
     */
    @Test
    public void accountTesting() {
        assertEquals("test",accountRegister());
        assertEquals("test",accountLogin());
    }

    /**
     * Testowanie rejestracji
     */
    public String accountRegister() {
        Account register = new Account("test","test","test","test","test","test");
        Main.getServer().sendMessage(register);
        return (String) Main.getServer().receiveMessage();
    }

    /**
     * Testowanie logowania
     */
    public String accountLogin() {
        Account login = new Account("test","test");
        Main.getServer().sendMessage(login);
        return (String) Main.getServer().receiveMessage();
    }

    /**
     * Testowanie pobierania listy badan lekarza
     */
    @Test
    public void doctorTestList() {
        List<String> desiredAnswer = new ArrayList<>();
        desiredAnswer.add("Badanie wzroku");
        desiredAnswer.add("Pomiar ciśnienia");
        Main.getServer().sendMessage(new Data("nazwa","TypyBadań","Uprawnienia","Lekarz",true));
        List result = (List) Main.getServer().receiveMessage();
        assertArrayEquals(result.toArray(),desiredAnswer.toArray());
        for(Object i: result) {
            System.out.println(i);
        }
    }

    /**
     * Testowanie pobierania listy badan pielegniarki
     */
    @Test
    public void nurseTestList() {
        List<String> desiredAnswer = new ArrayList<>();
        desiredAnswer.add("Pomiar ciśnienia");
        Main.getServer().sendMessage(new Data("nazwa","TypyBadań","Uprawnienia","Pielegniarka",true));
        List result = (List) Main.getServer().receiveMessage();
        assertArrayEquals(result.toArray(),desiredAnswer.toArray());
        for(Object i: result) {
            System.out.println(i);
        }
    }

    /**
     * Testowanie wysylania i odbierania wiadomosci z serwera (Niezidentyfikowana wiadomosc)
     */
    @Test
    public void serverUndefiniedMessage() {
        Main.getServer().sendMessage(1);
        assertNull(Main.getServer().receiveMessage());
    }

    /**
     * Testowanie tworzenia roznych typow badan
     */
    @Test
    public void testFactory() {
        Patient patient = new Patient("test","test","test","test");

        TestFactory factory = new EyeTestFactory(patient, -1.999, 1, 1, 1, true, true);
        app.Test test = factory.makeTest();
        assertEquals("Badanie wzroku",test.type);

        factory = new PressureTestFactory(patient,-1.999,1.0,1.0);
        test = factory.makeTest();
        assertEquals("Pomiar ciśnienia",test.type);
    }

    /**
     * Testowanie wysylania i odbierania wiadomosci z serwera (Archiwum)
     */
    @Test
    public void serverArchive() {
        Archive archive = new Archive("test");
        Main.getServer().sendMessage(archive);
        archive = (Archive) Main.getServer().receiveMessage();
        assertNull(archive.fields);

        archive = new Archive("Patient");
        Main.getServer().sendMessage(archive);
        archive = (Archive) Main.getServer().receiveMessage();

        assertNotNull(archive.fields);
        assertEquals("Patient",archive.table);

        for(int i = 0; i < archive.records; i++)
            System.out.println(archive.fields.get(i) + " " + archive.fields.get(i+archive.records));
    }

    /**
     * Testowanie wysylania i odbierania wiadomosci z serwera (Recepta)
     */
    @Test
    public void serverPrescription() {
        Patient patient = new Patient("test", "test", "test", "test");
        ArrayList<Medicine> medicines = new ArrayList<>();
        medicines.add(new Medicine("test",1));
        medicines.add(new Medicine("test",2));
        medicines.add(new Medicine("test",3));
        Prescription prescription = new Prescription(patient,medicines);
        Main.getServer().sendMessage(prescription);
        assertEquals(true,Main.getServer().receiveMessage());
    }

    /**
     * Czyszczenie bazy danych po wykonaniu testow
     */
    @After
    public void clear() throws SQLException {
        Connection database = DriverManager.getConnection("jdbc:mariadb://localhost:3306/szpital", "admin", "admin");
        Statement statement = database.createStatement();
        statement.executeQuery("DELETE FROM Badania WHERE opis LIKE '%-1.999%';");
        statement.executeQuery("DELETE FROM Recepty WHERE nazwaLeku = 'test';");
        statement.executeQuery("DELETE FROM Pacjenci WHERE imię = 'test';");
        statement.executeQuery("DELETE FROM Pracownicy WHERE imię = 'test';");
        database.close();
    }
}
