package src.model;

import java.util.Date;
import java.util.Objects;

public class Pagamento {
    private Date data;
    private double valorPago;
    private int codVenda;
    private int codMetodoPagamento;

    // Construtor
    public Pagamento(Date data, double valorPago, int codVenda, int codMetodoPagamento) {
        this.data = data;
        this.valorPago = valorPago;
        this.codVenda = codVenda;
        this.codMetodoPagamento = codMetodoPagamento;
    }

    // Getters and Setters
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public int getCodMetodoPagamento() {
        return codMetodoPagamento;
    }

    public void setCodMetodoPagamento(int codMetodoPagamento) {
        this.codMetodoPagamento = codMetodoPagamento;
    }

    // toString, equals, hashCode
    @Override
    public String toString() {
        return "Pagamento{" + "data=" + data + ", valorPago=" + valorPago + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Double.compare(pagamento.valorPago, valorPago) == 0 && codVenda == pagamento.codVenda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valorPago, codVenda);
    }
}

