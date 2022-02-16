package oop;

import javafx.scene.image.Image;

public abstract class AbstractRock implements IMapElement {
    protected Vector2d position;
    protected Image image;


    public AbstractRock(Vector2d position){
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public Image getImage(){
        return this.image;
    }

}
