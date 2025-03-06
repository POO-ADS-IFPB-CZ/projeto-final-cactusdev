package src.services.filters;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ClienteFilters {

    static public void filtrarTabelaPorCpf(String cpfFiltro, TableRowSorter<DefaultTableModel> rowSorter) {
        if (cpfFiltro.isEmpty()) {
            limparFiltros(rowSorter);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("^" + cpfFiltro + "$", 1));
        }
    }

    static public void filtrarTabelaPorNome(String nomeFiltro, TableRowSorter<DefaultTableModel> rowSorter) {
        if (nomeFiltro.isEmpty()) {
            limparFiltros(rowSorter);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + nomeFiltro, 0));
        }
    }

    static public void limparFiltros(TableRowSorter<DefaultTableModel> rowSorter) {
        rowSorter.setRowFilter(null);
    }
}
