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

        // Garante que a janela receba eventos do teclado
        setFocusable(true);
        requestFocusInWindow();
    }

    public static void main(String[] args) {
        MenuInicial dialog = new MenuInicial();
        dialog.pack();
        dialog.setVisible(true);
    }
}
