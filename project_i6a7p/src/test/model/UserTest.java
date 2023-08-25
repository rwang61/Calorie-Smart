package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
   User testUser;
    Activity meal;
    Activity exercise;

    @BeforeEach
    public void setup() {
        testUser = new User();
        meal = new Activity("BreakFast", Types.Intake, 100);
        exercise = new Activity("Running", Types.Burnt, 99);

    }

    @Test
    public void testConstructor() {
        assertEquals(0, testUser.getSize());
    }

    @Test
    public void testAddActivity() {
        testUser.addActivity(meal);
        assertEquals(1, testUser.getSize());

        testUser = new User();
        testUser.addActivity(meal);
        testUser.addActivity(exercise);

        ArrayList<Activity> a = testUser.getActivities();
        assertEquals(2, a.size());

    }

    @Test
    public void testGetCalorieBalance() {
        assertEquals(0, testUser.calCalorieBalance());

        testUser.addActivity(meal);
        assertEquals(100, testUser.calCalorieBalance());

        testUser.addActivity(exercise);
        assertEquals(1, testUser.calCalorieBalance());
    }

}
