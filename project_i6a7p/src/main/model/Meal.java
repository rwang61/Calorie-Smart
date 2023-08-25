package model;

// represents a Meal activity

public class Meal extends Activity {

    // EFFECTS: constructs a type of activity
    public Meal(String name, int calories) {
        super(name, Types.Intake, calories);
    }
}
