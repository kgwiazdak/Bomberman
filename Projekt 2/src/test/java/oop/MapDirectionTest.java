package oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MapDirectionTest {

    @Test
    public void nextTest() {
        assertNotEquals(MapDirection.WEST, MapDirection.EAST.next());
        assertNotEquals(MapDirection.WEST, MapDirection.WEST.next());
        assertNotEquals(MapDirection.EAST, MapDirection.EAST.next());
        assertNotEquals(MapDirection.EAST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.EAST.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.SOUTH.next());
        assertNotEquals(MapDirection.EAST, MapDirection.WEST.next());
        assertNotEquals(MapDirection.SOUTH, MapDirection.SOUTH.next());
        assertNotEquals(MapDirection.SOUTH, MapDirection.WEST.next());
        assertNotEquals(MapDirection.SOUTH, MapDirection.NORTH.next());
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.WEST, MapDirection.NORTH.next());
        assertNotEquals(MapDirection.NORTH, MapDirection.NORTH.next());
    }


    @Test
    public void previousTest() {
        assertNotEquals(MapDirection.NORTH, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.WEST, MapDirection.WEST.previous());
        assertNotEquals(MapDirection.WEST, MapDirection.SOUTH.previous());
        assertNotEquals(MapDirection.NORTH, MapDirection.WEST.previous());
        assertNotEquals(MapDirection.SOUTH, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.SOUTH, MapDirection.EAST.previous());
        assertNotEquals(MapDirection.EAST, MapDirection.NORTH.previous());
        assertNotEquals(MapDirection.EAST, MapDirection.EAST.previous());
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
        assertNotEquals(MapDirection.WEST, MapDirection.EAST.previous());
        assertNotEquals(MapDirection.EAST, MapDirection.WEST.previous());
        assertNotEquals(MapDirection.NORTH, MapDirection.SOUTH.previous());
        assertNotEquals(MapDirection.SOUTH, MapDirection.SOUTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
    }

    @Test
    public void toUnitVectorTest() {
        assertEquals(MapDirection.NORTH.toUnitVector(), new Vector2d(0,-1));
        assertEquals(MapDirection.SOUTH.toUnitVector(), new Vector2d(0,1));
        assertEquals(MapDirection.WEST.toUnitVector(), new Vector2d(-1,0));
        assertEquals(MapDirection.EAST.toUnitVector(), new Vector2d(1,0));
        assertNotEquals(MapDirection.NORTH.toUnitVector(), new Vector2d(1,0));
    }

}