package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Capture extends Move {

    public Capture(Piece movingPiece, Cell source, Cell target) {
        super(movingPiece, source, target);
    }

    @Override
    public String toString() {
        return movingPiece.toString() + "x" + target.toString() + modifier; 
    }
}
