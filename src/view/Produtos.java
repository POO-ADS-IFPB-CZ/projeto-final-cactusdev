package src.view;

import javax.swing.*;

public class Produtos extends JFrame {
    private JPanel contentPane;
    private JButton adicionarButton;
    private JButton removerButton;
    private JButton alterarButton;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JTable table1;
    private JButton buttonOK;

    public Produtos() {
        setContentPane(contentPane);
        setTitle("Produtos");
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        Produtos dialog = new Produtos();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
