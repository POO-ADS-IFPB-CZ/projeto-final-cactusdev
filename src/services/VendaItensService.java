package src.services;

import src.model.Item_produto;
import src.model.Venda;
import src.services.adapters.GenerateWithDateRandom;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;

public class VendaItensService {
    private Venda venda;

    public VendaItensService() {
        criarNovaVenda();
    }

    public void criarNovaVenda() {
        this.venda = new Venda(new GenerateWithDateRandom());
    }

    public void mostrarVendasNaTabela(DefaultTableModel model) {
        Collection<Item_produto> itensVenda = venda.getItens();

        model.setRowCount(0);

        for (Item_produto itemProduto : itensVenda) {
            model.addRow(new Object[]{
                    itemProduto.getCodigo(),
                itemProduto.getProduto().getDescricao(),
                    itemProduto.getQuantidade(),
                    itemProduto.getProduto().getMedida(),
                    itemProduto.getProduto().getPreco(),
                    itemProduto.getSubtotal()
            });
        }
    }

    public void adicionarItemVenda(Item_produto itemProduto, DefaultTableModel model) {
        Item_produto itemProdutoExisteNaVenda = venda.itemExist(itemProduto.getProduto().getCodigo());

        if (itemProdutoExisteNaVenda == null) {
            venda.adicionarItem(itemProduto);
        } else {
            itemProdutoExisteNaVenda.setQuantidade(itemProdutoExisteNaVenda.getQuantidade() + itemProduto.getQuantidade());
            venda.adicionarItem(itemProdutoExisteNaVenda);
        }

        mostrarVendasNaTabela(model);
    }

    public double getSubtotalVendaAtual(){
        return venda.getTotal();
    }
}

