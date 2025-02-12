package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class FinalizarVendaModal {

    public static void abrirFinalizarVendaModal() {
        // Cores
        Color corFundo = new Color(0xF4F4F4);
        Color corTexto = new Color(0x000000);
        Color corCampo = new Color(0xFFFFFF);

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(corFundo);

        // Campos
        JTextField valorTotalField = new JTextField(10);
        valorTotalField.setBackground(corCampo);
        valorTotalField.setEditable(false);

        JTextField valorEntregueField = new JTextField(10);
        valorEntregueField.setBackground(corCampo);

        JTextField trocoField = new JTextField(10);
        trocoField.setBackground(corCampo);
        trocoField.setEditable(false);

        // Opções de pagamento
        String[] metodosPagamento = {"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix"};
        JComboBox<String> metodoPagamentoBox = new JComboBox<>(metodosPagamento);

        // Rótulos
        JLabel valorTotalLabel = new JLabel("Valor Total:");
        JLabel valorEntregueLabel = new JLabel("Valor Entregue:");
        JLabel trocoLabel = new JLabel("Troco:");
        JLabel metodoPagamentoLabel = new JLabel("Método de Pagamento:");

        valorTotalLabel.setForeground(corTexto);
        valorEntregueLabel.setForeground(corTexto);
        trocoLabel.setForeground(corTexto);
        metodoPagamentoLabel.setForeground(corTexto);

        // Adiciona os componentes ao painel
        panel.add(valorTotalLabel);
        panel.add(valorTotalField);
        panel.add(valorEntregueLabel);
        panel.add(valorEntregueField);
        panel.add(trocoLabel);
        panel.add(trocoField);
        panel.add(metodoPagamentoLabel);
        panel.add(metodoPagamentoBox);

        // Formatar valores monetários
        DecimalFormat df = new DecimalFormat("0.00");

        // Evento para calcular troco automaticamente
        valorEntregueField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    double valorTotal = Double.parseDouble(valorTotalField.getText().isEmpty() ? "0" : valorTotalField.getText());
                    double valorEntregue = Double.parseDouble(valorEntregueField.getText().isEmpty() ? "0" : valorEntregueField.getText());
                    double troco = valorEntregue - valorTotal;
                    trocoField.setText(df.format(Math.max(troco, 0)));
                } catch (NumberFormatException ignored) {
                    trocoField.setText("");
                }
            }
        });

        // Configurar botões do JOptionPane
        UIManager.put("OptionPane.okButtonText", "Finalizar Venda");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // Exibir modal
        int result;
        do {
            result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Finalizar Venda",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                String valorTotal = valorTotalField.getText().trim();
                String valorEntregue = valorEntregueField.getText().trim();
                String troco = trocoField.getText().trim();
                String metodoPagamento = (String) metodoPagamentoBox.getSelectedItem();

                // Validação
                if (valorTotal.isEmpty() || valorEntregue.isEmpty() || metodoPagamento == null) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!", "Erro", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // Exibir resumo
                JOptionPane.showMessageDialog(null, "Venda Finalizada!\n" +
                        "Valor Total: R$" + valorTotal + "\n" +
                        "Valor Entregue: R$" + valorEntregue + "\n" +
                        "Troco: R$" + troco + "\n" +
                        "Método de Pagamento: " + metodoPagamento, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                break;
            }
        } while (true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FinalizarVendaModal::abrirFinalizarVendaModal);
    }
}
