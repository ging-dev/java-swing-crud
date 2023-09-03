package com.gingdev;

import java.sql.SQLException;
import javax.swing.SwingUtilities;
import com.gingdev.swing.StudentFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new StudentFrame();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
