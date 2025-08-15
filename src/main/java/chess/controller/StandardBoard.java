package chess.controller;

import chess.controller.moves.Move;
import chess.controller.pieces.Bishop;
import chess.controller.pieces.King;
import chess.controller.pieces.Knight;
import chess.controller.pieces.Pawn;
import chess.controller.pieces.Queen;
import chess.controller.pieces.Rook;

public class StandardBoard implements Board {
    Cell[][] board;

    public StandardBoard() {
        initBoard();
    }
    
    @Override
    public void initBoard() {
        board = new Cell[8][8];
        resetPieces();
    }

    @Override
    public boolean performMove(Move move) {

        return false;
    }

    @Override
    public boolean checkForChecks() {
        return false;
    }

    @Override
    public void resetPieces() {
        placeRooks();
        placeKnights();
        placeBishops();
        placePawns();
        placeRoyalty();
    }

    private void placeRooks() {
        board[0][0].setPiece(new Rook(true, 0, 0));
        board[7][0].setPiece(new Rook(true, 7, 0));
        board[0][7].setPiece(new Rook(false, 0, 7));
        board[7][7].setPiece(new Rook(false, 7, 7));
    }

    private void placeKnights() {
        board[1][0].setPiece(new Knight(true, 1, 0));
        board[6][0].setPiece(new Knight(true, 6, 0));
        board[1][7].setPiece(new Knight(false, 1, 7));
        board[6][7].setPiece(new Knight(false, 6, 7));
    }

    private void placeBishops() {
        board[2][0].setPiece(new Bishop(true, 2, 0));
        board[5][0].setPiece(new Bishop(true, 5, 0));
        board[2][7].setPiece(new Bishop(false, 2, 7));
        board[5][7].setPiece(new Bishop(false, 5, 7));
    }

    private void placePawns() {
        for (int x = 0; x < 8; x++) {
            board[x][1].setPiece(new Pawn(true, x, 1));
            board[x][6].setPiece(new Pawn(false, x, 6));
        }
    }

    private void placeRoyalty() {
        board[3][0].setPiece(new Queen(true, 3, 0));
        board[3][7].setPiece(new Queen(false, 3, 7));
        board[4][0].setPiece(new King(true, 4, 0));
        board[4][7].setPiece(new King(false, 4, 7));
    }
}
