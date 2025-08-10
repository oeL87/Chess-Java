package chess.controller.Pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class Queen extends Piece {
    public Queen(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Queen.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        for (int i = 0; i < 8; i++) {
            if (i != x) ret.add(new Position(i, y));
            if (i != y) ret.add(new Position(x, i));
            if (i != 0) {
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
        }
        return ret;
    }

    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        for (int i = 0; i < 8; i++) {
            if (i != x) ret.add(new Position(i, y));
            if (i != y) ret.add(new Position(x, i));
            if (i != 0) {
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
        }
        return ret;
    }
}
