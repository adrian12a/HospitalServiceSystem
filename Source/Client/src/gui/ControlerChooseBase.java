package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler gui - widok wyboru bazy danych w archiwum.
 */
public class ControlerChooseBase extends Controller implements Initializable {
    /**
     * Przycisk powrotu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Pole wyboru bazy danych.
     */
    public JFXComboBox chooseBase;
    /**
     * Przycisk akceptujacy wybor.
     */
    public JFXButton acceptButton;
    /**
     * Dodatkowe informacje.
     */
    public Label info;

    /**
     * Gdy nacisnieto przycisk powrotu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Menu","MainMenuScreen.fxml",960,540,this.getClass());
    }

    /**
     * Gdy nacisnieto przycisk akcepttujacy wybor.
     */
    public void acceptButtonPressed() {
        if(chooseBase.getValue() != null) {
            ControllerBase.option = chooseBase.getValue().toString();
            changeScene(acceptButton, "Bazy danych", "BaseScreen.fxml", 960, 540, this.getClass());
        }
        else
            info.setVisible(true);
    }

    /**
     * Inicjalizacja comboboxa.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseBase.getItems().add("Baza pacjentów");
        chooseBase.getItems().add("Baza pracowników");
        chooseBase.getItems().add("Baza recept");
        chooseBase.getItems().add("Baza przeprowadzonych badań");
    }
}
