package chess.UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.DimensionUIResource;

import chess.controller.*;
import chess.controller.MovementPattern.MovementType;

public class MainPanel extends JFrame implements MouseInputListener { 
    private JScrollPane scrollPane;
    private BoardPanel boardPanel;
    private Point selectedCell = null;
    List<Point> legalMoves = new ArrayList<>();

    public MainPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setSize(new DimensionUIResource(850, 640));
        setResizable(true);
        addMouseListener(this);
        setLocationRelativeTo(null);

        //TODO: allow user to change gametype
        boardPanel = new BoardPanel("default");
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.75;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        add(boardPanel, c);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        scrollPane = new MoveScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel moveLogPanel = new JPanel();
        moveLogPanel.setLayout(new BorderLayout());
        moveLogPanel.add(scrollPane, BorderLayout.CENTER);
        moveLogPanel.setPreferredSize(new Dimension(200, 600));
        c.gridx = 1;
        c.weightx = 0.25;
        add(moveLogPanel, c);

        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point boardLoc = boardPanel.getLocationOnScreen();
        int mouseX = e.getXOnScreen() - boardLoc.x;
        int mouseY = e.getYOnScreen() - boardLoc.y;

        int cellSize = boardPanel.getCellSize();

        int boardPixels = cellSize * 8;
        if (mouseX < cellSize || mouseY < cellSize ||
            mouseX >= cellSize + boardPixels || mouseY >= cellSize + boardPixels) {
            System.out.println("OOB lmao");
            selectedCell = null;
            boardPanel.setHighlight(null);
            boardPanel.repaint();
            return;
        }

        int row = (mouseX - cellSize) / cellSize;
        int col = 7 - (mouseY - cellSize) / cellSize;

        if (selectedCell == null || !legalMoves.contains(new Point(row, col))) {
            selectedCell = new Point(row, col);
            Cell cell = boardPanel.getBoard().getBoardCells()[row][col];
            legalMoves.clear();
            // System.out.println(cell.getPosition() + " " + selectedCell);
            if (cell.getPiece() != null) {
                boardPanel.getBoard().generateValidMoves(cell.getPiece(), cell.getPosition());
            }
        } else if (legalMoves.contains(new Point(row, col))) {
            Cell source = boardPanel.getBoard().getBoardCells()[selectedCell.x][selectedCell.y];
            Cell target = boardPanel.getBoard().getBoardCells()[row][col];
            boardPanel.getBoard().performMove(source, target, MovementType.SLIDING);
        }
    }
    //TODO: allow user to use mouse to do a bunch of things

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

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
