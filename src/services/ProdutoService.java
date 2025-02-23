package src.services;

import src.controller.ProdutoController;
import src.model.Produto;
import src.services.validators.ValidatorProduto;
import src.view.customErrors.Faill;

public class ProdutoService {
    private ProdutoController produtoController;
    public ProdutoService(ProdutoController produtoController){
        this.produtoController = produtoController;
    }

    public boolean criarProduto(String descricao, String precoUnitario, String unidadeMedida,String qtdEstoque,
String categoria){
        try{
            Produto produto = ValidatorProduto.validarCamposCriarProduto(null,descricao,precoUnitario,unidadeMedida,qtdEstoque,categoria);

            produtoController.addProduto(produto);
            return true;
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
            return  false;
    }
    }
}
