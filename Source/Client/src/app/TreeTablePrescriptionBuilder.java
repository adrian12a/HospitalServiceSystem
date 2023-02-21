package app;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * Budowniczy tabeli z danymi recept (wzorzec Budowniczy).
 */
public class TreeTablePrescriptionBuilder extends TreeTableBuilder {
    /**
     * Lista recept.
     */
    ObservableList<PrescriptionAdapter> prescriptions = FXCollections.observableArrayList();

    /**
     * Konstruktor.
     * @param treeTable Tabela
     * @param archive   Archiwum
     */
    public TreeTablePrescriptionBuilder(JFXTreeTableView treeTable, Archive archive) {
        this.treeTable = treeTable;
        this.archive = archive;
        buildColumns();
        buildTests();
    }

    /**
     * Budowanie kolumn.
     */
    void buildColumns() {
        buildColumn("Id",50, new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("id"));
        buildColumn("Imię pacjenta",200, new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("patientName"));
        buildColumn("Nazwisko Pacjenta",200, new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("patientSurname"));
        buildColumn("Data",200, new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("date"));
        buildColumn("Nazwa leku",200,new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("medicineName"));
        buildColumn("Ilość leku",200,new TreeItemPropertyValueFactory<PrescriptionAdapter,String>("medicineAmount"));
        treeTable.getColumns().addAll(columns);
    }

    /**
     * Budowanie danych (wypelnianie tabeli danymi recept).
     */
    void buildTests() {
        TreeItem<PrescriptionAdapter> root = new RecursiveTreeItem<>(prescriptions, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        for (int i = 0; i < archive.records; i++) {
            prescriptions.add(new PrescriptionAdapter(archive.fields.get(i), archive.fields.get(i + archive.records), archive.fields.get(i + archive.records * 2),
                    archive.fields.get(i + archive.records * 3), archive.fields.get(i + archive.records * 4), archive.fields.get(i + archive.records * 5)));
        }
    }
}
