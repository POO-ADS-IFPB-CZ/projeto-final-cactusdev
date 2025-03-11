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
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaFornecedor extends JDialog {
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
        setModal(true);
        setContentPane(contentPane);
        setTitle("Fornecedor");
        getRootPane().setDefaultButton(buttonOK);
        pack();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);

        fornecedorService.mostrarFornecedoresNaTabela(tableModel);

        cadastrarFornecedorButton.addActionListener((e -> {
            CadastrarFornecedor cadastrarFornecedor = new CadastrarFornecedor(tableModel);
            cadastrarFornecedor.setLocationRelativeTo(this);
            cadastrarFornecedor.setVisible(true);
        }));

        buscarButton.addActionListener((e -> buscarFornecedores()));

        sairButton.addActionListener((e) -> fecharJanela());

        verificarIsAlterarProduto(isAlterarProduto);

        selecionarButton.addActionListener((e -> verificarAndSelecionarFornecedor()));

        alterarFornecedorButton.addActionListener((e) -> abrirEdicaoFornecedor());

        configurarTeclaESC();

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
                } else if (e.getKeyCode() == KeyEvent.VK_F5) {
                    abrirEdicaoFornecedor();
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

    private void configurarTeclaESC() {
        JRootPane rootPane = this.getRootPane();
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, "fecharJanela");
        rootPane.getActionMap().put("fecharJanela", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fecharJanela();
            }
        });
    }

    private void verificarIsAlterarProduto(boolean isAlterarProduto) {
        if (!isAlterarProduto) {
            selecionarButton.setVisible(false);
        } else {
            alterarFornecedorButton.setVisible(false);
            tabela_fornecedores.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        verificarAndSelecionarFornecedor();
                    }
                }
            });
        }
    }

    private void verificarAndSelecionarFornecedor() {
        if (selecionarFornecedor()) {
            fecharJanela();
        }
    }

    private boolean selecionarFornecedor() {
        int row = tabela_fornecedores.getSelectedRow();
        if (row != -1) {
            String cnpj = (String) tabela_fornecedores.getValueAt(row, 0);
            fornecedorSelecionado = fornecedorService.getFornecedorPorCnpj(cnpj);

            if (fornecedorSelecionado == null) {
                throw new RuntimeException();
            }

            return true;
        } else {
            Faill.show(null, "Selecione um fornecedor válido");
            return false;
        }
    }

    private void abrirEdicaoFornecedor() {
        if (selecionarFornecedor()) {
            FornecedorAlterar fornecedorAlterar = new FornecedorAlterar(fornecedorSelecionado, tableModel);
            fornecedorAlterar.setVisible(true);
        }
    }

    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public static void main(String[] args) {
        TelaFornecedor dialog = new TelaFornecedor(false);
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        tabela_fornecedores = new JTable();
        String[] colunas = {"CNPJ","Tipo_pessoa", "Nome", "Telefone", "Endereço", "Data de cadastro"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela_fornecedores.setModel(tableModel);
        rowSorter = new TableRowSorter<>(tableModel);
        tabela_fornecedores.setRowSorter(rowSorter);
    }
}
