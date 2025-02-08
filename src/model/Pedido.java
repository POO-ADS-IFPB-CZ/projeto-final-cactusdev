package src.model;

import src.model.interfaces.CodigoGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Date;

public class Pedido implements Serializable {
    private final String codigo;
    private LocalDate data;
    private int quantidade
    private Produto produto;
    private Fornecedor fornecedor;

    static private final long serialVersionUID = 1L;

    // Construtor
    public Pedido(CodigoGenerator adapter, int quantidade, Produto produto, Fornecedor fornecedor) {
        this.codigo = adapter.gerarCodigo();
        this.quantidade = quantidade;
        this.data = LocalDate.now();
        this.produto = produto;
        this.fornecedor = fornecedor;
    }

    // Getters e Setters
    public String getCodigo() {
        return codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // toString, equals, hashCode


    @Override
    public String toString() {
        return "Pedido{" +
                "codigo='" + codigo + '\'' +
                ", data=" + data +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                ", fornecedor=" + fornecedor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(codigo, pedido.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}

