package user;

import javax.swing.*;
import java.awt.event.*;

public class Sidebar extends JPanel {
    private JButton home, passwordGenerator, settings, privacy;

    public Sidebar() {
        home = new JButton("Home");
        passwordGenerator = new JButton("Generate Password");
        settings = new JButton("Settings");
        privacy = new JButton("Privacy");

        add(home);
        add(passwordGenerator);
        add(settings);
        add(privacy);
    }

    public void addHomeListener(ActionListener listener) {
        home.addActionListener(listener);
    }

    public void addPasswordGeneratorListener(ActionListener listener) {
        passwordGenerator.addActionListener(listener);
    }

    public void addSettingListener(ActionListener listener) {
        settings.addActionListener(listener);
    }

    public void addPrivacyListener(ActionListener listener) {
        privacy.addActionListener(listener);
    }
}