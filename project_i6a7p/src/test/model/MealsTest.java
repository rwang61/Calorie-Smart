package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealsTest {
    Meals testMeals;

    @Test
    public void setUp() {
        testMeals = Meals.Breakfast;
        assertEquals(testMeals, Meals.Breakfast);
    }
}
