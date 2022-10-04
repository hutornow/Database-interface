package ru.hutor.ui;

import ru.hutor.entity.AgentEntity;
import ru.hutor.manager.DatabaseManager;
import ru.hutor.util.BaseForm;
import ru.hutor.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class AddingForm extends BaseForm {
    private JTextField titleField;
    private JTextField typeField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField pathField;
    private JTextField priorityField;
    private JButton saveButton;
    private JButton backButton;
    private JPanel mainPanel;

    public AddingForm() {
        super(800,600);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        backButton.addActionListener(e -> {
            dispose();
            new TableForm();
        });
        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String type = typeField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String path = pathField.getText();
            String priority = priorityField.getText();
            if (title.isEmpty()){
                DialogUtil.showError(this, "Не введено название");
                return;
            }
            if (type.isEmpty() || type.length() > 3){
                DialogUtil.showError(this, "Не введен тип агента");
                return;
            }
            if (address.isEmpty()){
                DialogUtil.showError(this, "Не введен адрес агента");
                return;
            }
            if (phone.isEmpty()){
                DialogUtil.showError(this, "Не введен телефон агента");
                return;
            }
            if (email.isEmpty()){
                DialogUtil.showError(this, "Не введена эл.почта агента");
                return;
            }
            if (path.length() > 1000){
                DialogUtil.showError(this, "Путь до картинки слишком длинный");
                return;
            }
            if (priority.isEmpty() || Integer.parseInt(priority) < 1) {
                DialogUtil.showError(this, "Приоритет агента не введен или введен некорректно");
                return;
            }

            try {
                DatabaseManager.insert(new AgentEntity(-1, title, type, address, phone, email, path, Integer.parseInt(priority)));
                DialogUtil.showInfo(this, "Агент успешно добавлен");
                dispose();
                new TableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка добавления агента");
                ex.printStackTrace();
            }
        });
    }
}
