package oop;

import oop.gui.App;
import javafx.scene.image.Image;

import java.util.ArrayList;

public abstract class AbstractCreature implements IMapElement{
    protected RectangularMap map;
    protected Vector2d position;
    protected MoveDirection direction;
    protected Image image;
    protected boolean canPlantTheBomb = true;

    public boolean isCanPlantTheBomb() {
        return canPlantTheBomb;
    }

    public void setCanPlantTheBomb(boolean canPlantTheBomb) {
        this.canPlantTheBomb = canPlantTheBomb;
    }



    public AbstractCreature(RectangularMap map){
        this.direction = MoveDirection.FORWARD;
        this.map = map;
    }



    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public abstract void changeImage(MapDirection direction);

    public RectangularMap getMap(){
        return this.map;
    }


    public void move(MapDirection direction){
        Vector2d vector = this.position.add(direction.toUnitVector());
        changeImage(direction);
        if (map.canMoveTo(vector)) {
            ArrayList<IMapElement> arr = map.objectAt(this.position);
            for (int i = 0; i < arr.size(); i++) {
                if (this == arr.get(i)) arr.remove(i);
                break;
            }
            if (map.mapElements.get(vector)==null){
                ArrayList<IMapElement> a = new ArrayList<>();
                a.add((IMapElement) this);
                map.mapElements.put(vector,a);
            } else {
                map.mapElements.get(vector).add((IMapElement) this);
            }
            this.position = vector;
        }
    }
}
