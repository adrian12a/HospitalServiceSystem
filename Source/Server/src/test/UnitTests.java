package test;

import app.DatabaseConnection;
import app.Main;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testy jednostkowe aplikacji serwera
 */
public class UnitTests {
    /**
     * Polaczenie z baza danych
     */
    DatabaseConnection database = Main.getDatabase();

    /**
     * Testowanie polaczenia z baza danych
     */
    @Test
    public void databaseConnection() {
        assertTrue(database.query("SELECT * FROM PACJENCI;"));
    }

    /**
     * Test metody pobierajacej nastepny wolny identyfikator z tabeli w bazie danych
     */
    @Test
    public void testNextId() {
        int value = database.getNextId("Pacjenci");
        int desiredValue = Integer.parseInt(database.selectOne("SELECT max(id) FROM Pacjenci","max(id)")) + 1;
        assertEquals(desiredValue,value);
    }
}
