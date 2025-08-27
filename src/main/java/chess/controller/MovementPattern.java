package chess.controller;

public class MovementPattern {
    private final Direction direction;
    private final MovementType type;

    public MovementPattern(Direction direction, MovementType type) {
        this.direction = direction;
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public MovementType getType() {
        return type;
    }

    public enum MovementType {
        SLIDING,
        JUMPING,
        STEPPING
    }
}

