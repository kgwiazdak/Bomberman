package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

public class CreatureLeft extends AbstractCreature{
    private LinkedHashMap<MapDirection, Image> images = new LinkedHashMap<>();
    public CreatureLeft(RectangularMap map){
        super(map);
        this.position = new Vector2d(1,1);
        try {
            this.image = new Image(new FileInputStream("src/main/resources/front1.jpeg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            images.put(MapDirection.SOUTH, new Image(new FileInputStream("src/main/resources/front1.jpeg")));
            images.put(MapDirection.NORTH, new Image(new FileInputStream("src/main/resources/back1.jpg")));
            images.put(MapDirection.EAST, new Image(new FileInputStream("src/main/resources/right1.jpg")));
            images.put(MapDirection.WEST, new Image(new FileInputStream("src/main/resources/left1.jpg")));
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
