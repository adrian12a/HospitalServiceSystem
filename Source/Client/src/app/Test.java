package app;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Klasa ktorej pojedynczy obiekt reprezentuje badanie.
 */
public class Test implements Serializable {
    /**
     * Pacjent, na ktorym przeprowadzono badanie.
     */
    public Patient patient;
    /**
     * Data wykonania badania,
     */
    public LocalDateTime date;
    /**
     * Opis badania.
     */
    public String description;
    /**
     * Typ badania.
     */
    public String type;

    /**
     * Konstruktor.
     * @param patient       Pacjent, na ktorym przeprowadzono badanie
     * @param description   Opis badania
     * @param type          Typ badania
     */
    Test(Patient patient, String description, String type) {
        this.patient = patient;
        this.description = description;
        this.type = type;
        date = LocalDateTime.now();
    }
}
