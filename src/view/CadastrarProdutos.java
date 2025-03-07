package src.view;

import src.controller.ProdutoController;
import src.services.ProdutoService;

import javax.swing.*;
import java.awt.*;

public class CadastrarProdutos extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> unidade_medida;
    private JTextField valor_unitario;
    private JTextField qtd_estoque;
    private JTextField descricao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JComboBox<String> categoria;
    private final ProdutoService produtoService;

    public CadastrarProdutos(Frame parent) {
        super(parent, "Cadastro de Produtos", true);
        produtoService = new ProdutoService(new ProdutoController());
        setContentPane(contentPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);

        confirmarButton.addActionListener(e -> {
            String descricaoProduto = descricao.getText();
            String precoUnitario = valor_unitario.getText();
            String unidadeMedida = (String) unidade_medida.getSelectedItem();
            String qtdEstoque = qtd_estoque.getText();
            String categoriaProduto = (String) categoria.getSelectedItem();

            if (produtoService.criarProduto(descricaoProduto, precoUnitario, unidadeMedida, qtdEstoque, categoriaProduto)) {
                limparCampos();
            }
        });

        cancelarButton.addActionListener(e -> dispose());
    }

    private void limparCampos() {
        descricao.setText("");
        valor_unitario.setText("");
        qtd_estoque.setText("");
    }
}
