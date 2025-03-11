package src.view;

import src.model.Fornecedor;
import src.services.FornecedorService;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class FornecedorAlterar extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField input_cnpj;
    private JTextField input_nome;
    private JTextField input_telefone;
    private JTextField input_endereco;
    private JComboBox tipo_pessoa;
    private JButton sairESCButton;
    private JButton alterarButton;
    private JLabel data_cadastro;
    private final Fornecedor fornecedor;
    private final FornecedorService fornecedorService;

    public FornecedorAlterar(Fornecedor fornecedor, DefaultTableModel tableModel) {
        fornecedorService = new FornecedorService();
        this.fornecedor = fornecedor;
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        setInformacoesFornecedor();

        eventoEditarFornecedor(tableModel);
        eventoFecharJanela();
        eventoTeclaESC();

        sairESCButton.addActionListener((e) -> fecharJanela());
    }

    private void eventoEditarFornecedor(DefaultTableModel tableModel) {
        alterarButton.addActionListener((e) -> {
            String nomeInput = input_nome.getText();
            String telefoneInput = input_telefone.getText();
            String enderecoInput = input_endereco.getText();
            String tipo = (String) tipo_pessoa.getSelectedItem();

            boolean foiModificado = !nomeInput.equals(fornecedor.getNome()) ||
                    !telefoneInput.equals(fornecedor.getTelefone()) ||
                    !enderecoInput.equals(fornecedor.getEndereco()) ||
                    !Objects.equals(tipo_pessoa.getSelectedItem(), fornecedor.getTipo().toString());

            if (foiModificado) {
                if (fornecedorService.editarFornecedor(nomeInput,enderecoInput, telefoneInput, tipo, fornecedor)) {
                    fornecedorService.mostrarFornecedoresNaTabela(tableModel);
                }
            } else {
                Faill.show(this, "Nenhuma alteração foi feita!");
            }
        });
    }

    private void eventoFecharJanela() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void eventoTeclaESC() {
        contentPane.registerKeyboardAction((e) -> fecharJanela(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void fecharJanela() {
        dispose();
    }

    private void setInformacoesFornecedor() {
        input_cnpj.setText(fornecedor.getCnpj());
        input_nome.setText(fornecedor.getNome());
        input_telefone.setText(fornecedor.getTelefone());
        input_endereco.setText(fornecedor.getEndereco());
        data_cadastro.setText(fornecedor.getDataCadastro().toString());
    }
}
