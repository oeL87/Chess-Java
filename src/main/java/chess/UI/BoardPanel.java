package chess.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import chess.controller.Board;
import chess.controller.Board.MoveLists;
import chess.controller.Cell;
import chess.controller.StandardBoard;
import chess.controller.errors.InvalidMoveException;
import chess.controller.errors.StillCheckedException;
import chess.controller.moves.Move;


public class BoardPanel extends JPanel {
    private final Board board;
    private Point selectedPoint;
    private List<Point> potentialMoves;
    private final static int CELL_SIZE = 60;
    private final static int BOARD_SIZE = 8;
    private final static Color WHITE = new Color(0xff86dfeb);
    private final static Color BLACK = new Color(0xff35818c);
    private final static Color SELECTED_COLOR = new Color(0xff5db5c2);

    public BoardPanel(String gametype) {
        // if (gametype == "default") { //TODO: make if statement concrete with more boardtypes
            board = new StandardBoard();
        // }
        setPreferredSize(new DimensionUIResource(600, 600));
        board.initBoard();
        selectedPoint = null;
        potentialMoves = null;
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        Color labelColor = BLACK;
        Font labelFont = new Font("Dialog", Font.BOLD, 18);

        g2D.setBackground(CardPanel.BACKGROUND);
        g2D.clearRect(0, 0, getWidth(), getHeight());

        paintBoard(g2D);

        g2D.setColor(labelColor);
        g2D.setFont(labelFont);
        for (int col = 0; col < BOARD_SIZE; col++) {
            String file = String.valueOf((char) ('a' + col));
            int x = (col + 1) * CELL_SIZE + CELL_SIZE / 2 - g2D.getFontMetrics().stringWidth(file) / 2;
            int y = (BOARD_SIZE + 1) * CELL_SIZE + labelFont.getSize();
            g2D.drawString(file, x, y);
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            String rank = String.valueOf(BOARD_SIZE - row);
            int x = CELL_SIZE / 2 - g2D.getFontMetrics().stringWidth(rank);
            int y = (row + 1) * CELL_SIZE + CELL_SIZE / 2 + labelFont.getSize() / 2;
            g2D.drawString(rank, x, y);
        }
    }

    private void paintBoard(Graphics2D g2D) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Cell cell = board.getBoardCells()[row][col];
                int x = row * CELL_SIZE + CELL_SIZE;
                int y = BOARD_SIZE * CELL_SIZE - col * CELL_SIZE;

                boolean isWHITE = (row + col) % 2 == 1;
                if (selectedPoint != null && selectedPoint.equals(new Point(row, col))) {
                    g2D.setColor(SELECTED_COLOR);
                } else {
                    g2D.setColor(isWHITE ? WHITE : BLACK);
                }
                g2D.fillRect(x, y, CELL_SIZE, CELL_SIZE);

                if (cell.getPiece() != null) {
                    g2D.drawImage(cell.getPiece().getImage(), x, y, CELL_SIZE, CELL_SIZE, this);
                }
            }
        }
        Color dots = new Color(0xffaaaaaa);
        g2D.setColor(dots);
        if (potentialMoves == null) return;
        
        for (Point point : potentialMoves) {
            int x = point.x * CELL_SIZE + CELL_SIZE + CELL_SIZE / 3;
            int y = BOARD_SIZE * CELL_SIZE - point.y * CELL_SIZE + CELL_SIZE / 3;
            g2D.fillOval(x, y, CELL_SIZE / 3, CELL_SIZE / 3); 
        }
    }

    public void setSelectedPoint(Point point) {
        this.selectedPoint = point;
        this.potentialMoves = new ArrayList<>();
        if (point == null) return;

        MoveLists lists = board.generateValidMoves(point);
        
        if (lists.hasMoves()) {
            potentialMoves.addAll(lists.getMoves());
        }

        if (lists.hasCaptures()) {
            potentialMoves.addAll(lists.getCaptures());
        }
    }

    public Board getBoard() {
        return board;
    }

    public Cell getCellAtPoint(Point point) {
        try {
            return board.getBoardCells()[point.x][point.y];
        } catch (Exception e) {
            return null;
        }
        
    }

    public Move performMove(Cell start, Cell target) throws InvalidMoveException, StillCheckedException {
        return board.performMove(start, target);
    }
} 
