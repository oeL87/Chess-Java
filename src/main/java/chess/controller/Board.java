package chess.controller;


public interface Board {

    /*
     * Initializes the board in a default state
     * 
     */
    void initBoard();

    /*
     * move is the move that is being played
     * return whether the move was successful or not
     */
    boolean performMove(Move move);

    /*
     * Check to see if there are any checks on the board
     * return whether a check appears or not
     */
    boolean checkForChecks();

    /*
     * Place pieces back to the whatever the boards base state is
     */
    void resetPieces();
}
