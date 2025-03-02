package src.services;

import src.controller.ProdutoController;
import src.model.Produto;
import src.services.validators.ValidatorProduto;
import src.view.customErrors.Faill;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;

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

    public void mostrarProdutosNaTabela(DefaultTableModel model) {
        Collection<Produto> produtos = produtoController.listarProdutos();

        // Remove linhas antigas
        model.setRowCount(0);

        for (Produto produto : produtos) {
            model.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getDescricao(),
                    produto.getPreco(),
                    produto.getQtdEstoque(),
                    produto.getCategoria(),
                    produto.getMedida()
            });
        }
    }

    public void atualizarTabela(DefaultTableModel model){
        model.setRowCount(0);

        mostrarProdutosNaTabela(model);
    }

}
