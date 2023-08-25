package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExercisesTest {
    Exercises testExercises;

    @Test
    public void testEnumExercises() {
        testExercises = Exercises.Biking;
        assertEquals(Exercises.Biking, testExercises);

    }
}

