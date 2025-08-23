package chess.UI;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

import chess.controller.Board;
import chess.controller.StandardBoard;
import chess.controller.Cell;

public class BoardPanel extends JPanel {
    private Board board;
    private Cell highlightedCell;
    private static int cellSize = 60;
    private static int boardsize = 8;
    private static Color white = new Color(0xff86dfeb);
    private static Color black = new Color(0xff35818c);
    private static Color selectedColor = new Color(0xff5db5c2);

    public BoardPanel(String gametype) {
        // if (gametype == "default") { //TODO: make if statement concrete with more boardtypes
            board = new StandardBoard();
        // }
        setPreferredSize(new DimensionUIResource(600, 600));
        board.initBoard();
        highlightedCell = null;
    }

    public int getCellSize() {
        return cellSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        Color labelColor = black;
        Font labelFont = new Font("Dialog", Font.BOLD, 18);

        g2D.setBackground(new Color(0xff4e6a6e));
        g2D.clearRect(0, 0, getWidth(), getHeight());

        paintBoard(g2D);

        g2D.setColor(labelColor);
        g2D.setFont(labelFont);
        for (int col = 0; col < boardsize; col++) {
            String file = String.valueOf((char) ('a' + col));
            int x = (col + 1) * cellSize + cellSize / 2 - g2D.getFontMetrics().stringWidth(file) / 2;
            int y = (boardsize + 1) * cellSize + labelFont.getSize();
            g2D.drawString(file, x, y);
        }

        for (int row = 0; row < boardsize; row++) {
            String rank = String.valueOf(boardsize - row);
            int x = cellSize / 2 - g2D.getFontMetrics().stringWidth(rank);
            int y = (row + 1) * cellSize + cellSize / 2 + labelFont.getSize() / 2;
            g2D.drawString(rank, x, y);
        }
    }

    private void paintBoard(Graphics2D g2D) {
        for (int row = 0; row < boardsize; row++) {
            for (int col = 0; col < boardsize; col++) {
                Cell cell = board.getBoardCells()[row][col];
                int x = row * cellSize + cellSize;
                int y = boardsize * cellSize - col * cellSize;

                boolean isWhite = (row + col) % 2 == 1;
                if (highlightedCell != null && highlightedCell.getRowNum() == row && (int)(highlightedCell.getColumnLetter() - 'a') == col) {
                    g2D.setColor(selectedColor);
                } else {
                    g2D.setColor(isWhite ? white : black);
                }
                g2D.fillRect(x, y, cellSize, cellSize);

                if (cell.getPiece() != null) {
                    g2D.drawImage(cell.getPiece().getImage(), x, y, cellSize, cellSize, this);
                }
            }
        }
    }

    public void setHighlight(Cell cell) {
        this.highlightedCell = cell;
    }

    public Board getBoard() {
        return board;
    }
}
