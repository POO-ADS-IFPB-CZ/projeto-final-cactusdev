package src.view;

import javax.swing.*;

public class CadastrarFornecedor extends JFrame {
    private JPanel contentPane;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField textNome;
    private JTextField textCnpj;
    private JTextField textTelefone;
    private JTextField textEndereco;
    private JComboBox comboBoxTipo;
    private JButton buttonOK;

    public CadastrarFornecedor() {
        setContentPane(contentPane);
        setTitle("Cadastro de Fornecedor");
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public static void main(String[] args) {
        CadastrarFornecedor dialog = new CadastrarFornecedor();
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }
}
