package app;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * Budowniczy tabeli z danymi pacjentow (wzorzec Budowniczy).
 */
public class TreeTablePatientBuilder extends TreeTableBuilder {
    /**
     * Lista z danymi pacjentow.
     */
    ObservableList<PatientAdapter> patients = FXCollections.observableArrayList();

    /**
     * Konstruktor.
     * @param treeTable     Tabela
     * @param archive       Archiwum
     */
    public TreeTablePatientBuilder(JFXTreeTableView treeTable, Archive archive) {
        this.treeTable = treeTable;
        this.archive = archive;
        buildColumns();
        buildPatients();
    }

    /**
     * Budowanie kolumn
     */
    void buildColumns() {
        buildColumn("Id",50, new TreeItemPropertyValueFactory<PatientAdapter,String>("id"));
        buildColumn("Imię",200, new TreeItemPropertyValueFactory<PatientAdapter,String>("name"));
        buildColumn("Nazwisko",200, new TreeItemPropertyValueFactory<PatientAdapter,String>("surname"));
        buildColumn("E-mail",250, new TreeItemPropertyValueFactory<PatientAdapter,String>("email"));
        buildColumn("Numer telefonu",240, new TreeItemPropertyValueFactory<PatientAdapter,String>("phoneNumber"));
        treeTable.getColumns().addAll(columns);
    }

    /**
     * Budowanie danych (wypelnianie tabeli danymi pacjentow).
     */
    void buildPatients() {
        TreeItem<PatientAdapter> root = new RecursiveTreeItem<>(patients,RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        for(int i = 0; i < archive.records; i++) {
            patients.add(new PatientAdapter(archive.fields.get(i),archive.fields.get(i+archive.records),archive.fields.get(i+archive.records*2),archive.fields.get(i+archive.records*3),archive.fields.get(i+archive.records*4)));
        }
    }
}
