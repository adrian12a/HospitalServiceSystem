package app;

import java.util.ArrayList;

/**
 * Klasa obslugujaca wiadomosci od klienta zawierajace obiekty klasy Archive.
 */
public class ArchiveReceiver implements Receiver {
    /**
     * Obsluz wiadomosc.
     * @param o         Wiadomosc
     * @param userId    Identyfikator uzytkownika od ktorego odebrano wiadomosc
     * @return Obiekt klasy Archive zawierajacy dane pobrane z bazy danych
     */
    @Override
    public Object receive(Object o, int userId) {
        Archive result = (Archive) o;
        switch(result.table) {
            case "Patient":
                result.fields = new ArrayList<>();
                result.fields.addAll(Main.getDatabase().selectList("SELECT id FROM pacjenci","id"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT imię FROM pacjenci","imię"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT nazwisko FROM pacjenci","nazwisko"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT email FROM pacjenci","email"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT telefon FROM pacjenci","telefon"));
                result.records = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM pacjenci","COUNT(*)"));
                break;
            case "Employee":
                result.fields = new ArrayList<>();
                result.fields.addAll(Main.getDatabase().selectList("SELECT id FROM pracownicy","id"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT imię FROM pracownicy","imię"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT nazwisko FROM pracownicy","nazwisko"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT email FROM pracownicy","email"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT typ FROM pracownicy","typ"));
                result.records = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM pracownicy","COUNT(*)"));
                break;
            case "Test":
                result.fields = new ArrayList<>();
                result.fields.addAll(Main.getDatabase().selectList("SELECT id FROM badania","id"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.imię FROM Badania b JOIN Pracownicy P on b.idPracownika = P.id","imię"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.nazwisko FROM Badania b JOIN Pracownicy P on b.idPracownika = P.id","nazwisko"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.imię FROM Badania b JOIN Pacjenci P on b.idPacjenta= P.id","imię"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.nazwisko FROM Badania b JOIN Pacjenci P on b.idPacjenta= P.id","nazwisko"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT t.nazwa FROM Badania b JOIN TypyBadań t on b.idBadania = t.id","nazwa"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT data FROM badania","data"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT opis FROM badania","opis"));
                result.records = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM badania","COUNT(*)"));
                break;
            case "Prescription":
                result.fields = new ArrayList<>();
                result.fields.addAll(Main.getDatabase().selectList("SELECT id FROM recepty","id"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.imię FROM recepty b JOIN pacjenci P on b.idPacjenta= P.id","imię"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT p.nazwisko FROM recepty b JOIN pacjenci P on b.idPacjenta= P.id","nazwisko"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT data FROM recepty","data"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT nazwaLeku FROM recepty","nazwaLeku"));
                result.fields.addAll(Main.getDatabase().selectList("SELECT ilośćLeku FROM recepty","ilośćLeku"));
                result.records = Integer.parseInt(Main.getDatabase().selectOne("SELECT COUNT(*) FROM recepty","COUNT(*)"));
                break;
        }
        return result;
    }
}
