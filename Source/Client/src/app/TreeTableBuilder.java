package app;

import com.jfoenix.controls.JFXTreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakcyjny budowniczy tabeli w archiwum (wzorzec Budowniczy).
 */
public abstract class TreeTableBuilder {
    /**
     * Lista kolumn w tabeli.
     */
    public List<TreeTableColumn> columns = new ArrayList<>();
    /**
     * Tabela/
     */
    public JFXTreeTableView treeTable;
    /**
     * Obiekt archiwum.
     */
    public Archive archive;

    /**
     * Budowanie pojedynczej kolumny.
     * @param name      Nazwa kolumny
     * @param width     Szerokosc kolumny
     * @param factory   Fabryka tabel
     */
    public void buildColumn(String name, int width, TreeItemPropertyValueFactory factory) {
        TreeTableColumn column = new TreeTableColumn(name);
        column.setMinWidth(width);
        column.setCellValueFactory(factory);
        columns.add(column);
    }

    /**
     * Pobranie wyniku - utworzonej tabeli.
     * @return Tabela.
     */
    public JFXTreeTableView getResult() {
        return treeTable;
    }
}
