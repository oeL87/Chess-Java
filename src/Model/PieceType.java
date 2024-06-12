package Model;

/*
This enum represents the 7 different types of chess pieces that can exist in the game
 */
public enum PieceType {
    PAWN (' ', "Pawn"),
    KNIGHT ('N', "Knight"),
    BISHOP ('B', "Bishop"),
    ROOK ('R', "Rook"),
    QUEEN ('Q', "Queen"),
    KING ('K', "King");

    private char pieceLetter;
    private String imageName;

    PieceType(char pieceLetter, String imageName) {
        this.pieceLetter = pieceLetter;
        this.imageName = imageName;
    }

    public char getPieceLetter() {
        return pieceLetter;
    }

    public String getImageName() {
        return imageName;
    }
}
