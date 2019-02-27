package com.velugo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUI implements ActionListener {
    JFrame frame;
    JButton button;
    JLabel label;
    JTextField text;
    JLabel note;

    void go() {
        frame = new JFrame("Калькулятор");
        button = new JButton("Посчитать");
        label = new JLabel("Здесь будет ответ");
        text = new JFormattedTextField();
        text.setToolTipText("Напишите выражение");
        note = new JLabel("Введите выражение");

        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(BorderLayout.CENTER, text);
        frame.getContentPane().add(BorderLayout.EAST, button);
        frame.getContentPane().add(BorderLayout.SOUTH, label);
        frame.getContentPane().add(BorderLayout.WEST, note);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        label.setText("Ответ: " + new Calculator().evaluate(text.getText()));
        button.setText("Посчитать ещё раз");
    }
}
