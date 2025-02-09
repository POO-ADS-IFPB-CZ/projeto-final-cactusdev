package src.view;

import javax.swing.*;
import java.awt.*;

public class ClienteModal {

    public static void abrirClienteModal() {
        // Criação do painel principal
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Definir cores
        Color corFundo = new Color(0xF4F4F4); // Um tom claro para o fundo
        Color corBotoes = new Color(0x5C6BC0); // Tom de azul para os botões
        Color corTexto = new Color(0x000000); // Cor preta para o texto (opcional)
        Color corCampo = new Color(0xFFFFFF); // Cor branca para os campos de texto

        // Configurar o layout e a cor de fundo do painel
        panel.setBackground(corFundo);

        // Criar os campos de texto
        JTextField nomeField = new JTextField(15);
        nomeField.setBackground(corCampo);
        JTextField dataCadastroField = new JTextField(10);
        dataCadastroField.setBackground(corCampo);
        JTextField cpfField = new JTextField(11);
        cpfField.setBackground(corCampo);
        JTextField telefoneField = new JTextField(11);
        telefoneField.setBackground(corCampo);
        JTextField cidadeField = new JTextField(12);
        cidadeField.setBackground(corCampo);

        // Definir os estados para o JComboBox
        String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
                "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        JComboBox<String> estadoBox = new JComboBox<>(estados);

        // Definir os rótulos
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setForeground(corTexto);
        JLabel dataCadastroLabel = new JLabel("Data Cadastro:");
        dataCadastroLabel.setForeground(corTexto);
        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setForeground(corTexto);
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setForeground(corTexto);
        JLabel cidadeLabel = new JLabel("Cidade:");
        cidadeLabel.setForeground(corTexto);
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setForeground(corTexto);

        // Adicionar os componentes ao painel
        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(dataCadastroLabel);
        panel.add(dataCadastroField);
        panel.add(cpfLabel);
        panel.add(cpfField);
        panel.add(telefoneLabel);
        panel.add(telefoneField);
        panel.add(cidadeLabel);
        panel.add(cidadeField);
        panel.add(estadoLabel);
        panel.add(estadoBox);

        // Criar o botão de confirmação
        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.setBackground(corBotoes);
        confirmarButton.setForeground(Color.WHITE);
        panel.add(confirmarButton);

        // Exibir o modal
        int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String dataCadastro = dataCadastroField.getText();
            String cpf = cpfField.getText();
            String telefone = telefoneField.getText();
            String cidade = cidadeField.getText();
            String estado = (String) estadoBox.getSelectedItem();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado:\n"
                    + "Nome: " + nome + "\n"
                    + "Data Cadastro: " + dataCadastro + "\n"
                    + "CPF: " + cpf + "\n"
                    + "Telefone: " + telefone + "\n"
                    + "Cidade: " + cidade + "\n"
                    + "Estado: " + estado);
        }
    }

    public static void main(String[] args) {
        // Chama o método para abrir o modal
        SwingUtilities.invokeLater(ClienteModal::abrirClienteModal);
    }
}
