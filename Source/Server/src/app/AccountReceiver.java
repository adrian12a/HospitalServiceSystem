package app;

/**
 * Klasa obslugujaca wiadomosci od klienta zawierajace obiekty klasy Account.
 */
public class AccountReceiver implements Receiver {

    /**
     * Obsluz wiadomosc.
     * @param o         Wiadomosc
     * @param userId    Identyfikator uzytkownika od ktorego odebrano wiadomosc
     * @return Identyfikator uzytkownika
     */
    @Override
    public Object receive(Object o, int userId) {
        int result = -1;
        if(o instanceof Account) {
            if(((Account) o).wantRegister()) {
                result = register((Account) o);
            }
            else {
                result = login((Account) o);
            }
        }
        return result;
    }

    /**
     * Logowanie uzytkownika - sprawdzenie czy dane konta zgadzaja sie z danymi w bazie danych.
     * @param a Wiadomosc
     * @return  Identyfikator uzytkownika lub -1 w przypadku niepowodzenia
     */
    public int login(Account a) {
        String input = "SELECT id FROM Pracownicy WHERE login = '" + a.login + "' AND hasło = '" + a.password + "';";
        String result = Main.getDatabase().selectOne(input,"id");
        if(result != null)
            return Integer.parseInt(result);
        else
            return -1;
    }

    /**
     * Rejestracja uzytkownika - dodanie konta do bazy danych.
     * @param a Wiadomosc
     * @return Identyfikator uzytkownika lub -1 w przypadku niepowodzenia
     */
    public int register(Account a) {
        int id = Main.getDatabase().getNextId("Pracownicy");
        String input = "INSERT INTO Pracownicy VALUES (" + id + ",'" + a.name + "','" + a.surname + "','" + a.login + "','" + a.password + "','" + a.email + "','" + a.type + "');";
        if(Main.getDatabase().query(input))
            return id;
        else
            return -1;
    }
}
