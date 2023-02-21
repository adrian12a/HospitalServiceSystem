package app;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * Budowniczy tabeli z danymi badan (wzorzec Budowniczy).
 */
public class TreeTableTestBuilder extends TreeTableBuilder {
    /**
     * Lista badan.
     */
    ObservableList<TestAdapter> tests = FXCollections.observableArrayList();

    /**
     * Konstruktor.
     * @param treeTable Tabela
     * @param archive   Archiwum
     */
    public TreeTableTestBuilder(JFXTreeTableView treeTable, Archive archive) {
        this.treeTable = treeTable;
        this.archive = archive;
        buildColumns();
        buildTests();
    }

    /**
     * Budowanie kolumn.
     */
    void buildColumns() {
        buildColumn("Id",50, new TreeItemPropertyValueFactory<AccountAdapter,String>("id"));
        buildColumn("Imię lekarza",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("doctorName"));
        buildColumn("Nazwisko lekarza",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("doctorSurname"));
        buildColumn("Imię pacjenta",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("patientName"));
        buildColumn("Nazwisko Pacjenta",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("patientSurname"));
        buildColumn("Typ badania",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("type"));
        buildColumn("Data",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("date"));
        buildColumn("Opis",1000,new TreeItemPropertyValueFactory<AccountAdapter,String>("description"));
        treeTable.getColumns().addAll(columns);
    }

    /**
     * Budowanie danych (wypelnianie tabeli danymi badan).
     */
    void buildTests() {
        TreeItem<TestAdapter> root = new RecursiveTreeItem<>(tests, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        for(int i = 0; i < archive.records; i++) {
            tests.add(new TestAdapter(archive.fields.get(i),archive.fields.get(i+archive.records),archive.fields.get(i+archive.records*2),
                    archive.fields.get(i+archive.records*3),archive.fields.get(i+archive.records*4),archive.fields.get(i+archive.records*5),
                    archive.fields.get(i+archive.records*6),archive.fields.get(i+archive.records*7)));
        }
    }
}
