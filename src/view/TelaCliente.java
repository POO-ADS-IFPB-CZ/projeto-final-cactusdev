package src.view;

import src.model.Cliente;
import src.services.ClienteService;
import src.services.filters.ClienteFilters;
import src.view.customErrors.Faill;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaCliente extends JFrame {
    private JPanel contentPane;
    private JButton cadastrarClienteButton;
    private JButton alterarClienteButton;
    private JButton sairButton;
    private JTable tabela_clientes;
    private JTextField nome;
    private JComboBox opcao_busca;
    private JTextField cpf;
    private JButton selecionarButton;
    private JButton buscarButton;
    private TableRowSorter<DefaultTableModel> rowSorter;
    private JButton buttonOK;
    private final ClienteService clienteService;
    private DefaultTableModel tableModel;
    private Cliente clienteSelecionado = null;

    public TelaCliente(boolean isVenda) {
        clienteService = new ClienteService();
        setContentPane(contentPane);
        setTitle("Cliente");
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clienteService.mostrarClientesNaTabela(tableModel);

        cadastrarClienteButton.addActionListener((e -> {
            CadastrarCliente cadastrarCliente = new CadastrarCliente(tableModel);
            cadastrarCliente.setVisible(true);
        }));

        buscarButton.addActionListener((e -> buscarClientes()));

        sairButton.addActionListener((e)-> fecharJanela());

        verificarIsVenda(isVenda);

        selecionarButton.addActionListener((e -> selecionarCliente()));

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (e.getComponent() == nome) {
                        opcao_busca.setSelectedItem("NOME");
                    } else if (e.getComponent() == cpf) {
                        opcao_busca.setSelectedItem("CPF");
                    }
                    buscarClientes();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    fecharJanela();
                }
            }
        };

        nome.addKeyListener(keyAdapter);
        cpf.addKeyListener(keyAdapter);
        opcao_busca.addKeyListener(keyAdapter);
        tabela_clientes.addKeyListener(keyAdapter);
        sairButton.addKeyListener(keyAdapter);
    }

    private void fecharJanela() {
        dispose();
    }

    private void buscarClientes() {
        String opcaoSelecionada = (String) opcao_busca.getSelectedItem();

        switch (opcaoSelecionada) {
            case "TODOS":
                ClienteFilters.limparFiltros(rowSorter);
                break;
            case "CPF":
                ClienteFilters.filtrarTabelaPorCpf(cpf.getText(), rowSorter);
                break;
            case "NOME":
                ClienteFilters.filtrarTabelaPorNome(nome.getText(), rowSorter);
                break;
            default:
                Faill.show(this, "Nenhuma opção de busca selecionada");
        }
    }

    private void verificarIsVenda(boolean isVenda){
        if (!isVenda){
            selecionarButton.setVisible(false);
        }else{
            alterarClienteButton.setVisible(false);

            //evento para selecionar produto na tabela, só funciona se estiver na venda
            tabela_clientes.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        selecionarCliente();
                    }
                }
            });
        }
    }

    private void selecionarCliente() {
        int row = tabela_clientes.getSelectedRow();
        if (row != -1) {
            String cpf = (String) tabela_clientes.getValueAt(row, 1);
            clienteSelecionado = clienteService.getClientePorCpf(cpf);

            if (clienteSelecionado == null){
                throw new RuntimeException();
            }

            if (!clienteSelecionado.getAtivo()){
                Faill.show(null, "Selecione um cliente ativo.");
                throw new RuntimeException();
            }

            fecharJanela();

        } else {
            Faill.show(null, "Selecione um cliente válido");
        }
    }

    public Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }

    public static void main(String[] args) {
        TelaCliente dialog = new TelaCliente(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void createUIComponents() {
        tabela_clientes = new JTable();
        String[] colunas = {"Nome", "Cpf", "Telefone", "Data de cadastro", "Status"};

        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição das células
            }
        };

        tabela_clientes.setModel(tableModel);

        tabela_clientes.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Verifica se o valor não é nulo
                if (value != null) {
                    String status = value.toString().trim(); // Converte para string e remove espaços

                    // Define a cor do texto com base no status
                    if ("Ativo".equalsIgnoreCase(status)) {
                        cell.setForeground(Color.GREEN);
                    } else {
                        cell.setForeground(Color.RED);
                    }
                }

                return cell;
            }
        });




        rowSorter = new TableRowSorter<>(tableModel);
        tabela_clientes.setRowSorter(rowSorter);
    }


}
