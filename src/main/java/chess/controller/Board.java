package chess.controller;

import java.awt.Point;
import java.util.List;

import chess.controller.errors.InvalidMoveException;
import chess.controller.errors.StillCheckedException;
import chess.controller.moves.Move;

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
    Move performMove(Cell source, Cell target) throws InvalidMoveException, StillCheckedException;

    /*
     * Check to see if there are any checks for isWhite
     */
    boolean checkForChecksForColor(boolean isWhite);

    /*
     * Check to see if isWhite is checkmated
     */
    boolean checkForCheckmateForColor(boolean isWhite);

    /*
     * Check to see if isWhite is stalemated
     */
    boolean checkForStalemateForColor(boolean isWhite);

    /*
     * Place pieces back to the whatever the boards base state is
     */
    void resetPieces();

    /*
     * returns the board
     */
    Cell[][] getBoardCells();

    /*
     * creates valid moves and captureable moves from a starting Point
     */
    MoveLists generateValidMoves(Point start);

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

        public boolean hasCaptures() {
            return captures != null;
        }

        public boolean hasMoves() {
            return moves != null;
        }

        public boolean contains(Point point) {
            boolean captureable = captures != null ? captures.contains(point) : false;
            boolean moveable = moves != null ? moves.contains(point) : false;

            return captureable || moveable;
        }

        @Override
        public String toString() {
            String ret = "MoveList ";
            String captureable = "captures: " + (captures != null ? captures.toString() : "empty ");
            String moveable = "moves: " + (moves != null ? moves.toString() : "empty");
            return ret + captureable + moveable;
        }
    }
}
