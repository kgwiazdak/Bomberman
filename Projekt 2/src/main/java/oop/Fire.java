package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fire implements IMapElement{
    private final RectangularMap map;
    private final Vector2d position;
    private Image image;

    public Fire(RectangularMap map, Vector2d position){
        this.map = map;
        this.position = position;
        try {
            this.image = new Image(new FileInputStream("src/main/resources/fire.jpg"));
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
