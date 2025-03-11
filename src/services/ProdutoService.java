package src.services;

import src.controller.ProdutoController;
import src.model.Fornecedor;
import src.model.Produto;
import src.services.formatters.ValorParaDinheiro;
import src.services.validators.ValidatorProduto;
import src.view.customErrors.Faill;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;

public class ProdutoService {
    private ProdutoController produtoController;
    public ProdutoService(){
        this.produtoController = new ProdutoController();
    }

    public boolean criarProduto(String descricao, String precoUnitario, String unidadeMedida, String qtdEstoque,
                                String categoria, Fornecedor fornecedor){
        try{
            Produto produto = ValidatorProduto.validarCamposCriarProduto(null,descricao,precoUnitario,unidadeMedida,qtdEstoque,categoria, fornecedor);

            produtoController.addProduto(produto);
            return true;
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
            return  false;
    }
    }

    public boolean editarProduto(String descricao, String precoUnitario, String unidadeMedida, String qtdEstoque,
                                String categoria, Fornecedor fornecedor, Produto produto){
        try{
            Produto produtoEditado = ValidatorProduto.validarCamposEditarProduto(descricao,precoUnitario,unidadeMedida,qtdEstoque,categoria, fornecedor, produto);

            produtoController.atualizarProduto(produtoEditado);

            return true;
        } catch (IllegalArgumentException e) {
            Faill.show(null, e.getMessage());
            return  false;
        }
    }

    public Produto getProdutoPorCodigo(String codigo){
        return produtoController.pegarProdutoPorCodigo(codigo);
    }

    public void mostrarProdutosNaTabela(DefaultTableModel model) {
        Collection<Produto> produtos = produtoController.listarProdutos();

        // Remove linhas antigas
        model.setRowCount(0);

        for (Produto produto : produtos) {
            model.addRow(new Object[]{
                    produto.getCodigo(),
                    produto.getDescricao(),
                    ValorParaDinheiro.converter(produto.getPreco(), "pt", "BR"),
                    produto.getQtdEstoque(),
                    produto.getCategoria(),
                    produto.getMedida(),
                    produto.getFornecedor().getNome()
            });
        }
    }

    public void atualizarTabela(DefaultTableModel model){
        model.setRowCount(0);

        mostrarProdutosNaTabela(model);
    }

}
