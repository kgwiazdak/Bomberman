package oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CreatureAndMapTest {
    RectangularMap map = new RectangularMap(13,13, 5);
    CreatureLeft kowalski = new CreatureLeft(map);
    CreatureRight nowak = new CreatureRight(map);
    ArrayList<IMapElement> arr1 = new ArrayList<>();
    ArrayList<IMapElement> arr2 = new ArrayList<>();


    @Test
    public void formationTest() {
        arr1.add(kowalski);
        arr2.add(nowak);

        assertNull(map.objectAt(new Vector2d(1, 1)));
        assertNull(map.objectAt(new Vector2d(1, 2)));
        assertNull(map.objectAt(new Vector2d(1, 3)));
        assertNull(map.objectAt(new Vector2d(2, 1)));
        assertNull(map.objectAt(new Vector2d(3, 1)));
        assertNotEquals(map.objectAt(new Vector2d(1,2)), kowalski);

        assertNull(map.objectAt(new Vector2d(map.getWidth() - 3, map.getHeight() - 2)));
        assertNull(map.objectAt(new Vector2d(map.getWidth() - 4, map.getHeight() - 2)));
        assertNull(map.objectAt(new Vector2d(map.getWidth() - 5, map.getHeight() - 2)));
        assertNull(map.objectAt(new Vector2d(map.getWidth() - 2, map.getHeight() - 4)));
        assertNull(map.objectAt(new Vector2d(map.getWidth() - 2, map.getHeight() - 5)));

        map.mapElements.put(new Vector2d(1,1), arr1);
        map.mapElements.put(new Vector2d(map.getWidth()-2, map.getHeight()-2), arr2);

        assertEquals(map.objectAt(new Vector2d(1,1)).get(0), kowalski);
        assertEquals(map.objectAt(new Vector2d(map.getWidth()-2,map.getHeight()-2)).get(0), nowak);
        assertTrue(map.isOccupied(new Vector2d(1,1)));
        assertFalse(map.isOccupied(new Vector2d(1,2)));
        assertTrue(map.canMoveTo(new Vector2d(1,2)));
        assertTrue(map.canMoveTo(new Vector2d(1,1)));
        assertFalse(map.canMoveTo(new Vector2d(1,0)));

        assertTrue(map.isOccupied(new Vector2d(map.getWidth()-2, map.getHeight()-2)));
        assertFalse(map.isOccupied(new Vector2d(map.getWidth()-2, map.getHeight()-3)));
        assertFalse(map.isOccupied(new Vector2d(map.getWidth()-2, map.getHeight()-3)));
        assertTrue(map.isOccupied(new Vector2d(map.getWidth()-2, map.getHeight()-2)));
        assertTrue(map.isOccupied(new Vector2d(map.getWidth()-2, map.getHeight()-1)));

        assertEquals(map.getUpperRight(),new Vector2d(map.getWidth()-1, map.getHeight()-1));
        assertEquals(map.getLowerLeft(),new Vector2d(0,0));

        assertEquals(kowalski.getPosition(), new Vector2d(1,1));
        kowalski.move(MapDirection.EAST);
        assertEquals(kowalski.getPosition(), new Vector2d(2,1));
        kowalski.move(MapDirection.WEST);
        kowalski.move(MapDirection.SOUTH);
        assertEquals(kowalski.getPosition(), new Vector2d(1,2));
        kowalski.move(MapDirection.NORTH);
        assertEquals(kowalski.getPosition(), new Vector2d(1,1));
    }
}
