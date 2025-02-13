package src.controller;

import src.dao.Dao;
import src.model.Produto;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

import javax.swing.*;
import java.io.IOException;
import java.lang.Error;
import java.util.Collection;

public final class ProdutoController {

    private final Dao<Produto, String> produtoDao;

    public ProdutoController(Dao<Produto, String> dao){
        this.produtoDao = dao;
    }

    public  void addProduto(Produto produto){
        try {
            produtoDao.addToList(produto.getCodigo(), produto);
            Success.show(null, "Produto adicionado com sucesso.");
        } catch (IOException e) {
            Faill.show(null,"Erro interno: " + e.getMessage());
        } catch (Error e) {
            Faill.show(null, "Produto já existe.");
        }
    }

    public Collection<Produto> listarProdutos(){
            return produtoDao.getList().values();
    }
}
