package user;

import java.awt.Color;

import javax.swing.*;

public class Container extends JPanel {
    private JLabel site, username;
    private JButton copyButton;

    public Container(String site, String username, String password) {
        this.site = new JLabel(site);
        this.username = new JLabel(username);
        copyButton = new JButton("Copy Password");

        add(this.site);
        add(this.username);
        add(copyButton);

        setBackground(Color.cyan);
    }
}
