package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Klasa glowna.
 */
public class Main extends Application {
    /**
     * Polaczenie z serwerem.
     */
    static ServerConnection server = new ServerConnection("localhost",44444);
    /**
     * Typ konta (jesli uzytkownik sie zalogowal).
     */
    static String accountType;

    /**
     * Pobranie polaczenia z serwerem.
     * @return Istancja polaczenia z serwerem.
     */
    public static ServerConnection getServer() {
        return server;
    }

    /**
     * Ustawienie typu konta uzytkownika.
     * @param type  Typ konta
     */
    public static void setAccountType(String type) { accountType = type; }

    /**
     * Pobranie typu konta uzytkownika.
     * @return Typ konta
     */
    public static String getAccountType() { return accountType; }

    /**
     * Uruchomienie gui.
     * @param stage Scena
     * @throws Exception W przypadku gdy nie uda sie stworzyc gui
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent login = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../gui/LoginScreen.fxml")));
        stage.setTitle("Logowanie");
        stage.setScene(new Scene(login, 300, 400));
        stage.show();
    }

    /**
     * Uruchomienie klienta.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        server.disconnect();
    }

}
