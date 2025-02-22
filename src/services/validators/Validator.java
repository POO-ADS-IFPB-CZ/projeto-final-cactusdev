package src.services.validators;

import src.model.Categoria_produto;
import src.model.Produto;
import src.services.adapters.GenerateWithDateRandom;

import java.awt.Component;

public class Validator {
    static public Produto validarCamposCriarProduto(
            Component parent,
            String descricao,
            String precoUnitario,
            String unidadeMedida,
            String qtdEstoque,
            String categoria) throws IllegalArgumentException {

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode estar vazia.");
        }

        double preco;
        try {
            preco = Double.parseDouble(precoUnitario);
            if (preco <= 0) {

                throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("O preço unitário deve ser um número válido.");
        }

        if (unidadeMedida == null || unidadeMedida.trim().isEmpty()) {

            throw new IllegalArgumentException("A unidade de medida não pode estar vazia.");
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(qtdEstoque);
            if (quantidade < 0) {

                throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
            }
        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("A quantidade em estoque deve ser um número inteiro válido.");
        }

        Categoria_produto categoriaEnum;
        try {
            categoriaEnum = Categoria_produto.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {

            throw new IllegalArgumentException("A categoria informada é inválida.");
        }

        return new Produto(new GenerateWithDateRandom(),descricao, preco,quantidade, categoriaEnum, unidadeMedida);
    }
}
