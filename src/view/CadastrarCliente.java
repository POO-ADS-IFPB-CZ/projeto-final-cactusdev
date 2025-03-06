package src.view;

import src.controller.ClienteController;
import src.services.ClienteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CadastrarCliente extends JDialog {
    private JPanel contentPane;
    private JButton concluir;
    private JButton cancelar;
    private JTextField nome;
    private JTextField cpf;
    private JTextField telefone;
    private final ClienteService clienteService;

    public CadastrarCliente(DefaultTableModel tableModel) {
        clienteService = new ClienteService();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(concluir);
        pack();
        // Centraliza a janela
        setLocationRelativeTo(null);

        concluir.addActionListener((e)-> {
            String nomeInput = nome.getText();
            String cpfInput = cpf.getText();
            String telefoneInput = telefone.getText();

            if (clienteService.cadastrarCliente(cpfInput,nomeInput, telefoneInput, this)) {
                clienteService.mostrarClientesNaTabela(tableModel);
                dispose();
            }
        });

        cancelar.addActionListener((e)-> {
            dispose();
        });
    }

    private void limparCampos(){
        nome.setText("");
        cpf.setText("");
        telefone.setText("");
    }
}
