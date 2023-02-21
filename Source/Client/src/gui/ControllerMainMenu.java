package gui;

import app.Main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler gui - widok glownego menu.
 */
public class ControllerMainMenu extends Controller implements Initializable {
    /**
     * Przycisk powrotu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Przycisk przenoszacy do ekranu tworzenia badan.
     */
    public JFXButton testButton;
    /**
     * Przycisk przenoszacy do ekranu wyswieltania archiwum.
     */
    public JFXButton baseButton;
    /**
     * Przycisk przenoszacy do ekranu tworzenia recept.
     */
    public JFXButton prescriptionButton;

    /**
     * Gdy nacisnieto przycisk powrotu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Logowanie","LoginScreen.fxml",300,400,this.getClass());
    }

    /**
     * Gdy nacisnieto przycisk badania.
     */
    public void testButtonPressed() {
        changeScene(testButton,"Badania","ChooseTestScreen.fxml",960,540,this.getClass());
    }

    /**
     * Gdy nacisnieto przycisk archiwum.
     */
    public void baseButtonPressed() {
        changeScene(testButton,"Bazy danych","ChooseBaseScreen.fxml",960,540,this.getClass());
    }

    /**
     * Inicjalizacja menu (w menu dostepne sa inne opcje dla Lekarza i Pielegniarki).
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.getAccountType().equals("Pielęgniarka")) {
            prescriptionButton.setVisible(false);
        }
        if(Main.getAccountType().equals("Lekarz")) {
            prescriptionButton.setVisible(true);
        }
    }

    /**
     * Gdy niacisnieto przycisk recepty.
     */
    public void prescriptionButtonPressed() {
        changeScene(prescriptionButton,"Recepty","PrescriptionScreen.fxml",960,540,this.getClass());
    }
}
