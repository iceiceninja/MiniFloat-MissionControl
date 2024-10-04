package org.example;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        JFrame jFrame = new JFrame("Mission Control");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setPreferredSize(new Dimension(700,500));
        Form form = new Form();
        jFrame.add(form, BorderLayout.PAGE_START);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}