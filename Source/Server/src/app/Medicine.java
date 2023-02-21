package app;

import java.io.Serializable;

/**
 * Klasa ktorej pojedynczy obiekt reprezentuje lek.
 */
public class Medicine implements Serializable {
    /**
     * Nazwa leku.
     */
    public String name;
    /**
     * Ilosc sztuk leku w opakowaniu.
     */
    public int amount;

    /**
     * Konstruktor.
     * @param name      Nazwa leku
     * @param amount    Ilosc sztuk leku w opakowaniu
     */
    public Medicine(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
}
