package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Castle extends Move {
    private final boolean kingside;

    public Castle(Piece movingPiece, Piece secondMovingPiece, Cell source, Cell target) {
        super(movingPiece, source, target);
        kingside = secondMovingPiece.getPosition().getX() > movingPiece.getPosition().getX();
    }

    @Override
    public String toString() {
        if (kingside) {
            return "O-O" + modifier;
        }
        return "O-O-O" + modifier;
    }
}
