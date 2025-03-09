package src.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Fornecedor implements Serializable {
    private final String cnpj_cpf;
    private Tipo_fornecedor tipo;
    private String nome,endereco,telefone;
    private LocalDate dataCadastro;
    private static final long serialVersionUID = 1L;

    public Fornecedor(String cnpj_cpf, Tipo_fornecedor tipo, String nome, String endereco, String telefone){
        this.cnpj_cpf = cnpj_cpf;
        this.tipo = tipo;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataCadastro = LocalDate.now();
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
                ", dataCadastro=" + dataCadastro +
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

    public Tipo_fornecedor getTipo() {
        return tipo;
    }

    public void setTipo(Tipo_fornecedor tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
