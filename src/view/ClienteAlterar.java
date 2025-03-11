package src.view;

import src.model.Cliente;
import src.services.ClienteService;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class ClienteAlterar extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField cpf;
    private JTextField nome;
    private JTextField telefone;
    private JComboBox ativo;
    private JButton apagarButton;
    private JButton alterarButton;
    private JLabel data_cadastro;
    private final Cliente cliente;
    private final ClienteService clienteService;

    public ClienteAlterar(Cliente cliente, DefaultTableModel tableModel) {
        clienteService = new ClienteService();
        this.cliente = cliente;
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        setIformacoesCliente();

        eventoEditarCliente(tableModel);
        eventoFecharJanela();
        eventoTeclaESC();

        // Fechar ao clicar no botão Cancelar
        buttonCancel.addActionListener((e) -> fecharJanela());
    }

    private void eventoEditarCliente(DefaultTableModel tableModel) {
        alterarButton.addActionListener((e) -> {
            String nomeInput = nome.getText();
            String telefoneInput = telefone.getText();
            String ativoSelecionado = (String) ativo.getSelectedItem();
            boolean ativoBoolean = ativoSelecionado.equals("SIM");

            // Verificar se houve mudanças nos dados
            boolean foiModificado = !nomeInput.equals(cliente.getNome()) ||
                    !telefoneInput.equals(cliente.getTelefone()) ||
                    ativoBoolean != cliente.getAtivo();

            if (foiModificado) {
                if (clienteService.editarCliente(nomeInput, telefoneInput, ativoSelecionado, cliente)) {
                    clienteService.mostrarClientesNaTabela(tableModel);
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

    public void setIformacoesCliente() {
        cpf.setText(cliente.getCpf());
        nome.setText(cliente.getNome());
        telefone.setText(cliente.getTelefone());
        data_cadastro.setText(cliente.getDataCadastro().toString());
        ativo.setSelectedItem(cliente.getAtivo() ? "SIM" : "NAO");
    }
}
