package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainForm extends JFrame {
    private JPanel mainPanel;
    private JRadioButton rbIntakeByMeal;
    private JRadioButton rbBurnByExercise;
    private JComboBox cbActivities;
    private JTextField txtCalories;
    private JPanel jframeChart;
    private JButton btLoadFromFile;
    private JButton btSaveToFile;
    private JButton btAdd;
    private JButton btQuit;
    private JTable myActivities;
    private JLabel lbBalanceCaption;
    private JScrollPane myScrollPane;
    private JLabel lbCurrentBalance;
    private DefaultTableModel tblModel;
    private User user;
    private JsonReader reader;
    private JsonWriter writer;
    private static final String JSON_STORE =  "./data/myFile.json";

    // EFFECTS: creates the UI screen
    public MainForm() {
        user = new User();
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);

        // set properties for the MainForm
        setContentPane(mainPanel);
        setTitle("Calorie Smart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        cbActivities.setModel(new DefaultComboBoxModel<>(Meals.values()));

        configureTable();

        rbIntakeByMeal.addActionListener(intakeByMeal());
        rbBurnByExercise.addActionListener(burnByExercise());

        btAdd.addActionListener(addActivity());
        btQuit.addActionListener(quitApp());
        btSaveToFile.addActionListener(saveToFile());
        btLoadFromFile.addActionListener(loadFromFile());
    }

    // MODIFIES: this
    // EFFECT: selects the activity type meal and the dropdown box for meals
    private ActionListener intakeByMeal() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbActivities.setModel(new DefaultComboBoxModel<>(Meals.values()));

            }
        };

    }

    // MODIFIES: this
    // EFFECT: selects the activity type exercise and sets up the dropdown box for exercises
    private ActionListener burnByExercise() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbActivities.setModel(new DefaultComboBoxModel<>(Exercises.values()));

            }
        };
    }

    // MODIFIES: this
    // EFFECTS: adds an activity to the row in the table
    private ActionListener addActivity() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Activity activity;
                if (rbIntakeByMeal.isSelected()) {
                    activity = new Meal(cbActivities.getSelectedItem().toString(),
                            Integer.parseInt(txtCalories.getText()));
                } else {
                    activity = new Exercise(cbActivities.getSelectedItem().toString(),
                            Integer.parseInt(txtCalories.getText()));
                }

                user.addActivity(activity);

                Object[] row = {activity.getDate(), activity.getName(), activity.getCalories(),
                        activity.getType()};
                tblModel.addRow(row);

                lbCurrentBalance.setText(Integer.toString(user.calCalorieBalance()));



            }
        };
    }

    // MODIFIES: this
    // EFFECTS: sets up a default table and headers, initializes the table model
    private void configureTable() {
        myActivities.setModel(new DefaultTableModel(null, new String[]{"Data", "Activity", "Calories", "Type"}));
        tblModel = (DefaultTableModel) myActivities.getModel();
    }

    // EFFECTS: quits the app and prints the steps to console
    private ActionListener quitApp() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                Runtime.getRuntime().exit(0);

            }
        };


    }

    // MODIFIES: this
    // EFFECTS: saves the user's activities to the file
    private ActionListener saveToFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(user);
                    writer.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            }
        };
    }

    // MODIFIES: this
    // EFFECTS: loads the user's activities
    private ActionListener loadFromFile() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user = reader.read();

                    loadTableWithActivities();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };

    }

    // MODIFIES: this
    // EFFECTS: loads the activities from the file onto the table
    private void loadTableWithActivities() {
        tblModel.setRowCount(0); // resets the table

        for (Activity activity : user.getActivities()) {
            Object[] row = {activity.getDate(), activity.getName(), activity.getCalories(),
                    activity.getType()};
            tblModel.addRow(row);


        }
        lbCurrentBalance.setText(Integer.toString(user.calCalorieBalance()));
    }


}
