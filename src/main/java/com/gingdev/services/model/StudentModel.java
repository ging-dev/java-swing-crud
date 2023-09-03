package com.gingdev.services.model;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import com.gingdev.services.dao.StudentDAO;
import com.gingdev.services.domain.Student;

public class StudentModel extends AbstractTableModel {
    private StudentDAO dao;
    private List<Student> students;

    public StudentModel() throws SQLException {
        dao = new StudentDAO();
        students = dao.all();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch (columnIndex) {
            case 1:
                student.setName(aValue.toString());
                break;
            case 2:
                student.setAge(Integer.parseInt(aValue.toString()));
                break;
        }

        try {
            dao.createOrUpdate(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void add(String name, Integer age) {
        Student student = new Student(name, age);
        try {
            dao.createOrUpdate(student);
            students.add(student);
            fireTableRowsInserted(getRowCount(), getRowCount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(int row) {
        try {
            dao.detele(Integer.parseInt(getValueAt(row, 0).toString()));
            students.remove(row);
            fireTableRowsDeleted(row, row);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return students.get(row).toArray()[col];
    }
}
