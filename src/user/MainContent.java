package user;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import database.*;

public class MainContent extends JPanel {
    private JButton addNewItem;
    private JScrollPane scrollPane;

    public MainContent() {
        setLayout(new BorderLayout());

        SQLiteConnection sqliteConnection = new SQLiteConnection("information.db");
        Connection connection = sqliteConnection.getConnection();
        Data data = new Data();

        scrollPane = new JScrollPane(data);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        addNewItem = new JButton("Add");
        addNewItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    JPanel inputPanel = new JPanel(new GridLayout(3, 2, 2, 2));
                    JTextField site = new JTextField(20);
                    JTextField username = new JTextField(20);
                    JPasswordField password = new JPasswordField(20);
                    inputPanel.add(new JLabel("Enter site name:"));
                    inputPanel.add(site);
                    inputPanel.add(new JLabel("Enter username:"));
                    inputPanel.add(username);
                    inputPanel.add(new JLabel("Enter password:"));
                    inputPanel.add(password);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Input",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon());

                    if (result == JOptionPane.OK_OPTION) {
                        if (connection == null) {
                            JOptionPane.showMessageDialog(null, "Failed to connect to the database.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        StoreData sd = new StoreData(connection);
                        sd.storeCredentials(site.getText(), username.getText(), String.valueOf(password.getPassword()));
                        JOptionPane.showMessageDialog(null, "Your data has been Saved!");
                        data.populateTable();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(addNewItem, BorderLayout.SOUTH);
    }
}
