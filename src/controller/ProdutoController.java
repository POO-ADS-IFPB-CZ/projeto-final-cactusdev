package src.controller;

import src.dao.Dao;
import src.dao.DaoWithFile;
import src.model.Produto;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;


import java.io.IOException;
import java.util.Collection;

public final class ProdutoController {

    private final Dao<Produto, String> produtoDao;

    public ProdutoController(){
        try {
            this.produtoDao = new DaoWithFile<Produto, String>("Produtos");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public  void addProduto(Produto produto){
        try {
            produtoDao.addToList(produto.getCodigo(), produto);
            Success.show(null, "Produto adicionado com sucesso.");
        } catch (IOException e) {
            Faill.show(null,"Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Produto já existe.");
        }
    }

    public  void removeProduto(String cod_produto){
        try {
            produtoDao.removeToList(cod_produto);
            Success.show(null, "Produto Removido com sucesso.");
        } catch (IOException e) {
            Faill.show(null,"Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Produto não existe.");
        }
    }

    public  void atualizarQtdProduto(String cod_produto, double qtd_produto){
        Produto produto = produtoDao.getList().get(cod_produto);

        if (produto == null){
            Faill.show(null, "Não é possivel atualizar a quantidade de um produto que não existe no estoque.");
            return;
        }


        if (qtd_produto >= 0){
            produto.setQtdEstoque(qtd_produto);
            atualizarProduto(produto);
            return;
        }

        Faill.show(null, "A quantidade informada não é válida.");
    }

    public void atualizarProduto(Produto produto){
        try {
            produtoDao.updateItemOnList(produto.getCodigo(), produto);
            Success.show(null, "Produto atualizado com sucesso.");
        } catch (IOException e) {
            Faill.show(null,"Erro interno: " + e.getMessage());
        } catch (CustomError e) {
            Faill.show(null, "Produto não existe.");
        }
    }

    public Collection<Produto> listarProdutos(){
            return produtoDao.getList().values();
    }

    public void salvarProdutos(){
        if (!produtoDao.getList().isEmpty()){
            try {
                produtoDao.saveFile();
                Success.show(null, "Produtos salvo com sucesso.");
            } catch (IOException e) {
                Faill.show(null, "Erro interno ao salvar os produtos: " + e.getMessage());
            }
        }
    }
}
