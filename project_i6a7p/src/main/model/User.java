package model;

import org.json.JSONArray;
import org.json.JSONObject;
import ui.Main;

import java.util.ArrayList;


// represents all activities and provides the calorie balance

public class User {
    private ArrayList<Activity> activities;
    private static boolean EnableLogEvent = true;

    // EFFECTS: creates an empty list of activities
    public User() {
        activities = new ArrayList<Activity>();
    }

    // MODIFIES: this
    // EFFECTS: adds an activity to the list
    public void addActivity(Activity activity) {
        activities.add(activity);

        if (EnableLogEvent) {
            EventLog.getInstance().logEvent(
                    new Event(String.format("A new activity \"%s\" with calories of \"%d\" was added to the list"
                                    + " for the user.",
                            activity.getName(),
                            activity.getCalories())));
        }

    }

    // EFFECTS: gets the size of the activities collection
    public int getSize() {
        return activities.size();
    }

    // EFFECTS: calculate the calorie balance
    public int calCalorieBalance() {

        EventLog.getInstance().logEvent(
                    new Event("The total calorie balance is calculated"));

        return calCalorieIntakes() - calCaloriesBurnt();

    }

    // EFFECTS: calculate the total calorie intake
    public int calCalorieIntakes() {
        int intake = 0;
        for (Activity activity : activities) {
            if (activity.getType() == Types.Intake) {
                intake += activity.getCalories();
            }
        }
        return intake;
    }

    // EFFECTS: calculate the total calories burned
    public int calCaloriesBurnt() {
        int burnt = 0;
        for (Activity activity: activities) {
            if (activity.getType() == Types.Burnt) {
                burnt += activity.getCalories();
            }
        }
        return burnt;
    }

    // getters
    public ArrayList<Activity> getActivities() {
        EventLog.getInstance().logEvent(
                    new Event("The activities was retrieved"));
        return activities;
    }

    // EFFECTS: returns meals or exercises in activities
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (Activity activity : activities) {
            jsonArray.put(activity.toJson());
        }

        json.put("activities", jsonArray);
        return json;
    }

}
