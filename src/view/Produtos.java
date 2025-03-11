package src.view;

import src.controller.ProdutoController;
import src.model.Produto;
import src.services.ProdutoService;
import src.services.filters.ProdutoFilters;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Produtos extends JDialog {
    private JPanel contentPane;
    private JTextField inputDescricao;
    private JTextField inputCodigo;
    private JComboBox<String> selectCategoria;
    private JComboBox<String> opcaoBusca;
    private JButton buscarButton;
    private JTable tabelaProdutos;
    private JButton sairESCButton;
    private JButton alterarProdutoButton;
    private JButton cadastrarProdutoButton;
    private JPanel panelBotoes;
    private JButton selecionarButton;
    private JButton buttonOK;
    private Produto produtoSelecionado = null;
    private final ProdutoService produtoService;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public Produtos(boolean isVenda) {
        produtoService = new ProdutoService();
        setModal(true);
        setContentPane(contentPane);
        setTitle("Produtos");
        pack();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);

        produtoService.mostrarProdutosNaTabela(tableModel);

        cadastrarProdutoButton.addActionListener((e -> {
            CadastrarProdutos cadastrarProduto = new CadastrarProdutos(tableModel);
            cadastrarProduto.setLocationRelativeTo(this);
            cadastrarProduto.setVisible(true);
        }));

        buscarButton.addActionListener((e -> buscarProdutos()));
        sairESCButton.addActionListener((e) -> fecharJanela());
        verificarIsVenda(isVenda);
        selecionarButton.addActionListener((e -> verificarAndSelecionarProduto()));
        alterarProdutoButton.addActionListener((e) -> abrirEdicaoProduto());

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (e.getComponent() == inputDescricao) {
                        opcaoBusca.setSelectedItem("DESCRICAO");
                    } else if (e.getComponent() == inputCodigo) {
                        opcaoBusca.setSelectedItem("CODIGO");
                    }
                    buscarProdutos();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    fecharJanela();
                } else if (e.getKeyCode() == KeyEvent.VK_F5) {
                    abrirEdicaoProduto();
                }
            }
        };

        inputDescricao.addKeyListener(keyAdapter);
        inputCodigo.addKeyListener(keyAdapter);
        opcaoBusca.addKeyListener(keyAdapter);
        tabelaProdutos.addKeyListener(keyAdapter);
        sairESCButton.addKeyListener(keyAdapter);
    }

    private void fecharJanela() {
        dispose();
    }

    private void buscarProdutos() {
        String opcaoSelecionada = (String) opcaoBusca.getSelectedItem();

        switch (opcaoSelecionada) {
            case "TODOS":
                ProdutoFilters.limparFiltros(rowSorter);
                break;
            case "CODIGO":
                ProdutoFilters.filtrarTabelaPorCodigo(inputCodigo.getText(), rowSorter);
                break;
            case "DESCRICAO":
                ProdutoFilters.filtrarTabelaPorDescricao(inputDescricao.getText(), rowSorter);
                break;
            default:
                Faill.show(this, "Nenhuma opção de busca selecionada");
        }
    }

    private void verificarIsVenda(boolean isVenda) {
        if (!isVenda) {
            selecionarButton.setVisible(false);
        } else {
            alterarProdutoButton.setVisible(false);
            tabelaProdutos.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        verificarAndSelecionarProduto();
                    }
                }
            });
        }
    }

    private void verificarAndSelecionarProduto() {
        if (selecionarProduto()) {
            fecharJanela();
        }
    }

    private boolean selecionarProduto() {
        int row = tabelaProdutos.getSelectedRow();
        if (row != -1) {
            String codigoProduto = (String) tabelaProdutos.getValueAt(row, 0);
            produtoSelecionado = produtoService.getProdutoPorCodigo(codigoProduto);

            if (produtoSelecionado == null) {
                throw new RuntimeException();
            }
            return true;
        } else {
            Faill.show(null, "Selecione um produto válido");
            return false;
        }
    }

    private void abrirEdicaoProduto() {
        if (selecionarProduto()) {
            ProdutoAlterar produtoAlterar = new ProdutoAlterar(produtoSelecionado, tableModel);
            produtoAlterar.setVisible(true);
        }
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public static void main(String[] args) {
        Produtos dialog = new Produtos(false);
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        tabelaProdutos = new JTable();

        String[] colunas = {"Codigo", "Descricao", "Preco", "Estoque", "Categoria", "Medida", "Fornecedor"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaProdutos.setModel(tableModel);

        rowSorter = new TableRowSorter<>(tableModel);
        tabelaProdutos.setRowSorter(rowSorter);
    }
}
