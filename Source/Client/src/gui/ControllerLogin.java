package gui;

import app.Account;
import app.Log;
import app.Main;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Kontroler gui - ekran logowania.
 */
public class ControllerLogin extends Controller {
    /**
     * Dodatkowe informacje.
     */
    public Label info;
    /**
     * Pole tekstowe - login uzytkownika.
     */
    public TextField login;
    /**
     * Pole tekstowe - haslo uzytkownika.
     */
    public PasswordField password;
    /**
     * Przycisk sluzacy do zalogowania.
     */
    public JFXButton loginButton;
    /**
     * Przycisk przenoszacy do ekranu rejestracji.
     */
    public Button registerButton;

    /**
     * Gdy nacisnieto przycisk logowania.
     */
    public void loginButtonPressed() {
        Account account = new Account(login.getText(),password.getText());
        String response = login(account);
        if(response.equals("Lekarz") || response.equals("Pielęgniarka")) {
            Main.setAccountType(response);
            changeScene(loginButton, "Menu", "MainMenuScreen.fxml", 960, 540, this.getClass());
        }
        else
            info.setText("Wprowadzono błędne dane");
    }

    /**
     * Gdy nacisnieto przycisk rejestracji.
     */
    public void registerButtonPressed() {
        changeScene(registerButton,"Rejestracja","RegisterScreen.fxml",960,540,this.getClass());
    }

    /**
     * Logowanie.
     * @param a Konto uzytkownika.
     * @return Wynik logowanie (true - udalo sie zalogowac, false - nie udalo sie zalogowac)
     */
    String login(Account a) {
        Log.info("Wysylanie zadania logowania do serwera");
        Main.getServer().sendMessage(a);
        return (String) Main.getServer().receiveMessage();
    }
}
