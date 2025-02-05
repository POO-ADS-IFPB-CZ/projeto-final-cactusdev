package src.model;

import src.model.interfaces.CodigoGenerator;

import java.io.Serializable;
import java.util.Objects;

public class Item_produto implements Serializable {
    private final String codigo;
    private Produto produto;
    private double quantidade;

    static private final long serialVersionUID = 1L;

    public Item_produto(CodigoGenerator adapter, Produto produto, double quantidade) {
        this.codigo = adapter.gerarCodigo();
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade += quantidade;
    }

    public double getSubtotal(){
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return "Item_produto{" +
                "codigo='" + codigo + '\'' +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item_produto that = (Item_produto) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
