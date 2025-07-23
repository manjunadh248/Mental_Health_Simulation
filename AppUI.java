package Mental_Health_Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AppUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextArea responseArea;
    private JComboBox<String> moodBox;
    private JTextArea historyArea;

    private String userName;
    private ArrayList<String> moodHistory = new ArrayList<>();
    private OpenAIMoodEvaluator evaluator = new OpenAIMoodEvaluator();
    private MoodStorage storage = new MoodStorage("mood_history.txt");

    public void createAndShowGUI() {
        frame = new JFrame("Mental Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new CardLayout());

        JPanel welcomePanel = buildWelcomePanel();
        JPanel sessionPanel = buildSessionPanel();
        JPanel historyPanel = buildHistoryPanel();

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Welcome", welcomePanel);
        tabs.addTab("Session", sessionPanel);
        tabs.addTab("Mood History", historyPanel);

        frame.add(tabs);
        frame.setVisible(true);
    }

    private JPanel buildWelcomePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Enter your name:"));
        nameField = new JTextField();
        panel.add(nameField);
        JButton proceed = new JButton("Continue");
        proceed.addActionListener(e -> userName = nameField.getText().trim());
        panel.add(proceed);
        return panel;
    }

    private JPanel buildSessionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel top = new JPanel(new GridLayout(3, 1));
        top.add(new JLabel("Select your mood:"));
        moodBox = new JComboBox<>(new String[]{"Happy", "Sad", "Anxious", "Angry", "Tired", "Excited"});
        top.add(moodBox);

        JButton analyze = new JButton("Evaluate Mood");
        analyze.addActionListener(e -> handleMoodEvaluation());
        top.add(analyze);

        responseArea = new JTextArea(10, 40);
        responseArea.setLineWrap(true);
        responseArea.setWrapStyleWord(true);
        responseArea.setEditable(false);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(responseArea), BorderLayout.CENTER);

        return panel;
    }

    private JPanel buildHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        historyArea = new JTextArea(10, 40);
        historyArea.setEditable(false);
        JButton refresh = new JButton("Refresh History");
        refresh.addActionListener(e -> loadMoodHistory());
        panel.add(refresh, BorderLayout.NORTH);
        panel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        return panel;
    }

    private void handleMoodEvaluation() {
        if (userName == null || userName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your name in the Welcome tab.");
            return;
        }

        String mood = (String) moodBox.getSelectedItem();
        moodHistory.add(userName + ": " + mood);
        storage.saveMood(userName + ": " + mood);

        String response = evaluator.evaluateMood(mood);
        responseArea.setText(response);
    }

    private void loadMoodHistory() {
        historyArea.setText(storage.loadHistory());
    }
}