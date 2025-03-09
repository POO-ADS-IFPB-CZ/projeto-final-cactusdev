package src.services.filters;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class FornecedorFilters {

    public static void filtrarTabelaPorCnpj(String cnpj, TableRowSorter<DefaultTableModel> rowSorter) {
        DefaultTableModel model = (DefaultTableModel) rowSorter.getModel();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + cnpj, 0); // Filtra na primeira coluna (CNPJ)
        rowSorter.setRowFilter(rowFilter);
    }

    public static void filtrarTabelaPorNome(String nome, TableRowSorter<DefaultTableModel> rowSorter) {
        DefaultTableModel model = (DefaultTableModel) rowSorter.getModel();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + nome, 1); // Filtra na segunda coluna (Nome)
        rowSorter.setRowFilter(rowFilter);
    }

    public static void limparFiltros(TableRowSorter<DefaultTableModel> rowSorter) {
        rowSorter.setRowFilter(null); // Limpa o filtro da tabela
    }
}
