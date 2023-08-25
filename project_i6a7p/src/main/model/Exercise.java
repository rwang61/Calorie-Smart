package model;

// represents an Exercise activity

public class Exercise extends Activity {

    // EFFECTS: constructs a type of activity
    public Exercise(String name, int calories) {
        super(name, Types.Burnt, calories);
    }
}
