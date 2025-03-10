package src.view;

import src.model.Cliente;
import src.services.VendaItensService;
import src.services.formatters.ValorParaDinheiro;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FinalizarVenda extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField total;
    private JTextField troco;
    private JComboBox<String> metodo_pagamento;
    private JTextField valor_pago;

    public FinalizarVenda(VendaItensService vendaItensService, DefaultTableModel tableModel) {
        setTitle("Finalizar venda");
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);

        total.setText(ValorParaDinheiro.converter(vendaItensService.getSubtotalVendaAtual(), "pt", "BR"));
        valor_pago.setText(ValorParaDinheiro.converter(vendaItensService.getSubtotalVendaAtual(), "pt", "BR"));
        troco.setText("0.00");

        // Define o comportamento do campo valor_pago baseado no método de pagamento
        metodo_pagamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (metodo_pagamento.getSelectedItem().equals("DINHEIRO")) {
                    valor_pago.setEnabled(true);
                } else {
                    valor_pago.setEnabled(false);
                    valor_pago.setText(total.getText());
                    troco.setText("0.00");
                }
            }
        });

        buttonCancel.addActionListener((e)-> dispose());

        buttonOK.addActionListener((e)-> {
            try{
                vendaItensService.finalizarVenda((String) metodo_pagamento.getSelectedItem(),valor_pago.getText(),vendaItensService.getSubtotalVendaAtual(),tableModel);
                this.dispose();
            } catch (IllegalArgumentException ex) {
                Faill.show(this,ex.getMessage());
            }
        });

        // Adiciona listener para calcular o troco automaticamente
        valor_pago.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> calcularTroco(vendaItensService.getSubtotalVendaAtual()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> calcularTroco(vendaItensService.getSubtotalVendaAtual()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> calcularTroco(vendaItensService.getSubtotalVendaAtual()));
            }
        });

        // Restringe o campo valor_pago para aceitar apenas números e ponto decimal
        valor_pago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.') {
                    e.consume(); // Bloqueia caracteres não numéricos
                }

                // Impede mais de um ponto decimal
                if (c == '.' && valor_pago.getText().contains(".")) {
                    e.consume();
                }
            }
        });
    }

    private void calcularTroco(double totalVenda) {
        double valorPago = 0;

        try {
            String textoValorPago = valor_pago.getText().trim();
            if (!textoValorPago.isEmpty()) {
                valorPago = Double.parseDouble(textoValorPago);
            }
        } catch (NumberFormatException e) {
            // Se der erro, deixa o troco como 0 e sai da função
            troco.setText("0.00");
            return;
        }

        double trocoCalculado = Math.max(0, valorPago - totalVenda);
        troco.setText(ValorParaDinheiro.converter(trocoCalculado, "pt", "BR"));
    }
}
