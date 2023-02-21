package gui;

import app.Patient;
import app.PressureTestFactory;
import app.TestFactory;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

/**
 * Kontroler gui - widok pomiaru cisnienia.
 */
public class ControllerPressureTest extends ControllerTestTemplate {
    /**
     * Przycisk powortu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Przycisk akceptujacy dane badania.
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
     * Pole tekstowe - cisnienie skurczowe.
     */
    public TextField systolicPressure;
    /**
     * Pole tekstowe - cisnienie rozkurczowe.
     */
    public TextField diastolicPressure;
    /**
     * Pole tekstowe - puls.
     */
    public TextField pulse;

    /**
     * Tworzenie obiektu pacjenta (pobieranie danych pacjenta z gui).
     */
    @Override
    void makePatient() {
        String name = patientName.getText();
        String surname = patientSurname.getText();
        String email = patientEmail.getText();
        String phoneNumber = patientPhoneNumber.getText();
        patient = new Patient(name, surname, email, phoneNumber);
    }

    /**
     * Tworzenie badania (pobieranie danych badania z gui).
     * @param patient Pacjent
     */
    @Override
    void makeTest(Patient patient) {
        double systolicPressureValue = getDouble(systolicPressure);
        double diastolicPressureValue = getDouble(diastolicPressure);
        double pulseValue = getDouble(pulse);
        TestFactory factory =  new PressureTestFactory(patient,systolicPressureValue,diastolicPressureValue,pulseValue);
        test = factory.makeTest();
    }

    /**
     * Gdy nacisnieto przycisk powrotu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Badania","ChooseTestScreen.fxml",960,540,this.getClass());
    }

    /**
     * Gdy nacisnieto przycisk akceptujacy dane badania.
     */
    public void acceptButtonPressed() {
        acceptTest();
        changeScene(backButton,"Badania","ChooseTestScreen.fxml",960,540,this.getClass());
    }
}
