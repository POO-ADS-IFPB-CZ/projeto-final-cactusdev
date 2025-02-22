package src.view;

import src.controller.ProdutoController;
import src.dao.DaoWithFile;
import src.model.Categoria_produto;
import src.model.Item_produto;
import src.model.Produto;
import src.model.Venda;
import src.services.adapters.codigo_generator.GenerateWithDateRandom;
import src.services.adapters.codigo_generator.GenerateWithValue;

import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Venda venda = new Venda(new GenerateWithDateRandom());
//
        Produto produto = new Produto(new GenerateWithValue("12"),"Feijao", 34, 4, Categoria_produto.TINTA);
//
//        venda.adicionarItem(new Item_produto(new GenerateWithValue("1234"), produto, 2));
//
//        System.out.println(venda);
//
//        venda.adicionarItem(new Item_produto(new GenerateWithValue("5555"), produto, 5));
//
//        System.out.println(venda);

        //JOptionPane.showMessageDialog(null, "Erro interno ao cadastrar produto, tente novamente.", "ERROR", JOptionPane.ERROR_MESSAGE);

        try {
            ProdutoController produtoController = new ProdutoController(new DaoWithFile<Produto, String>("Produtos"));

            produtoController.addProduto(produto);

            System.out.println("Itens antes da remoção: " + produtoController.listarProdutos());
            //daoProduto.removeToList(produto.getCodigo());
            System.out.println("Itens depois da remoção: " + produtoController.listarProdutos());

            produtoController.listarProdutos().forEach(System.out::println);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
