package com.gingdev.application.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.gingdev.services.model.StudentModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentFrame extends JFrame implements ActionListener, ListSelectionListener {
    private StudentModel model;
    private JTable table;
    private JButton delete;
    private JButton add;
    private JTextField name;
    private JTextField age;

    public StudentFrame() throws SQLException {
        super("Student");

        model = new StudentModel();

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(this);

        JScrollPane scroll = new JScrollPane(table);

        add = new JButton("Thêm");
        add.addActionListener(this);

        delete = new JButton("Xóa");
        delete.setEnabled(false);
        delete.addActionListener(this);

        JPanel rpanel = new JPanel();
        rpanel.setLayout(new FlowLayout());
        rpanel.add(delete);
        rpanel.add(add);

        name = new JTextField(50);
        name.setBorder(new TitledBorder("Tên"));
        age = new JTextField(50);
        age.setBorder(new TitledBorder("Tuổi"));

        JPanel bpanel = new JPanel(new GridLayout(1, 2));
        bpanel.add(name);
        bpanel.add(age);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(rpanel, BorderLayout.EAST);
        mainPanel.add(bpanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(add)) {
            model.add(name.getText(), Integer.parseInt(age.getText().toString()));
            name.setText("");
            age.setText("");

            return;
        }

        model.remove(table.getSelectedRow());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (-1 == table.getSelectedRow()) {
            delete.setEnabled(false);
        } else {
            delete.setEnabled(true);
        }
    }
}
