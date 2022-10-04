package ru.hutor.ui;

import ru.hutor.entity.AgentEntity;
import ru.hutor.manager.DatabaseManager;
import ru.hutor.ui.TableForm;
import ru.hutor.util.BaseForm;
import ru.hutor.util.DialogUtil;

import javax.swing.*;
import java.sql.SQLException;

public class EditForm extends BaseForm {
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
    private JTextField idField;
    private JButton deleteButton;

    public EditForm(AgentEntity entity) {
        super(800,600);
        setContentPane(mainPanel);
        initForm(entity);
        initButtons();
        setVisible(true);
    }

    private void initForm(AgentEntity entity) {
        titleField.setText(entity.getTitle());
        typeField.setText(entity.getAgentType());
        addressField.setText(entity.getAddress());
        phoneField.setText(entity.getPhone());
        emailField.setText(entity.getEmail());
        pathField.setText(entity.getLogoPath());
        priorityField.setText(String.valueOf(entity.getPriority()));
        idField.setText(String.valueOf(entity.getId()));
        idField.setEditable(false);
    }

    private void initButtons() {
        deleteButton.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(this, "Вы точно хотите удалить агента?", "Подтверждение", JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.YES_OPTION){
                try {
                    DatabaseManager.delete(Integer.parseInt(idField.getText()));
                    DialogUtil.showInfo(this, "Агент успешно удален");
                    dispose();
                    new TableForm();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    DialogUtil.showError(this, "Ошибка удаления агента");
                }
            }
        });
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
                DatabaseManager.update(new AgentEntity(Integer.parseInt(idField.getText()), title, type, address, phone, email, path, Integer.parseInt(priority)));
                DialogUtil.showInfo(this, "Агент успешно отредактирован");
                dispose();
                new TableForm();
            } catch (SQLException ex) {
                DialogUtil.showError(this, "Ошибка редактирования агента");
                ex.printStackTrace();
            }
        });
    }
}
