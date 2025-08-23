package chess.UI;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import chess.controller.moves.Move;

public class MoveScrollPane extends JScrollPane {
    private int moveCount;
    private MovePanel recentMovePanel;

    public MoveScrollPane(Container c) {
        super(c);
        moveCount = 0;
    }

    public void addMove(Move move) {
        if (move.isPieceWhite()) {
            recentMovePanel = new MovePanel(moveCount, move);
            recentMovePanel.setMaximumSize(new Dimension(200, getHeight()));
            add(recentMovePanel);
            moveCount++;
        } else {
            recentMovePanel.addBlackMove(move);
        }
    }
}
