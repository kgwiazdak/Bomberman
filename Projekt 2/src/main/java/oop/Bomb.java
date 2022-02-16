package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bomb implements IMapElement{
    private final Vector2d position;
    private final RectangularMap map;
    private Image image;
    public static int delay = 2000;
    public static int innerDelay = 500;

    public static void setDelay(int d) {
        delay = d;
    }


    public Bomb(Vector2d position, RectangularMap map){
        this.position = position;
        this.map = map;
        try {
            this.image = new Image(new FileInputStream("src/main/resources/bomb.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
