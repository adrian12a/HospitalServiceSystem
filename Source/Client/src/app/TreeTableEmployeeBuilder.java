package app;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

/**
 * Budowniczy tabeli z danymi pracownikow (wzorzec Budowniczy).
 */
public class TreeTableEmployeeBuilder extends TreeTableBuilder {
    /**
     * Lista pracownikow.
     */
    ObservableList<AccountAdapter> employees = FXCollections.observableArrayList();

    /**
     * Konstruktor.
     * @param treeTable Tabela
     * @param archive   Archiwum
     */
    public TreeTableEmployeeBuilder(JFXTreeTableView treeTable, Archive archive) {
        this.treeTable = treeTable;
        this.archive = archive;
        buildColumns();
        buildEmployees();
    }

    /**
     * Budowanie kolumn.
     */
    void buildColumns() {
        buildColumn("Id",50, new TreeItemPropertyValueFactory<AccountAdapter,String>("id"));
        buildColumn("Imię",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("name"));
        buildColumn("Nazwisko",200, new TreeItemPropertyValueFactory<AccountAdapter,String>("surname"));
        buildColumn("E-mail",250, new TreeItemPropertyValueFactory<AccountAdapter,String>("email"));
        buildColumn("Stanowisko",240, new TreeItemPropertyValueFactory<AccountAdapter,String>("type"));
        treeTable.getColumns().addAll(columns);
    }

    /**
     * Budowanie danych (wypelnianie tabeli danymi pracownikow).
     */
    void buildEmployees() {
        TreeItem<AccountAdapter> root = new RecursiveTreeItem<>(employees, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        for(int i = 0; i < archive.records; i++) {
            employees.add(new AccountAdapter(archive.fields.get(i),archive.fields.get(i+archive.records),archive.fields.get(i+archive.records*2),archive.fields.get(i+archive.records*3),archive.fields.get(i+archive.records*4)));
        }
    }
}
