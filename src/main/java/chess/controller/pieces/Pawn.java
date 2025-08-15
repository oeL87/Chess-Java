package chess.controller.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class Pawn extends Piece {
    private boolean doubleMove;

    public Pawn(boolean white, int x, int y) {
        super(white, x, y);
        doubleMove = true;
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Pawn.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        ret.add(new Position(pos.getX(), pos.getY() + (white ? 1 : -1)));
        if (doubleMove) {
            ret.add(new Position(pos.getX(), pos.getY() + (white ? 2 : -2)));
        }
        return ret;
    }

    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        ret.add(new Position(pos.getX() + 1, pos.getY() + (white ? 1 : -1)));
        ret.add(new Position(pos.getX() - 1, pos.getY() + (white ? 1 : -1)));
        return ret;
    }

    @Override
    public boolean movePiece(int x, int y) {
        try {
            pos.setX(x);
            pos.setY(y);
            doubleMove = false;
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
