// package UI;

// import Model.*;

import javax.swing.*;
import java.awt.*;

/*
This class represents a JPanel that can display the Board gameBoard
 */
public class BoardPanel extends JPanel {
    // private Board gameBoard;

    public BoardPanel() {
        setPreferredSize(new Dimension(623, 635));
        // gameBoard = new Board();
    }

    @Override
    public void paint(Graphics g) {
//        System.out.println(getWidth() + " " + getHeight());
        Graphics2D g2D = (Graphics2D) g;
        g2D.setBackground(new Color(0xff4e6a6e));
        g2D.clearRect(0,0, getWidth(), getHeight());
//        System.out.println("----------------------------");
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int cornerX = x * 60 + 60;
                int cornerY = getHeight() - y * 60 - 120;
                // Cell current = gameBoard.getColumns().get(x).getCells().get(y);

//                System.out.print(((current.isCellMove() ? "1" : "0")) + "|");

//                 g2D.setColor(new Color(current.isCellHighlighted() ? 0xff5db5c2 :
//                         (current.isCellWhite() ? 0xff86dfeb : 0xff35818c)));
//                 g2D.fillRect(cornerX, cornerY, 60, 60);
//                 if (current.isHasPiece()) {
//                     g2D.drawImage(current.getPiece().getImage(), cornerX, cornerY, this);
//                 } else if (current.isCellMove()) {
//                     g2D.setColor(new Color(0xffaaaaaa));
//                     g2D.fillOval(cornerX + 20, cornerY + 20, 20, 20);
// //                    System.out.println("test");
//                 }

            }
            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Dialog", Font.PLAIN, 32));
            g2D.drawString(Integer.toString(8 - x), 30, x * 60 + 90);
//            System.out.println();
        }
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Dialog", Font.PLAIN, 32));
        g2D.drawString("a     b     c     d     e     f     g     h", 80, 580);

    }

    // public Board getGameBoard() {
    //     return gameBoard;
    // }
}
