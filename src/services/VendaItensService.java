package src.services;

import src.controller.ProdutoController;
import src.controller.VendaController;
import src.model.Cliente;
import src.model.Item_produto;
import src.model.Produto;
import src.model.Venda;
import src.services.adapters.GenerateWithDateRandom;
import src.services.formatters.ValorParaDinheiro;
import src.services.validators.ValidatorVenda;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class VendaItensService {
    private Venda venda;
    private final VendaController vendaController;
    private final ProdutoController produtoController;

    public VendaItensService() {
        criarNovaVenda();
        vendaController = new VendaController();
        produtoController = new ProdutoController();
    }

    private void criarNovaVenda() {
        this.venda = new Venda(new GenerateWithDateRandom());
    }

    public boolean cancelarVendaAtual(DefaultTableModel tableModel){
        if (!this.venda.getItens().isEmpty()){
            criarNovaVenda();
            mostrarVendasNaTabela(tableModel);
            return true;
        }

        return false;
    }

    public void setClienteVenda(Cliente cliente){
        this.venda.setCliente(cliente);
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
                    ValorParaDinheiro.converter(itemProduto.getProduto().getPreco(), "pt", "BR"),
                    ValorParaDinheiro.converter(itemProduto.getSubtotal(), "pt", "BR")
            });
        }
    }

    public void adicionarItemVenda(Item_produto itemProduto, DefaultTableModel model) throws IllegalArgumentException {
        Item_produto itemProdutoExisteNaVenda = venda.itemExist(itemProduto.getProduto().getCodigo());

        if (!quantidadeIsValida(itemProduto,itemProdutoExisteNaVenda)){
            throw new IllegalArgumentException("Essa quantidade para esse produto não existe no extoque.");
        }

        if (itemProdutoExisteNaVenda == null) {
            venda.adicionarItem(itemProduto);
        } else {
            itemProdutoExisteNaVenda.setQuantidade(itemProdutoExisteNaVenda.getQuantidade() + itemProduto.getQuantidade());
            venda.adicionarItem(itemProdutoExisteNaVenda);
        }

        mostrarVendasNaTabela(model);
    }

    private boolean quantidadeIsValida(Item_produto itemProduto, Item_produto item_exist){
        Produto produto = itemProduto.getProduto();

       if (item_exist == null){
           return itemProduto.getQuantidade() <= produto.getQtdEstoque();
       }

       return (item_exist.getQuantidade() + itemProduto.getQuantidade()) <= produto.getQtdEstoque();
    }

    public double getSubtotalVendaAtual(){
        return venda.getTotal();
    }

    public void removerItem(String codigoItem, DefaultTableModel tableModel){
        venda.removerItem(codigoItem);
        mostrarVendasNaTabela(tableModel);
    }

    public void atualizarQtdItem(String codItem, double qtd) throws IllegalArgumentException{
        Item_produto itemProduto = venda.getItem(codItem);
        Item_produto itemExist = venda.itemExist(itemProduto.getProduto().getCodigo());

        if (!quantidadeIsValida(itemProduto, itemExist)){
            throw new IllegalArgumentException("Essa quantidade para esse produto não existe no extoque.");
        }

        itemProduto.setQuantidade(qtd);
        venda.adicionarItem(itemProduto);

    }

    public void finalizarVenda(String metodoPagamento, String valorPagoStr, double totalVenda, DefaultTableModel tableModel) throws IllegalArgumentException {
            ValidatorVenda.verificarFinalizarVenda(metodoPagamento, valorPagoStr, totalVenda);
            venda.setMetodoPagamento(metodoPagamento);
            this.vendaController.adicionarVenda(this.venda);
            atualizarQtdEstoqueAposVenda();
            criarNovaVenda();
            mostrarVendasNaTabela(tableModel);
    }

    private void atualizarQtdEstoqueAposVenda(){
        this.venda.getItens().forEach((item)->{
            Produto produto = item.getProduto();
            produto.setQtdEstoque(produto.getQtdEstoque() - item.getQuantidade());
            produtoController.atualizarProduto(produto);
        });
    }
}

