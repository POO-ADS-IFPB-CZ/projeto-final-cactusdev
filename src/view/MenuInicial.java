package src.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        produtoButton.addActionListener((e)-> {
            Produtos produtos = new Produtos(false);
            produtos.setVisible(true);
        });
    }

    public static void main(String[] args) {
        MenuInicial dialog = new MenuInicial();
        dialog.pack();
        dialog.setVisible(true);
    }
}
