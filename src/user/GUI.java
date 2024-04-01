package user;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
    public GUI() {
        setTitle("Lockbox");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Homepage hp = new Homepage();
        hp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        getContentPane().add(hp, BorderLayout.CENTER);
    }
}