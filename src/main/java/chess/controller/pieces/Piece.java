package chess.controller.pieces;

import java.util.List;

import chess.Model.ChessImage;
import chess.controller.Position;

public abstract class Piece {
    protected final boolean white;
    protected ChessImage image;
    protected Position pos;

    public Piece(boolean white, int x, int y) {
        this.white = white;
        pos = new Position(x, y);
        image = null;
    }

    /*
     * returns the corresponding image from /data/
     */
    public ChessImage getImage() {
        return image;
    }

    /*
     * returns a list of Cells the piece can potentially move to
     */
    public abstract List<Position> generateMovableCells();

    /*
     * returns a list of Cells the piece can potentially capture
     */
    public abstract List<Position> generateCapturableCells();

    /*
     * change the position of the piece
     * returns whether the position is valid or not
     */
    public boolean movePiece(int x, int y) {
        try {
            pos.setX(x);
            pos.setY(y);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}
