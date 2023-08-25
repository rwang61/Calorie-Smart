package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTest {
    Meal testMeal;

    @BeforeEach
    public void setUp() {
        testMeal = new Meal("Lunch", 100);
    }

    @Test
    public void testConstructor() {
        assertEquals("Lunch", testMeal.getName());
        assertEquals(100, testMeal.getCalories());
    }
}
