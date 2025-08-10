package chess.controller;

import chess.controller.Pieces.Piece;

public abstract class Move {
    protected Piece piece;
    protected Cell source;
    protected Cell target;
    
    public Move(Piece piece, Cell source, Cell target) {
        this.piece = piece;
        this.source = source;
        this.target = target;
    }


}
