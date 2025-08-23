package chess.controller.pieces;

import java.util.Arrays;
import java.util.List;

import chess.controller.ChessImage;
import chess.controller.Direction;
import chess.controller.MovementPattern;
import chess.controller.MovementPattern.MovementType;

public class Bishop extends Piece {
    public Bishop(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Bishop.png");
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        return Arrays.asList(
            new MovementPattern(Direction.NORTHWEST, MovementType.SLIDING),
            new MovementPattern(Direction.NORTHEAST, MovementType.SLIDING),
            new MovementPattern(Direction.SOUTHWEST, MovementType.SLIDING),
            new MovementPattern(Direction.SOUTHEAST, MovementType.SLIDING)
        );
    }

}
