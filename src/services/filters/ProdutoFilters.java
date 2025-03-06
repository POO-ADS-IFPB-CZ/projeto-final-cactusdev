package src.services.filters;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProdutoFilters {

    static public void filtrarTabelaPorCodigo(String codigoFiltro, TableRowSorter<DefaultTableModel> rowSorter) {
        if (codigoFiltro.isEmpty()) {
            limparFiltros(rowSorter);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("^" + codigoFiltro + "$", 0));
        }
    }

    static public void limparFiltros(TableRowSorter<DefaultTableModel> rowSorter) {
            rowSorter.setRowFilter(null);
    }

    static public void filtrarTabelaPorDescricao(String descricao, TableRowSorter<DefaultTableModel> rowSorter) {
        if (descricao.isEmpty()) {
            limparFiltros(rowSorter);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + descricao, 1));
        }
    }

    static public void filtrarTabelaPorCategoria(String categoria, TableRowSorter<DefaultTableModel> rowSorter) {
        if (categoria == null || categoria.trim().isEmpty()) {
            limparFiltros(rowSorter);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + categoria, 4));
        }
    }
}
