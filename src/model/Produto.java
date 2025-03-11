package src.model;

import src.model.interfaces.CodigoGenerator;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {
    private final String  codigo;
    private String descricao;
    //Visibilidade de pacote (package) ou default
    private double preco;
    private double qtdEstoque;
    private Categoria_produto categoria;
    private String medida;
    private Fornecedor fornecedor;

    static private final long serialVersionUID = 1L;

    public Produto(CodigoGenerator adapter, String descricao, double preco, double quantidade ,
                   Categoria_produto categoria, String medida, Fornecedor fornecedor) {
        this.codigo = adapter.gerarCodigo();
        this.descricao = descricao;
        this.preco = preco;
        this.qtdEstoque = quantidade < 0 ? 0 : quantidade;
        this.categoria = categoria;
        this.medida = medida;
        this.fornecedor = fornecedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Categoria_produto getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria_produto categoria) {
        this.categoria = categoria;
    }

    public double getQtdEstoque() {
        return qtdEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQtdEstoque(double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", qtdEstoque=" + qtdEstoque +
                ", categoria=" + categoria +
                ", medida='" + medida + '\'' +
                ", fornecedor=" + fornecedor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigo, produto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}