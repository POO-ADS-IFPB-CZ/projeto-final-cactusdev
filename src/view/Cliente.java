package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Cliente extends JFrame {
    private JPanel contentPane;
    private JButton cadastrarClienteButton;
    private JButton alterarClienteButton;
    private JButton sairButton;
    private JTable table1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton buttonOK;

    public Cliente() {

        setContentPane(contentPane);
        setTitle("Cliente");
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUIComponents();
    }

    private void createUIComponents() {
        String[] colunas = {"Nome", "Cpf", "Telefone", "Data de cadastro", "Status"};

        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        table1.setModel(tableModel);

        rowSorter = new TableRowSorter<>(tableModel);
        table1.setRowSorter(rowSorter);
    }

    public static void main(String[] args) {
        Cliente dialog = new Cliente();
        dialog.pack();
        dialog.setVisible(true);
    }
}
