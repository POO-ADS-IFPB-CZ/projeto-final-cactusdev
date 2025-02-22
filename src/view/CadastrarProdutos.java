package src.view;

import javax.swing.*;

public class CadastrarProdutos extends JFrame {
    private JPanel contentPane;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JButton buttonOK;

    public CadastrarProdutos() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        CadastrarProdutos dialog = new CadastrarProdutos();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
