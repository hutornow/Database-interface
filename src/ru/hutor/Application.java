package ru.hutor;

import ru.hutor.entity.AgentEntity;
import ru.hutor.manager.DatabaseManager;
import ru.hutor.ui.EditForm;
import ru.hutor.ui.TableForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new TableForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "hg56Ju$m9loSr12vb@NN12&mA");
    }
}
