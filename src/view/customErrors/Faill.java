package src.view.customErrors;

import javax.swing.*;
import java.awt.*;

public final class Faill {
    public static void show(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
