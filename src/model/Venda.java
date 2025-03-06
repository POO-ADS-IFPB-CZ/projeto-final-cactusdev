package src.model;

import src.model.interfaces.CodigoGenerator;

import java.io.Serializable;
import java.util.Objects;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Venda implements Serializable {

    private final String codigo;
    private Map<String, Item_produto> itens;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private String metodoPagamento;
    static private final long serialVersionUID = 1L;

    public Venda(CodigoGenerator adapter){
        this.codigo = adapter.gerarCodigo();
        itens = new HashMap<>();
        dataHora = LocalDateTime.now();
        this.cliente = null;
    }

    public double getTotal(){
        double total = 0;
        for (Item_produto item : this.itens.values()){
            total += item.getSubtotal();
        }
        return total;
    }

    public void adicionarItem(Item_produto item){
        this.itens.put(item.getCodigo(), item);
    }

    public Item_produto itemExist(String codigoProduto){
        Item_produto item = null;

        for (Map.Entry<String, Item_produto> entry : this.itens.entrySet()){
            if (entry.getValue().getProduto().getCodigo().equals(codigoProduto)){
                item = entry.getValue();
            }
        }
        return item;

    }

    public boolean removerItem(String codigoItem){
        if (this.itens.get(codigoItem) != null){
            this.itens.remove(codigoItem);
            return true;
        }
        return false;
    }

    public Collection<Item_produto> getItens(){
        return this.itens.values();
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "codigo='" + codigo + '\'' +
                ", itens=" + itens +
                ", dataHora=" + dataHora +
                ", cliente=" + cliente +
                ", metodoPagamento=" + metodoPagamento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return Objects.equals(codigo, venda.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMetodoPagamento() {
        return this.metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Item_produto getItem(String codigo){
        return this.itens.get(codigo);
    }
}

