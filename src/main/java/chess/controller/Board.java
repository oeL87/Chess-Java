package chess.controller;

import java.util.List;

import java.awt.Point;

import chess.controller.MovementPattern.MovementType;
import chess.controller.pieces.Piece;

public interface Board {
    /*
     * Initializes the board in a default state
     * 
     */
    void initBoard();

    /*
     * pass in the source and the target cells
     * return whether the move was successful or not
     */
    boolean performMove(Cell source, Cell target, MovementType type);

    /*
     * Check to see if there are any checks on the board
     * return whether a check appears or not
     */
    boolean checkForChecks();

    /*
     * Place pieces back to the whatever the boards base state is
     */
    void resetPieces();

    /*
     * returns the board
     */
    Cell[][] getBoardCells();

    /*
     * creates valid moves and captureable moves from a piece
     */
    MoveLists generateValidMoves(Piece piece, Point start);

    public static class MoveLists {
        private final List<Point> captures;
        private final List<Point> moves;

        public MoveLists(List<Point> captures, List<Point> moves) {
            this.captures = captures;
            this.moves = moves;
        }

        public List<Point> getCaptures() {
            return captures;
        }

        public List<Point> getMoves() {
            return moves;
        }
    }
}
