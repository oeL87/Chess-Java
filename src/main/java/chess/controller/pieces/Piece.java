package chess.controller.pieces;

import java.util.List;
import java.awt.Image;
import java.awt.Point;

import chess.controller.ChessImage;
import chess.controller.MovementPattern;

public abstract class Piece {
    protected final boolean white;
    protected ChessImage image;
    protected Point pos;

    public Piece(boolean white, int x, int y) {
        this.white = white;
        pos = new Point(x, y);
        image = null;
    }

    /*
     * returns the corresponding image from data/
     */
    public Image getImage() {
        return image.getImage();
    }

    /*
     * returns a list of Cells the piece can potentially move to
     */
    public abstract List<MovementPattern> getMovementPattern();

    /*
     * change the position of the piece
     */
    public void movePiece(int x, int y) {
        pos.x = x;
        pos.y = y;
    }

    public Point getPosition() {
        return pos;
    }

    public boolean isPieceWhite() {
        return white;
    }
}
