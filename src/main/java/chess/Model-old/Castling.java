package Model;

public class Castling extends Move {
    boolean kingSide;
    public Castling(Piece piece, Cell moveFrom, boolean kingSide) {
        super(piece, moveFrom);
        this.kingSide = kingSide;
        asString = "O-O" + (kingSide ? "" : "-O");
    }

    public boolean isKingSide() {
        return kingSide;
    }
}
