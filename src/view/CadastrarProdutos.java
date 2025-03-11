package src.view;

import src.controller.ProdutoController;
import src.model.Fornecedor;
import src.services.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CadastrarProdutos extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> unidade_medida;
    private JTextField valor_unitario;
    private JTextField qtd_estoque;
    private JTextField descricao;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JComboBox<String> categoria;
    private JTextField input_fornecedor;
    private JButton adicionarF2Button;
    private Fornecedor fornecedorSelecionado = null;

    public CadastrarProdutos(DefaultTableModel tabelaProdutos, ProdutoService produtoService) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(confirmarButton);
        setTitle("Cadastro de Produtos");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        confirmarButton.addActionListener(e -> {
            String descricaoProduto = descricao.getText();
            String precoUnitario = valor_unitario.getText();
            String unidadeMedida = (String) unidade_medida.getSelectedItem();
            String qtdEstoque = qtd_estoque.getText();
            String categoriaProduto = (String) categoria.getSelectedItem();

            if (produtoService.criarProduto(descricaoProduto, precoUnitario, unidadeMedida, qtdEstoque, categoriaProduto, fornecedorSelecionado)) {
                produtoService.atualizarTabela(tabelaProdutos);
                dispose();
            }


        });

        eventosAdicionarFornecedor();

        cancelarButton.addActionListener((e)-> {
            dispose();
        });
    }

    private void adicionarFornecedor(){
        TelaFornecedor telaFornecedor = new TelaFornecedor(true);
        telaFornecedor.setLocationRelativeTo(this);
        telaFornecedor.setVisible(true);

        fornecedorSelecionado = telaFornecedor.getFornecedorSelecionado();
        if (fornecedorSelecionado != null){
            input_fornecedor.setText(fornecedorSelecionado.getNome());
        }
    }

    private void eventosAdicionarFornecedor(){
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "adicionarFornecedor");

        getRootPane().getActionMap().put("adicionarFornecedor", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarFornecedor();
            }
        });

        adicionarF2Button.addActionListener((e)-> {
            adicionarFornecedor();
        });
    }

    private void limparCampos(){
        descricao.setText("");
        valor_unitario.setText("");
        qtd_estoque.setText("");
    }
}
