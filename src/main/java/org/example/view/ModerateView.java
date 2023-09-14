package org.example.view;

import org.example.dto.response.CommentDTO;
import org.example.service.CommentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ModerateView extends JDialog {
    JPanel titlePanel, tablePanel, returnPanel;
    JLabel title;
    JTable table;
    JButton deleteButton, returnButton;
    public ModerateView() {
        setTitle("Modération");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        titlePanel = new JPanel();
        title = new JLabel("Modérer les commentaires");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);

        tablePanel = new JPanel();
        String[] columns = new String[] {"Id", "Contenu", "Auteur"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        table = new JTable(model);
        // Récupération des commentaires
        CommentService commentService = new CommentService();
        List<CommentDTO> comments = commentService.getAllComments();
        for (CommentDTO comment: comments) {
            model.addRow(new Object[] {
                    comment.getId(),
                    comment.getComment(),
                    comment.getId()
            });
        }
        table.setBounds(30, 40, 100, 100);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

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
        add(returnPanel, BorderLayout.SOUTH);
        deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idValue = (int) table.getValueAt(table.getSelectedRow(), 0);
                // A CONTINUER
            }
        });
        returnPanel.add(deleteButton);

        setVisible(true);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
    }
}
