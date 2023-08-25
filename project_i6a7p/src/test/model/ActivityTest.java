package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityTest {
    Activity testActivity;

    @BeforeEach
    public void setUp(){
        testActivity = new Activity("Exercise", Types.Burnt, 99);
    }

    @Test
    public void testConstructor() {
        assertEquals("Exercise", testActivity.getName());
        assertEquals( Types.Burnt, testActivity.getType());
        assertEquals(99, testActivity.getCalories());
        assertEquals(LocalDate.now(), testActivity.getDate());
    }



}
