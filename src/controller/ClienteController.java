package src.controller;

import src.dao.Dao;
import src.model.Cliente;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import java.io.IOException;
import java.util.Collection;

public final class ClienteController {

    private final Dao<Cliente, String> clienteDao;

    public ClienteController(Dao<Cliente, String> dao) {
        this.clienteDao = dao;
    }

    public void addCliente(Cliente cliente) {
        try {
            clienteDao.addToList(cliente.getCpf(), cliente);
            Success.show(null, "Cliente adicionado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (Exception e) {
            Faill.show(null, "Cliente já existe.");
        }
    }

    public void removeCliente(String cpf) {
        try {
             clienteDao.removeToList(cpf);
             Success.show(null, "Cliente removido com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Cliente não encontrado.");
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            clienteDao.updateItemOnList(cliente.getCpf(), cliente);
                Success.show(null, "Cliente atualizado com sucesso.");
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Cliente não encontrado.");
        }
    }

    public Collection<Cliente> listarClientes() {
        return clienteDao.getList().values();
    }

    public void salvarClientes() {
        if (!clienteDao.getList().isEmpty()) {
            try {
                clienteDao.saveFile();
                Success.show(null, "Clientes salvos com sucesso.");
            } catch (IOException e) {
                Faill.show(null, "Erro interno ao salvar os clientes: " + e.getMessage());
            }
        }
    }
}
