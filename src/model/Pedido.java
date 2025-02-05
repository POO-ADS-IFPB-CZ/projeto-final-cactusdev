package src.model;

import java.util.Objects;
import java.util.Date;

public class Pedido {
    private int codigo;
    private Date data;
    private double subtotal;
    private String status;
    private int idFornecedor;

    // Construtor
    public Pedido(int codigo, Date data, double subtotal, String status, int idFornecedor) {
        this.codigo = codigo;
        this.data = data;
        this.subtotal = subtotal;
        this.status = status;
        this.idFornecedor = idFornecedor;
    }

    // Getters e Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", data=" + data +
                ", subtotal=" + subtotal +
                ", status='" + status + '\'' +
                ", idFornecedor=" + idFornecedor +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return codigo == pedido.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

