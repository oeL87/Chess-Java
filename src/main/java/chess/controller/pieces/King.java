package chess.controller.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class King extends Piece {
    private boolean canCastle; 

    public King(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "King.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (pos.getX() == x && pos.getY() == y) continue;
                ret.add(new Position(x, y));
            }
        }
        if (canCastle) {
            ret.add(new Position(2, pos.getY()));
            ret.add(new Position(6, pos.getY()));
        }
        return ret;
    }
    
    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                if (pos.getX() == x && pos.getY() == y) continue;
                ret.add(new Position(x, y));
            }
        }
        return ret;
    }

    @Override
    public boolean movePiece(int x, int y) {
        try {
            pos.setX(x);
            pos.setY(y);
            canCastle = false;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
