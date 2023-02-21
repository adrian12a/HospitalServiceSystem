package app;

import java.io.Serializable;

/**
 * Klasa ktorej obiekt klient wysyla do serwera gdy chce pobrac dane z bazy danych (np. do ComboBoxa).
 */
public class Data implements Serializable {
    /**
     * Nazwa pola w tabeli. (np. Nazwa)
     */
    String data;
    /**
     * Nazwa tabeli. (np. Badania)
     */
    String table;
    /**
     * Nazwa pola warunkowego (np. Uprawnienia)
     */
    String condition;
    /**
     * Wartosc pola warunkowego (np. Lekarz)
     */
    String conditionValue;
    /**
     * true - pobierz dane unikalne (bez powtorzen), false - pobierz wszystkie dane
     */
    boolean distinct;

    /**
     * Konstruktor
     * @param data              Nazwa pola w tabeli
     * @param table             Nazwa tabeli
     * @param condition         Nazwa pola warunkowego
     * @param conditionValue    Wartosc pola warunkowego
     * @param distinct          true - pobierz dane unikalne (bez powtorzen), false - pobierz wszystkie dane
     */
    public Data(String data, String table, String condition, String conditionValue, boolean distinct) {
        this.data = data;
        this.table = table;
        this.condition = condition;
        this.conditionValue = conditionValue;
        this.distinct = distinct;
    }
}
