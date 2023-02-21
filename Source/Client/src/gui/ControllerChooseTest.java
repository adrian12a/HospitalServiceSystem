package gui;

import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler gui - widok wyboru rodzaju badania.
 */
public class ControllerChooseTest extends Controller implements Initializable {
    /**
     * Przycisk powrotu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Pole wyboru typu badania.
     */
    public JFXComboBox chooseTest;
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
     * Gdy nacisnieto przycisk akceptujacy wybor.
     */
    public void acceptButtonPressed() {
        if(chooseTest.getValue() != null) {
            if (chooseTest.getValue().toString().equals("Badanie wzroku"))
                changeScene(acceptButton, "Badanie wzroku", "EyeTestScreen.fxml", 960, 540, this.getClass());
            if (chooseTest.getValue().toString().equals("Pomiar ciśnienia"))
                changeScene(acceptButton, "Pomiar ciśnienia", "PressureTestScreen.fxml", 960, 540, this.getClass());
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
        initCombobox(chooseTest,"nazwa","TypyBadań","Uprawnienia", Main.getAccountType());
    }
}
