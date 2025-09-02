package chess.controller.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.controller.ChessImage;
import chess.controller.MovementPattern;

public class Pawn extends Piece {

    public Pawn(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Pawn.png");
    }

    @Override
    public List<MovementPattern> getMovementPattern() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return "";
    }
}
