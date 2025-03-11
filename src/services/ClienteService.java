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
            Cliente cliente = ValidatorCliente.validarCamposCriarCliente(nome,cpf, telefone);

            clienteController.addCliente(cliente);
            return  true;
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
            return false;
        }
    }

    public boolean editarCliente(String nome, String telefone, String ativo, Cliente cliente){
        try{
            Cliente clienteEditado = ValidatorCliente.validarCamposEditarCliente(nome, ativo, telefone, cliente);

            clienteController.atualizarCliente(clienteEditado);

            return true;

        }catch (IllegalArgumentException e){
            Faill.show(null, e.getMessage());
            return false;
        }
    }

    public boolean apagarCliente(String cpf){
        return clienteController.removeCliente(cpf);
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
