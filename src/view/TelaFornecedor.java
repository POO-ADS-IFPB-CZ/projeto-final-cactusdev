package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class TelaFornecedor extends JFrame {
    private JPanel contentPane;
    private JButton alterarFornecedorButton;
    private JButton cadastrarFornecedorButton;
    private JButton sairButton;
    private JTextField nome;
    private JComboBox opcao_busca;
    private JTable table1;
    private JTextField cnpj;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton buttonOK;

    public TelaFornecedor() {

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
        TelaFornecedor dialog = new TelaFornecedor();
        dialog.pack();
        dialog.setVisible(true);
    }
}
