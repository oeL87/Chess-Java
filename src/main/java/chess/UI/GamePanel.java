package chess.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.DimensionUIResource;

import chess.controller.Board.MoveLists;
import chess.controller.Cell;
import chess.controller.errors.InvalidMoveException;
import chess.controller.errors.StillCheckedException;
import chess.controller.moves.Move;

public class GamePanel extends JFrame implements MouseInputListener { 
    private final MoveScrollPane scrollPane;
    private final BoardPanel boardPanel;
    private MoveLists legalMoves = null;
    private Cell source = null;
    private Cell target = null;

    public GamePanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Chess");
        setSize(new DimensionUIResource(850, 640));
        setResizable(true);
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
        boardPanel.repaint();
        scrollPane.repaint();

        Point boardLoc = boardPanel.getLocationOnScreen();
        int mouseX = e.getXOnScreen() - boardLoc.x;
        int mouseY = e.getYOnScreen() - boardLoc.y;

        int cellSize = boardPanel.getCellSize();

        int boardPixels = cellSize * 8;
        if (mouseX < cellSize || mouseY < cellSize ||
            mouseX >= cellSize + boardPixels || mouseY >= cellSize + boardPixels) {
            System.out.println("OOB lmao");
            boardPanel.setSelectedPoint(null);
            boardPanel.repaint();
            return;
        }

        int row = (mouseX - cellSize) / cellSize;
        int col = 7 - (mouseY - cellSize) / cellSize;
        Cell selected = boardPanel.getCellAtPoint(new Point(row, col));

        if (selected == null) return;
        boardPanel.setSelectedPoint(new Point(row, col));

        if (source == null) {
            source = selected;
            legalMoves = boardPanel.getBoard().generateValidMoves(source.getPosition());
        } else if (source.equals(selected)) {
            source = null;
            target = null;
            boardPanel.setSelectedPoint(null);
            boardPanel.repaint();
            return;
        } else {
            target = selected;
        }
        if (target == null) return;

        if (source.getPiece() == null) {
            source = null;
            target = null;
            return;
        }
        
        if (legalMoves != null && legalMoves.contains(target.getPosition())) {
            try {
                Move move = boardPanel.performMove(source, target);
                scrollPane.addMove(move);
            } catch (InvalidMoveException | StillCheckedException m) {
                System.out.println(m.getMessage());
            }
            
            
        }

        source = null;
        target = null;
        boardPanel.setSelectedPoint(null);
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
