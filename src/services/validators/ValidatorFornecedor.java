package src.services.validators;

import src.model.Fornecedor;
import src.model.Tipo_fornecedor;

public class ValidatorFornecedor {
    static public Fornecedor validarCamposCriarFornecedor(
            String nome,
            String cnpj,
            String telefone,
            String endereco,
            String tipo) throws IllegalArgumentException {

        // Valida o nome do fornecedor
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do fornecedor não pode estar vazio.");
        }

        // Valida o CNPJ
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("O CNPJ deve conter 14 dígitos numéricos.");
        }

        // Valida o telefone
        if (telefone == null || !telefone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("O telefone deve conter 10 ou 11 dígitos numéricos.");
        }

        // Valida o endereço
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode estar vazio.");
        }

        // Converte a string para Tipo_fornecedor
        Tipo_fornecedor tipoFornecedor;
        try {
            tipoFornecedor = Tipo_fornecedor.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Tipo de fornecedor inválido. Valores permitidos: FISICA, JURIDICA.");
        }

        // Retorna um novo objeto Fornecedor validado
        return new Fornecedor(cnpj, tipoFornecedor, nome, endereco, telefone);
    }
}