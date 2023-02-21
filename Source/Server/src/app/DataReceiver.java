package app;

import java.util.List;

/**
 * Klasa obslugujaca wiadomosci od klienta zawierajace obiekty klasy Archive.
 */
public class DataReceiver implements Receiver {

    /**
     * Obsluz wiadomosc.
     * @param o         Wiadomosc
     * @param userId    Identyfikator uzytkownika od ktorego odebrano wiadomosc
     * @return Lista zawierajaca dane pobrane z bazy danych (null w przypadku niepowodzenia)
     */
    @Override
    public Object receive(Object o, int userId) {
        if(o instanceof Data) {
            List<String> result;
            String input = "SELECT " + ((Data) o).data + " FROM " + ((Data) o).table + " WHERE " + ((Data) o).condition + " LIKE '%" + ((Data) o).conditionValue + "%';";
            result = Main.getDatabase().selectList(input,((Data) o).data);
            return result;
        }
        else
            return null;
    }
}
