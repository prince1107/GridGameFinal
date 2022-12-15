package com.example.gridgamefinal;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class BoardSquare {
    private String name;
    private Location loc;
    private Image image;

    private int health;
    private int owner;

    private int population;

    private BoardPiece piece;

    private ArrayList<BoardPiece> troops = new ArrayList<>();
    private Image image2;

    public BoardSquare(int row, int column, String n, Image img,int own,int h){
        name = n;
        loc = new Location(row,column);
        image = img;
        //0,1,2  0=unowned
        owner = own;
        health = h;
        population = 200;
    }

    public BoardSquare(int row, int column, String n, Image img,int own,BoardPiece p){
        name = n;
        loc = new Location(row,column);
        image = img;
        //0,1,2  0=unowned
        owner = own;
        piece = p;
        health = p.getHealth();
        image2 = p.getImage();
        population = 200;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public BoardPiece getPiece() {
        return piece;
    }

    public Image getImage2() {
        return image2;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void nullifyPiece() {
        this.piece = null;
        this.image2 = null;
        this.health = -1;
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

    public void setPiece(BoardPiece piece) {
        this.piece = piece;
    }

    public ArrayList<BoardPiece> getTroops() {
        return troops;
    }

    public void setTroops(BoardPiece troop) {
        this.troops.add(troop);
    }
    public void removeTroops(BoardPiece troop) {
        this.troops.remove(troop);
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
