package chess.controller.moves;

import chess.controller.Cell;
import chess.controller.pieces.Piece;

public class Move {
    protected Piece movingPiece;
    protected Cell source;
    protected Cell target;
    protected String modifier;
    
    public Move(Piece movingPiece, Cell source, Cell target) {
        this.movingPiece = movingPiece;
        this.source = source;
        this.target = target;
        this.modifier = "";
    }

    public boolean isPieceWhite() {
        return movingPiece.isPieceWhite();
    }

    public Piece getPiece() {
        return movingPiece;
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

    public void setCheck(boolean isCheck) {
        if (isCheck) {
            modifier = "+";
        }
    }

    public void setCheckmate(boolean isMate) {
        if (isMate) {
            modifier = "#";
        }
    }

    @Override
    public String toString() {
        return movingPiece.toString() + target.toString() + modifier;
    }
}
