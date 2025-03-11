package src.services.validators;

import src.services.formatters.ValorParaDinheiro;
import src.view.customErrors.Faill;

import java.awt.*;

public class ValidatorVenda {
    public static void verificarFinalizarVenda(String metodoPagamento, String valorPagoStr, double totalVenda) throws IllegalArgumentException{

        if (totalVenda <= 0){
            throw new IllegalArgumentException("Venda zerada, adicione itens para finalizar a compra");
        }

        if (metodoPagamento == null || metodoPagamento.isEmpty()) {
            throw new IllegalArgumentException("Selecione um método de pagamento.");
        }

        double valorPago;
        try {
            valorPago = Double.parseDouble(ValorParaDinheiro.converterParaDouble(valorPagoStr.trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Valor pago inválido");
        }

        // Se for dinheiro, o valor pago precisa ser maior ou igual ao total da venda
        if (metodoPagamento.equals("DINHEIRO") && valorPago < totalVenda) {
            throw new IllegalArgumentException("O valor pago deve ser igual ou maior que o total da venda.");
        }
    }
}
