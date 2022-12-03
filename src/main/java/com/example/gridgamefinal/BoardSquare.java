package com.example.gridgamefinal;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class BoardSquare {
    private String name;
    private Location loc;
    private Image image;
    private int power;

    private int ownner;
    public BoardSquare(int row, int column, String n, Image img,int own,int p){
        name = n;
        loc = new Location(row,column);
        image = img;
        //0,1,2  2=unowned
        ownner = own;
        power =p;
    }

    public int getPower() {
        return power;
    }
    public void changeLocation(int r,int c){
        loc.setRow(r);
        loc.setColumn(c);
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Location getLoc() {
        return loc;
    }
    public int getRowLoc(){
        return loc.getRow();
    }
    public  int getColLoc(){
        return loc.getColumn();
    }
}
