package src.services;

import src.controller.ProdutoController;
import src.model.Produto;
import src.services.validators.Validator;
import src.view.customErrors.Faill;

public class ProdutoService {
    private ProdutoController produtoController;
    public ProdutoService(ProdutoController produtoController){
        this.produtoController = produtoController;
    }

    public void criarProduto(String descricao, String precoUnitario, String unidadeMedida,String qtdEstoque,
String categoria){
        try{
            Produto produto = Validator.validarCamposCriarProduto(null,descricao,precoUnitario,unidadeMedida,qtdEstoque,categoria);

            produtoController.addProduto(produto);
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
    }
    }
}
