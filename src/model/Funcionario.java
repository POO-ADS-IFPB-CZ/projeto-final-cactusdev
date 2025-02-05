package src.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Funcionario {
    private int codigo;
    private Date dataAdmissao;
    private String nome;
    private Date dataNascimento;
    private String cargo;
    private String telefone;
    private boolean ativo;

    // Construtor
    public Funcionario(int codigo, Date dataAdmissao, String nome, Date dataNascimento, String cargo, String telefone, boolean ativo) {
        this.codigo = codigo;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "codigo=" + codigo + ", nome='" + nome + '\'' + '}';
    }

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return codigo == that.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

