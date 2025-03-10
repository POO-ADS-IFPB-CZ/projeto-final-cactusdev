package src.view;

import src.model.Cliente;
import src.model.Item_produto;
import src.model.Produto;
import src.services.VendaItensService;
import src.services.adapters.GenerateWithDateRandom;
import src.services.formatters.ValorParaDinheiro;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class TelaVenda extends JFrame {
    private JPanel contentPane;
    private JTextField quantidade_item;
    private JTextField preco_unitario_item;
    private JTextField total_item;
    private JTextField unidade_item;
    private JTextField subtotal_venda;
    private JTextField descricao_item;
    private JTable tabela_itens_venda;
    private JTextField total_venda;
    private JTextField cliente_venda;
    private JButton buttonOK;
    private Produto produtoSelecionado = null;
    DefaultTableModel modelo;
    VendaItensService vendaItensService = new VendaItensService();

    public TelaVenda() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFocusable(true);

        vendaItensService.mostrarVendasNaTabela(modelo);

        quantidade_item.setEnabled(false);

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "abrirProdutos");

        getRootPane().getActionMap().put("abrirProdutos", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtos telaProdutos = new Produtos(true);
                telaProdutos.setVisible(true);

                produtoSelecionado = telaProdutos.getProdutoSelecionado();

                if (produtoSelecionado != null){
                    atualizarInputs(produtoSelecionado);
                    quantidade_item.setEnabled(true);
                }
            }
        });

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "abrirClientes");

        getRootPane().getActionMap().put("abrirClientes", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCliente telaCliente = new TelaCliente(true);
                telaCliente.setVisible(true);
                Cliente cliente = telaCliente.getClienteSelecionado();
                vendaItensService.setClienteVenda(cliente);

                if (cliente != null) cliente_venda.setText(cliente.getNome());


            }
        });

        deletarItemTabela();

        eventoAtualizaQuantidadeItem();

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "finalizarVenda");

        getRootPane().getActionMap().put("finalizarVenda", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinalizarVenda telaFinalizar = new FinalizarVenda(vendaItensService, modelo);
                telaFinalizar.setVisible(true);
            }
        });



        eventoInputQuantidade();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    new MenuInicial();
                    dispose();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                requestFocusInWindow();
            }
        });

        setVisible(true);
    }

    public void atualizarInputs(Produto produto){
        descricao_item.setText(produto.getDescricao());
        preco_unitario_item.setText(ValorParaDinheiro.converter(produto.getPreco(), "pt", "BR"));
        quantidade_item.setText("1");
        total_item.setText(ValorParaDinheiro.converter(produto.getPreco(), "pt", "BR"));
        unidade_item.setText(produto.getMedida());
    }

    private void eventoInputQuantidade() {
        quantidade_item.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> atualizarTotal());
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> atualizarTotal());
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {

            }
        });

        quantidade_item.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    adicionarItem();
                }
            }
        });
    }

    private void deletarItemTabela(){
        tabela_itens_venda.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int linhaSelecionada = tabela_itens_venda.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        vendaItensService.removerItem((String) tabela_itens_venda.getValueAt(linhaSelecionada, 0), modelo);
                        atualizarTotalVenda();
                        zerarCampos();
                    }
                }
            }
        });

    }

    private void adicionarItem(){
        if (produtoSelecionado != null && !quantidade_item.getText().trim().isEmpty()){
            try{
                double qtdItem = Integer.parseInt(quantidade_item.getText().trim());
                Item_produto itemProduto = new Item_produto(new GenerateWithDateRandom(), produtoSelecionado, qtdItem);
                vendaItensService.adicionarItemVenda(itemProduto, modelo);
                atualizarTotalVenda();
            }catch (NumberFormatException ex){
                Faill.show(null, "Não foi possivel adicionar o item");
            }catch (IllegalArgumentException e){
                Faill.show(null,e.getMessage());
            }
        }
    }

    private void eventoAtualizaQuantidadeItem() {
        tabela_itens_venda.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                JTextField editor = (JTextField) getComponent();
                String valor = editor.getText().trim();
                int linhaSelecionada = tabela_itens_venda.getSelectedRow();

                if (linhaSelecionada != -1) {
                    String codItem = (String) tabela_itens_venda.getValueAt(linhaSelecionada, 0);

                    try {
                        int quantidade = Integer.parseInt(valor);

                        if (quantidade <= 0) {
                            throw new NumberFormatException(); // Impede valores negativos ou zero
                        }

                        try {
                            vendaItensService.atualizarQtdItem(codItem, quantidade);
                        } catch (IllegalArgumentException ex) {
                            Faill.show(null, ex.getMessage());
                        }
                    } catch (NumberFormatException ex) {
                        Faill.show(null, "Quantidade inválida. Insira um número inteiro positivo.");
                        return false; // Impede a célula de ser salva com um valor inválido
                    } finally {
                        vendaItensService.mostrarVendasNaTabela(modelo);
                        atualizarInputs(produtoSelecionado);
                        atualizarTotalVenda();
                    }
                }

                return super.stopCellEditing();
            }
        });

    }

    private void zerarCampos(){
        descricao_item.setText("SEM DESCRIÇÃO");
        preco_unitario_item.setText("0.00");
        quantidade_item.setEnabled(false);
        quantidade_item.setText("");
        total_item.setText("0.00");
        unidade_item.setText("NENHUMA");
    }

    private void atualizarTotalVenda(){
        total_venda.setText(ValorParaDinheiro.converter(vendaItensService.getSubtotalVendaAtual(), "pt", "BR"));
        subtotal_venda.setText(ValorParaDinheiro.converter(vendaItensService.getSubtotalVendaAtual(), "pt", "BR"));
    }

    private void atualizarTotal() {
        if (produtoSelecionado != null) {
            int quantidade;

            try {
                quantidade = Integer.parseInt(quantidade_item.getText().trim());
            } catch (NumberFormatException e) {
                quantidade = 1;
                quantidade_item.setText("1");
            }

            double preco = produtoSelecionado.getPreco();
            total_item.setText(ValorParaDinheiro.converter(quantidade*preco, "pt", "BR"));
        }
    }

    public static void main(String[] args) {
        TelaVenda dialog = new TelaVenda();
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        tabela_itens_venda = new JTable();

        tabela_itens_venda.getTableHeader().setVisible(false);
        tabela_itens_venda.getTableHeader().setPreferredSize(new Dimension(0, 0));

        tabela_itens_venda.setShowGrid(false); // Remove as linhas internas
        tabela_itens_venda.setBorder(null);

        tabela_itens_venda.setIntercellSpacing(new Dimension(0, 30)); // Define um espaçamento de 5px entre as células

        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Apenas a coluna 2 será editável, todas as outras não
                return column == 2; // Modifique para a coluna que você deseja
            }
        };

        modelo.addColumn("Item");
        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Medida");
        modelo.addColumn("Preço Unitário");
        modelo.addColumn("Subtotal");

        tabela_itens_venda.setModel(modelo);


        tabela_itens_venda.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    cell.setBackground(table.getSelectionBackground()); // Usa a cor padrão de seleção
                    cell.setForeground(table.getSelectionForeground());
                } else {
                    cell.setBackground(Color.GREEN); // Cor personalizada para células não selecionadas
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        });



    }
}
