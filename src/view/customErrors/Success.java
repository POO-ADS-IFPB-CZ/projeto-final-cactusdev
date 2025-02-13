package src.view.customErrors;

import javax.swing.*;
import java.awt.*;

public class Success {
    public static void show(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
}
