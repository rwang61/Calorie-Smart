package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
// runs console based application

public class CalorieTracker {
    private Types types;
    private Meals meals;
    private Exercises exercises;
    private int calories;
    private int input;
    private Scanner scan;
    private User user;
    private static final String JSON_STORE = "./data/myFile.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: runs the Calorie Tracker application
    public CalorieTracker() {
        user = new User();
        scan = new Scanner(System.in);


        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        runApplication();
    }

    private void runApplication() {
        int choice = 0;
        do {
            mainMenu();
            choice = scan.nextInt();
            switch (choice) {
                case 0:
                    Runtime.getRuntime().exit(0);
                case 1:
                    createNewActivity();
                    break;
                case 2:
                    viewAllActivities();
                    break;
                case 3:
                    viewCalorieBalance();
                    break;
                case 4:
                    loadFromFile();
                    break;
                case 5:
                    saveToFile();
            }
        } while (choice != 0);
    }

    private void mainMenu() {
        System.out.println("Welcome to Calorie Smart");
        System.out.println("Enter the number of the respective action you would like to do:");
        System.out.println("1. Create a new Activity");
        System.out.println("2. View all the activities");
        System.out.println("3. View Calorie Balance");
        System.out.println("4. Load from File");
        System.out.println("5. Save all to File");
        System.out.println("0. Exit");


    }

    // MODIFIES: this
    // EFFECTS: load activities from files
    private void loadFromFile() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded activities from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from file " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: save activities to files
    private void saveToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            System.out.println("Activities have been saved to: " + JSON_STORE + "\n");
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to file: "  + JSON_STORE + "\n");
        }
    }

    private void viewCalorieBalance() {
        System.out.println("The calorie balance is: \r");
        System.out.printf("%-14s%10d%n", "Total Intake: ", user.calCalorieIntakes());
        System.out.printf("%-14s%10d%n", "Total Burnt: ", user.calCaloriesBurnt());
        System.out.printf("%-14s%10d%n", "Balance: ", user.calCalorieBalance());
    }

    // EFFECTS: lists out all the activities
    private void viewAllActivities() {
        System.out.println("Here are your activities:\r ");
        printActivityHeader();
        for (Activity activity : user.getActivities()) {
            printActivity(activity);
        }
    }

    // EFFECTS: prints out the activities header
    private void printActivityHeader() {
        System.out.printf("%-14s%-16s%-10s%10s%n", "Date", "Name", "Type", "Calories");
    }

   // EFFECTS: prints one  activity
    private void printActivity(Activity activity) {
        System.out.printf("%-14s%-16s%-10s%10d%n",
                activity.getDate(),
                activity.getName(),
                activity.getType(),
                activity.getCalories());
    }

    // MODIFIES: this
    // EFFECTS: use the user input to create an activity and output the result
    private void createNewActivity() {
        Types types = collectTypes();
        Activity activity;
        Meals meals = null;
        Exercises exercises = null;
        if (types == null) {
            return;
        }
        if (types == Types.Intake) {
            meals = collectMeals();
            calories = collectCalories();
            activity = new Meal(meals.toString(), calories);
        } else {
            exercises = collectExercises();
            calories = collectCalories();
            activity = new Exercise(exercises.toString(), calories);
        }
        if (meals == null && exercises == null) {
            return;
        }
        user.addActivity(activity);

    }



    // MODIFIES: this
    // EFFECTS: collects the user's input for Activity type
    private Types collectTypes() {
        do {
            System.out.println("Please select 1 - Intake, 2 - Burnt, 0 - Quit");
            input = scan.nextInt();
            switch (input) {
                case 0:
                   // printResult();;
                    break;
                case 1:
                    types = Types.Intake;
                    break;
                case 2:
                    types = Types.Burnt;
                    break;
            }
        } while (input > 2);

        return types;
    }

    // MODIFIES: this
    // EFFECTS: collects the user's input for meals
    private Meals collectMeals() {
        do {
            System.out.println("Please select 1 - BreakFast, 2 - Lunch, 3- Dinner, 4 - Other, 0 - Quit");
            input = scan.nextInt();
            switch (input) {
                case 0:
                    //printResult();
                    //Runtime.getRuntime().exit(0);
                    break;
                case 1:
                    meals = Meals.Breakfast;
                    break;
                case 2:
                    meals = Meals.Lunch;
                    break;
                case 3:
                    meals = Meals.Dinner;
                    break;
                case 4:
                    meals = Meals.Other;
                    break;
            }
        } while (input > 4);
        return meals;

    }

    // MODIFIES: this
    // EFFECTS: collects the user's input for exercises
    public Exercises collectExercises() {
        do {
            System.out.println("Please select 1 - Biking, 2 - Running, 3- Swimming, 4 - Other, 0 - Quit");

            input = scan.nextInt();

            switch (input) {
                case 0:
                    //printResult();
                    //Runtime.getRuntime().exit(0);
                    break;
                case 1:
                    exercises = Exercises.Biking;
                    break;
                case 2:
                    exercises = Exercises.Running;
                    break;
                case 3:
                    exercises = Exercises.Swimming;
                    break;
                case 4:
                    exercises = Exercises.Other;
                    break;
            }
        } while (input > 4);
        return exercises;
    }

    // EFFECTS: collects the user's input for calories
    private int collectCalories() {
        System.out.println("Please input calories:");
        return scan.nextInt();

    }




}
