package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;

public class Form extends JPanel {
    JTextField addressField;
    JTextField portField;
    JButton submitForm;
    JCheckBox retryBox;
    JTextArea outputField;

    Dimension DEFAULT_FIELD_DIMENSION = new Dimension(75,30);
    public Form()
    {
        setLayout( new BoxLayout(this, BoxLayout.PAGE_AXIS) );
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel title = new JLabel("Mission Control");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JLabel addressLabel = new JLabel("IP Address:");
//        addressField = new JTextField(12); // assuming IPv4
//        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        addressField.setPreferredSize(DEFAULT_FIELD_DIMENSION);
//
//        JLabel portLabel = new JLabel("Port Number:");
//        portField = new JTextField(5);
//
//        JLabel retryLabel = new JLabel("Enable Retries?:");
//        retryBox = new JCheckBox();

        submitForm = new JButton("Send Signal");

        JLabel outputLabel = new JLabel("Output:");
        outputField = new JTextArea(16,32);
        outputField.setEditable(false);
        outputField.setLineWrap(true);
//        outputField.setPreferredSize(new Dimension(200,150));
        JScrollPane scroll = new JScrollPane(outputField);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        submitForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });


        add(title);
        addressField = (JTextField) addComponent("IP Address:", new JTextField(12));
        portField = (JTextField) addComponent("Port Number:",new JTextField(5));
        retryBox = (JCheckBox) addComponent("Enable Retries? ", new JCheckBox());

        add(submitForm);

        add(outputLabel);
        add(scroll);
//        add(outputField);
    }
    Component addComponent(String label, Component component)
    {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel(label);
        jPanel.add(jLabel);
        jPanel.add(component);
        add(jPanel);
        return component;
    }
    private void submitForm()
    {
        try {
            // Add more validation logic, this currently sucks but will catch if it is too long
            if (InetAddress.getByName(addressField.getText()) instanceof Inet4Address ||
                    InetAddress.getByName(addressField.getText()) instanceof Inet6Address) {
                ConnectionDetails connectionDetails = ConnectionDetails.builder()
                        .ipAddress(addressField.getText())
                        .port(portField.getText())
                        .doRetry(retryBox.isSelected())
                        .build();
                outputText(connectionDetails.toString());
            }
        }catch (UnknownHostException e)
        {
            outputText(e.getMessage());
        }
    }
    public void outputText(String text)
    {
        outputField.append(text + "\n");
    }
}
