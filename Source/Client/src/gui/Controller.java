package gui;

import app.Data;
import app.Log;
import app.Main;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Abstrakcyjny kontroler gui, zawiera metody, ktore moga byc wykorzystywane w wielu kontrolerach.
 */
public abstract class Controller {
    /**
     * Zmiana sceny.
     * @param button    Przycisk
     * @param title     Nazwa okna
     * @param fxml      Plik fxml zawierajacy scene
     * @param width     Szerokosc okna
     * @param height    Wysokosc okna
     * @param c         Klasa wywolujaca
     */
    public void changeScene(Button button, String title, String fxml, int width, int height, Class c) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setTitle(title);
        try {
            stage.setScene(new Scene(FXMLLoader.load(c.getResource(fxml)), width, height));
        } catch (IOException e) {
            Log.error("Błąd zmiany sceny");
            e.printStackTrace();
        }
        stage.show();
    }

    /**
     * Inicjalizacja comboboxa.
     * @param box               Combobox
     * @param data              Nazwa pola w tabeli
     * @param table             Nazwa tabeli z ktorej nalezy pobrac dane
     * @param condition         Nazwa pola warunkowego
     * @param conditionValue    Wartosc pola warunkowego
     */
    public void initCombobox(JFXComboBox box, String data, String table, String condition, String conditionValue) {
        Log.info("Wysylanie zadania do serwera: pobierz dane do comboboxa");
        Main.getServer().sendMessage(new Data(data,table,condition,conditionValue,true));
        List list = (List) Main.getServer().receiveMessage();
        for(Object test : list) {
            box.getItems().add(test);
        }
    }

    /**
     * Pobranie liczby tupu double z pola tekstowego.
     * @param textField Pole tekstowe
     * @return Pobrana wartosc typu double (0.0 w przypadku bledu)
     */
    public double getDouble(TextField textField) {
        double result;
        try {
            result = Double.parseDouble(textField.getText());
        }
        catch (Exception e) {
            result = 0.0;
        }
        return result;
    }

    /**
     * Pobranie liczby typu int z pola tekstowego.
     * @param textField Pole tekstowe
     * @return Pobrana wartosc typu int (0 w przypadku bledu)
     */
    public int getInt(TextField textField) {
        int result;
        try {
            result = Integer.parseInt(textField.getText());
        }
        catch (Exception e) {
            result = 0;
        }
        return result;
    }
}
