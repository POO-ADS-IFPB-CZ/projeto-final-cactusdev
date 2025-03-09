package src.view;

import src.controller.ProdutoController;
import src.services.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

    public CadastrarProdutos(DefaultTableModel tabelaProdutos) {
        produtoService = new ProdutoService(new ProdutoController());
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Cadastro de Produtos");
        pack();
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
                produtoService.atualizarTabela(tabelaProdutos);
                dispose();
            }


        });

        cancelarButton.addActionListener((e)-> {
            dispose();
        });
    }

    private void limparCampos(){
        descricao.setText("");
        valor_unitario.setText("");
        qtd_estoque.setText("");
    }
}
