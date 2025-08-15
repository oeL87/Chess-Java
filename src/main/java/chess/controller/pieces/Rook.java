package chess.controller.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class Rook extends Piece {
    public Rook(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Rook.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i != pos.getX()) ret.add(new Position(i, pos.getY()));
            if (i != pos.getY()) ret.add(new Position(pos.getX(), i));
        }
        return ret;
    }

    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i != pos.getX()) ret.add(new Position(i, pos.getY()));
            if (i != pos.getY()) ret.add(new Position(pos.getX(), i));
        }
        return ret;
    }
}
