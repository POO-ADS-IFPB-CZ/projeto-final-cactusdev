package src.model;

import java.util.Date;
import java.util.Objects;

public class Venda {
    private int codigo;
    private double subtotal;
    private double desconto;
    private Date data;
    private int codFuncionario;
    private String cpfCliente;
    private int notaVenda;

    // Construtor
    public Venda(int codigo, double subtotal, double desconto, Date data, int codFuncionario, String cpfCliente, int notaVenda) {
        this.codigo = codigo;
        this.subtotal = subtotal;
        this.desconto = desconto;
        this.data = data;
        this.codFuncionario = codFuncionario;
        this.cpfCliente = cpfCliente;
        this.notaVenda = notaVenda;
    }

    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public int getNotaVenda() {
        return notaVenda;
    }

    public void setNotaVenda(int notaVenda) {
        this.notaVenda = notaVenda;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Venda{" + "codigo=" + codigo + ", subtotal=" + subtotal + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venda venda = (Venda) o;
        return codigo == venda.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}

