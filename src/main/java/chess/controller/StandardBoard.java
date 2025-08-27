package chess.controller;

import java.util.ArrayList;
import java.util.List;

import java.awt.Point;

import chess.controller.MovementPattern.MovementType;
import chess.controller.pieces.*;

public class StandardBoard implements Board {
    private Cell[][] board;
    private int moveCount;
    private boolean whiteTurn;

    public StandardBoard() {
        initBoard();
        moveCount = 0;
        whiteTurn = true;
    }
    
    @Override
    public void initBoard() {
        board = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell((char)('a' + row), col + 1);
            }
        }

        resetPieces();
    }

    @Override
    public boolean performMove(Cell source, Cell target, MovementType type) {
        if (source.getPiece() == null) return false;
        Piece piece = source.getPiece();
        source.setPiece(target.getPiece());
        target.setPiece(piece);
        piece.movePiece(target.getPosition().x, target.getPosition().y);
        if (target.getPiece() != null) {
            target.getPiece().movePiece(source.getPosition().x, source.getPosition().y);
        }
        whiteTurn = !whiteTurn;
        moveCount++;
        return true;
    }

    @Override
    public boolean checkForChecks() {
        boolean check = false;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col].getPiece() != null) {
                    check = check || checkForKing(board[row][col]);
                }
            }
        }
        return check;
    }

    private boolean checkForKing(Cell cell) {


        return false;//stub
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
        for (int row = 0; row < 8; row++) {
            board[row][1].setPiece(new Pawn(true, row, 1));
            board[row][6].setPiece(new Pawn(false, row, 6));
        }
    }

    private void placeRoyalty() {
        board[3][0].setPiece(new Queen(true, 3, 0));
        board[3][7].setPiece(new Queen(false, 3, 7));
        board[4][0].setPiece(new King(true, 4, 0));
        board[4][7].setPiece(new King(false, 4, 7));
    }

    @Override
    public Cell[][] getBoardCells() {
        return board;
    }

    @Override
    public MoveLists generateValidMoves(Piece piece, Point start) {
        if (piece instanceof Pawn) {
            return generatePawnMoves(piece, start);
        }

        List<MovementPattern> patterns = piece.getMovementPattern();
        List<Point> moves = new ArrayList<>();
        List<Point> captures = new ArrayList<>();

        for (MovementPattern pattern : patterns) {
            Direction dir = pattern.getDirection();
            boolean pieceColor = piece.isPieceWhite();
            System.out.println(dir);
            switch (pattern.getType()) {
                case SLIDING:
                    Point curr = new Point(start.x + dir.getDeltaX(), start.y + dir.getDeltaY());
                    while (validatePosition(curr, pieceColor)) {
                        sortMove(curr, pieceColor, moves, captures);
                        curr.x += dir.getDeltaX();
                        curr.y += dir.getDeltaY();
                    }
                    break;
                case JUMPING:
                case STEPPING:
                    Point point = new Point(start.x + dir.getDeltaX(), start.y + dir.getDeltaY());
                    if (!validatePosition(point, pieceColor)) break;
                    sortMove(point, pieceColor, moves, captures);
                    break;       
            }

        }
        System.out.println("piece has " + moves.size() + " moves\n and " + captures.size() + " captures");
        return new MoveLists(captures, moves);
    }

    private boolean validatePosition(Point point, boolean pieceColor) {
        return point.x < board.length && point.x >= 0 &&
                point.y < board[0].length && point.y >= 0 &&
                (board[point.x][point.y].getPiece() == null ||
                (board[point.x][point.y].getPiece() != null &&
                board[point.x][point.y].getPiece().isPieceWhite() != pieceColor));
    }

    private void sortMove(Point point, boolean pieceColor, List<Point> moves, List<Point> captures) {
        if (board[point.x][point.y].getPiece() != null && 
            board[point.x][point.y].getPiece().isPieceWhite() != pieceColor) {
            captures.add(point);
        } else if (board[point.x][point.y].getPiece() == null) {
            moves.add(point);
        }
    }

    private MoveLists generatePawnMoves(Piece pawn, Point start) {
        List<Point> moves = new ArrayList<>();
        List<Point> captures = new ArrayList<>();
        boolean isWhite = pawn.isPieceWhite();
        int dir = isWhite ? 1 : -1;
        int startRow = isWhite ? 1 : 6;
        int row = start.x;
        int col = start.y;

        Point step = new Point(row, col + dir);
        if (validatePosition(step, isWhite) && board[step.x][step.y].getPiece() == null) {
            moves.add(step);
            Point doubleMove = new Point(row, col + 2 * dir);
            if (col == startRow && board[doubleMove.x][doubleMove.y].getPiece() == null) {
                moves.add(doubleMove);
            }
        }

        for (int take = -1; take <= 1; take += 2) {
            int newCol = col + dir;
            int newRow = row + take;
            Point diag = new Point(newRow, newCol);
            if (validatePosition(diag, isWhite)) {
                Piece target = board[diag.x][diag.y].getPiece();
                if (target != null && target.isPieceWhite() != isWhite) {
                    captures.add(diag);
                }
            }
        }
        System.out.println("piece has " + moves.size() + " moves\n and " + captures.size() + " captures");
        return new MoveLists(captures, moves);
    }
}
