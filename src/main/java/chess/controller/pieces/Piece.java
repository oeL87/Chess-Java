package chess.controller.pieces;

import java.awt.Image;
import java.awt.Point;
import java.util.List;

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
    public void movePiece(Point p) {
        pos.x = p.x;
        pos.y = p.y;
    }

    public Point getPosition() {
        return pos;
    }

    public boolean isPieceWhite() {
        return white;
    }
}
