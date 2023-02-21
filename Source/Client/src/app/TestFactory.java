package app;

/**
 * Interfejs ktorego klasu implementuja w celu stworzenia obiektow badan roznych rodzajow (wzorzec Metoda wytworcza).
 */
public interface TestFactory {
    /**
     * Utworz badanie.
     * @return Badanie
     */
    Test makeTest();
}
