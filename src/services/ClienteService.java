package src.services;

import src.controller.ClienteController;
import src.model.Cliente;
import src.model.Produto;
import src.services.validators.ValidatorCliente;
import src.view.customErrors.Faill;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class ClienteService {
    private final ClienteController clienteController;

    public ClienteService(){

        this.clienteController = new ClienteController();
    }

    public boolean cadastrarCliente(String cpf, String nome, String telefone, Component parent){
        try{
            Cliente cliente = ValidatorCliente.validarCamposCriarCliente(parent,nome,cpf, telefone);

            clienteController.addCliente(cliente);
            return  true;
        } catch (RuntimeException e) {
            Faill.show(null, e.getMessage());
            return false;
        }
    }

    public void mostrarClientesNaTabela(DefaultTableModel model) {
        Collection<Cliente> clientes = clienteController.listarClientes();

        // Remove linhas antigas
        model.setRowCount(0);

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getDataCadastro(),
                    cliente.getAtivo() ? "Ativo" : "Desativado"
            });
        }
    }

    public Cliente getClientePorCpf(String cpf){
        return clienteController.getClientePorCpf(cpf);
    }
}
