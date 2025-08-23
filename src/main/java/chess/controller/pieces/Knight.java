package chess.controller.pieces;

import java.util.Arrays;
import java.util.List;

import chess.controller.ChessImage;
import chess.controller.Direction;
import chess.controller.MovementPattern;
import chess.controller.MovementPattern.MovementType;

public class Knight extends Piece {
    public Knight(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Knight.png");
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        return Arrays.asList(
            new MovementPattern(new Direction(1, 2), MovementType.JUMPING),
            new MovementPattern(new Direction(2, 1), MovementType.JUMPING),
            new MovementPattern(new Direction(2, -1), MovementType.JUMPING),
            new MovementPattern(new Direction(1, -2), MovementType.JUMPING),
            new MovementPattern(new Direction(-1, -2), MovementType.JUMPING),
            new MovementPattern(new Direction(-2, -1), MovementType.JUMPING),
            new MovementPattern(new Direction(-2, 1), MovementType.JUMPING),
            new MovementPattern(new Direction(-1, 2), MovementType.JUMPING)
        );
    }
}
