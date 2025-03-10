package src.view;

import src.controller.ProdutoController;
import src.model.Produto;
import src.services.ProdutoService;
import src.services.filters.ProdutoFilters;
import src.view.customErrors.Faill;
import src.view.customErrors.Success;

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
    private final ProdutoService produtoService = new ProdutoService(new ProdutoController());
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public Produtos(boolean isVenda) {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Produtos");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);


        produtoService.mostrarProdutosNaTabela(tableModel);

        buscarButton.addActionListener((e) -> buscarProdutos());

        // Evento para sair ao clicar no botão "Sair (ESC)"
        sairESCButton.addActionListener((e) -> fecharJanela());
        verificarIsVenda(isVenda);

        selecionarButton.addActionListener((e) -> selecionarProduto());

        cadastrarProdutoButton.addActionListener((e)-> {
            CadastrarProdutos cadastrarProdutos = new CadastrarProdutos(tableModel);
            cadastrarProdutos.setLocationRelativeTo(this);
            cadastrarProdutos.setVisible(true);
        });

        // Adiciona evento para detectar Enter nos campos de pesquisa e ESC para sair
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (e.getComponent() == inputDescricao) {
                        opcaoBusca.setSelectedItem("DESCRICAO");
                    } else if (e.getComponent() == inputCodigo) {
                        opcaoBusca.setSelectedItem("CODIGO");
                    } else if (e.getComponent() == selectCategoria) {
                        opcaoBusca.setSelectedItem("CATEGORIA");
                    }
                    buscarProdutos();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    fecharJanela();
                }
            }
        };

        inputDescricao.addKeyListener(keyAdapter);
        inputCodigo.addKeyListener(keyAdapter);
        selectCategoria.addKeyListener(keyAdapter);
        tabelaProdutos.addKeyListener(keyAdapter);
        sairESCButton.addKeyListener(keyAdapter);
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
            case "CATEGORIA":
                ProdutoFilters.filtrarTabelaPorCategoria((String) selectCategoria.getSelectedItem(), rowSorter);
                break;
            default:
                Faill.show(this, "Nenhuma opção de busca selecionada");
        }
    }

    private void verificarIsVenda(Boolean isVenda){
        if (!isVenda){
            selecionarButton.setVisible(false);

        }else{
            alterarProdutoButton.setVisible(false);

            //evento para selecionar produto na tabela, só funciona se estiver na venda
            tabelaProdutos.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        selecionarProduto();
                    }
                }
            });
        }
    }

    private void fecharJanela() {
        dispose(); // Fecha a janela
    }

    private void selecionarProduto() {
        int row = tabelaProdutos.getSelectedRow();
        if (row != -1) {
            String codigo = (String) tabelaProdutos.getValueAt(row, 0);
            produtoSelecionado= new ProdutoController()
                    .pegarProdutoPorCodigo(codigo)
                    .orElse(null);

            if (produtoSelecionado == null){
                throw new RuntimeException();
            }

            fecharJanela();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public static void main(String[] args) {
        Produtos dialog = new Produtos(true);
        dialog.setVisible(true);
        //System.exit(0);
    }

    private void createUIComponents() {
        tabelaProdutos = new JTable();

        String[] colunas = {"Codigo", "Descricao", "Preco", "Estoque", "Categoria", "Medida"};
        tableModel = new DefaultTableModel(colunas, 0);
        tabelaProdutos.setModel(tableModel);

        rowSorter = new TableRowSorter<>(tableModel);
        tabelaProdutos.setRowSorter(rowSorter);
    }
}
