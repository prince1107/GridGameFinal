package com.example.gridgamefinal;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<BoardSquare> owned = new ArrayList<>();

    public Player(String n){
        name = n;

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

    public  void changeLocation(int droll, String[] boardPieceNames, BoardSquare[][] board){
        //find number of board piece

//        currentPieceNumber=0;
//        for (int i = 0; i < boardPieceNames.length; i++) {
//            if (currentBoardPiece.getName().equals(boardPieceNames[i])) {
//                currentPieceNumber=i;
//            }
//        }
//        System.out.println("current piece # " + currentPieceNumber);
//        if(currentPieceNumber+droll>boardPieceNames.length-1){
//            currentPieceNumber = droll+currentPieceNumber- boardPieceNames.length;
//        }else{
//            currentPieceNumber+=droll;
//        }
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (board[i][j].getName().equals(boardPieceNames[currentPieceNumber])){
//
//                    playerLoc.setRow(board[i][j].getRowLoc());
//                    playerLoc.setColumn(board[i][j].getColLoc());
//                    currentBoardPiece = board[i][j];
//                }
//            }
//        }

    }
}
