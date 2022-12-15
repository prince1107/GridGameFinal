package com.example.gridgamefinal;

import javafx.scene.image.Image;

public class BoardPiece {
    private String name;
    private Image image;

    private int health;

    private int power;
    public BoardPiece(String n, Image img, int h, int p){
        name = n;
        image = img;
        health = h;
        power = p;
    }
//

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getPower() {
        return power;
    }
}
