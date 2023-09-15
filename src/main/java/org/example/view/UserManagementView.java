package org.example.view;

import org.example.dto.response.UserManagementResponseDTO;
import org.example.dto.response.UsersListDTO;
import org.example.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagementView extends JDialog {
    JPanel titlePanel, searchPanel, searchNamePanel, searchIdPanel, tablePanel, returnPanel;
    JLabel titleLabel, nameSearchLabel, idSearchLabel;
    JTextField nameSearchText, idSearchText;
    JButton searchNameButton, searchIdButton, returnButton, deleteButton;
    JTable table;
    DefaultTableModel model;

    public UserManagementView() {
        UserService userService = new UserService();
        String[] columns = new String[] {"Id", "Prénom", "Nom", "Téléphone", "Email", "Admin"};
        model = new DefaultTableModel(columns,0);
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
                model.setRowCount(0);
                table.setModel(model);
                model.fireTableDataChanged();
                UsersListDTO users = userService.getByLastName(nameSearchText.getText());
                for (UserManagementResponseDTO user : users.getList()) {
                    model.addRow(new Object[] {
                            user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getPhone(),
                            user.getEmail(),
                            user.isAdmin()
                    });
                }
                nameSearchText.setText("");
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
                model.setRowCount(0);
                table.setModel(model);
                UserManagementResponseDTO user = userService.getById(Integer.parseInt(idSearchText.getText()));
                model.addRow(new Object[] {
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhone(),
                        user.getEmail(),
                        user.isAdmin()
                });
                idSearchText.setText("");
            }
        });
        searchIdPanel.add(searchIdButton);
        searchPanel.add(searchIdPanel);
        add(searchPanel, c);


        tablePanel = new JPanel();
        table = new JTable(model);
        table.setBounds(30, 40, 100, 100);
        JScrollPane sp = new JScrollPane(table);
        c.gridy = 2;
        c.gridx = 0;
        add(sp, c);

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
                System.out.println(idValue);
                userService.delete(idValue);
                model.removeRow(table.getSelectedRow());
            }
        });
        returnPanel.add(deleteButton);
        add(returnPanel, c);

        setVisible(true);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
    }
}
