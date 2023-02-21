package gui;

import app.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Kontroler gui - widok tabeli w archiwum.
 */
public class ControllerBase extends Controller implements Initializable {
    /**
     * Przycisk powrotu do poprzedniego ekranu.
     */
    public JFXButton backButton;
    /**
     * Tabela.
     */
    public JFXTreeTableView treeTable;
    /**
     * Wybrana tabela (np. tabela pracownikow, tabela pacjentow).
     */
    public static String option;

    /**
     * Gdy nacisnieto przycisk powrotu.
     */
    public void backButtonPressed() {
        changeScene(backButton,"Bazy danych","ChooseBaseScreen.fxml",960,540,this.getClass());
    }

    /**
     * Inicjalizacja tabeli.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Archive data = null;
        switch(option) {
            case "Baza pacjentów":
                Log.info("Wysylanie zadania pobrania danych z bazy pacjentow do serwera");
                data = new Archive("Patient");
                break;
            case "Baza pracowników":
                Log.info("Wysylanie zadania pobrania danych z bazy pracownikow do serwera");
                data = new Archive("Employee");
                break;
            case "Baza przeprowadzonych badań":
                Log.info("Wysylanie zadania pobrania danych z bazy przeprowadzonych badan do serwera");
                data = new Archive("Test");
                break;
            case "Baza recept":
                Log.info("Wysylanie zadania pobrania danych z bazy recept do serwera");
                data = new Archive("Prescription");
                break;
        }
        if(data != null) {
            Main.getServer().sendMessage(data);
            data = (Archive) Main.getServer().receiveMessage();
        }

        switch(option) {
            case "Baza pacjentów":
                TreeTablePatientBuilder patientBuilder = new TreeTablePatientBuilder(treeTable, data);
                treeTable = patientBuilder.getResult();
                break;
            case "Baza pracowników":
                TreeTableEmployeeBuilder employeeBuilder = new TreeTableEmployeeBuilder(treeTable, data);
                treeTable = employeeBuilder.getResult();
                break;
            case "Baza przeprowadzonych badań":
                TreeTableTestBuilder testBuilder = new TreeTableTestBuilder(treeTable, data);
                treeTable = testBuilder.getResult();
                break;
            case "Baza recept":
                TreeTablePrescriptionBuilder prescriptionBuilder = new TreeTablePrescriptionBuilder(treeTable, data);
                treeTable = prescriptionBuilder.getResult();
                break;
        }
    }
}
