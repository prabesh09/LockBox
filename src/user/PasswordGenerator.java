package user;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PasswordGenerator extends JPanel {
    private JPanel componentPanel, sliderPanel, passwordPanel;
    private JCheckBox letters, mixed, punctuation, number;
    private JButton copyButton;
    private JLabel passwordLabel, rangeLabel;
    private JSlider range;
    private int value = 8;

    public PasswordGenerator() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        componentPanel = new JPanel();
        sliderPanel = new JPanel();
        passwordPanel = new JPanel();

        letters = new JCheckBox("Letters", true);
        letters.setEnabled(false);
        mixed = new JCheckBox("Mixed Case");
        punctuation = new JCheckBox("Punctuation");
        number = new JCheckBox("Numbers");

        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                passwordLabel.setText(generatePassword(value));
            }
        };

        mixed.addItemListener(itemListener);
        punctuation.addItemListener(itemListener);
        number.addItemListener(itemListener);

        range = new JSlider(value, 36, value);
        rangeLabel = new JLabel(String.valueOf(value));

        passwordLabel = new JLabel(generatePassword(value));

        range.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    value = source.getValue();
                    rangeLabel.setText(String.valueOf(value));
                    passwordLabel.setText(generatePassword(value));
                }
            }
        });

        copyButton = new JButton("Copy Password");
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                StringSelection str = new StringSelection(passwordLabel.getText());
                clip.setContents(str, str);
                JOptionPane.showMessageDialog(null, "Your Password has been Copied!");
            }
        });

        componentPanel.add(letters);
        componentPanel.add(mixed);
        componentPanel.add(punctuation);
        componentPanel.add(number);
        add(componentPanel, gbc);
        gbc.gridy++;

        sliderPanel.add(range);
        sliderPanel.add(rangeLabel);
        add(sliderPanel, gbc);
        gbc.gridy++;

        passwordPanel.add(passwordLabel);
        passwordPanel.add(copyButton);
        add(passwordPanel, gbc);
    }

    private String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        if (letters.isSelected()) {
            password.append("abcdefghijklmnopqrstuvwxyz");
        }
        if (mixed.isSelected()) {
            password.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }
        if (punctuation.isSelected()) {
            password.append("!@#$%^&*()-_=+[]{};:'\"<>,.?/\\");
        }
        if (number.isSelected()) {
            password.append("0123456789");
        }

        StringBuilder generatedPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * password.length());
            generatedPassword.append(password.charAt(randomIndex));
        }

        return generatedPassword.toString();
    }
}