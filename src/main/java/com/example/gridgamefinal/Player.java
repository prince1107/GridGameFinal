package com.example.gridgamefinal;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<BoardSquare> owned = new ArrayList<>();

    private ArrayList<BoardPiece> pieces = new ArrayList<>();

    public Player(String n, ArrayList<BoardPiece> p){
        name = n;
        for (BoardPiece piece: p) {
            pieces.add(piece);
        }
    }
    public void removeFromOwned(BoardSquare r){
        owned.remove(r);
    }
    public void setOwned(BoardSquare b) {
        owned.add(b);
    }

    public String getName() {
        return name;
    }

    public ArrayList<BoardPiece> getPieces() {
        return pieces;
    }

    public ArrayList<BoardSquare> getOwned() {
        return owned;
    }

    public void removePieces(BoardPiece piece) {
        pieces.remove(piece);
    }
}
