package chess.controller.Pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public class Knight extends Piece {
    public Knight(boolean white, int x, int y) {
        super(white, x, y);
        image = new ChessImage("./data/Chess_" + (white ? "White_" : "Black_") + "Knight.png");
    }

    @Override
    public List<Position> generateMovableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        int[][] knightMoves = {
            {1, 2}, {2, 1}, {2, -1}, {1, -2},
            {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}
        };

        for (int[] move : knightMoves) {
            try {
                ret.add(new Position(x + move[0], y + move[1]));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return ret;
    }

    @Override
    public List<Position> generateCapturableCells() {
        List<Position> ret = new ArrayList<>();
        int x = pos.getX(), y = pos.getY();
        int[][] knightMoves = {
            {1, 2}, {2, 1}, {2, -1}, {1, -2},
            {-1, 2}, {-2, 1}, {-2, -1}, {-1, -2}
        };

        for (int[] move : knightMoves) {
            try {
                ret.add(new Position(x + move[0], y + move[1]));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        return ret;
    }
}
