package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

public class CreatureRight extends AbstractCreature{
    private LinkedHashMap<MapDirection, Image> images = new LinkedHashMap<>();

    public CreatureRight(RectangularMap map){
        super(map);
        this.position = new Vector2d(map.width-2,map.height-2);
        try {
            this.image = new Image(new FileInputStream("src/main/resources/front2.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            images.put(MapDirection.SOUTH, new Image(new FileInputStream("src/main/resources/front2.jpg")));
            images.put(MapDirection.NORTH, new Image(new FileInputStream("src/main/resources/back2.jpg")));
            images.put(MapDirection.EAST, new Image(new FileInputStream("src/main/resources/right2.png")));
            images.put(MapDirection.WEST, new Image(new FileInputStream("src/main/resources/left2.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void changeImage(MapDirection direction){
        this.image = images.get(direction);
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public String toString() {
        return "CreatureRight{" +
                "position=" + position +
                '}';
    }
}
