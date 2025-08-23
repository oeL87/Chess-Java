package chess.UI;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import chess.controller.moves.Move;

public class MovePanel extends JPanel {
    private int moveNum;
    private Move whiteMove;
    private Move blackMove;

    public MovePanel(int moveNum, Move whiteMove) {
        this.moveNum = moveNum;
        this.whiteMove = whiteMove;
    }

    public void addBlackMove(Move blackMove) {
        this.blackMove = blackMove;
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
