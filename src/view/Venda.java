package src.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Venda extends JFrame {
    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField4;
    private JTable table1;
    private JTextField textField7;
    private JButton buttonOK;

    public Venda() {
        setContentPane(contentPane);
        getRootPane().setDefaultButton(buttonOK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Produto");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Preço Unitário");
        modelo.addColumn("Total");

        table1.setModel(modelo);

        setFocusable(true);

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

    public static void main(String[] args) {
        Venda dialog = new Venda();
        dialog.pack();
        dialog.setVisible(true);
    }
}
