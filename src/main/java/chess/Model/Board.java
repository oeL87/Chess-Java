package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
This class represents the board that the game of chess will be played on.
 */
public class Board implements Iterable<Column> {
    private List<Column> columns = new ArrayList<>();
    private List<Piece> pieces = new ArrayList<>();
//    private LinkedList2D board = new LinkedList2D();

    private boolean isWhiteTurn = true;
    private boolean whiteChecked;
    private boolean blackChecked;

    //Creates a new Board that assigns the correct column letter, row number, and all the knight moves
    public Board() {
        boolean isWhite = true;
        for (int x = 97; x < 105; x++) {
            columns.add(new Column(Character.toString(x).charAt(0), isWhite));
            isWhite = !isWhite;
        }
        //0, 0 is bottom right (a1)
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Cell current = columns.get(x).getCells().get(y);
                if (x < 7 && y < 6) {
                    current.setUpRight(columns.get(x + 1).getCells().get(y + 2));
                }
                if (x < 6 && y < 7) {
                    current.setRightUp(columns.get(x + 2).getCells().get(y + 1));
                }
                if (x < 6 && y > 0) {
                    current.setRightDown(columns.get(x + 2).getCells().get(y - 1));
                }
                if (x < 7 && y > 1) {
                    current.setDownRight(columns.get(x + 1).getCells().get(y - 2));
                }
                if (x > 0 && y > 1) {
                    current.setDownLeft(columns.get(x - 1).getCells().get(y - 2));
                }
                if (x > 1 && y > 0) {
                    current.setLeftDown(columns.get(x - 2).getCells().get(y - 1));
                }
                if (x > 1 && y < 7) {
                    current.setLeftUp(columns.get(x - 2).getCells().get(y + 1));
                }
                if (x > 0 && y < 6) {
                    current.setUpLeft(columns.get(x - 1).getCells().get(y + 2));
                }
            }
        }
        resetPieces();
//        placeBishops();
//        addPieceToCell(new Piece(PieceType.ROOK, 'a', 1, true));
//        addPieceToCell(new Piece(PieceType.KING, 'e', 1, true));
//        addPieceToCell(new Piece(PieceType.QUEEN, 'd', 5, true));
//        placeRooks();
        generateAllMovableCells();
        checkForChecks();
    }

    //Method that resets the board to its starting position to start a new game
    public void resetPieces() {
        pieces.clear();
        placeRooks();
        placeKnights();
        placeBishops();
//        placePawns();
        addPieceToCell(new Piece(PieceType.KING, 'e', 1, true));
        addPieceToCell(new Piece(PieceType.KING, 'e', 8, false));
        addPieceToCell(new Piece(PieceType.QUEEN, 'd', 1, true));
        addPieceToCell(new Piece(PieceType.QUEEN, 'd', 8, false));
    }

    //Place the 4 rooks to their starting squares
    private void placeRooks() {
        for (int x = 0; x < 4; x++) {
            boolean isWhite = x >= 2;
            addPieceToCell(new Piece(PieceType.ROOK, x % 2 == 0 ? 'a' : 'h', isWhite ? 1 : 8, isWhite));
        }
    }

    //Places the 4 knights to their starting squares
    private void placeKnights() {
        for (int x = 0; x < 4; x++) {
            boolean isWhite = x >= 2;
            addPieceToCell(new Piece(PieceType.KNIGHT, x % 2 == 0 ? 'b' : 'g', isWhite ? 1 : 8, isWhite));
        }
    }

    //Places the 4 Bishops to their starting squares
    private void placeBishops() {
        for (int x = 0; x < 4; x++) {
            boolean isWhite = x >= 2;
            addPieceToCell(new Piece(PieceType.BISHOP, x % 2 == 0 ? 'c' : 'f', isWhite ? 1 : 8, isWhite));
        }
    }

    //Places the 16 pawns to their starting squares
    private void placePawns() {
        for (int x = 0; x < 16; x++) {
            boolean isWhite = x >= 8;
            addPieceToCell(new Piece(PieceType.PAWN, Character.toString(97 + x % 8).charAt(0),
                    isWhite ? 2 : 7, isWhite));
        }
    }

    //Method that adds the Piece to corresponding letter and row number
    public void addPieceToCell(Piece piece) {
        for (Column column : columns) {
            if (column.getColumnLetter() == piece.getColumnLetter()) {
                for (Cell cell : column) {
                    if (cell.getRowNum() == piece.getRowNum()) {
                        cell.setPiece(piece);
                        break;
                    }
                }
                break;
            }
        }
        pieces.add(piece);
    }

    public void generateAllMovableCells() {
        for (Column column : this) {
            for (Cell c : column) {
                generateMovableCells(c);
            }
        }
    }

    public void generateMovableCells(Cell selected) {
        List<Cell> nextMovableCells = new ArrayList<>();
        if (selected.isHasPiece()) {
            char columnLetter = selected.getColumnLetter();
            int rowNum = selected.getRowNum();
            switch (selected.getPiece().getPieceType()) {
                case KING:
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            try {
                                Cell current = getColumns().get(columnLetter + a - 97)
                                        .getCells().get(rowNum + b - 1);
                                if (current.isHasPiece() &&
                                        current.getPiece().isPieceWhite() != selected.getPiece().isPieceWhite()) {
                                    nextMovableCells.add(current);
                                } else if(!current.equals(selected) &&
                                        rowNum + b < 9 && rowNum + b > 0
                                        && (int) columnLetter + a - '`' < 9 &&
                                        (int) columnLetter + a - '`' > 0) {
                                    nextMovableCells.add(current);
                                }

                            } catch (IndexOutOfBoundsException ignored) {}
                        }
                    }
                    break;
                case QUEEN:
                case ROOK:
                    nextMovableCells.addAll(setEntireRow(columnLetter - 1, rowNum, true, selected, new ArrayList<>()));
                    nextMovableCells.addAll(setEntireRow(columnLetter + 1, rowNum, false, selected, new ArrayList<>()));
                    nextMovableCells.addAll(setEntireColumn(columnLetter, rowNum + 1, true, selected, new ArrayList<>()));
                    nextMovableCells.addAll(setEntireColumn(columnLetter, rowNum - 1, false, selected, new ArrayList<>()));
                    if (selected.getPiece().getPieceType() == PieceType.ROOK) {
                        break;
                    }
                case BISHOP:
//                    System.out.println(columnLetter + " " + rowNum);
                    for (int a = 0; a < 4; a++) {
                        nextMovableCells.addAll(setEntireDiagonal(columnLetter + (a % 2 == 0 ? -1 : 1), rowNum + (a < 2 ? 1 : -1),
                                a < 2, a % 2 == 0, selected, new ArrayList<>()));
                    }
                    break;
                case KNIGHT:
                    nextMovableCells.add(selected.getDownLeft());
                    nextMovableCells.add(selected.getLeftDown());
                    nextMovableCells.add(selected.getLeftUp());
                    nextMovableCells.add(selected.getUpLeft());
                    nextMovableCells.add(selected.getUpRight());
                    nextMovableCells.add(selected.getRightUp());
                    nextMovableCells.add(selected.getRightDown());
                    nextMovableCells.add(selected.getDownRight());
                    break;
                case PAWN:
                    nextMovableCells.add(getColumns().get(columnLetter - 97).getCells().get(rowNum - 1 + (selected.getPiece().isPieceWhite() ? 1 : - 1)));
                    if (selected.getPiece().isDoubleMove()) {
                        nextMovableCells.add(getColumns().get(columnLetter - 97).getCells().get(rowNum - 1 + (selected.getPiece().isPieceWhite() ? 2 : - 2)));
                    }
//                    nextMovableCells.add(new Cell(true, Character.toString(columnLetter).charAt(0),
//                            rowNum + (selected.getPiece().isPieceWhite() ? 1 : - 1)));
//                    if (selected.getPiece().isDoubleMove()) {
//                        nextMovableCells.add(new Cell(true, Character.toString(columnLetter).charAt(0),
//                                rowNum + (selected.getPiece().isPieceWhite() ? 2 : -2)));
//                    }
            }
            Predicate<Cell> notNull = Objects::nonNull;

            nextMovableCells = nextMovableCells.stream()
                    .filter(notNull)
                    .collect(Collectors.toList());
        }
        selected.setNextMoveableCells(nextMovableCells);

//        for (Cell c : nextMovableCells) {
//            c.setCellMove(!c.isCellMove());
//        }
//        System.out.println(nextMovableCells.size());
    }

    //Highlights all the cells in the given Y to be possible moves
    private List<Cell> setEntireRow(int currentX, int currentY, boolean left,Cell selected, List<Cell> nextMovableCells) {
        if (currentX > 'h' || currentX < 'a') {
            return nextMovableCells;
        }
        Cell current = getColumns().get(currentX - 'a').getCells().get(currentY - 1);
//                new Cell(true, Character.toString(currentX).charAt(0), currentY);
        if (current.isHasPiece() && current.getPiece().isPieceWhite() != selected.getPiece().isPieceWhite()) {
            nextMovableCells.add(current);
            return nextMovableCells;
        } else if (current.isHasPiece()) {
            return nextMovableCells;
        }
        if (current.equals(selected)) {
            return nextMovableCells;
        }
        nextMovableCells.add(current);
//        setCellInverseMove(current);
        setEntireRow(left ? currentX - 1 : currentX + 1, currentY, left,selected, nextMovableCells);
        return nextMovableCells;
    }

    //Highlights all the cells in the given X to be possible moves
    private List<Cell> setEntireColumn(int currentX, int currentY, boolean up, Cell selected, List<Cell> nextMovableCells) {
        if (currentY > 8 || currentY < 1) {
            return nextMovableCells;
        }
//        System.out.println(up ? "test" : "");
        Cell current = getColumns().get(currentX - 'a').getCells().get(currentY - 1);
//                new Cell(true,
//                Character.toString(currentX).charAt(0), currentY);
//        System.out.println(current.getColumnLetter() + " " + current.getRowNum());
//        System.out.println(Character.toString(currentX) + " " + currentY);
        if (current.isHasPiece() && current.getPiece().isPieceWhite() != selected.getPiece().isPieceWhite()) {
            nextMovableCells.add(current);
            return nextMovableCells;
        } else if (current.isHasPiece()) {
            return nextMovableCells;
        }
        if (current.equals(selected)) {
            return nextMovableCells;
        }
        nextMovableCells.add(current);
//        setCellInverseMove(current);
        setEntireColumn(currentX, up ? currentY + 1 : currentY - 1, up, selected, nextMovableCells);
        return nextMovableCells;
    }

    //Highlights all the cells in the given diagonal to be possible moves
    private List<Cell> setEntireDiagonal(int currentX, int currentY, boolean up, boolean left, Cell selected, List<Cell> nextMovableCells) {
//        System.out.println(currentX + (left ? -1 : 1) + "" + (up ? currentY + 1 : currentY - 1));
//        System.out.println("test2");
        if (currentX > 'h' || currentX < 'a' || currentY > 8 || currentY < 1) {
            return nextMovableCells;
        }
        Cell current = getColumns().get(currentX - 'a').getCells().get(currentY - 1);
//                new Cell(true,
//                Character.toString(currentX).charAt(0), currentY);
//        System.out.println(current.getColumnLetter() + "" + current.getRowNum());
        if (current.isHasPiece() && current.getPiece().isPieceWhite() != selected.getPiece().isPieceWhite()) {
            nextMovableCells.add(current);
            return nextMovableCells;
        } else if (current.isHasPiece()) {
            return nextMovableCells;
        }
        if (current.equals(selected)) {
            return nextMovableCells;
        }
//        System.out.println("test3");
//        System.out.println(this.columnLetter + "" + rowNum);
        nextMovableCells.add(current);
//        System.out.println(nextMoveableCells.size());
//        setCellInverseMove(current);
        setEntireDiagonal(currentX + (left ? -1 : 1), up ? currentY + 1 : currentY - 1, up, left, selected, nextMovableCells);
        return nextMovableCells;
    }

    public boolean checkForChecks() {
        generateAllMovableCells();
        whiteChecked = false;
        blackChecked = false;
        for (Column col : this) {
            for (Cell cel : col) {
//                System.out.print(cel);
//                System.out.print(": [");
//                for (Cell q : cel.getNextMoveableCells()) {
//                    System.out.print(q+", ");
//                }
//                System.out.println(']');
                for (Cell c : cel.getNextMoveableCells()) {
                    if (c.isHasPiece() && c.getPiece().getPieceType() == PieceType.KING &&
                            c.getPiece().isPieceWhite() != cel.getPiece().isPieceWhite()) {
//                        System.out.println(c + " " + cel);
                        if (c.getPiece().isPieceWhite()) {
//                            System.out.println("white");
                            whiteChecked = true;
                        } else {
//                            System.out.println("black");
                            blackChecked = true;
                        }
                    }
                }
            }
        }
//        System.out.println("in check " + blackChecked + " " + whiteChecked);
        assert !(blackChecked && whiteChecked) : "both in check";
        return isWhiteTurn ? blackChecked : whiteChecked;
    }

    @Override
    public Iterator<Column> iterator() {
        return columns.iterator();
    }

    //GETTERS
    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.isWhiteTurn = whiteTurn;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public boolean isWhiteChecked() {
        return whiteChecked;
    }

    public boolean isBlackChecked() {
        return blackChecked;
    }
}
