package user;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Homepage extends JPanel {
    private JPanel cardPanel = new JPanel(new CardLayout());

    Sidebar sidebar = new Sidebar();

    MainContent mainContent = new MainContent();
    PasswordGenerator passwordGenerator = new PasswordGenerator();
    Settings settings = new Settings();
    Privacy privacy = new Privacy();

    public Homepage() {
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.NORTH);

        cardPanel.add(mainContent, "MainContent");
        cardPanel.add(passwordGenerator, "PasswordGenerator");
        cardPanel.add(settings, "Settings");
        cardPanel.add(privacy, "Privacy");

        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "MainContent");

        sidebar.addHomeListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "MainContent");
            }
        });

        sidebar.addPasswordGeneratorListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "PasswordGenerator");
            }
        });

        sidebar.addSettingListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Settings");
            }
        });

        sidebar.addPrivacyListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
                cardLayout.show(cardPanel, "Privacy");
            }
        });
    }
}