package chess.controller.pieces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.controller.ChessImage;
import chess.controller.Direction;
import chess.controller.MovementPattern;
import chess.controller.MovementPattern.MovementType;

public class Pawn extends Piece {
    private boolean doubleMove;

    public Pawn(boolean white, int x, int y) {
        super(white, x, y);
        doubleMove = true;
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Pawn.png");
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        List<MovementPattern> ret = new ArrayList<>(Arrays.asList(
            new MovementPattern(white ? Direction.NORTHEAST : Direction.SOUTHEAST, MovementType.SPECIAL),
            new MovementPattern(white ? Direction.NORTHWEST : Direction.SOUTHWEST, MovementType.SPECIAL),
            new MovementPattern(white ? Direction.NORTH : Direction.SOUTH, MovementType.SPECIAL)
        ));
        if (doubleMove) {
            ret.add(new MovementPattern(new Direction(0, white ? 2 : -2), MovementType.SPECIAL));
        }
        return ret;
    }

    @Override
    public void movePiece(int x, int y) {
        pos.x = x;
        pos.y = y;
        doubleMove = false;
    }
}
