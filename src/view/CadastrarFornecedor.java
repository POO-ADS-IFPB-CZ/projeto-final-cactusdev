package src.view;

import src.model.Tipo_fornecedor;
import src.services.FornecedorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CadastrarFornecedor extends JDialog {
    private JPanel contentPane;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JTextField nome;
    private JTextField cnpj;
    private JTextField telefone;
    private JTextField endereco;
    private JComboBox tipo_pessoa;
    private JButton buttonOK;
    private FornecedorService fornecedorService;

    public CadastrarFornecedor(DefaultTableModel tableModel) {
        fornecedorService = new FornecedorService();
        setContentPane(contentPane);
        setTitle("Cadastro de Fornecedor");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        confirmarButton.addActionListener((e)-> {
            String nomeInput = nome.getText();
            String cnpjInput = cnpj.getText();
            String telefoneInput = telefone.getText();
            String enderecoInput = endereco.getText();
            String tipoPessoa = (String) tipo_pessoa.getSelectedItem();

            if (fornecedorService.cadastrarFornecedor(nomeInput,cnpjInput,tipoPessoa, enderecoInput,telefoneInput)) {
                fornecedorService.mostrarFornecedoresNaTabela(tableModel);
                dispose();
            }
        });

        cancelarButton.addActionListener((e)-> {
            dispose();
        });
    }
}
