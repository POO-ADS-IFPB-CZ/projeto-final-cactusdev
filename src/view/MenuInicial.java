package src.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuInicial extends JFrame {
    private JPanel contentPane;
    private JButton vendaButton;
    private JButton produtoButton;
    private JButton clienteButton;
    private JButton fornecedorButton;
    private JButton sairButton;
    private JPanel JPanel;
    private JButton buttonOK;

    public MenuInicial() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        ImageIcon icon = new ImageIcon("src/view/img/log.png");
        setIconImage(icon.getImage());
        setTitle("PV Miudezas para Construção");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton[] botoes = {vendaButton, produtoButton, clienteButton, fornecedorButton, sairButton};
        for (JButton botao : botoes) {
            botao.setFocusPainted(false);
        }

        vendaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaVenda vendaTela = new TelaVenda();
                vendaTela.setVisible(true);
            }
        });

        produtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos produtosTela = new Produtos(false);
                produtosTela.setVisible(true);
            }
        });

        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCliente telaCliente = new TelaCliente(false);
                telaCliente.setVisible(true);
            }
        });

        fornecedorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaFornecedor fornecedorTela = new TelaFornecedor();
                fornecedorTela.setVisible(true);
                adicionarFechamentoESC(fornecedorTela);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void adicionarFechamentoESC(JFrame frame) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        MenuInicial dialog = new MenuInicial();
        dialog.pack();
        dialog.setVisible(true);
    }
}
