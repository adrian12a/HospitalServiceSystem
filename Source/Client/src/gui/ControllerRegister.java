package gui;

import app.Account;
import app.Log;
import app.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Kontroler gui - ekran rejestracji uzytkownika.
 */
public class ControllerRegister extends Controller {
    /**
     * Pole tekstowe - login uzytkownika.
     */
    public TextField login;
    /**
     * Pole tekstowe - e-mail uzytkownika.
     */
    public TextField email;
    /**
     * Pole tekstowe - haslo uzytkownika.
     */
    public PasswordField password;
    /**
     * Przycisk radiowy - typ konta (Lekarz).
     */
    public JFXRadioButton radioDoctor;
    /**
     * Przycisk radiowy - typ konta (Pielegniarka).
     */
    public JFXRadioButton radioNurse;
    /**
     * Przycisk rejestrujacy uzytkownika.
     */
    public JFXButton registerButton;
    /**
     * Pole tekstowe - dodatkowe informacje.
     */
    public Label registerInfo;
    /**
     * Przycisk powrotu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Pole tekstowe - imie uzytkownika.
     */
    public TextField surname;
    /**
     * Pole tekstowe - nazwisko uzytkownika.
     */
    public TextField name;
    /**
     * Typ konta.
     */
    public String type = "Lekarz";

    /**
     * Gdy zostanie wybrany przycisk radiowy "Lekarz".
     */
    public void radioDoctorAction() {
        radioDoctor.setSelected(true);
        radioNurse.setSelected(false);
        type = "Lekarz";
    }

    /**
     * Gdy zostanie wybrany przycisk radiowy "Pielegniarka".
     */
    public void radioNurseAction() {
        radioNurse.setSelected(true);
        radioDoctor.setSelected(false);
        type = "Pielęgniarka";
    }

    /**
     * Gdy nacisnieto przycisk rejestrujacy uzytkownika.
     */
    public void registerButtonPressed() {
        Account account = new Account(name.getText(),surname.getText(),login.getText(),password.getText(),email.getText(),type);
        String response = register(account);
        if(response.equals("Lekarz") || response.equals("Pielęgniarka")) {
            Main.setAccountType(response);
            changeScene(registerButton, "Menu", "MainMenuScreen.fxml", 960, 540, this.getClass());
        }
        else
            registerInfo.setText("Wprowadzono błędne dane");
    }

    /**
     * Gdy nacisnieto przycisk powrotu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Logowanie","LoginScreen.fxml",300,400,this.getClass());
    }

    /**
     * Rejestracja konta uzytkownika.
     * @param a Konto]
     * @return Typ konta, jesli udalo sie stworzyc konto.
     */
    String register(Account a) {
        Log.info("Wysylanie zadania rejestracji do serwera");
        Main.getServer().sendMessage(a);
        return (String) Main.getServer().receiveMessage();
    }
}
