package chess.controller;

public class Direction {
    private final int deltaX;
    private final int deltaY;

    public Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
    
    public int getDeltaX() { 
        return deltaX; 
    }

    public int getDeltaY() { 
        return deltaY; 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Direction direction = (Direction) obj;
        return deltaX == direction.deltaX && deltaY == direction.deltaY;
    }

    @Override
    public String toString() {
        return "Direction: (" + deltaX + ", " + deltaY + ")"; 
    }

    public static final Direction NORTH = new Direction(0, 1);
    public static final Direction SOUTH = new Direction(0, -1);
    public static final Direction EAST = new Direction(1, 0);
    public static final Direction WEST = new Direction(-1, 0);
    public static final Direction NORTHEAST = new Direction(1, 1);
    public static final Direction NORTHWEST = new Direction(-1, 1);
    public static final Direction SOUTHEAST = new Direction(1, -1);
    public static final Direction SOUTHWEST = new Direction(-1, -1);
}
