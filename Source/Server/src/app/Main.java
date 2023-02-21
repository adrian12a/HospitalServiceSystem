package app;

import gui.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa glowna, uruchamia serwer oraz tworzy okno gui.
 */
public class Main extends Application {
    /**
     * Egzekutor zarzadzajacy watkami klientow.
     */
    static ExecutorService executor = Executors.newFixedThreadPool(8);
    /**
     * Polaczenie z baza danych.
     */
    static DatabaseConnection database = new DatabaseConnection("jdbc:mariadb://localhost:3306/szpital", "admin", "admin");
    /**
     * Kontroler gui.
     */
    static MainController controller;

    /**
     * Pobranie instancji polaczenia z baza danych.
     * @return Polaczenie z baza danych
     */
    public static DatabaseConnection getDatabase() {
        return database;
    }

    /**
     * Pobranie egzekutora zarzadzajacego watkami klientow.
     * @return  Egzekutor
     */
    public static ExecutorService getExecutor() {
        return executor;
    }

    /**
     * Pobranie kontrolera gui.
     * @return Kontroler
     */
    public static MainController getController() {
        return controller;
    }

    /**
     * Uruchomienie gui.
     * @param stage Scena
     * @throws Exception W przypadku gdy nie uda sie stworzyc gui
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainScreen.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        stage.setTitle("Serwer");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * Uruchomienie serwera.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
