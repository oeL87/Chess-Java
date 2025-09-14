package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class PromotionCapture extends Capture {
    private final Piece promotedPiece;

    public PromotionCapture(Piece movingPiece, Cell source, Cell target, Piece promotedTo) {
        super(movingPiece, source, target);
        this.promotedPiece = promotedTo;
    }

    @Override
    public String toString() {
        return source.getColumnLetter() + "x" + target.toString() + "=" + promotedPiece.toString() + modifier;
    }
}