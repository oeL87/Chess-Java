package chess.controller.pieces;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import chess.controller.ChessImage;
import chess.controller.Direction;
import chess.controller.MovementPattern;
import chess.controller.MovementPattern.MovementType;

public class King extends Piece {
    private boolean kingSideCastle;
    private boolean queenSideCastle; 

    public King(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "King.png");
        kingSideCastle = true;
        queenSideCastle = true;
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        List<MovementPattern> ret = new ArrayList<>(Arrays.asList(
            new MovementPattern(Direction.NORTH, MovementType.STEPPING),
            new MovementPattern(Direction.NORTHEAST, MovementType.STEPPING),
            new MovementPattern(Direction.EAST, MovementType.STEPPING),
            new MovementPattern(Direction.SOUTHEAST, MovementType.STEPPING),
            new MovementPattern(Direction.SOUTH, MovementType.STEPPING),
            new MovementPattern(Direction.SOUTHWEST, MovementType.STEPPING),
            new MovementPattern(Direction.WEST, MovementType.STEPPING),
            new MovementPattern(Direction.NORTHWEST, MovementType.STEPPING)
        ));
        if (kingSideCastle) {
            ret.add(new MovementPattern(new Direction(2, 0), MovementType.STEPPING));
        }
        if (queenSideCastle) {
            ret.add(new MovementPattern(new Direction(-2, 0), MovementType.STEPPING));
        }
        return ret;
    }

    @Override
    public void movePiece(int x, int y) {
        pos.x = x;
        pos.y = y;
        kingSideCastle = false;
        queenSideCastle = false;
    }
}
