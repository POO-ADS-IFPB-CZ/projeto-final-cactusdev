package src.view;

import javax.swing.*;
import java.awt.*;

public class ClienteModal {

    public static void abrirClienteModal() {
        // Criação do painel principal
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        // Definir cores
        Color corFundo = new Color(0xF4F4F4); // Tom claro para o fundo
        Color corTexto = new Color(0x000000); // Cor preta para o texto
        Color corCampo = new Color(0xFFFFFF); // Branco para os campos de texto
        Color corAzul = new Color(-15713818);  // Azul para o botão OK
        Color corVermelho = new Color(0xDC3545); // Vermelho para o botão Cancelar

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

        // Criar rótulos
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel dataCadastroLabel = new JLabel("Data Cadastro:");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel telefoneLabel = new JLabel("Telefone:");
        JLabel cidadeLabel = new JLabel("Cidade:");
        JLabel estadoLabel = new JLabel("Estado:");

        // Aplicar cor ao texto dos rótulos
        nomeLabel.setForeground(corTexto);
        dataCadastroLabel.setForeground(corTexto);
        cpfLabel.setForeground(corTexto);
        telefoneLabel.setForeground(corTexto);
        cidadeLabel.setForeground(corTexto);
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

        // Criar botões personalizados
        JButton okButton = new JButton("OK");
        okButton.setBackground(corAzul);
        okButton.setForeground(Color.WHITE);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setBackground(corVermelho);
        cancelButton.setForeground(Color.WHITE);

        // Criar array com os botões personalizados
        Object[] options = {okButton, cancelButton};

        // Exibir o JOptionPane com os botões personalizados
        int result = JOptionPane.showOptionDialog(
                null,
                panel,
                "Cadastro de Cliente",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0] // Define "OK" como botão padrão
        );

        // Verifica se o usuário clicou em "OK" (índice 0)
        if (result == 0) {
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
