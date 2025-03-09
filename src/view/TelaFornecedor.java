package src.view;

import src.model.Fornecedor;
import src.services.FornecedorService;
import src.services.filters.FornecedorFilters;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaFornecedor extends JFrame {
    private JPanel contentPane;
    private JButton alterarFornecedorButton;
    private JButton cadastrarFornecedorButton;
    private JButton sairButton;
    private JTextField nome;
    private JComboBox opcao_busca;
    private JTable tabela_fornecedores;
    private JTextField cnpj;
    private JButton selecionarButton;
    private JButton buscarButton;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton buttonOK;
    private final FornecedorService fornecedorService;
    private DefaultTableModel tableModel;
    private Fornecedor fornecedorSelecionado = null;

    public TelaFornecedor(boolean isAlterarProduto) {
        fornecedorService = new FornecedorService();
        setContentPane(contentPane);
        setTitle("Fornecedor");
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fornecedorService.mostrarFornecedoresNaTabela(tableModel);

        cadastrarFornecedorButton.addActionListener((e -> {
            CadastrarFornecedor cadastrarFornecedor = new CadastrarFornecedor(tableModel);
            cadastrarFornecedor.setVisible(true);
        }));

        verificarIsAlterarProduto(isAlterarProduto);

        buscarButton.addActionListener((e -> buscarFornecedores()));

        sairButton.addActionListener((e) -> fecharJanela());

        selecionarButton.addActionListener((e -> selecionarFornecedor()));

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (e.getComponent() == nome) {
                        opcao_busca.setSelectedItem("NOME");
                    } else if (e.getComponent() == cnpj) {
                        opcao_busca.setSelectedItem("CNPJ");
                    }
                    buscarFornecedores();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    fecharJanela();
                }
            }
        };

        nome.addKeyListener(keyAdapter);
        cnpj.addKeyListener(keyAdapter);
        opcao_busca.addKeyListener(keyAdapter);
        tabela_fornecedores.addKeyListener(keyAdapter);
        sairButton.addKeyListener(keyAdapter);
    }

    private void fecharJanela() {
        dispose();
    }

    private void verificarIsAlterarProduto(boolean isAlterarProduto){
        if (!isAlterarProduto){
            selecionarButton.setVisible(false);
        }else{
            alterarFornecedorButton.setVisible(false);

            //evento para selecionar produto na tabela, só funciona se estiver na venda
            tabela_fornecedores.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        selecionarFornecedor();
                    }
                }
            });
        }
    }

    private void buscarFornecedores() {
        String opcaoSelecionada = (String) opcao_busca.getSelectedItem();

        switch (opcaoSelecionada) {
            case "TODOS":
                FornecedorFilters.limparFiltros(rowSorter);
                break;
            case "CNPJ":
                FornecedorFilters.filtrarTabelaPorCnpj(cnpj.getText(), rowSorter);
                break;
            case "NOME":
                FornecedorFilters.filtrarTabelaPorNome(nome.getText(), rowSorter);
                break;
            default:
                Faill.show(this, "Nenhuma opção de busca selecionada");
        }
    }

    private void selecionarFornecedor() {
        int row = tabela_fornecedores.getSelectedRow();
        if (row != -1) {
            String cnpj = (String) tabela_fornecedores.getValueAt(row, 0);
            fornecedorSelecionado = fornecedorService.getFornecedorPorCnpj(cnpj);

            if (fornecedorSelecionado == null) {
                throw new RuntimeException();
            }

            fecharJanela();
        } else {
            Faill.show(null, "Selecione um fornecedor válido");
        }
    }

    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public static void main(String[] args) {
        TelaFornecedor dialog = new TelaFornecedor(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        tabela_fornecedores = new JTable();
        String[] colunas = {"CNPJ", "Nome", "Telefone", "Endereço", "Data de cadastro"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição das células
            }
        };

        tabela_fornecedores.setModel(tableModel);

        rowSorter = new TableRowSorter<>(tableModel);
        tabela_fornecedores.setRowSorter(rowSorter);
    }
}
