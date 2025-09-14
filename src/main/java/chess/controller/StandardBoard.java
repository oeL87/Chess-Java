package chess.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import chess.controller.errors.InvalidMoveException;
import chess.controller.errors.StillCheckedException;
import chess.controller.moves.Capture;
import chess.controller.moves.Castle;
import chess.controller.moves.Move;
import chess.controller.moves.Promotion;
import chess.controller.moves.PromotionCapture;
import chess.controller.pieces.Bishop;
import chess.controller.pieces.King;
import chess.controller.pieces.Knight;
import chess.controller.pieces.Pawn;
import chess.controller.pieces.Piece;
import chess.controller.pieces.Queen;
import chess.controller.pieces.Rook;

public final class StandardBoard implements Board {
    private Cell[][] board;
    private int moveCount;
    private boolean whiteTurn;
    private Move lastMove;

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
    public Move performMove(Cell source, Cell target) throws InvalidMoveException, StillCheckedException {
        Piece piece = source.getPiece();  
        validateMove(source, target, piece);

        if (piece instanceof King && Math.abs(target.getPosition().x - source.getPosition().x) == 2) {
            Piece castled = performCastle(source, target);
            lastMove = new Castle(piece, castled, source, target);
        } else if (target.getPiece() == null) {
            lastMove = new Move(piece, source, target);
        } else {
            lastMove = new Capture(piece, source, target);
        }

        source.setPiece(null);
        target.setPiece(piece);
        piece.movePiece(target.getPosition());
        lastMove.setCheck(checkForChecksForColor(!whiteTurn));
        lastMove.setCheckmate(checkForCheckmateForColor(!whiteTurn));
        whiteTurn = !whiteTurn;
        moveCount++;

        return lastMove;
    }

    @Override
    public boolean isPromotion(Piece piece) {
        if (!(piece instanceof Pawn)) return false;
        return !((piece.isPieceWhite() && piece.getPosition().y != 7) || 
                (!piece.isPieceWhite() && piece.getPosition().y != 2));
    }

    @Override
    public Move performMove(Cell source, Cell target, Piece promoted) throws InvalidMoveException, StillCheckedException {
        if (promoted instanceof Pawn) throw new InvalidMoveException("Cannot promote to Pawn");

        Piece piece = source.getPiece();
        validateMove(source, target, piece);

        if (target.getPiece() == null) {
            lastMove = new Promotion(piece, source, target, promoted);
        } else {
            lastMove = new PromotionCapture(piece, source, target, promoted);
        }
        
        source.setPiece(null);
        target.setPiece(promoted);
        piece.movePiece(target.getPosition());
        lastMove.setCheck(checkForChecksForColor(!whiteTurn));
        lastMove.setCheckmate(checkForCheckmateForColor(!whiteTurn));
        whiteTurn = !whiteTurn;
        moveCount++;
        
        return lastMove;
    }

    @Override
    public boolean checkForChecksForColor(boolean isWhite) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                Piece p = cell.getPiece();
                if (p instanceof King && p.isPieceWhite() == isWhite) {
                    return isCellAttacked(cell, !isWhite);
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkForCheckmateForColor(boolean isWhite) {
        if (!checkForChecksForColor(isWhite)) return false;
        return !canKingMove(isWhite);
    }

    @Override
    public boolean checkForStalemateForColor(boolean isWhite) {
        if (checkForCheckmateForColor(isWhite)) return false;
        return !canKingMove(isWhite);
    }

    private boolean canKingMove(boolean isWhite) {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                Piece p = cell.getPiece();
                if (p != null && p.isPieceWhite() == isWhite) {
                    Point piecePos = cell.getPosition();
                    MoveLists possibleMoves = generateValidMoves(piecePos, false);

                    if (possibleMoves.getMoves() != null) {
                        for (Point move : possibleMoves.getMoves()) {
                            board[piecePos.x][piecePos.y].setPiece(null);
                            board[move.x][move.y].setPiece(p);
                            boolean stillInCheck = checkForChecksForColor(isWhite);
                            board[piecePos.x][piecePos.y].setPiece(p);
                            board[move.x][move.y].setPiece(null);
                            if (!stillInCheck) return true;
                        }
                    }

                    if (possibleMoves.getCaptures() != null) {
                        for (Point capture : possibleMoves.getCaptures()) {
                            board[piecePos.x][piecePos.y].setPiece(null);
                            board[capture.x][capture.y].setPiece(p);
                            boolean stillInCheck = checkForChecksForColor(isWhite);
                            board[piecePos.x][piecePos.y].setPiece(p);
                            board[capture.x][capture.y].setPiece(null);
                            if (!stillInCheck) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void validateMove(Cell source, Cell target, Piece piece) throws InvalidMoveException, StillCheckedException {
        if (piece == null) throw new InvalidMoveException("Source has no piece");
        if (piece.isPieceWhite() != whiteTurn) throw new InvalidMoveException("Source's piece colour is wrong");
      
        Piece oldSourcePiece = source.getPiece();
        Piece oldTargetPiece = target.getPiece();
        source.setPiece(null);
        target.setPiece(piece);

        if (checkForChecksForColor(piece.isPieceWhite())) {
            source.setPiece(oldSourcePiece);
            target.setPiece(oldTargetPiece);
            throw new StillCheckedException(piece.isPieceWhite() + " King is still in check");
        }
        source.setPiece(oldSourcePiece);
        target.setPiece(oldTargetPiece);
    }

    private Piece performCastle(Cell source, Cell target) throws InvalidMoveException {
        int row = source.getRowNum() - 1;
        Piece kingRook = board[7][row].getPiece();
        Piece queenRook = board[0][row].getPiece();
        Piece castled = null;
        if (target.getPosition().x > source.getPosition().x && kingRook instanceof Rook && ((Rook) kingRook).canCastle()) {
            Cell rookSource = board[7][row];
            Cell rookTarget = board[5][row];
            rookTarget.setPiece(rookSource.getPiece());
            rookSource.setPiece(null);
            rookTarget.getPiece().movePiece(rookTarget.getPosition());
            castled = kingRook;
        } else if (queenRook instanceof Rook && ((Rook) queenRook).canCastle()) {
            Cell rookSource = board[0][row];
            Cell rookTarget = board[3][row];
            rookTarget.setPiece(rookSource.getPiece());
            rookSource.setPiece(null);
            rookTarget.getPiece().movePiece(rookTarget.getPosition());
            castled = queenRook;
        }
        if (castled == null) throw new InvalidMoveException("No piece to castle to");
        return castled;
    }

    public Move getLastMove() {
        return lastMove;
    }

    private boolean isCellAttacked(Cell cell, boolean isWhite) {
        Point pos = cell.getPosition();
        for (Cell[] row : board) {
            for (Cell attacker : row) {
                Piece p = attacker.getPiece();
                if (p != null && p.isPieceWhite() == isWhite) {
                    MoveLists moves = generateValidMoves(attacker.getPosition(), false);
                    if (moves != null && moves.getCaptures() != null && moves.getCaptures().contains(pos)) {
                        return true;
                    }
                }
            }
        }
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
    public MoveLists generateValidMoves(Point start) {
        return generateValidMoves(start, true);
    }

    private MoveLists generateValidMoves(Point start, boolean includeCastling) {
        Piece piece = board[start.x][start.y].getPiece();
        if (piece == null) return new MoveLists(null, null);

        if (piece instanceof Pawn) {
            return generatePawnMoves(piece, start);
        }

        List<MovementPattern> patterns = piece.getMovementPattern();
        List<Point> moves = new ArrayList<>();
        List<Point> captures = new ArrayList<>();

        for (MovementPattern pattern : patterns) {
            Direction dir = pattern.getDirection();
            boolean pieceColor = piece.isPieceWhite();

            switch (pattern.getType()) {
                case SLIDING:
                    Point curr = new Point(start.x + dir.getDeltaX(), start.y + dir.getDeltaY());
                    boolean capture = false;
                    while (validatePosition(curr, pieceColor) && !capture) {
                        capture = sortMove(curr, pieceColor, moves, captures);
                        curr = new Point(curr.x + dir.getDeltaX(), curr.y + dir.getDeltaY());
                    }
                    break;
                case JUMPING:
                case STEPPING:
                    Point point = new Point(start.x + dir.getDeltaX(), start.y + dir.getDeltaY());
                    if (validatePosition(point, pieceColor)) 
                        sortMove(point, pieceColor, moves, captures);
                    break;       
                case SPECIAL: 
                    if (includeCastling) {
                        Point specialPoint = new Point(start.x + dir.getDeltaX(), start.y + dir.getDeltaY());
                        if (validatePosition(specialPoint, pieceColor) && canCastle(start, specialPoint, whiteTurn))
                            moves.add(specialPoint);
                    }
                    break;       
            }

        }

        return new MoveLists(captures, moves);
    }

    private boolean canCastle(Point kingPoint, Point target, boolean isWhite) {
        int row = kingPoint.y;
        int colSource = kingPoint.x;
        int colTarget = target.x;

        if (colSource < colTarget) {
            Cell rookCell = board[7][row];
            Piece rook = rookCell.getPiece();
            if (!(rook instanceof Rook) || !((Rook) rook).canCastle()) return false;

            for (int x = colSource + 1; x < 7; x++) {
                if (board[x][row].getPiece() != null) return false;
            }

            for (int x = colSource; x <= colSource + 2; x++) {
                if (wouldBeInCheck(kingPoint, new Point(x, row), isWhite)) return false;
            }
            return true;
        } else {
            Cell rookCell = board[0][row];
            Piece rook = rookCell.getPiece();
            if (!(rook instanceof Rook) || !((Rook) rook).canCastle()) return false;

            for (int x = colSource - 1; x > 0; x--) {
                if (board[x][row].getPiece() != null) return false;
            }

            for (int x = colSource; x >= colSource - 2; x--) {
                if (wouldBeInCheck(kingPoint, new Point(x, row), isWhite)) return false;
            }
            return true;
        }
    }

    private boolean wouldBeInCheck(Point from, Point to, boolean isWhite) {
        Piece king = board[from.x][from.y].getPiece();
        Piece temp = board[to.x][to.y].getPiece();
        board[from.x][from.y].setPiece(null);
        board[to.x][to.y].setPiece(king);
        boolean inCheck = checkForChecksForColor(isWhite);
        board[to.x][to.y].setPiece(temp);
        board[from.x][from.y].setPiece(king);
        return inCheck;
    }

    private boolean validatePosition(Point point, boolean pieceColor) {
        return point.x < board.length && point.x >= 0 &&
                point.y < board[0].length && point.y >= 0 &&
                (board[point.x][point.y].getPiece() == null ||
                (board[point.x][point.y].getPiece() != null &&
                board[point.x][point.y].getPiece().isPieceWhite() != pieceColor));
    }

    private boolean sortMove(Point point, boolean pieceColor, List<Point> moves, List<Point> captures) {
        if (board[point.x][point.y].getPiece() != null && 
            board[point.x][point.y].getPiece().isPieceWhite() != pieceColor) {
            captures.add(point);
            return true;
        } else if (board[point.x][point.y].getPiece() == null) {
            moves.add(point);
        }
        return false;
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

        if (lastMove != null && lastMove.getPiece() instanceof Pawn) {
            int lastFromY = lastMove.getSource().getPosition().y;
            int lastToY = lastMove.getTarget().getPosition().y;
            int lastToX = lastMove.getTarget().getPosition().x;
            if (Math.abs(lastToY - lastFromY) == 2 && col == lastToY && Math.abs(row - lastToX) == 1) {
                Point enPassant = new Point(lastToX, col + dir);
                if (validatePosition(enPassant, isWhite)) {
                    captures.add(enPassant);
                }
            }
        }

        return new MoveLists(captures, moves);
    }
}
