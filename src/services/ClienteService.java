package src.services;

import src.controller.ClienteController;
import src.model.Cliente;
import src.services.validators.ValidatorCliente;
import src.view.customErrors.Faill;

import java.awt.*;

public class ClienteService {
    private final ClienteController clienteController;

    public ClienteService(ClienteController clienteController){
        this.clienteController = clienteController;
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
}
