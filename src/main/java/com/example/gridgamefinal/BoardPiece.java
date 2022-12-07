package com.example.gridgamefinal;

import javafx.scene.image.Image;

public class BoardPiece {
    private String name;
    private Image image;
    private Image image2;
    private int owner;
    public BoardPiece(String n, Image img,int own){
        name = n;
        image = img;
        //0,1,2  2=unowned
        owner = own;
    }

    public BoardPiece(String n, Image img, Image img2, int own){
        name = n;
        image = img;
        image2 = img2;
        //0,1,2  2=unowned
        owner = own;
    }

    public Image getImage() {
        return image;
    }

    public Image getImage2() {
        return image2;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getOwner() {
        return owner;
    }
}
