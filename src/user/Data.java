package user;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

public class Data extends JPanel {
    private Connection connection;
    private JTable table;
    private DefaultTableModel tableModel;

    public Data() {
        setLayout(new BorderLayout());

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:information.db");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + e.getMessage());
            return;
        }

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        populateTable();

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedRow());
        add(deleteButton, BorderLayout.NORTH);
    }

    public void populateTable() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM credentials");

            tableModel.setColumnIdentifiers(new Object[] { "Site", "Username", "Password" });
            tableModel.setRowCount(0);

            while (resultSet.next()) {
                String site = resultSet.getString("site");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Object[] row = { site, username, password };
                tableModel.addRow(row);
            }

            resultSet.close();
            tableModel.fireTableDataChanged();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to populate table: " + e.getMessage());
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String site = table.getValueAt(selectedRow, 0).toString();
            try {
                PreparedStatement statement = connection.prepareStatement("DELETE FROM credentials WHERE site = ?");
                statement.setString(1, site);
                statement.executeUpdate();
                populateTable();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Failed to delete row: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }

    @Override
    public void finalize() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}