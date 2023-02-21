package gui;

import app.*;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Kotroler gui - widok tworzenia recepty.
 */
public class ControllerPrescription extends Controller {
    /**
     * Przycisk powortu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Przycisk akceptujacy dane recepty.
     */
    public JFXButton acceptButton;
    /**
     * Pole tekstowe - imie pacjenta.
     */
    public TextField patientName;
    /**
     * Pole tekstowe - nazwisko pacjenta.
     */
    public TextField patientSurname;
    /**
     * Pole tekstowe - e-mail pacjenta.
     */
    public TextField patientEmail;
    /**
     * Pole tekstowe - numer telefonu pacjenta.
     */
    public TextField patientPhoneNumber;
    /**
     * Pole tekstowe - nazwa leku.
     */
    public TextField medicineName;
    /**
     * Pole tekstowe - liczba sztuk przepisanego leku.
     */
    public TextField medicineAmount;
    /**
     * Przycisk dodajacy lek do recepty.
     */
    public JFXButton addPrescriptionButton;
    /**
     * Pole tekstowe - leki dodane do recepty.
     */
    public Label medicinesLabel;
    /**
     * Lista lekow dodanych do recepty.
     */
    ArrayList<Medicine> medicineList = new ArrayList<>();

    /**
     * Gdy nacisnieto przycisk powortu do poprzedniego ekranu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Menu","MainMenuScreen.fxml",960,540,this.getClass());
    }

    /**
     * Gdy nacisnieto przycisk akcpetujacy dane recepty.
     */
    public void acceptButtonPressed() {
        makePrescription();
        changeScene(backButton,"Menu","MainMenuScreen.fxml",960,540,this.getClass());
    }

    /**
     * Tworzenie recepty.
     */
    private void makePrescription() {
        String name = patientName.getText();
        String surname = patientSurname.getText();
        String email = patientEmail.getText();
        String phoneNumber = patientPhoneNumber.getText();
        Patient patient = new Patient(name, surname, email, phoneNumber);
        Prescription prescription = new Prescription(patient,medicineList);
        Log.info("Wysylanie recepty do serwera");
        Main.getServer().sendMessage(prescription);
        Main.getServer().receiveMessage();
    }

    /**
     * Gdy nacisnieto przycisk dodawania leku do recepty.
     */
    public void addPrescriptionButtonPressed() {
        String name = medicineName.getText();
        int amount = getInt(medicineAmount);
        if(!name.equals("") && amount > 0) {
            medicineList.add(new Medicine(name, amount));
            String text = "";
            for(Medicine obj:medicineList)
                text += obj.name + " " + obj.amount + "\n";
            medicinesLabel.setText(text);
        }
    }
}
