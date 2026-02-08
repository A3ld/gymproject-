package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonTest extends JFrame {
    private JTextField textField;
    private JRadioButton plainButton;
    private JRadioButton boldButton;
    private JRadioButton italicButton;
    private JRadioButton boldItalicButton;
    private ButtonGroup buttonGroup;

    public RadioButtonTest() {
        super("RadioButtonTest");
        
        textField = new JTextField("Watch the font style change");
        textField.setFont(new Font("Serif", Font.PLAIN, 14));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        
        plainButton = new JRadioButton("Plain", true);
        boldButton = new JRadioButton("Bold");
        italicButton = new JRadioButton("Italic");
        boldItalicButton = new JRadioButton("Bold/Italic");
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(plainButton);
        buttonGroup.add(boldButton);
        buttonGroup.add(italicButton);
        buttonGroup.add(boldItalicButton);
        
        RadioButtonHandler handler = new RadioButtonHandler();
        plainButton.addActionListener(handler);
        boldButton.addActionListener(handler);
        italicButton.addActionListener(handler);
        boldItalicButton.addActionListener(handler);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(plainButton);
        buttonPanel.add(boldButton);
        buttonPanel.add(italicButton);
        buttonPanel.add(boldItalicButton);
        
        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
              
    private class RadioButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int style = Font.PLAIN;
            String message = "";
            
            if (event.getSource() == plainButton) {
                style = Font.PLAIN;
                message = "You chose Plain";
            } else if (event.getSource() == boldButton) {
                style = Font.BOLD;
                message = "You chose Bold";
            } else if (event.getSource() == italicButton) {
                style = Font.ITALIC;
                message = "You chose Italic";
            } else if (event.getSource() == boldItalicButton) {
                style = Font.BOLD | Font.ITALIC;
                message = "You chose Bold/Italic";
            }
            
            textField.setText(message);
            textField.setFont(new Font("Serif", style, 14));
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RadioButtonTest());
    }
}
