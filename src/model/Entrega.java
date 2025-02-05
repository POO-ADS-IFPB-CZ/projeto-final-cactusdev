package src.model;

import java.util.Date;
import java.util.Objects;

public class Entrega {
    private int id;
    private String rua;
    private String bairro;
    private String cep;
    private String status;
    private Date prazo;
    private int codFuncionario;
    private int codVenda;

    // Construtor
    public Entrega(int id, String rua, String bairro, String cep, String status, Date prazo, int codFuncionario, int codVenda) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.status = status;
        this.prazo = prazo;
        this.codFuncionario = codFuncionario;
        this.codVenda = codVenda;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Entrega{" + "id=" + id + ", status='" + status + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return id == entrega.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

