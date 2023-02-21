package app;

import java.io.Serializable;
import java.util.List;

/**
 * Klasa sluzaca do przekazania danych z bazy danych do klienta w celu wyswietlenia ich w archiwum.
 */
public class Archive implements Serializable {
    /**
     * Nazwa tabeli w bazie.
     */
    public String table;
    /**
     * Wartosci pol w tabeli.
     */
    public List<String> fields;
    /**
     * Liczba rekordow w tabeli.
     */
    public int records;

    /**
     * Konstruktor
     * @param table Nazwa tabeli
     */
    public Archive(String table) {
        this.table = table;
    }
}
