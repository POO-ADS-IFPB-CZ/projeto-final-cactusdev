package src.model;

import java.util.Date;
import java.util.Objects;

public class Cliente {
    private String cpf;
    private String nome;
    private String telefone;
    private Date dataCadastro;

    // Construtor
    public Cliente(String cpf, String nome, String telefone, Date dataCadastro) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataCadastro = dataCadastro;
    }

    // Getters and Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Cliente{" + "cpf='" + cpf + '\'' + ", nome='" + nome + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
