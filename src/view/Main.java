package src.view;

import src.controller.ClienteController;
import src.controller.ProdutoController;
import src.dao.DaoWithFile;
import src.model.Categoria_produto;
import src.model.Cliente;
import src.model.Produto;
import src.services.ClienteService;
import src.services.adapters.GenerateWithValue;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        Venda venda = new Venda(new GenerateWithDateRandom());
//
        Produto produto = new Produto(new GenerateWithValue("12"),"Feijao", 34, 4, Categoria_produto.TINTA, "UN");
//
//        venda.adicionarItem(new Item_produto(new GenerateWithValue("1234"), produto, 2));
//
//        System.out.println(venda);
//
//        venda.adicionarItem(new Item_produto(new GenerateWithValue("5555"), produto, 5));
//
//        System.out.println(venda);

        //JOptionPane.showMessageDialog(null, "Erro interno ao cadastrar produto, tente novamente.", "ERROR", JOptionPane.ERROR_MESSAGE);

        ProdutoController produtoController = new ProdutoController();

        produtoController.addProduto(produto);

        System.out.println("Itens antes da remoção: " + produtoController.listarProdutos());
        //daoProduto.removeToList(produto.getCodigo());
        System.out.println("Itens depois da remoção: " + produtoController.listarProdutos());

        produtoController.listarProdutos().forEach(System.out::println);

        Cliente diego = new Cliente("1234567891011", "Jacinto Leite Aquino Reguo", "8899953523671");

        diego.setAtivo(false);

        new ClienteController().addCliente(diego);

    }
}
