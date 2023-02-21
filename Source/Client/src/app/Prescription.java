package app;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Klasa ktorej pojedynczy obiekt reprezentuje recepte.
 */
public class Prescription implements Serializable {
    /**
     * Pacjent, ktoremu przepisano recepte.
     */
    Patient patient;
    /**
     * Lista lekow przepisanych w recepcie.
     */
    ArrayList<Medicine> medicines;
    /**
     * Data wystawienia recepty.
     */
    LocalDateTime date;

    /**
     * Konstruktor
     * @param patient       Pacjent, ktoremu przepisano recepte
     * @param medicines     Lista lekow przepisanych w recepcie
     */
    public Prescription(Patient patient, ArrayList<Medicine> medicines) {
        this.patient = patient;
        this.medicines = medicines;
        date = LocalDateTime.now();
    }
}
