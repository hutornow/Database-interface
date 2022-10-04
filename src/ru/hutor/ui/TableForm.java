package ru.hutor.ui;

import ru.hutor.entity.AgentEntity;
import ru.hutor.util.BaseForm;
import ru.hutor.util.TableModel;
import ru.hutor.manager.DatabaseManager;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableForm extends BaseForm {
    private JTable table;
    private JPanel mainPanel;
    private JButton addButton;
    TableModel<AgentEntity> model;
    String[] columnNames = {"ID", "Название", "Тип агента" , "Адрес" , "Телефон" , "Email" , "Путь к картинке" , "Логотип" , "Приоритет"};

    public TableForm() {

        super(1200, 800);
        setContentPane(mainPanel);
        initTable();
        initButtons();
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowHeight(50);
        setVisible(true);
    }

    private void initButtons() {
        addButton.addActionListener(e -> {
            dispose();
            new AddingForm();
        });
    }

    private void initTable() {
        try {
            model = new TableModel<AgentEntity>(AgentEntity.class, DatabaseManager.selectAll(), columnNames);
            table.setModel(model);
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2){
                        int row = table.rowAtPoint(e.getPoint());
                        if(row != -1){
                            dispose();
                            new EditForm(model.getRows().get(row));
                        }
                    }
                }
            });

            TableColumn column = table.getColumn("Путь к картинке");
            column.setMinWidth(0);
            column.setPreferredWidth(0);
            column.setMaxWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
