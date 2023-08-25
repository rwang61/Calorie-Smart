package model;

import org.json.JSONObject;

import java.time.LocalDate;


// represents an Activity with a name, type, calories and date
public class Activity {
    private String name;
    private Types type;
    private int calories;
    private LocalDate date;

    // REQUIRES: calories > 0
    // EFFECTS: creates an activity
    public Activity(String name, Types type, int calories) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        date = LocalDate.now();
    }

    // REQUIRES: calories > 0
    // EFFECTS: creates an activity with today's date
    public Activity(String name, Types type, int calories, LocalDate date) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.date = date;
    }



    // getters
    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return date;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("date", this.date.toString());
        json.put("activity", this.name);
        json.put("calories", this.calories);
        json.put("type", this.type.toString());

        return json;
    }


}
