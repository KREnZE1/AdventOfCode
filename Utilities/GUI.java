package Utilities;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GUI {

    private static JComboBox<String> action, param1, year, day, part;
    private static JTextField param2;
    private static JButton execUtility, execYear;

    private static JTextArea console;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Advent of Code");
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        action = new JComboBox<>(new String[] { "Action", "Create", "Remove" });
        action.addActionListener((ActionEvent e) -> tryToggleUtilty());
        gbc.insets = new Insets(20, 5, 5, 5);
        gbc.weighty = 0.5;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(action, gbc);

        param1 = new JComboBox<>(new String[] { "Param", "Year", "Years", "All", "Empty" });
        param1.addActionListener((ActionEvent e) -> tryToggleUtilty());
        gbc.gridx = 1;
        frame.add(param1, gbc);

        param2 = new JTextField("");
        param2.addActionListener((ActionEvent e) -> tryToggleUtilty());
        gbc.gridx = 2;
        frame.add(param2, gbc);

        execUtility = new JButton("Execute");
        execUtility.addActionListener((ActionEvent e) -> tryUtility());
        execUtility.setEnabled(false);
        gbc.gridx = 3;
        frame.add(execUtility, gbc);

        JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(sep1, gbc);

        year = new JComboBox<>();
        year.addActionListener((ActionEvent e) -> tryToggleYear());
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(year, gbc);

        day = new JComboBox<>(new String[] { "Day", "Day 01", "Day 02", "Day 03", "Day 04", "Day 05", "Day 06",
                "Day 07", "Day 08", "Day 09", "Day 10", "Day 11", "Day 12", "Day 13", "Day 14", "Day 15", "Day 16",
                "Day 17", "Day 18", "Day 19", "Day 20", "Day 21", "Day 22", "Day 23", "Day 24", "Day 25" });
        day.addActionListener((ActionEvent e) -> tryToggleYear());
        gbc.gridx = 1;
        frame.add(day, gbc);

        part = new JComboBox<>(new String[] { "Part", "Part 1", "Part 2" });
        part.addActionListener((ActionEvent e) -> tryToggleYear());
        gbc.gridx = 2;
        frame.add(part, gbc);

        execYear = new JButton("Execute");
        execYear.setEnabled(false);
        execYear.addActionListener((ActionEvent e) -> tryYear());
        gbc.gridx = 3;
        frame.add(execYear, gbc);

        JSeparator sep2 = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.weighty = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(sep2, gbc);

        console = new JTextArea();
        console.setEditable(false);
        JScrollPane scroll = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(800, 300));
        scroll.setMinimumSize(scroll.getPreferredSize());
        gbc.insets = new Insets(5, 5, 20, 5);
        gbc.gridy = 4;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(scroll, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static void tryToggleUtilty() {
        if ("Action".equals(getSelectedItem(action)) || "Param".equals(getSelectedItem(param1)))
            execUtility.setEnabled(false);
        else if ("Create".equals(getSelectedItem(action))
                && ("All".equals(getSelectedItem(param1)) || "Empty".equals(getSelectedItem(param1))))
            execUtility.setEnabled(false);
        else
            execUtility.setEnabled(true);
    }

    private static void tryToggleYear() {
        if ("Year".equals((String) (year.getSelectedItem())) || "Day".equals((String) (day.getSelectedItem()))
                || "Part".equals((String) (part.getSelectedItem())))
            execYear.setEnabled(false);
        else
            execYear.setEnabled(true);
    }

    private static void tryUtility() {
        try {
            String className = "Utilities." + getSelectedItem(action) + "r";
            Class<?> c = Class.forName(className);

            String methodName = getMethodName(getSelectedItem(action), getSelectedItem(param1));
            for (Method m : c.getDeclaredMethods()) {
                if (methodName.equals(m.getName())) {
                    m.invoke(null, param2.getText().split(", ")); //TODO: Add more robust parsing
                    break;
                }
            }
        } catch (ClassNotFoundException cnf) {
            //TODO: Do something
        } catch (IllegalAccessException iae) {

        } catch (InvocationTargetException ite) {

        }
    }

    private static void tryYear() {
        try {
            String className = "Years.Y" + getSelectedItem(year) + "." + getSelectedItem(day).replace(' ', '_')
                    + ".Main";
            Class<?> c = Class.forName(className);

            String methodName = "solve" + getSelectedItem(part).replace(" ", "");
            for (Method m : c.getDeclaredMethods()) {
                if (methodName.equals(m.getName())) {
                    m.invoke(null);
                    break;
                }
            }
        } catch (ClassNotFoundException cnf) {
            //Should not happen, because all three comboboxes have predefined contents, all of which should link to a runnable method
            System.err.println(
                    "An error occurred during finding a solution method.\nThe following parameters lead to a faulty call:\n"
                            + ((String) year.getSelectedItem()) + "\n"
                            + ((String) day.getSelectedItem() + "\n" + ((String) part.getSelectedItem())));
            System.err.println(cnf.getLocalizedMessage());
            System.err.println(cnf.getStackTrace());
        } catch (IllegalAccessException | InvocationTargetException exc) {
            //Should not happen, for example if a private method is called
            //Should not happen, because my methods don't throw errors
            System.err.println("An unexpected error has occurred during execution of a solution");
            System.err.println(exc.getLocalizedMessage());
            System.err.println(exc.getStackTrace());
        }
    }

    private static String getSelectedItem(JComboBox<String> comb) {
        return (String) comb.getSelectedItem();
    }

    private static String getMethodName(String action, String param1) {
        if ("Create".equals(action)) {
            return "Year".equals(param1) ? "createYearStructure" : "loopThroughYears";
        } else if ("Remove".equals(action)) {
            if ("Year".equals(param1))
                return "remYear";
            else if ("Years".equals(param1))
                return "remYears";
            else if ("All".equals(param1))
                return "remAll";
            else if ("Empty".equals(param1))
                return "remAllEmpty";
        }
        System.err.println("No class found for action " + action + " and parameter " + param1);
        return "";

    }
}
//TODO: Print error output to own console instead of std:err
//TODO: Print part solutions to own console instead of std:out