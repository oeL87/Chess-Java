package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Castle extends Move {
    private final Piece secondMovingPiece;

    public Castle(Piece movingPiece, Piece secondMovingPiece, Cell source, Cell target) {
        super(movingPiece, source, target);
        this.secondMovingPiece = secondMovingPiece;
    }

    @Override
    public String toString() {
        if (secondMovingPiece.getPosition().getX() < movingPiece.getPosition().getX()) {
            return "O-O";
        }
        return "O-O-O";
    }
}
