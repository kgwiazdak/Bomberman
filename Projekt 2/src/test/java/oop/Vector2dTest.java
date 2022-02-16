package oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Vector2dTest {
    Vector2d a = new Vector2d(4, 2);
    Vector2d b = new Vector2d(1, 1);
    Vector2d c = new Vector2d(1, 1);

    @Test
    public void testAdd(){
        assertTrue(a.add(b).equals(new Vector2d(5,3)));
        assertFalse(a.add(b).equals(c));
    }


    @Test
    public void testEquals(){
        assertTrue(c.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    public void testToString(){
        assertEquals(a.toString(), "(4,2)");
        assertEquals(b.toString(), "(1,1)");
    }
}