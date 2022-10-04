package ru.hutor.util;

import javax.swing.*;
import java.awt.*;

public class BaseForm extends JFrame {

    private final String APP_TITLE = "Компания “Прыгай мой мячик” ";
    private final ImageIcon icon = new ImageIcon("resources/icon.png");

    public BaseForm(int width, int height){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(width, height));
        setTitle(APP_TITLE);
        setIconImage(icon.getImage());
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - height / 2);
    }
}
