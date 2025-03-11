package src.services.validators;

import src.model.Categoria_produto;
import src.model.Fornecedor;
import src.model.Produto;
import src.services.adapters.GenerateWithDateRandom;

import java.awt.Component;

public class ValidatorProduto {
    static public Produto validarCamposCriarProduto(
            Component parent,
            String descricao,
            String precoUnitario,
            String unidadeMedida,
            String qtdEstoque,
            String categoria,
            Fornecedor fornecedor) throws IllegalArgumentException {

        validarCampos(descricao, precoUnitario, unidadeMedida, qtdEstoque, categoria, fornecedor);

        return new Produto(new GenerateWithDateRandom(),descricao, Double.parseDouble(precoUnitario),Double.parseDouble(qtdEstoque), Categoria_produto.valueOf(categoria.toUpperCase()), unidadeMedida, fornecedor);
    }

    static public Produto validarCamposEditarProduto(
            String descricao,
            String precoUnitario,
            String unidadeMedida,
            String qtdEstoque,
            String categoria,
            Fornecedor fornecedor,
            Produto produto) throws IllegalArgumentException {

        validarCampos(descricao, precoUnitario, unidadeMedida, qtdEstoque, categoria, fornecedor);

        produto.setDescricao(descricao);
        produto.setPreco(Double.parseDouble(precoUnitario));
        produto.setMedida(unidadeMedida);
        produto.setQtdEstoque(Double.parseDouble(qtdEstoque));
        produto.setCategoria(Categoria_produto.valueOf(categoria.toUpperCase()));
        produto.setFornecedor(fornecedor);

        return produto;
    }

    static public void validarCampos(String descricao,
                               String precoUnitario,
                               String unidadeMedida,
                               String qtdEstoque,
                               String categoria,
                               Fornecedor fornecedor) throws IllegalArgumentException{
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

        double quantidade;
        try {
            quantidade = Double.parseDouble(qtdEstoque);
            if (quantidade < 0) {

                throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
            }
        } catch (NumberFormatException e) {

            throw new IllegalArgumentException("A quantidade em estoque deve ser um número inteiro válido.");
        }

        try {
            Categoria_produto.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {

            throw new IllegalArgumentException("A categoria informada é inválida.");
        }

        if (fornecedor == null){
            throw new IllegalArgumentException("O fornecedor é necessário.");
        }
    }
}
