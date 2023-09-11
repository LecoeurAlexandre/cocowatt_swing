package org.example.view;

import org.example.dto.response.UserManagementResponseDTO;
import org.example.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserManagementView extends JDialog {
    JPanel titlePanel, searchPanel, searchNamePanel, searchIdPanel, tablePanel, returnPanel;
    JLabel titleLabel, nameSearchLabel, idSearchLabel;
    JTextField nameSearchText, idSearchText;
    JButton searchNameButton, searchIdButton, returnButton, deleteButton;
    JTable table;

    public UserManagementView() {
        UserService userService = new UserService();
        setTitle("Gestion utilisateurs");
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        GridBagConstraints c = new GridBagConstraints();

        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(5,30,5,30);
        titlePanel = new JPanel();
        titleLabel = new JLabel("Supprimer un utilisateur");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        add(titlePanel, c);

        c.gridy = 1;
        c.gridx = 0;
        searchPanel = new JPanel(new BorderLayout());

        searchNamePanel = new JPanel(new FlowLayout());
        nameSearchText = new JTextField(15);
        searchNamePanel.add(nameSearchText);
        nameSearchLabel = new JLabel("Rechercher par nom");
        searchNamePanel.add(nameSearchLabel);
        searchNameButton = new JButton("OK");
        searchNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserManagementResponseDTO> users = userService.getByLastName(nameSearchText.getText());
                c.gridy = 2;
                c.gridx = 0;
                displayTableByLastName(users, c);
            }
        });
        searchNamePanel.add(searchNameButton);
        searchPanel.add(searchNamePanel, BorderLayout.NORTH);

        searchIdPanel = new JPanel(new FlowLayout());
        idSearchText = new JTextField(15);
        searchIdPanel.add(idSearchText);
        idSearchLabel = new JLabel("Rechercher par id");
        searchIdPanel.add(idSearchLabel);
        searchIdButton = new JButton("OK");
        searchIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManagementResponseDTO user = userService.getById(Integer.parseInt(idSearchText.getText()));
                c.gridy = 2;
                c.gridx = 0;
                displayTableById(user, c);
            }
        });
        searchIdPanel.add(searchIdButton);
        searchPanel.add(searchIdPanel);
        add(searchPanel, c);


        tablePanel = new JPanel();


        c.gridy = 3;
        c.gridx = 0;
        returnPanel = new JPanel();
        returnButton = new JButton("Retour");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menu = new MenuView();
                menu.setVisible(true);
                dispose();
            }
        });
        returnPanel.add(returnButton);

        deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idValue = (int) table.getValueAt(table.getSelectedRow(), 0);
                // A CONTINUER
            }
        });
        returnPanel.add(deleteButton);
        add(returnPanel, c);

        setVisible(true);
        pack();

    }
    private void displayTableByLastName(List<UserManagementResponseDTO> users, GridBagConstraints c) {
        String[] columns = new String[] {"Id", "Prénom", "Nom", "Téléphone", "Email", "Admin"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        table = new JTable(model);
        for (UserManagementResponseDTO user : users) {
            model.addRow(new Object[] {
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.isAdmin()
            });
        }
        table.setBounds(30, 40, 100, 100);
        JScrollPane sp = new JScrollPane(table);
        add(sp, c);
    }
    private void displayTableById(UserManagementResponseDTO user, GridBagConstraints c) {
        String[] columns = new String[] {"Id", "Prénom", "Nom", "Téléphone", "Email", "Admin"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        table = new JTable(model);
        model.addRow(new Object[] {
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.isAdmin()
        });
        table.setBounds(30, 40, 100, 100);
        JScrollPane sp = new JScrollPane(table);
        add(sp, c);
    }
}
