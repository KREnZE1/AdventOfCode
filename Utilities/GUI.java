package Utilities;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class GUI {

    static final int wid = 800, hei = 500;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Advent of Code");

        //#region Components

        JComboBox<String> action = new JComboBox<>(new String[]{"Create", "Remove"});
        action.setBounds((int)(wid*0.04), (int)(hei*0.1), (int)(wid*0.2), (int)(hei*0.05));
        frame.add(action);

        JComboBox<String> param1 = new JComboBox<>();
        param1.setBounds((int)(wid*0.28), (int)(hei*0.1), (int)(wid*0.2), (int)(hei*0.05));
        //TODO: Add options and refresh when 'action' is changed
        frame.add(param1);

        JTextField param2 = new JTextField();
        param2.setBounds((int)(wid*0.52), (int)(hei*0.1), (int)(wid*0.2), (int)(hei*0.05));
        //TODO: Refresh content when 'action' is changed
        frame.add(param2);

        JButton execUtility = new JButton("Execute");
        execUtility.setBounds((int)(wid*0.76), (int)(hei*0.1), (int)(wid*0.2), (int)(hei*0.05));
        execUtility.addActionListener((ActionEvent e) -> {}); //TODO: Add action
        frame.add(execUtility);


        JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        sep1.setBounds((int)(wid*0.005), (int)(hei*0.18), (int)(wid*0.99), (int)(hei*0.01));
        frame.add(sep1);


        JComboBox<String> years = new JComboBox<>();
        years.setBounds((int)(wid*0.04), (int)(hei*0.21), (int)(wid*0.2), (int)(hei*0.05));
        //TODO: Add options
        frame.add(years);

        JComboBox<String> days = new JComboBox<>(new String[]{"Day", "Day 01", "Day 02", "Day 03", "Day 04", "Day 05", "Day 06", "Day 07", "Day 08", "Day 09", "Day 10", "Day 11", "Day 12", "Day 13", "Day 14", "Day 15", "Day 16", "Day 17", "Day 18", "Day 19", "Day 20", "Day 21", "Day 22", "Day 23", "Day 24", "Day 25"});
        days.setBounds((int)(wid*0.28), (int)(hei*0.21), (int)(wid*0.2), (int)(hei*0.05));
        frame.add(days);

        JComboBox<String> part = new JComboBox<>(new String[]{"Part", "Part 1", "Part 2"});
        part.setBounds((int)(wid*0.52), (int)(hei*0.21), (int)(wid*0.2), (int)(hei*0.05));
        frame.add(part);

        JButton execYear = new JButton("Execute");
        execYear.setBounds((int)(wid*0.76), (int)(hei*0.21), (int)(wid*0.2), (int)(hei*0.05));
        execYear.addActionListener((ActionEvent e) -> {}); //TODO: Add action
        //TODO: Disable button if execution is impossible
        frame.add(execYear);


        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        sep2.setBounds((int)(wid*0.005), (int)(hei*0.29), (int)(wid*0.99), (int)(hei*0.01));
        frame.add(sep2);


        

        //#endregion

        frame.setLayout(null);
        frame.setSize(wid, hei);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
