package persistance;

import model.User;
import model.Activity;
import model.Types;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    private User user;

    @BeforeEach
    void runBefore() {
        user = new User();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonexistentFile.json");
        try {
            User user = reader.read();
            fail("IOException is not caught");
        } catch(IOException e) {

        }
    }

    @Test
    void testEmptyActivities() {
        JsonWriter writer = new JsonWriter("./data/testEmptyActivitiesFile.json");
        JsonReader reader = new JsonReader("./data/testEmptyActivitiesFile.json");

        try {
            writer.open();
            writer.write(user);
            writer.close();

            user = reader.read();

            assertEquals(0, user.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testTwoActivities() {
        try {
            user.addActivity(new Activity("BreakFast", Types.Intake, 200));
            user.addActivity(new Activity("Swimming", Types.Burnt, 100));
            JsonWriter writer = new JsonWriter("./data/testTwoActivitiesFile.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testTwoActivitiesFile.json");
            user = reader.read();

            assertEquals(200, user.calCalorieIntakes());
            assertEquals(100, user.calCaloriesBurnt());
            assertEquals(100, user.calCalorieBalance());
            assertEquals(2, user.getSize());
        } catch (IOException e) {
            fail ("Couldn't read from file");
        }
    }
}

