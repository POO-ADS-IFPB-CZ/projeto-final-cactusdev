package src.view;

import src.controller.ClienteController;
import src.services.ClienteService;

import javax.swing.*;

public class CadastrarCliente extends JDialog {
    private JPanel contentPane;
    private JButton concluir;
    private JButton cancelar;
    private JTextField nome;
    private JTextField cpf;
    private JTextField telefone;
    private final ClienteService clienteService;

    public CadastrarCliente() {
        clienteService = new ClienteService(new ClienteController());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(concluir);

        // Centraliza a janela
        setLocationRelativeTo(null);

        concluir.addActionListener((e)-> {
            System.out.println(new ClienteController().listarClientes());
            String nomeInput = nome.getText();
            String cpfInput = cpf.getText();
            String telefoneInput = telefone.getText();

            if (clienteService.cadastrarCliente(cpfInput,nomeInput, telefoneInput, this)) limparCampos();
        });

        cancelar.addActionListener((e)-> {
            dispose();
        });
    }

    public static void main(String[] args) {
        CadastrarCliente dialog = new CadastrarCliente();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void limparCampos(){
        nome.setText("");
        cpf.setText("");
        telefone.setText("");
    }
}
