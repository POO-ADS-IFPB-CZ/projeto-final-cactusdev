package src.controller;

import src.dao.Dao;
import src.dao.DaoWithFile;
import src.model.Cliente;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import java.io.IOException;
import java.util.Collection;

public final class ClienteController {

    private final Dao<Cliente, String> clienteDao;

    public ClienteController() {
        try {
            this.clienteDao = new DaoWithFile<Cliente, String>("Clientes");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public boolean removeCliente(String cpf) {
        try {
             clienteDao.removeToList(cpf);
             Success.show(null, "Cliente removido com sucesso.");
             return  true;
        } catch (IOException e) {
            Faill.show(null, "Erro interno: " + e.getMessage());
            return false;
        } catch (CustomError e) {
            Faill.show(null, "Cliente não encontrado.");
            return false;
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

    public Cliente getClientePorCpf(String cpf){
       return clienteDao.getList().get(cpf);
    }
}
