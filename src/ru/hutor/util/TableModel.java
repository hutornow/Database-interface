package ru.hutor.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;

@AllArgsConstructor
public class TableModel<T> extends AbstractTableModel {
    Class<T> columns;
    @Getter @Setter
    List<T> rows;
    String[] columnNames;
    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.getDeclaredFields().length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Field field = columns.getDeclaredFields()[columnIndex];
        field.setAccessible(true);
        T t = rows.get(rowIndex);
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
