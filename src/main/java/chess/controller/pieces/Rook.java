package chess.controller.pieces;

import java.util.Arrays;
import java.util.List;

import chess.controller.ChessImage;
import chess.controller.Direction;
import chess.controller.MovementPattern;
import chess.controller.MovementPattern.MovementType;

public class Rook extends Piece {
    private boolean castleTo;
    public Rook(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Rook.png");
        castleTo = true;
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        return Arrays.asList(
            new MovementPattern(Direction.NORTH, MovementType.SLIDING),
            new MovementPattern(Direction.EAST, MovementType.SLIDING),
            new MovementPattern(Direction.SOUTH, MovementType.SLIDING),
            new MovementPattern(Direction.WEST, MovementType.SLIDING)
        );
    }

    public boolean canCastle() {
        return castleTo;
    }
}
