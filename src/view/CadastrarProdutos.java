package src.view;

import src.controller.ProdutoController;
import src.services.ProdutoService;

import javax.swing.*;

public class CadastrarProdutos extends JFrame {
    private JPanel contentPane;
    private JComboBox unidade_medida;
    private JTextField valor_unitario;
    private JTextField qtd_estoque;
    private JTextField descricao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JComboBox categoria;
    private JButton buttonOK;
    private final ProdutoService produtoService;

    public CadastrarProdutos() {
        produtoService = new ProdutoService(new ProdutoController());
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        confirmarButton.addActionListener((e)-> {
            System.out.println(new ProdutoController().listarProdutos());
            String descricaoProduto = descricao.getText();
            String precoUnitario = valor_unitario.getText();
            String unidadeMedida = (String) unidade_medida.getSelectedItem();
            String qtdEstoque = qtd_estoque.getText();
            String categoriaProduto = (String) categoria.getSelectedItem();

            if (produtoService.criarProduto(descricaoProduto, precoUnitario, unidadeMedida, qtdEstoque, categoriaProduto)) limparCampos();


        });

        cancelarButton.addActionListener((e)-> {
            dispose();
        });
    }

    public static void main(String[] args) {
        CadastrarProdutos dialog = new CadastrarProdutos();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void limparCampos(){
        descricao.setText("");
        valor_unitario.setText("");
        qtd_estoque.setText("");
    }
}
