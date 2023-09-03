package com.gingdev.services.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gingdev.services.database.DB;
import com.gingdev.services.domain.Student;

public class StudentDAO implements IDAO<Student> {

    @Override
    public Student createOrUpdate(Student t) throws SQLException {
        Connection conn = DB.getConn();
        if (t.getId() == null) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (name, age) VALUES (?, ?)");
            stmt.setString(1, t.getName());
            stmt.setInt(2, t.getAge());

            if (stmt.executeUpdate() > 0) {
                ResultSet rs = conn.createStatement().executeQuery("SELECT last_insert_rowid()");
                if (rs.next()) {
                    t.setId(rs.getInt(1));

                    return t;
                }
            }

            throw new SQLException("Failed to create user");
        }

        PreparedStatement stmt = conn.prepareStatement("UPDATE student SET name = ?, age = ? WHERE id = ?");

        stmt.setString(1, t.getName());
        stmt.setInt(2, t.getAge());
        stmt.setInt(3, t.getId());

        if (stmt.executeUpdate() > 0) {
            return t;
        }

        throw new SQLException("Failed to update user");
    }

    @Override
    public boolean detele(Integer id) throws SQLException {
        PreparedStatement stmt = DB.getConn().prepareStatement("DELETE FROM student WHERE id = ?");
        stmt.setInt(1, id);

        return stmt.executeUpdate() > 0;
    }

    @Override
    public List<Student> all() throws SQLException {
        List<Student> students = new ArrayList<>();
        ResultSet rs = DB.getConn().createStatement().executeQuery("SELECT * FROM student");
        while (rs.next()) {
            students.add(new Student(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        return students;
    }

}
