package app;

import javafx.scene.paint.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Polaczenie z baza danych.
 */
public class DatabaseConnection {
    /**
     * Polaczenie z baza danych.
     */
    private Connection database;
    /**
     * Adres bazy danych.
     */
    final private String url;
    /**
     * Login do bazy danych.
     */
    final private String user;
    /**
     * Haslo do bazy danych.
     */
    final private String password;

    /**
     * Konstruktor
     * @param url       Adres bazy danych
     * @param user      Login do bazy danych
     * @param password  Haslo do bazy danych
     */
    DatabaseConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Polaczenie z baza.
     */
    public void connect() {
        try {
            database = DriverManager.getConnection(url,user,password);
            Log.info("Pomyślnie połączono z bazą danych");
            if(Main.getController() != null)
                Main.getController().databaseStatus("Połączono", Color.GREEN);
        } catch (SQLException throwables) {
            Log.error("Błąd łączenia z bazą danych");
            if(Main.getController() != null)
                Main.getController().databaseStatus("Wystąpił błąd", Color.RED);
            throwables.printStackTrace();
        }
    }

    /**
     * Zakonczenie polaczenia z baza.
     */
    public void disconnect() {
        try {
            database.close();
            if(Main.getController() != null)
                Main.getController().databaseStatus("Rozłączono", Color.ORANGE);
        } catch (SQLException e) {
            Log.error("Błąd zamykania polaczenia z bazą danych");
            if(Main.getController() != null)
                Main.getController().databaseStatus("Wystąpił błąd", Color.RED);
            e.printStackTrace();
        }
    }

    /**
     * Sprawdzenie polaczenia z baza, proba polaczenia z baza w przypadku braku polaczenia.
     */
    public void checkConnection() {
        if(database == null)
            connect();
    }

    /**
     * Pobranie pojedynczej wartosci z bazy danych.
     * @param input     Zapytanie do bazy danych
     * @param output    Nazwa pola w tabeli ktorego dane nalezy pobranc z bazy danych
     * @return  Pobrane dane
     */
    public String selectOne(String input, String output) {
        checkConnection();
        try {
            Statement statement = database.createStatement();
            ResultSet result = statement.executeQuery(input);
            if (!result.next())
                return null;
            else
                return result.getString(output);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Pobranie danych z bazy danych w formie listy
     * @param input     Zapytanie do bazy danych
     * @param output    Nazwa pola w tabeli ktorego dane nalezy pobranc z bazy danych
     * @return  Pobrane dane
     */
    public List<String> selectList(String input, String output) {
        checkConnection();
        try {
            Statement statement = database.createStatement();
            ResultSet result = statement.executeQuery(input);
            List<String> list = new ArrayList<>();
            while(result.next())
                list.add(result.getString(output));
            return list;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Pobranie nastepnego wolnego id w tabeli.
     * @param table Nazwa tabeli
     * @return Wolne id
     */
    public int getNextId(String table) {
        int result = 1;
        String input = "SELECT max(id) FROM " + table + ";";
        String output = selectOne(input,"max(id)");
        if(output != null)
            result = Integer.parseInt(output) + 1;
        return result;
    }

    /**
     * Wyslanie zapytania do bazy danych.
     * @param input     Zapytanie do bazy danych
     * @return true - w przypadku powodzeniea, false - w przypadku niepowodzenia
     */
    public boolean query(String input) {
        checkConnection();
        try {
            Statement statement = database.createStatement();
            statement.executeQuery(input);
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
