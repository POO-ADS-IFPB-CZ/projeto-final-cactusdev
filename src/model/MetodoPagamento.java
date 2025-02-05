package src.model;

import java.util.Objects;

public class MetodoPagamento {
    private int id;
    private String tipo;
    private boolean ativo;

    // Construtor
    public MetodoPagamento(int id, String tipo, boolean ativo) {
        this.id = id;
        this.tipo = tipo;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "MetodoPagamento{" + "id=" + id + ", tipo='" + tipo + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetodoPagamento that = (MetodoPagamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

