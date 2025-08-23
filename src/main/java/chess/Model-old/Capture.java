package Model;

public class Capture extends Move {
    public Capture(Piece piece, Cell moveFrom) {
        super(piece, moveFrom);
        if (piece.getPieceType() == PieceType.PAWN) {
            asString = moveFrom.getColumnLetter() + "x" + piece.getColumnLetter() + (piece.getRowNum());
        } else {
            asString = piece.getPieceType().getPieceLetter() + "x" + piece.getColumnLetter() + (piece.getRowNum());
        }
    }
}
