package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Promotion extends Move {
    private final Piece promotedPiece;

    public Promotion(Piece movingPiece, Cell source, Cell target, Piece promotedTo) {
        super(movingPiece, source, target);
        this.promotedPiece = promotedTo;
    }

    @Override
    public String toString() {
        return target.toString() + "=" + promotedPiece.toString() + modifier;
    }
}