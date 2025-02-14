package src.view;

import javax.swing.*;

public class Venda extends JFrame {
    private JPanel contentPane;
    private JList list1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField4;
    private JButton buttonOK;

    public Venda() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Venda dialog = new Venda();
        dialog.pack();
        dialog.setVisible(true);
    }
}
