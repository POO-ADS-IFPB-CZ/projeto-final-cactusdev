package src.model;

import java.io.Serializable;
import java.util.*;

public class Fornecedor implements Serializable {
    private final String cnpj_cpf;
    private String tipo;
    private String nome,endereco,telefone;
    private Map<String,Produto> produtos;
    private static final long serialVersionUID = 1L;

    public Fornecedor(String cnpj_cpf, String tipo, String nome, String endereco, String telefone, Map<String,Produto> produtos){
        this.cnpj_cpf = cnpj_cpf;
        this.tipo = tipo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.produtos = new HashMap<>(produtos);
    }

    public String getCnpj() {
        return cnpj_cpf;
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
                "cnpj_cpf='" + cnpj_cpf + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", produtos=" + produtos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedor that = (Fornecedor) o;
        return Objects.equals(cnpj_cpf, that.cnpj_cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnpj_cpf);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
