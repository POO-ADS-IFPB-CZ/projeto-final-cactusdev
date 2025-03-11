package src.view;

import src.model.Fornecedor;
import src.model.Produto;
import src.services.ProdutoService;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class ProdutoAlterar extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JTextField input_nome;
    private JTextField input_quantidade;
    private JTextField input_valor;
    private JButton alterarButton;
    private JComboBox<String> input_categoria;
    private JComboBox<String> unidade_medida;
    private JTextField input_fornecedor;
    private JButton adicionarF2Button;
    private JTextField input_codigo;
    private final Produto produto;
    private final ProdutoService produtoService;
    private Fornecedor fornecedorSelecionado;

    public ProdutoAlterar(Produto produtoSelecionado, DefaultTableModel tableModel) {
        produtoService = new ProdutoService();
        this.produto = produtoSelecionado;
        setContentPane(contentPane);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        setInformacoesProduto();
        eventoEditarProduto(tableModel);
        eventoFecharJanela();
        eventoTeclaESC();
    }

    private void eventoEditarProduto(DefaultTableModel tableModel) {
        alterarButton.addActionListener((e) -> {
            String nomeInput = input_nome.getText();
            String quantidadeInput = input_quantidade.getText();
            String valorInput = input_valor.getText();
            String categoria = (String) input_categoria.getSelectedItem();
            String unidade = (String) unidade_medida.getSelectedItem();
            String fornecedor = input_fornecedor.getText();

            boolean foiModificado = !nomeInput.equals(produto.getDescricao()) ||
                    !quantidadeInput.equals(String.valueOf(produto.getQtdEstoque())) ||
                    !valorInput.equals(String.valueOf(produto.getPreco())) ||
                    !Objects.equals(categoria, produto.getCategoria().toString()) ||
                    !Objects.equals(unidade, produto.getMedida()) ||
                    !fornecedor.equals(produto.getFornecedor().getNome());

            if (foiModificado) {
                if (produtoService.editarProduto(nomeInput, valorInput, unidade, quantidadeInput, categoria, fornecedorSelecionado, produto)) {
                    produtoService.mostrarProdutosNaTabela(tableModel);
                }
            } else {
                Faill.show(this, "Nenhuma alteração foi feita!");
            }
        });

        adicionarF2Button.addActionListener((e) -> abrirTelaFornecedor());

        eventoTeclaF2();

    }

    private void eventoFecharJanela() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void eventoTeclaF2() {
        contentPane.registerKeyboardAction((e) -> abrirTelaFornecedor(),
                KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void abrirTelaFornecedor() {
        TelaFornecedor telaFornecedor = new TelaFornecedor(true);
        telaFornecedor.setLocationRelativeTo(this);
        telaFornecedor.setVisible(true);

        fornecedorSelecionado = telaFornecedor.getFornecedorSelecionado();
        if (fornecedorSelecionado != null) {
            input_fornecedor.setText(fornecedorSelecionado.getNome());
        }
    }


    private void eventoTeclaESC() {
        contentPane.registerKeyboardAction((e) -> fecharJanela(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void fecharJanela() {
        dispose();
    }

    private void setInformacoesProduto() {
        input_codigo.setText(produto.getCodigo());
        input_nome.setText(produto.getDescricao());
        input_quantidade.setText(String.valueOf(produto.getQtdEstoque()));
        input_valor.setText(String.valueOf(produto.getPreco()));
        input_categoria.setSelectedItem(produto.getCategoria());
        unidade_medida.setSelectedItem(produto.getMedida());
        input_fornecedor.setText(produto.getFornecedor().getNome());
        fornecedorSelecionado = produto.getFornecedor();
    }
}