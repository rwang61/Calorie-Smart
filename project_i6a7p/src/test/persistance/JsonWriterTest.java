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

public class JsonWriterTest {
    private User user;

    @BeforeEach
    void runBefore() {
        user = new User();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data\0illegalFile.json");
            writer.open();
            fail("IOException was expected");
        } catch(IOException e) {

        }
    }

    @Test
    void testDefaultFile() {
        try {
            JsonWriter writer = new JsonWriter();
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/default.json");
            user = reader.read();
            assertEquals(0, user.calCalorieBalance());
        } catch (IOException e) {
            fail("Exception should not have been thrown");

        }
    }

    @Test
    void testWriterEmptyActivities() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyActivities.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyActivities.json");
            user = reader.read();
            assertEquals(0, user.calCalorieBalance());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
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
            fail ("Exception should not have been thrown");
        }
    }
}

