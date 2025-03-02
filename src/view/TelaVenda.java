package src.view;

import src.model.Item_produto;
import src.model.Produto;
import src.services.VendaItensService;
import src.services.adapters.GenerateWithDateRandom;
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
        preco_unitario_item.setText(String.format("%.2f", produto.getPreco()));
        quantidade_item.setText("1");
        total_item.setText(String.format("%.2f", produto.getPreco()));
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

    private void adicionarItem(){
        if (produtoSelecionado != null && !quantidade_item.getText().trim().isEmpty()){
            try{
                double qtdItem = Double.parseDouble(quantidade_item.getText().trim());
                Item_produto itemProduto = new Item_produto(new GenerateWithDateRandom(), produtoSelecionado, qtdItem);
                vendaItensService.adicionarItemVenda(itemProduto, modelo);
                subtotal_venda.setText(String.format("%.2f", vendaItensService.getSubtotalVendaAtual()));
                total_venda.setText(String.format("%.2f", vendaItensService.getSubtotalVendaAtual()));
            }catch (NumberFormatException ex){
                Faill.show(null, "Não foi possivel adicionar o item");
            }
        }
    }

    private void atualizarTotal() {
        if (produtoSelecionado != null) {
            int quantidade;

            try {
                quantidade = Integer.parseInt(quantidade_item.getText().trim());
            } catch (NumberFormatException e) {
                quantidade = 1;

                if (!quantidade_item.getText().trim().isEmpty()){
                    quantidade_item.setText("1");
                }
            }

            double preco = produtoSelecionado.getPreco();
            total_item.setText(String.format("%.2f", quantidade * preco));
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


        modelo = new DefaultTableModel();
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

                if (!isSelected) { // Para manter a seleção funcionando corretamente
                    cell.setBackground(Color.GREEN);
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        });


    }
}
