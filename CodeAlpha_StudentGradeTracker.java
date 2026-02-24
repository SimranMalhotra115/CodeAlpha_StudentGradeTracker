import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CodeAlpha_StudentGradeTracker extends JFrame implements ActionListener {

    JTextField nameField, marksField;
    JButton addButton, clearButton;
    JTable table;
    DefaultTableModel model;

    public CodeAlpha_StudentGradeTracker() {
        setTitle("Student Grade Tracker");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        panel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Marks (comma separated):"));
        marksField = new JTextField();
        panel.add(marksField);

        addButton = new JButton("Add Student");
        clearButton = new JButton("Clear Fields");

        panel.add(addButton);
        panel.add(clearButton);

        add(panel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Name", "Average", "Highest", "Lowest", "Grade"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(this);
        clearButton.addActionListener(e -> {
            nameField.setText("");
            marksField.setText("");
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String marksText = marksField.getText();

        try {
            String[] marksArray = marksText.split(",");
            ArrayList<Integer> marks = new ArrayList<>();

            for (String m : marksArray) {
                marks.add(Integer.parseInt(m.trim()));
            }

            int sum = 0, max = marks.get(0), min = marks.get(0);

            for (int m : marks) {
                sum += m;
                if (m > max) max = m;
                if (m < min) min = m;
            }

            double avg = (double) sum / marks.size();

            // Add row to table
            String grade;
            if (avg >= 90) grade = "A";
            else if (avg >= 75) grade = "B";
            else if (avg >= 50) grade = "C";
            else grade = "Fail";
            model.addRow(new Object[]{name, avg, max, min, grade});

            nameField.setText("");
            marksField.setText("");
            nameField.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    public static void main(String[] args) {
        new CodeAlpha_StudentGradeTracker();
    }
}