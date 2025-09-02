package chess.UI;

import java.awt.Container;

import javax.swing.JScrollPane;

import chess.controller.moves.Move;

public class MoveScrollPane extends JScrollPane {
    private int moveCount;
    private MovePanel recentMovePanel;
    private final Container c;

    public MoveScrollPane(Container c) {
        super(c);
        moveCount = 1;
        this.c = c;
    }

    public void addMove(Move move) {
        if (recentMovePanel != null && !recentMovePanel.hasBlackMove()) {
            addBlackMove(move);
        } else {
            addWhiteMove(move);
        }
        revalidate();
        repaint();
    }

    private void addWhiteMove(Move move) {
        recentMovePanel = new MovePanel(moveCount, move, c.getSize());
        c.add(recentMovePanel);
        moveCount++;
    }

    private void addBlackMove(Move move) {
        recentMovePanel.addBlackMove(move);
    }
}
