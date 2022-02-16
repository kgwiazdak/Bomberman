package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LightRock extends AbstractRock implements IMapElement{

    public LightRock(Vector2d position) throws FileNotFoundException {
        super(position);
        this.image = new Image(new FileInputStream("src/main/resources/cegla.jpg"));
    }

    @Override
    public String toString() {
        return "LightRock{" +
                "position=" + position +
                '}';
    }
}
