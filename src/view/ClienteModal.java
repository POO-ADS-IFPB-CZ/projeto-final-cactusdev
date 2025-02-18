package src.view;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class ClienteModal {

    public static void abrirClienteModal() {
        // Definir cores
        Color corFundo = new Color(0xF4F4F4);
        Color corTexto = new Color(0x000000);
        Color corCampo = new Color(0xFFFFFF);
        Color corAzul = new Color(0x007BFF);
        Color corVermelho = new Color(0xDC3545);

        // Criar painel
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(corFundo);

        // Criar campos
        JTextField nomeField = new JTextField(15);
        JTextField dataCadastroField = new JTextField(10);
        JTextField cpfField = new JTextField(11);
        JTextField telefoneField = new JTextField(11);
        JTextField cidadeField = new JTextField(12);

        // Configurar fundo dos campos
        nomeField.setBackground(corCampo);
        dataCadastroField.setBackground(corCampo);
        cpfField.setBackground(corCampo);
        telefoneField.setBackground(corCampo);
        cidadeField.setBackground(corCampo);

        // Definir os estados
        String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG",
                "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        JComboBox<String> estadoBox = new JComboBox<>(estados);

        // Criar rótulos
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel dataCadastroLabel = new JLabel("Data Cadastro (dd/mm/aaaa):");
        JLabel cpfLabel = new JLabel("CPF:");
        JLabel telefoneLabel = new JLabel("Telefone:");
        JLabel cidadeLabel = new JLabel("Cidade:");
        JLabel estadoLabel = new JLabel("Estado:");

        nomeLabel.setForeground(corTexto);
        dataCadastroLabel.setForeground(corTexto);
        cpfLabel.setForeground(corTexto);
        telefoneLabel.setForeground(corTexto);
        cidadeLabel.setForeground(corTexto);
        estadoLabel.setForeground(corTexto);

        // Adicionar componentes ao painel
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

        // 💡 Personalizar botões do JOptionPane via UIManager
        UIManager.put("OptionPane.okButtonText", "Confirmar");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        // Exibir JOptionPane
        int result;
        do {
            result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Cadastro de Cliente",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) { // Usuário clicou em "Confirmar"
                String nome = nomeField.getText().trim();
                String dataCadastro = dataCadastroField.getText().trim();
                String cpf = cpfField.getText().trim();
                String telefone = telefoneField.getText().trim();
                String cidade = cidadeField.getText().trim();
                String estado = (String) estadoBox.getSelectedItem();

                // Verificar se os dados são válidos
                String erro = validarCampos(nome, dataCadastro, cpf, telefone, cidade);
                if (!erro.isEmpty()) {
                    JOptionPane.showMessageDialog(null, erro, "Erro no Cadastro", JOptionPane.ERROR_MESSAGE);
                    continue; // Volta para a tela de cadastro
                }

                // Exibir os dados cadastrados
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!\n\n"
                        + "Nome: " + nome + "\n"
                        + "Data Cadastro: " + dataCadastro + "\n"
                        + "CPF: " + cpf + "\n"
                        + "Telefone: " + telefone + "\n"
                        + "Cidade: " + cidade + "\n"
                        + "Estado: " + estado, "Cadastro Concluído", JOptionPane.INFORMATION_MESSAGE);
                break; // Sai do loop, pois o cadastro foi concluído
            } else {
                break; // Se o usuário clicar em "Cancelar", sai do loop
            }
        } while (true);
    }

    /**
     * Valida os campos do formulário.
     */
    private static String validarCampos(String nome, String dataCadastro, String cpf, String telefone, String cidade) {
        if (nome.isEmpty()) return "O campo 'Nome' não pode estar vazio.";
        if (!Pattern.matches("^\\d{2}/\\d{2}/\\d{4}$", dataCadastro))
            return "O campo 'Data Cadastro' deve estar no formato dd/mm/aaaa.";
        if (!Pattern.matches("^\\d{11}$", cpf)) return "O campo 'CPF' deve conter exatamente 11 números.";
        if (!Pattern.matches("^\\d{10,11}$", telefone)) return "O campo 'Telefone' deve conter 10 ou 11 números.";
        if (cidade.isEmpty()) return "O campo 'Cidade' não pode estar vazio.";

        return ""; // Nenhum erro
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClienteModal::abrirClienteModal);
    }
}
