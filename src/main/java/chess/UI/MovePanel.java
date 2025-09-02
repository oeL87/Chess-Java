package chess.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import chess.controller.moves.Move;

public class MovePanel extends JPanel {
    private final int moveNum;
    private final Move whiteMove;
    private Move blackMove;

    public MovePanel(int moveNum, Move whiteMove, Dimension parentDimension) {
        this.moveNum = moveNum;
        this.whiteMove = whiteMove;
        setPreferredSize(new Dimension(parentDimension.width, parentDimension.height / 5));
        setMaximumSize(new Dimension(parentDimension.width, parentDimension.height / 5));
    }

    public void addBlackMove(Move blackMove) {
        this.blackMove = blackMove;
    }

    public boolean hasBlackMove() {
        return blackMove != null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setBackground(Color.WHITE);
        g2D.clearRect(0, 0, getWidth(), getHeight());

        String display = String.valueOf(moveNum) + ". " + whiteMove.toString() + " " + (blackMove == null ? "" : blackMove.toString());
        g2D.drawString(display, getWidth() / 10, getHeight() / 2);
    }
}
