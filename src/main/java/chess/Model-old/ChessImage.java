package Model;

import java.awt.*;
/*
    Images of chess pieces taken from https://commons.wikimedia.org/wiki/Category:SVG_chess_pieces
    Represents the images that will show up on the board
*/
public class ChessImage {
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Image image;

    public ChessImage(String imagePath) {
        image = toolkit.getImage(imagePath).getScaledInstance(60, 60, Image.SCALE_DEFAULT);
    }

    public Image getImage() {
        return image;
    }
}
