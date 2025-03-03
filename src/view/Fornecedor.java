package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Fornecedor extends JFrame {
    private JPanel contentPane;
    private JButton alterarFornecedorButton;
    private JButton cadastrarFornecedorButton;
    private JButton sairButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTable table1;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton buttonOK;

    public Fornecedor() {

        setContentPane(contentPane);
        setTitle("Fornecedor");
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUIComponents();
    }

    private void createUIComponents() {
        String[] colunas = {"CNPJ", "Nome", "Telefone", "Endereço"};

        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        table1.setModel(tableModel);

        rowSorter = new TableRowSorter<>(tableModel);
        table1.setRowSorter(rowSorter);
    }

    public static void main(String[] args) {
        Fornecedor dialog = new Fornecedor();
        dialog.pack();
        dialog.setVisible(true);
    }
}
