package oop;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HardRock extends AbstractRock implements IMapElement{

    public HardRock(Vector2d position) throws FileNotFoundException {
        super(position);
        image = new Image(new FileInputStream("src/main/resources/rock.png"));
    }

    @Override
    public String toString() {
        return "HardRock{" +
                "position=" + position +
                '}';
    }
}
