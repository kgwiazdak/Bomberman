package oop;

import oop.gui.App;

import java.util.ArrayList;

public class PlantTheBomb implements Runnable{


    private final App app;
    private final RectangularMap map;
    private final AbstractCreature creature1, creature2;
    private Vector2d vector;
    private Vector2d up;
    private Vector2d down;
    private Vector2d left;
    private Vector2d right;



    public PlantTheBomb(App app, Vector2d position, RectangularMap map, AbstractCreature creature1, AbstractCreature creature2){
        this.app = app;
        this.vector = position;
        this.map = map;
        this.creature1 = creature1;
        this.creature2 = creature2;
    }

    private boolean checkTerms(Vector2d position){
        if (map.canMoveTo(position)) {
            if (map.objectAt(position)!=null && map.objectAt(position).size()>0 && map.objectAt(position).get(0) instanceof AbstractCreature){
                if (map.objectAt(position).size()>1){
                    app.changeLiveScore((AbstractCreature) map.objectAt(position).get(0));
                    app.changeLiveScore((AbstractCreature) map.objectAt(position).get(1));
                } else {
                    app.changeLiveScore((AbstractCreature) map.objectAt(position).get(0));
                }
                map.objectAt(position).clear();
                map.objectAt(position).add(0, new Fire(map, position));
                return true;
            }

            if (map.objectAt(position)==null) {
                ArrayList<IMapElement> arr= new ArrayList<>();
                arr.add(new Fire(map, position));
                map.mapElements.put(position, arr);
            } else {
                map.objectAt(position).add(new Fire(map, position));
            }
        }

        else if (!(map.objectAt(position).get(0) instanceof HardRock)){
            map.objectAt(position).clear();
            map.objectAt(position).add(new Fire(map, position));
        }
        return false;
    }



    private void endGame(){
        app.newGame();
    }

    private void delFire(Vector2d position){
        if (map.objectAt(position).size()>0 && map.objectAt(position).get(0) instanceof Fire) {
            map.objectAt(position).remove(0);
        }
    }

    @Override
    public void run() {
        try {
            up = vector.add(MapDirection.NORTH.toUnitVector());
            down = vector.add(MapDirection.SOUTH.toUnitVector());
            right = vector.add(MapDirection.EAST.toUnitVector());
            left = vector.add(MapDirection.WEST.toUnitVector());
            Bomb bomb = new Bomb(vector, map);
            map.objectAt(vector).add(0, bomb);
            app.printMap2();


            Thread.sleep(Bomb.delay);

            int counter = 0;
            if (creature1.getPosition() == vector) {
                counter++;
                app.changeLiveScore((AbstractCreature) map.objectAt(vector).get(1));
            }
            if (creature2.getPosition() == vector) {
                counter++;
                app.changeLiveScore((AbstractCreature) map.objectAt(vector).get(2));
            }


            map.objectAt(vector).clear();
            map.objectAt(vector).add(new Fire(map, vector));
            if (checkTerms(up)) counter++;
            if (checkTerms(down)) counter++;
            if (checkTerms(left)) counter++;
            if (checkTerms(right)) counter++;
            app.printMap2();

            Thread.sleep(Bomb.innerDelay);
            if (counter>0) endGame();

            delFire(vector);
            delFire(up);
            delFire(down);
            delFire(left);
            delFire(right);

            app.printMap2();
            creature1.setCanPlantTheBomb(true);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
