package Model;

public class Move {
    protected Cell moveFrom;
    protected Piece piece;
    protected String asString;

    public Move(Piece piece, Cell moveFrom) {
        this.piece = piece;
        this.moveFrom = moveFrom;
        asString = piece.getPieceType().getPieceLetter() + String.valueOf(piece.getColumnLetter()) + piece.getRowNum();
    }

    public void setChecked(boolean checked) {
        asString += checked ? "+" : "";
    }

    @Override
    public String toString() {
        return asString;
    }

}
