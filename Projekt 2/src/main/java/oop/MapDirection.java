package oop;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST, NIEZNANY_KIERUNEK;

//    public String toString(){
//        return switch (this) {
//            case NORTH -> "North";
//            case SOUTH -> "South";
//            case WEST -> "West";
//            case EAST -> "East";
//            default -> "idk";
//        };
//    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0, -1);
            case SOUTH -> new Vector2d(0, 1);
            case WEST -> new Vector2d(-1, 0);
            case EAST -> new Vector2d(1, 0);
            default -> new Vector2d(0, 0);
        };
    }

    public MapDirection next(){
        return switch (this) {
            case NORTH -> EAST;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case EAST -> SOUTH;
            default -> NIEZNANY_KIERUNEK;
        };
    }
    public MapDirection previous(){
        return switch (this) {
            case NORTH -> WEST;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case EAST -> NORTH;
            default -> NIEZNANY_KIERUNEK;
        };
    }
}