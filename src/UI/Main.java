package UI;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/*
This is the main class of the project, it handles the displaying of all the swing components and organizes
everything to allow a user to play chess, as well as listening for the user's inputs via a mouse
 */
public class Main extends JFrame implements MouseListener {
    private JPanel moveLogPane;
    private BoardPanel boardPanel;
    private JScrollPane scrollPane;
    private MovePanel currentMove;
    private Cell selected;
    private Cell moveTo;

    //Creates the JFrame and manages the JPanels subclasses within it
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setSize(831, 635);
//        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        addMouseListener(this);

        setLocationRelativeTo(null);

        JPanel moveLogPanel = new JPanel();
        moveLogPanel.setLayout(new BorderLayout());
        boardPanel = new BoardPanel();
        moveLogPane = new MoveLogPane();

        scrollPane = new JScrollPane(moveLogPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        moveLogPanel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.75;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(boardPanel, c);

        c.gridx = 1;
        c.weightx = 0.25;
        add(moveLogPanel, c);

        setVisible(true);
    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    //Adds a move to a JPanel depending on if it is black or white
    public void addMove(Cell selected, Cell moveTo, Move move) {
        System.out.println("66");
        ///TODO: if already in check, check to see if the move stops it,
        ///TODO: else, check to make sure the move doesnt put yourself in check
        // create a copy board maybe?
        System.out.println(move);
        if (boardPanel.getGameBoard().isWhiteTurn() && boardPanel.getGameBoard().isWhiteChecked()) {
            Board copy = new Board();
            System.out.println("73");
            for (int x = 0; x < 8; x++) {
                copy.getColumns().get(x).setCells(List.copyOf(boardPanel.getGameBoard().getColumns().get(x).getCells()));
                // repeat
            }
//            copy.setColumns(List.copyOf(boardPanel.getGameBoard().getColumns()));
            copy.checkForChecks();
            Cell selectedCopy = copy.getColumns().get(0).getCells().get(0), moveToCopy = copy.getColumns().get(0).getCells().get(0);
            for (Column col : copy) {
                for (Cell c : col) {
                    if (c.equals(selected)) {
                        selectedCopy = c;
                    } else if (c.equals(moveTo)) {
                        moveToCopy = c;
                    }
                }
            }
            moveToCopy.setPiece(selectedCopy.getPiece());
            selectedCopy.setPiece(null);
            copy.checkForChecks();
            if (copy.isWhiteChecked()) {
                System.out.println("still in check: white");
                selected.getNextMoveableCells().remove(moveTo);
                return;
            }
        } else if (!boardPanel.getGameBoard().isWhiteTurn() && boardPanel.getGameBoard().isBlackChecked()) {
            Board copy = new Board();
            System.out.println("78");
            for (int x = 0; x < 8; x++) {
                List<Column> columnCopy = new ArrayList<>();
            }
//            copy.setColumns(List.copyOf(boardPanel.getGameBoard().getColumns()));
            copy.checkForChecks();
            Cell selectedCopy = copy.getColumns().get(0).getCells().get(0), moveToCopy = copy.getColumns().get(0).getCells().get(0);
            for (Column col : copy) {
                for (Cell c : col) {
                    if (c.equals(selected)) {
                        selectedCopy = c;
                    } else if (c.equals(moveTo)) {
                        moveToCopy = c;
                    }
                }
            }
            moveToCopy.setPiece(selectedCopy.getPiece());
            selectedCopy.setPiece(null);
            copy.checkForChecks();
            if (copy.isBlackChecked()) {
                System.out.println("still in check: black");
                selected.getNextMoveableCells().remove(moveTo);
                return;
            }
        }
        selected.getPiece().setColumnLetter(moveTo.getColumnLetter());
        selected.getPiece().setRowNum(moveTo.getRowNum());
        moveTo.setPiece(selected.getPiece());

        selected.setPiece(null);


        if (move instanceof Castling) {
            if (((Castling) move).isKingSide()) {
                int row = selected.getRowNum() - 1;
                Cell right = boardPanel.getGameBoard().getColumns().get(7).getCells().get(row);
                while (selected.getColumnLetter() < right.getColumnLetter()) {
                    if (right.isHasPiece() && right.getPiece().canCastle()) {
                        right.getPiece().setColumnLetter('f');
                        Cell rook = boardPanel.getGameBoard().getColumns().get(5).getCells().get(row);
                        rook.setPiece(right.getPiece());
                        right.setPiece(null);
                        break;
                    }
                    right = boardPanel.getGameBoard().getColumns().get(right.getColumnLetter() - 97 - 1).getCells().get(row);
                }
            } else {
                int row = selected.getRowNum() - 1;
                Cell left = boardPanel.getGameBoard().getColumns().get(0).getCells().get(row);
                while (selected.getColumnLetter() > left.getColumnLetter()) {
                    if (left.isHasPiece() && left.getPiece().canCastle()) {
                        left.getPiece().setColumnLetter('d');
                        Cell rook = boardPanel.getGameBoard().getColumns().get(3).getCells().get(row);
                        rook.setPiece(left.getPiece());
                        left.setPiece(null);
                        break;
                    }
                    left = boardPanel.getGameBoard().getColumns().get(left.getColumnLetter() - 97 + 1).getCells().get(row);
                }
            }
        }





        move.setChecked(boardPanel.getGameBoard().checkForChecks());

        if (moveTo.getPiece().isPieceWhite()) {
            currentMove = new MovePanel(moveLogPane.getComponentCount() + 1, move);
            currentMove.setPreferredSize(new Dimension(200, 100));
            currentMove.setMaximumSize(new Dimension(200, 100));
            moveLogPane.add(currentMove);
//            System.out.println(moveLogPane.getComponentCount());
        } else {
            currentMove.addBlackMove(move);
        }
        moveTo.getPiece().setDoubleMove(false);
        moveTo.getPiece().setCanCastle(false);
        boardPanel.getGameBoard().setWhiteTurn(!boardPanel.getGameBoard().isWhiteTurn());
        this.selected = null;
        this.moveTo = null;
        scrollPane.revalidate();
        scrollPane.repaint();
        boardPanel.repaint();
    }

    public void clearMoveBoard() {
        for (Column col : boardPanel.getGameBoard()) {
            for (Cell c : col) {
                c.setCellMove(false);
            }
        }
    }

    //Breaks down what the user's input means and alters the board to show the new information
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / 60 - 1;
        int y = 8 - (e.getY() - 30) / 60;
//        System.out.println(selected.getColumnLetter() + " " + selected.getRowNum());
        clearMoveBoard();
        if (selected == null) {
            System.out.println("106");
            selected = boardPanel.getGameBoard().getColumns().get(x).getCells().get(y);
        } else {
            System.out.println("109");
            moveTo = boardPanel.getGameBoard().getColumns().get(x).getCells().get(y);
        }
        if (selected.getPiece() == null) {
//            System.out.println(selected.getColumnLetter() + " " + selected.getRowNum());
            selected = null;
            moveTo = null;
            repaint();
            return;
        }
        //Generating the Cells the given piece can move to (including ones with pieces)
        boardPanel.getGameBoard().generateMovableCells(selected);
        List<Cell> movables = new ArrayList<>();
        List<Cell> capturables = new ArrayList<>();
        for (Cell movable : selected.getNextMoveableCells()) {
//            Cell movable = boardPanel.getGameBoard().getColumns().get(c.getColumnLetter() - 97).getCells().get(c.getRowNum() - 1);
            movable.setCellMove(!movable.isCellMove());
            if (!movable.isHasPiece()) {
                movables.add(movable);
            } else if (movable.getPiece().isPieceWhite() == !selected.getPiece().isPieceWhite() &&
                selected.getPiece().getPieceType() != PieceType.PAWN) {
                capturables.add(movable);
            }
        }
        //Generating the Cells the given piece can capture (pawns being different)
        if (selected.getPiece().getPieceType() == PieceType.PAWN) {
            selected.generatePawnCaptures();
            for (Cell c : selected.getNextCapturableCells()) {
                Cell capturable = boardPanel.getGameBoard().getColumns().get(c.getColumnLetter() - 97).getCells().get(c.getRowNum() - 1);
//                capturable.setCellMove(!capturable.isCellMove());
                if (capturable.isHasPiece() && !capturables.contains(c)) {
                    capturables.add(capturable);
                }
            }
        } else {
            for (Cell c : movables) {
                if (c.isHasPiece() && !capturables.contains(c)) {
                    capturables.add(c);
                }
            }
        }
        //Castling
        if (selected.getPiece().getPieceType() == PieceType.KING) {
            if (selected.getPiece().canCastle()) {
                int row = selected.getRowNum() - 1;
                Cell left = boardPanel.getGameBoard().getColumns().get(0).getCells().get(row);
                boolean castleQueen = false;
                Cell right = boardPanel.getGameBoard().getColumns().get(7).getCells().get(row);
                boolean castleKing = false;
                while (left.getColumnLetter() < selected.getColumnLetter()) {
                    if (left.isHasPiece() && left.getPiece().canCastle()) {
                        castleQueen = true;
                    } else if (castleQueen && left.isHasPiece()) {
                        castleQueen = false;
                        break;
                    }
//                    System.out.println(left.getPiece().getPieceType());
                    left = boardPanel.getGameBoard().getColumns().get(left.getColumnLetter() - 97 + 1).getCells().get(row);
                }
                if (castleQueen) {
                    Cell castle = boardPanel.getGameBoard().getColumns().get(2).getCells().get(row);
                    castle.setCellMove(true);
                    selected.setQueenSide(castle);
                }
//                System.out.println(right);
                while (selected.getColumnLetter() < right.getColumnLetter()) {
                    if (right.isHasPiece() && right.getPiece().canCastle()) {
                        castleKing = true;
                    } else if (castleKing && right.isHasPiece()) {
                        castleKing = false;
                        break;
                    }
//                    System.out.println(right.getPiece().getPieceType());
                    right = boardPanel.getGameBoard().getColumns().get(right.getColumnLetter() - 97 - 1).getCells().get(row);
                }
                if (castleKing) {
                    Cell castle = boardPanel.getGameBoard().getColumns().get(6).getCells().get(row);
                    castle.setCellMove(true);
                    selected.setKingSide(castle);
                }
            }
        }

        selected.setNextMoveableCells(movables);
        selected.setNextCapturableCells(capturables);
//        System.out.print('[');
//        for (Cell c : capturables) {
//            System.out.print(c+", ");
//        }
//        System.out.println(']');

        if (movables.isEmpty() && capturables.isEmpty()) {
            System.out.println("227");
            selected = null;
            moveTo = null;
            return;
        }
        if (selected.getPiece().isPieceWhite() ==
                boardPanel.getGameBoard().isWhiteTurn() && moveTo != null) {

            if (selected.getNextMoveableCells().contains(moveTo) && moveTo.getPiece() == null) {
                //move piece
                System.out.println("move");
                addMove(selected, moveTo, new Move(selected.getPiece(), selected));
//                boardPanel.getGameBoard().setWhiteTurn(!boardPanel.getGameBoard().isWhiteTurn());
//                selected = null;
//                moveTo = null;
            } else if (selected.getNextCapturableCells().contains(moveTo) && moveTo.getPiece() != null) {
                //Capture piece instead
                System.out.println("capture");
//                selected.getPiece().setColumnLetter(moveTo.getColumnLetter());
//                selected.getPiece().setRowNum(moveTo.getRowNum());
                addMove(selected, moveTo, new Capture(selected.getPiece(), selected));
//                boardPanel.getGameBoard().setWhiteTurn(!boardPanel.getGameBoard().isWhiteTurn());
//                selected = null;
//                moveTo = null;
            } else if (moveTo == selected.getQueenSide()) {
//                selected.getPiece().setColumnLetter(moveTo.getColumnLetter());
//                selected.getPiece().setRowNum(moveTo.getRowNum());
                addMove(selected, moveTo, new Castling(selected.getPiece(), selected,false));
//                boardPanel.getGameBoard().setWhiteTurn(!boardPanel.getGameBoard().isWhiteTurn());
//                selected = null;
//                moveTo = null;
            } else if (moveTo == selected.getKingSide()) {
//                selected.getPiece().setColumnLetter(moveTo.getColumnLetter());
//                selected.getPiece().setRowNum(moveTo.getRowNum());
                addMove(selected, moveTo, new Castling(selected.getPiece(), selected, true));
//                boardPanel.getGameBoard().setWhiteTurn(!boardPanel.getGameBoard().isWhiteTurn());
//                selected = null;
//                moveTo = null;
            } else {
                System.out.println("223");
                selected = null;
                moveTo = null;
            }
            for (Column col : boardPanel.getGameBoard()) {
                for (Cell c : col) {
                    c.setCellMove(false);
                }
            }
        } else if (moveTo != null) {
            System.out.println("233");
            selected = null;
            moveTo = null;
        }

//        if (moveTo != null) {
//            System.out.println("154");
//            selected = null;
//            moveTo = null;
//        }

//        selected = null;
//        moveTo = null;

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}