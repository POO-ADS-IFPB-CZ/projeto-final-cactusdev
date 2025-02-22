package src.services.validators;

import src.model.Cliente;

import java.awt.Component;

public class ValidatorCliente {
    static public Cliente validarCamposCriarCliente(
            Component parent,
            String nome,
            String cpf,
            String telefone) throws IllegalArgumentException {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode estar vazio.");
        }

        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("O CPF deve conter 11 dígitos numéricos.");
        }

        if (telefone == null || !telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("O telefone deve conter 10 ou 11 dígitos numéricos.");
        }

        return new Cliente(cpf, nome, telefone);
    }
}

