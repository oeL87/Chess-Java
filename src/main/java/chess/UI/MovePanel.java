package UI;

import Model.Move;

import javax.swing.*;
import java.awt.*;

/*
This class represents a single Panel that remembers 2 moves, one for white and one for black, and will display them.
 */
public class MovePanel extends JPanel {
    private int moveNumber;
    private Move whiteMove;
    private Move blackMove;

    public MovePanel(int moveNumber, Move whiteMove) {
        this.moveNumber = moveNumber;
        this.whiteMove = whiteMove;
//        if (capture) {
//            whiteMove = new Capture(whitePiece, moveFrom);
//        } else {
//            whiteMove = new Move(whitePiece, moveFrom);
//        }
        blackMove = null;
    }
    //Generates a random number with Math.random() and creates a colour using it
//    private static Color getRandomColor() {
//        return new Color((int) (Math.random() * 0x1000000));
//    }
    public void addBlackMove(Move blackMove) {
        this.blackMove = blackMove;
//        if (capture) {
//            blackMove = new Capture(blackPiece, moveFrom);
//        } else {
//            blackMove = new Move(blackPiece, moveFrom);
//        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setBackground(Color.white);
        g2D.clearRect(0,0, getWidth(), getHeight());
//        g2D.drawString(moveNumber + ". " + whiteMove.toString(), 20, getHeight() / 2);
        if (blackMove != null) {
            g2D.drawString(moveNumber + ". " + whiteMove.toString() + " " + blackMove.toString(), 20, getHeight() / 2);
        } else {
            g2D.drawString(moveNumber + ". " + whiteMove.toString(), 20, getHeight() / 2);
        }
    }
}
