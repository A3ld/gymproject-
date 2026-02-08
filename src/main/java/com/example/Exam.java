package com.example;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Exam extends JFrame {

    JLabel l1;
    JComboBox<String> b1;

    
    String[] s2 = {"bug1.PNG", "bug2.PNG", "travelbug.PNG", "buganim.gif"};

    Icon[] s1 = { new ImageIcon("bug1.PNG"),  new ImageIcon("bug2.PNG"),new ImageIcon("travelbug.PNG"),new ImageIcon("buganim.gif")
    
    };

    Exam() {

        super("Testing JComboBox ");

        Container c1 = getContentPane();
        c1.setLayout(new FlowLayout());

        l1 = new JLabel(s1[0]);
        c1.add(l1);

        b1 = new JComboBox<>(s2);
        c1.add(b1);

        
        b1.addItemListener(new ItemListener() {
            
            
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int index = b1.getSelectedIndex();
                    l1.setIcon(s1[index]);
                    l1.setText("");
                }
            }
        });

        setSize(500, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
           Exam exam = new Exam();
    }
}
