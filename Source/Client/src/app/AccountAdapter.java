package app;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

/**
 * Klasa adaptujaca obiekty klasy Account tak, aby mozna ja bylo wyswietlic w formie tabeli (wzorzec Adapter).
 */
public class AccountAdapter extends RecursiveTreeObject<AccountAdapter> {
    /**
     * Id konta uzytkownika.
     */
    private String id;
    /**
     * Obiekt klasy Account do adaptacji.
     */
    private Account account;

    /**
     * Konstruktor.
     * @param id        Id konta.
     * @param name      Imie uzytkownika.
     * @param surname   Nazwisko uzytkownika.
     * @param email     E-mail uzytkownika.
     * @param type      Typ konta.
     */
    public AccountAdapter(String id, String name, String surname, String email, String type) {
        this.id = id;
        account = new Account(name,surname,"","",email,type);
    }

    /**
     * Pobranie id konta.
     * @return Id konta
     */
    public String getId() {
        return id;
    }

    /**
     * Pobranie imienia uzytkownika.
     * @return Imie uzytkownika
     */
    public String getName() {
        return account.name;
    }

    /**
     * Pobranie nazwiska uzytkownika.
     * @return Nazwisko uzytkownika
     */
    public String getSurname() {
        return account.surname;
    }

    /**
     * Pobranie e-maila uzytkownika.
     * @return E-mail uzytkownika
     */
    public String getEmail() {
        return account.email;
    }

    /**
     * Pobranie typu konta.
     * @return Typ konta
     */
    public String getType() {
        return account.type;
    }
}
