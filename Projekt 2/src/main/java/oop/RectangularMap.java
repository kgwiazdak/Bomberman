package oop;

import java.io.FileNotFoundException;
import java.util.*;

public class RectangularMap {
    int width; int height; Vector2d lowerLeft; Vector2d upperRight;
    public LinkedHashMap<Vector2d, ArrayList<IMapElement>> mapElements = new LinkedHashMap<>();


    Random rand = new Random();

    public RectangularMap(int width, int height, int nOfLightRock){
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width-1, height-1);

        Vector2d vector;

        // hard rock
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i==0 || i==width-1 || j==0 || j==height-1 || (i%2==0 && j%2==0)){
                    vector = new Vector2d(i,j);
                    try {
                        ArrayList<IMapElement> arr= new ArrayList<>();
                        arr.add(new HardRock(vector));
                        mapElements.put(vector, arr);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        int i=0, nx, ny;
        while (i<nOfLightRock){
            nx = rand.nextInt((width-1)-1)+1;
            ny = rand.nextInt((height-1)-1)+1;
            if (nx==1 && (ny==1 || ny==2 || ny==3)) continue;
            if (ny==1 && (nx==1 || nx==2 || nx==3)) continue;
            if (nx==width-2 && (ny==height-2 || ny==height-3 || ny==height-4)) continue;
            if (ny==width-2 && (nx==height-2 || nx==width-3 || nx==width-4))continue;

            vector = new Vector2d(nx, ny);
            if (!isOccupied(vector)){
                i++;
                try {
                    ArrayList<IMapElement> arr= new ArrayList<>();
                    arr.add(new LightRock(vector));
                    mapElements.put(vector, arr);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position) || !(objectAt(position).get(0) instanceof AbstractRock) && !(objectAt(position).get(0) instanceof Bomb);
    }

    public ArrayList objectAt(Vector2d position){
        return mapElements.get(position);
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position)!=null && objectAt(position).size()>0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }
}
