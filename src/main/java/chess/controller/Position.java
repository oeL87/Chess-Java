package chess.controller;

public class Position {
    private int x;
    private int y;
    
    public Position(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        if (x < 0 || x > 7) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0 || y > 7) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds");
        }
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
