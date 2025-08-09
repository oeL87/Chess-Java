package chess.controller;


public interface IBoardFacade {

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
     * return boolean
     */
    boolean checkForChecks();
}
