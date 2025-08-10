package chess.controller.Pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class Bishop extends Piece {
    public Bishop(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Bishop.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        for (int i = 1; i < 8; i++) {
            try {
                ret.add(new Position(x - i, y - i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x - i, y + i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x + i, y - i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x + i, y + i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return ret;
    }

    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        for (int i = 1; i < 8; i++) {
            try {
                ret.add(new Position(x - i, y - i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x - i, y + i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x + i, y - i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            try {
                ret.add(new Position(x + i, y + i));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return ret;
    }
}
