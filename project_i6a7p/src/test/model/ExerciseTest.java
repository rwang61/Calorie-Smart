package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Exercise exerciseTest;

    @BeforeEach
    public void setUp() {
        exerciseTest = new Exercise("Running", 100);
    }

    @Test
    public void testConstructor() {
        assertEquals("Running", exerciseTest.getName());
        assertEquals(100, exerciseTest.getCalories());
    }
}
