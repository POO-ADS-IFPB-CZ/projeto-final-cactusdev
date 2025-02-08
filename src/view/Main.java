package src.view;

import src.model.Categoria_produto;
import src.model.Item_produto;
import src.model.Produto;
import src.model.Venda;
import src.services.adapters.codigo_generator.GenerateWithDateRandom;
import src.services.adapters.codigo_generator.GenerateWithValue;

public class Main {
    public static void main(String[] args) {
        Venda venda = new Venda(new GenerateWithDateRandom());

        Produto produto = new Produto(new GenerateWithDateRandom(),"Feijao", 34, 4, Categoria_produto.TINTA);

        venda.adicionarItem(new Item_produto(new GenerateWithValue("1234"), produto, 2));

        System.out.println(venda);

        venda.adicionarItem(new Item_produto(new GenerateWithValue("5555"), produto, 5));

        System.out.println(venda);
    }
}
