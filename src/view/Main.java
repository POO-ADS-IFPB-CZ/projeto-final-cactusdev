package src.view;

import src.dao.DaoWithFile;
import src.model.Categoria_produto;
import src.model.Item_produto;
import src.model.Produto;
import src.model.Venda;
import src.services.adapters.codigo_generator.GenerateWithDateRandom;
import src.services.adapters.codigo_generator.GenerateWithValue;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Venda venda = new Venda(new GenerateWithDateRandom());

        Produto produto = new Produto(new GenerateWithDateRandom(),"Feijao", 34, 4, Categoria_produto.TINTA);

        venda.adicionarItem(new Item_produto(new GenerateWithValue("1234"), produto, 2));

        System.out.println(venda);

        venda.adicionarItem(new Item_produto(new GenerateWithValue("5555"), produto, 5));

        System.out.println(venda);

        try {
            DaoWithFile<Produto, String> daoProduto = new DaoWithFile<>("Produtos");

            daoProduto.addToList(produto.getCodigo(), produto);

            System.out.println("Itens antes da remoção: " + daoProduto.getItensList());
            //daoProduto.removeToList(produto.getCodigo());
            System.out.println("Itens depois da remoção: " + daoProduto.getItensList());

            Map<String, Produto> produtoMap = daoProduto.getItensList();

            produtoMap.values().forEach((p)-> {
                System.out.println(p.toString());
            });

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
