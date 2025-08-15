package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Move {
    protected Piece movingPiece;
    protected Cell source;
    protected Cell target;
    
    public Move(Piece movingPiece, Cell source, Cell target) {
        this.movingPiece = movingPiece;
        this.source = source;
        this.target = target;
    }

    @Override
    public String toString() {
        return movingPiece.toString() + target.toString();
    }
}
