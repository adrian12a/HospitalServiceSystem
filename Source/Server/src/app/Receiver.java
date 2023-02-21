package app;

/**
 * Interfejs implementowany w celu obsluzenia nowego rodzaju wiadomosci.
 */
public interface Receiver {
        /**
         * Oblsuzenie wiadomosci.
         * @param o             Wiadomosc
         * @param userId        Identyfikator uzytkownika od ktorego odebrano wiadomosc
         * @return Wiadomosc zwrotna do klienta
         */
        Object receive(Object o, int userId);
}
