package src.model;

import java.io.Serializable;
import java.util.*;

public class Fornecedor implements Serializable {
    private final String cnpj;
    private String nome,endereco,telefone;
    private Map<String,Produto> produtos;
    private static final long serialVersionUID = 1L;

    public Fornecedor(String cnpj, String nome, String endereco, String telefone, Map<String,Produto> produtos){
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.produtos = new HashMap<>(produtos);
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Map<String,Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<String,Produto> produtos) {
        this.produtos = produtos;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", produtos=" + produtos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(cnpj, that.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpj);
    }


}
