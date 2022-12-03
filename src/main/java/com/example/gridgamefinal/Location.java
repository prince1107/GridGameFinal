package com.example.gridgamefinal;

public class Location {
    private int row;
    private int column;
    public Location(int r, int c){
        row = r;
        column = c;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
