package src.services.validators;

import src.model.Cliente;

import java.awt.Component;
import java.util.Objects;

public class ValidatorCliente {
    static public Cliente validarCamposCriarCliente(
            String nome,
            String cpf,
            String telefone) throws  IllegalArgumentException {

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

    static public Cliente validarCamposEditarCliente(String nome,
                               String ativo,
                               String telefone, Cliente cliente) throws IllegalArgumentException{
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode estar vazio.");
        }

        if (ativo == null){
            throw new IllegalArgumentException("Especifique se o cliente é ativo ou não.");
        }

        Boolean converteAtivo = ativo.equals("SIM");

        if (telefone == null || !telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("O telefone deve conter 10 ou 11 dígitos numéricos.");
        }

        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setAtivo(converteAtivo);

        return cliente;

    }
}

