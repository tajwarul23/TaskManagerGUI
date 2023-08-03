import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerGUI extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton removeButton;

    private List<String> tasks;

    public TaskManagerGUI() {
        tasks = new ArrayList<>();
        taskListModel = new DefaultListModel<>();

        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskField = new JTextField(20);

        addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    taskListModel.addElement(task);
                    taskField.setText("");
                }
            }
        });

        removeButton = new JButton("Mark as Done");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String removedTask = tasks.remove(selectedIndex);
                    taskListModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(TaskManagerGUI.this, "Task removed: " + removedTask);
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskField);
        inputPanel.add(addButton);

        

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); // Increase both width and height
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerGUI();
            }
        });
    }
}

/////////////////////////////////////

