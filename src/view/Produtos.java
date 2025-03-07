package src.view;

import src.controller.ProdutoController;
import src.services.ProdutoService;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Produtos extends JFrame {
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
    private JButton buttonOK;
    private final ProdutoService produtoService = new ProdutoService(new ProdutoController());
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public Produtos() {
        setContentPane(contentPane);
        setTitle("Produtos");
        getRootPane().setDefaultButton(buttonOK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        produtoService.mostrarProdutosNaTabela(tableModel);

        buscarButton.addActionListener((e) -> buscarProdutos());

        // Evento para sair ao clicar no botão "Sair (ESC)"
        sairESCButton.addActionListener((e) -> fecharJanela());

        cadastrarProdutoButton.addActionListener((e) -> {
            CadastrarProdutos cadastrarProdutos = new CadastrarProdutos(this);
            cadastrarProdutos.setVisible(true);
        });

        // Adiciona evento para detectar Enter nos campos de pesquisa e ESC para sair
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
                rowSorter.setRowFilter(null);
                break;
            case "CODIGO":
                filtrarTabelaPorCodigo();
                break;
            case "DESCRICAO":
                filtrarTabelaPorDescricao();
                break;
            case "CATEGORIA":
                filtrarTabelaPorCategoria();
                break;
            default:
                Faill.show(this, "Nenhuma opção de busca selecionada");
        }
    }

    private void filtrarTabelaPorCodigo() {
        String codigoFiltro = inputCodigo.getText().trim();

        if (codigoFiltro.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("^" + codigoFiltro + "$", 0));
        }
    }

    private void filtrarTabelaPorDescricao() {
        String descricaoFiltro = inputDescricao.getText().trim();

        if (descricaoFiltro.isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + descricaoFiltro, 1));
        }
    }

    private void filtrarTabelaPorCategoria() {
        String categoriaFiltro = (String) selectCategoria.getSelectedItem();

        if (categoriaFiltro == null || categoriaFiltro.trim().isEmpty()) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + categoriaFiltro, 4));
        }
    }

    private void fecharJanela() {
        dispose(); // Fecha a janela
    }

    public static void main(String[] args) {
        Produtos dialog = new Produtos();
        dialog.pack();
        dialog.setVisible(true);
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
