package oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "("+this.x+","+this.y+")";
    }

//    public boolean precedes(Vector2d other){
//        if (this.x<= other.x && this.y<= other.y) return true;
//        else return false;
//    }
//
//    public boolean follows(Vector2d other){
//        if (this.x>= other.x && this.y>= other.y) return true;
//        else return false;
//    }
//
//    public Vector2d upperRight(Vector2d other){
//        int x_max, y_max;
//        if (this.x>other.x) x_max=this.x;
//        else x_max=other.x;
//        if (this.y> other.y) y_max=this.y;
//        else y_max= other.y;
//        Vector2d vector = new Vector2d(x_max, y_max);
//        return vector;
//    }
//
//    public Vector2d lowerLeft(Vector2d other){
//        int x_min, y_min;
//        if (this.x<other.x) x_min=this.x;
//        else x_min=other.x;
//        if (this.y< other.y) y_min =this.y;
//        else y_min = other.y;
//        Vector2d vector = new Vector2d(x_min, y_min);
//        return vector;
//    }

    public Vector2d add(Vector2d other){
        Vector2d vector = new Vector2d(this.x+ other.x, this.y+other.y);
        return vector;
    }

//    public Vector2d subtract(Vector2d other){
//        Vector2d vector = new Vector2d(this.x - other.x, this.y - other.y);
//        return vector;
//    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if (this.x==that.x && this.y==that.y) return true;
        return false;
    }

//    public Vector2d opposite(){
//        Vector2d vector = new Vector2d(-this.x, -this.y);
//        return vector;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
