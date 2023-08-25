package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

// JsonWriter class modelled from edx
// Represents a reader that reads activities from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads activities from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);


        EventLog.getInstance().logEvent(
                    new Event("All activities were loaded from the file"));

        return parseActivities(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseActivities(JSONObject jsonObject) {

        User user = new User();

        JSONArray jsonArray = jsonObject.getJSONArray("activities");
        for (Object json : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) json;
            addActivity(user, jsonObject1);
        }

        return user;
    }

    // MODIFIES: activities
    // EFFECTS: parses activity from JSON object and adds it to Activities
    private void addActivity(User user, JSONObject jsonObject) {
        LocalDate date = LocalDate.parse(jsonObject.getString("date"));
        String name = jsonObject.getString("activity");
        Types type = Types.valueOf(jsonObject.getString("type"));
        int calories = jsonObject.getInt("calories");


        user.addActivity(new Activity(name, type, calories, date));

    }
}

