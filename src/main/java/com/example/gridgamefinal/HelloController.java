package com.example.gridgamefinal;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    public ListView piecesView;
    public ListView squareInfoView;
    public Label resultsLabel;
    public Label timerLabel;
    public Button startButton;
    public Button clearButton;
    public Button turnButton;
    @FXML
    GridPane gpane;
    private ArrayList<Player> currentPlayers = new ArrayList<>();
    private BoardSquare[][] board = new BoardSquare[15][15];
    private ImageView[][] boardImages = new ImageView[15][15];

    private BoardPiece airbase,fighter,bomber,silo,nuke,battleship,carrier,sub;

    //startingPieces dont include nukes
    private ArrayList<BoardPiece> startingPieces = new ArrayList<BoardPiece>();

    private BoardPiece selectedPiece = null;

    private BoardPiece selectedPiece2 = null;

    @FXML
    private ImageView b0000, b0001, b0002, b0003, b0004, b0005, b0006, b0007, b0008, b0009, b0010, b0011, b0012, b0013, b0014, b0100, b0101, b0102, b0103, b0104, b0105, b0106, b0107, b0108, b0109, b0110, b0111, b0112, b0113, b0114, b0200, b0201, b0202, b0203, b0204, b0205, b0206, b0207, b0208, b0209, b0210, b0211, b0212, b0213, b0214, b0300, b0301, b0302, b0303, b0304, b0305, b0306, b0307, b0308, b0309, b0310, b0311, b0312, b0313, b0314, b0400, b0401, b0402, b0403, b0404, b0405, b0406, b0407, b0408, b0409, b0410, b0411, b0412, b0413, b0414, b0500, b0501, b0502, b0503, b0504, b0505, b0506, b0507, b0508, b0509, b0510, b0511, b0512, b0513, b0514, b0600, b0601, b0602, b0603, b0604, b0605, b0606, b0607, b0608, b0609, b0610, b0611, b0612, b0613, b0614, b0700, b0701, b0702, b0703, b0704, b0705, b0706, b0707, b0708, b0709, b0710, b0711, b0712, b0713, b0714, b0800, b0801, b0802, b0803, b0804, b0805, b0806, b0807, b0808, b0809, b0810, b0811, b0812, b0813, b0814, b0900, b0901, b0902, b0903, b0904, b0905, b0906, b0907, b0908, b0909, b0910, b0911, b0912, b0913, b0914, b1000, b1001, b1002, b1003, b1004, b1005, b1006, b1007, b1008, b1009, b1010, b1011, b1012, b1013, b1014, b1100, b1101, b1102, b1103, b1104, b1105, b1106, b1107, b1108, b1109, b1110, b1111, b1112, b1113, b1114, b1200, b1201, b1202, b1203, b1204, b1205, b1206, b1207, b1208, b1209, b1210, b1211, b1212, b1213, b1214, b1300, b1301, b1302, b1303, b1304, b1305, b1306, b1307, b1308, b1309, b1310, b1311, b1312, b1313, b1314, b1400, b1401, b1402, b1403, b1404, b1405, b1406, b1407, b1408, b1409, b1410, b1411, b1412, b1413, b1414;
    private String[] boardSquareNames = {"player 1 land", "player 2 land", "player 3 land", "player 4 land", "water"};
    private int playerTurn=0;

    private int defconLevel=5;

    Image red,orange,purple,dblue,lblue,airbasePic,fighterPic,bomberPic,siloPic,nukePic,battleshipPic,carrierPic,subPic;

    boolean finished = false;
    Timer myTimer = new Timer();

    private int time = 45;
    TimerTask myTimerTask = new TimerTask(){
        @Override
        public void run() {
            if (!finished) {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        timerLabel.setText("DEFCON " + defconLevel + ": " + time/60 + " min, " + time%60 + " sec left");
                        time--;
                        if (time == 0){
                            if (defconLevel == 1){
                                defconLevel--;
                                endGame();
                            }
                            if (defconLevel == 2){
                                defconLevel--;
                                time = 45;
                            }
                            if (defconLevel == 3){
                                defconLevel--;
                                time = 27;
                            }
                            if (defconLevel == 4){
                                defconLevel--;
                                time = 27;
                            }
                            if (defconLevel == 5){
                                defconLevel--;
                                time = 36;
                            }
                        }
                    }
                });
            }
        }
    };


    public HelloController(){

        FileInputStream redd,orangee,purplee,dbluee,lbluee,airbasee,fighterr,bomberr,siloo,nukee,battleshipp,carrierr,subb;
        try {
            redd = new FileInputStream("src/main/Pictures/red.png");
            red = new Image(redd);
            orangee= new FileInputStream("src/main/Pictures/orange.png");
            orange = new Image(orangee);
            purplee = new FileInputStream("src/main/Pictures/purple.jpeg");
            purple = new Image(purplee);
            dbluee = new FileInputStream("src/main/Pictures/dark-blue.jpeg");
            dblue = new Image(dbluee);
            lbluee = new FileInputStream("src/main/Pictures/ocean-blue.jpeg");
            lblue = new Image(lbluee);


            airbasee = new FileInputStream("src/main/Pictures/Airbase.png");
            airbasePic = new Image(airbasee);
            fighterr = new FileInputStream("src/main/Pictures/Fighter.png");
            fighterPic = new Image(fighterr);
            bomberr = new FileInputStream("src/main/Pictures/Bomber.png");
            bomberPic = new Image(bomberr);

            siloo = new FileInputStream("src/main/Pictures/Sam.png");
            siloPic = new Image(siloo);
            nukee = new FileInputStream("src/main/Pictures/Nuke.png");
            nukePic = new Image(nukee);

            battleshipp = new FileInputStream("src/main/Pictures/Battleship.png");
            battleshipPic = new Image(battleshipp);
            carrierr = new FileInputStream("src/main/Pictures/Carrier.png");
            carrierPic = new Image(carrierr);
            subb = new FileInputStream("src/main/Pictures/Sub.png");
            subPic = new Image(subb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        airbase = new BoardPiece("airbase", airbasePic, 100, 0);
        fighter = new BoardPiece("fighter", fighterPic, 20, 3);
        bomber = new BoardPiece("bomber", bomberPic, 30, 5);
        silo = new BoardPiece("silo", siloPic, 150, 0);
        nuke = new BoardPiece("nuke", nukePic, 0, 20);
        battleship = new BoardPiece("battleship", battleshipPic, 50, 4);
        carrier = new BoardPiece("carrier", carrierPic, 30, 2);
        sub = new BoardPiece("sub", subPic, 20, 5);

        startingPieces.add(airbase);
        startingPieces.add(airbase);
        startingPieces.add(airbase);
        startingPieces.add(fighter);
        startingPieces.add(fighter);
        startingPieces.add(fighter);
        startingPieces.add(fighter);
        startingPieces.add(fighter);
        startingPieces.add(fighter);
        startingPieces.add(bomber);
        startingPieces.add(bomber);
        startingPieces.add(bomber);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(silo);
        startingPieces.add(battleship);
        startingPieces.add(battleship);
        startingPieces.add(battleship);
        startingPieces.add(carrier);
        startingPieces.add(carrier);
        startingPieces.add(carrier);
        startingPieces.add(sub);
        startingPieces.add(sub);
        startingPieces.add(sub);
        for (int i = 0; i < 20; i++) {
            startingPieces.add(nuke);
        }




        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new BoardSquare(i,j,boardSquareNames[0],red,1,-1);
            }
        }
        board[0][5] = new BoardSquare(0,5,boardSquareNames[0],red,1,-1);
        board[0][6] = new BoardSquare(0,6,boardSquareNames[0],red,1,-1);
        board[1][5] = new BoardSquare(1,5,boardSquareNames[0],red,1,-1);
        board[1][6] = new BoardSquare(1,6,boardSquareNames[0],red,1,-1);
        board[2][5] = new BoardSquare(2,5,boardSquareNames[0],red,1,-1);

        board[5][0] = new BoardSquare(5,0,boardSquareNames[0],red,1,-1);
        board[5][1] = new BoardSquare(5,1,boardSquareNames[0],red,1,-1);
        board[5][2] = new BoardSquare(5,2,boardSquareNames[0],red,1,-1);
        board[6][0] = new BoardSquare(6,0,boardSquareNames[0],red,1,-1);
        board[6][1] = new BoardSquare(6,1,boardSquareNames[0],red,1,-1);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][14-j] = new BoardSquare(i,14-j,boardSquareNames[3],orange,4,-1);
            }
        }
        board[2][9] = new BoardSquare(2,9,boardSquareNames[3],orange,4,-1);
        board[3][8] = new BoardSquare(3,8,boardSquareNames[3],orange,4,-1);
        board[3][9] = new BoardSquare(3,9,boardSquareNames[3],orange,4,-1);
        board[4][8] = new BoardSquare(4,8,boardSquareNames[3],orange,4,-1);
        board[4][9] = new BoardSquare(4,9,boardSquareNames[3],orange,4,-1);

        board[5][11] = new BoardSquare(5,11,boardSquareNames[3],orange,4,-1);
        board[5][12] = new BoardSquare(5,12,boardSquareNames[3],orange,4,-1);
        board[5][13] = new BoardSquare(5,13,boardSquareNames[3],orange,4,-1);
        board[5][14] = new BoardSquare(5,14,boardSquareNames[3],orange,4,-1);
        board[6][14] = new BoardSquare(6,14,boardSquareNames[3],orange,4,-1);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[14-i][j] = new BoardSquare(14-i,j,boardSquareNames[1],dblue,2,-1);
            }
        }
        board[8][4] = new BoardSquare(8,4,boardSquareNames[1],dblue,2,-1);
        board[9][3] = new BoardSquare(9,3,boardSquareNames[1],dblue,2,-1);
        board[9][4] = new BoardSquare(9,4,boardSquareNames[1],dblue,2,-1);
        board[9][5] = new BoardSquare(9,5,boardSquareNames[1],dblue,2,-1);
        board[10][5] = new BoardSquare(10,5,boardSquareNames[1],dblue,2,-1);

        board[12][6] = new BoardSquare(12,6,boardSquareNames[1],dblue,2,-1);
        board[13][5] = new BoardSquare(13,5,boardSquareNames[1],dblue,2,-1);
        board[13][6] = new BoardSquare(13,6,boardSquareNames[1],dblue,2,-1);
        board[14][5] = new BoardSquare(14,5,boardSquareNames[1],dblue,2,-1);
        board[14][6] = new BoardSquare(14,6,boardSquareNames[1],dblue,2,-1);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[14-i][14-j] = new BoardSquare(14-i,14-j,boardSquareNames[2],purple,3,-1);
            }
        }
        board[8][12] = new BoardSquare(8,12,boardSquareNames[2],purple,3,-1);
        board[9][9] = new BoardSquare(9,9,boardSquareNames[2],purple,3,-1);
        board[9][12] = new BoardSquare(9,12,boardSquareNames[2],purple,3,-1);
        board[9][13] = new BoardSquare(9,13,boardSquareNames[2],purple,3,-1);
        board[9][14] = new BoardSquare(9,14,boardSquareNames[2],purple,3,-1);

        board[10][8] = new BoardSquare(10,8,boardSquareNames[2],purple,3,-1);
        board[10][9] = new BoardSquare(10,9,boardSquareNames[2],purple,3,-1);
        board[11][8] = new BoardSquare(11,8,boardSquareNames[2],purple,3,-1);
        board[11][9] = new BoardSquare(11,9,boardSquareNames[2],purple,3,-1);
        board[12][9] = new BoardSquare(12,9,boardSquareNames[2],purple,3,-1);
        //board pieces

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new BoardSquare(i,j,boardSquareNames[4],lblue,0,-1);
                }

            }
        }
        currentPlayers.add(new Player("Player 1 (red)", startingPieces));
        currentPlayers.add(new Player("Player 2 (darkblue)", startingPieces));
        currentPlayers.add(new Player("Player 3 (purple)", startingPieces));
        currentPlayers.add(new Player("Player 4 (orange)", startingPieces));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].getImage() == red)  {
                    currentPlayers.get(0).setOwned(board[i][j]);
                }
                if (board[i][j].getImage() == dblue)  {
                    currentPlayers.get(1).setOwned(board[i][j]);
                }
                if (board[i][j].getImage() == purple)  {
                    currentPlayers.get(2).setOwned(board[i][j]);
                }
                if (board[i][j].getImage() == orange)  {
                    currentPlayers.get(3).setOwned(board[i][j]);
                }
            }
        }
    }


    private void startTimer() {
        myTimer.scheduleAtFixedRate(myTimerTask, 0, 1000);
    }


    @FXML
    protected void handleStart(ActionEvent event){

        if (time > 0){
            startTimer();
        } else{
            time = 300;
        }

        finished = false;

        boolean idk = true;

        if (idk){
            boardImages[0][0]=b0000;
            boardImages[0][1]=b0001;
            boardImages[0][2]=b0002;
            boardImages[0][3]=b0003;
            boardImages[0][4]=b0004;
            boardImages[0][5]=b0005;
            boardImages[0][6]=b0006;
            boardImages[0][7]=b0007;
            boardImages[0][8]=b0008;
            boardImages[0][9]=b0009;
            boardImages[0][10]=b0010;
            boardImages[0][11]=b0011;
            boardImages[0][12]=b0012;
            boardImages[0][13]=b0013;
            boardImages[0][14]=b0014;

            boardImages[1][0]=b0100;
            boardImages[1][1]=b0101;
            boardImages[1][2]=b0102;
            boardImages[1][3]=b0103;
            boardImages[1][4]=b0104;
            boardImages[1][5]=b0105;
            boardImages[1][6]=b0106;
            boardImages[1][7]=b0107;
            boardImages[1][8]=b0108;
            boardImages[1][9]=b0109;
            boardImages[1][10]=b0110;
            boardImages[1][11]=b0111;
            boardImages[1][12]=b0112;
            boardImages[1][13]=b0113;
            boardImages[1][14]=b0114;

            boardImages[2][0]=b0200;
            boardImages[2][1]=b0201;
            boardImages[2][2]=b0202;
            boardImages[2][3]=b0203;
            boardImages[2][4]=b0204;
            boardImages[2][5]=b0205;
            boardImages[2][6]=b0206;
            boardImages[2][7]=b0207;
            boardImages[2][8]=b0208;
            boardImages[2][9]=b0209;
            boardImages[2][10]=b0210;
            boardImages[2][11]=b0211;
            boardImages[2][12]=b0212;
            boardImages[2][13]=b0213;
            boardImages[2][14]=b0214;

            boardImages[3][0]=b0300;
            boardImages[3][1]=b0301;
            boardImages[3][2]=b0302;
            boardImages[3][3]=b0303;
            boardImages[3][4]=b0304;
            boardImages[3][5]=b0305;
            boardImages[3][6]=b0306;
            boardImages[3][7]=b0307;
            boardImages[3][8]=b0308;
            boardImages[3][9]=b0309;
            boardImages[3][10]=b0310;
            boardImages[3][11]=b0311;
            boardImages[3][12]=b0312;
            boardImages[3][13]=b0313;
            boardImages[3][14]=b0314;

            boardImages[4][0]=b0400;
            boardImages[4][1]=b0401;
            boardImages[4][2]=b0402;
            boardImages[4][3]=b0403;
            boardImages[4][4]=b0404;
            boardImages[4][5]=b0405;
            boardImages[4][6]=b0406;
            boardImages[4][7]=b0407;
            boardImages[4][8]=b0408;
            boardImages[4][9]=b0409;
            boardImages[4][10]=b0410;
            boardImages[4][11]=b0411;
            boardImages[4][12]=b0412;
            boardImages[4][13]=b0413;
            boardImages[4][14]=b0414;

            boardImages[5][0]=b0500;
            boardImages[5][1]=b0501;
            boardImages[5][2]=b0502;
            boardImages[5][3]=b0503;
            boardImages[5][4]=b0504;
            boardImages[5][5]=b0505;
            boardImages[5][6]=b0506;
            boardImages[5][7]=b0507;
            boardImages[5][8]=b0508;
            boardImages[5][9]=b0509;
            boardImages[5][10]=b0510;
            boardImages[5][11]=b0511;
            boardImages[5][12]=b0512;
            boardImages[5][13]=b0513;
            boardImages[5][14]=b0514;

            boardImages[6][0]=b0600;
            boardImages[6][1]=b0601;
            boardImages[6][2]=b0602;
            boardImages[6][3]=b0603;
            boardImages[6][4]=b0604;
            boardImages[6][5]=b0605;
            boardImages[6][6]=b0606;
            boardImages[6][7]=b0607;
            boardImages[6][8]=b0608;
            boardImages[6][9]=b0609;
            boardImages[6][10]=b0610;
            boardImages[6][11]=b0611;
            boardImages[6][12]=b0612;
            boardImages[6][13]=b0613;
            boardImages[6][14]=b0614;

            boardImages[7][0]=b0700;
            boardImages[7][1]=b0701;
            boardImages[7][2]=b0702;
            boardImages[7][3]=b0703;
            boardImages[7][4]=b0704;
            boardImages[7][5]=b0705;
            boardImages[7][6]=b0706;
            boardImages[7][7]=b0707;
            boardImages[7][8]=b0708;
            boardImages[7][9]=b0709;
            boardImages[7][10]=b0710;
            boardImages[7][11]=b0711;
            boardImages[7][12]=b0712;
            boardImages[7][13]=b0713;
            boardImages[7][14]=b0714;

            boardImages[8][0]=b0800;
            boardImages[8][1]=b0801;
            boardImages[8][2]=b0802;
            boardImages[8][3]=b0803;
            boardImages[8][4]=b0804;
            boardImages[8][5]=b0805;
            boardImages[8][6]=b0806;
            boardImages[8][7]=b0807;
            boardImages[8][8]=b0808;
            boardImages[8][9]=b0809;
            boardImages[8][10]=b0810;
            boardImages[8][11]=b0811;
            boardImages[8][12]=b0812;
            boardImages[8][13]=b0813;
            boardImages[8][14]=b0814;

            boardImages[9][0]=b0900;
            boardImages[9][1]=b0901;
            boardImages[9][2]=b0902;
            boardImages[9][3]=b0903;
            boardImages[9][4]=b0904;
            boardImages[9][5]=b0905;
            boardImages[9][6]=b0906;
            boardImages[9][7]=b0907;
            boardImages[9][8]=b0908;
            boardImages[9][9]=b0909;
            boardImages[9][10]=b0910;
            boardImages[9][11]=b0911;
            boardImages[9][12]=b0912;
            boardImages[9][13]=b0913;
            boardImages[9][14]=b0914;

            boardImages[10][0]=b1000;
            boardImages[10][1]=b1001;
            boardImages[10][2]=b1002;
            boardImages[10][3]=b1003;
            boardImages[10][4]=b1004;
            boardImages[10][5]=b1005;
            boardImages[10][6]=b1006;
            boardImages[10][7]=b1007;
            boardImages[10][8]=b1008;
            boardImages[10][9]=b1009;
            boardImages[10][10]=b1010;
            boardImages[10][11]=b1011;
            boardImages[10][12]=b1012;
            boardImages[10][13]=b1013;
            boardImages[10][14]=b1014;

            boardImages[11][0]=b1100;
            boardImages[11][1]=b1101;
            boardImages[11][2]=b1102;
            boardImages[11][3]=b1103;
            boardImages[11][4]=b1104;
            boardImages[11][5]=b1105;
            boardImages[11][6]=b1106;
            boardImages[11][7]=b1107;
            boardImages[11][8]=b1108;
            boardImages[11][9]=b1109;
            boardImages[11][10]=b1110;
            boardImages[11][11]=b1111;
            boardImages[11][12]=b1112;
            boardImages[11][13]=b1113;
            boardImages[11][14]=b1114;

            boardImages[12][0]=b1200;
            boardImages[12][1]=b1201;
            boardImages[12][2]=b1202;
            boardImages[12][3]=b1203;
            boardImages[12][4]=b1204;
            boardImages[12][5]=b1205;
            boardImages[12][6]=b1206;
            boardImages[12][7]=b1207;
            boardImages[12][8]=b1208;
            boardImages[12][9]=b1209;
            boardImages[12][10]=b1210;
            boardImages[12][11]=b1211;
            boardImages[12][12]=b1212;
            boardImages[12][13]=b1213;
            boardImages[12][14]=b1214;

            boardImages[13][0]=b1300;
            boardImages[13][1]=b1301;
            boardImages[13][2]=b1302;
            boardImages[13][3]=b1303;
            boardImages[13][4]=b1304;
            boardImages[13][5]=b1305;
            boardImages[13][6]=b1306;
            boardImages[13][7]=b1307;
            boardImages[13][8]=b1308;
            boardImages[13][9]=b1309;
            boardImages[13][10]=b1310;
            boardImages[13][11]=b1311;
            boardImages[13][12]=b1312;
            boardImages[13][13]=b1313;
            boardImages[13][14]=b1314;

            boardImages[14][0]=b1400;
            boardImages[14][1]=b1401;
            boardImages[14][2]=b1402;
            boardImages[14][3]=b1403;
            boardImages[14][4]=b1404;
            boardImages[14][5]=b1405;
            boardImages[14][6]=b1406;
            boardImages[14][7]=b1407;
            boardImages[14][8]=b1408;
            boardImages[14][9]=b1409;
            boardImages[14][10]=b1410;
            boardImages[14][11]=b1411;
            boardImages[14][12]=b1412;
            boardImages[14][13]=b1413;
            boardImages[14][14]=b1414;
        }

        refreshGridPane();
    }
    ImageView firstClick;
    ImageView secondClick;
    int click1X,click2X,click1Y,click2Y;
    @FXML
    private void handleClickImage(MouseEvent event) {
        if (selectedPiece2 != null) {
            secondClick = (ImageView) (event.getSource());
            click2Y = GridPane.getRowIndex(secondClick);
            click2X = GridPane.getColumnIndex(secondClick);
            if (board[click2X][click2Y].getOwner() != 0 && (playerTurn%4 + 1) == board[click2X][click2Y].getOwner() && board[click2X][click2Y].getHealth() != -1){
                resultsLabel.setText("You can't fight yourself!");
            } else {
                checkResult();
            }
            firstClick = null;
            selectedPiece2 = null;
            secondClick = null;
        } else if (selectedPiece != null){
            firstClick = (ImageView) (event.getSource());
            click1Y = GridPane.getRowIndex(firstClick);
            click1X = GridPane.getColumnIndex(firstClick);
            checkResult();
            selectedPiece = null;
            firstClick = null;
        } else {
            if (firstClick == null) {
                firstClick = (ImageView) (event.getSource());
                click1Y = GridPane.getRowIndex(firstClick);
                click1X = GridPane.getColumnIndex(firstClick);
                squareInfoView.getItems().clear();
                squareInfoView.getItems().add("Name: " + board[click1X][click1Y].getName());
                squareInfoView.getItems().add("Health: " + board[click1X][click1Y].getHealth());
                squareInfoView.getItems().add("Owner: " + board[click1X][click1Y].getOwner());
                squareInfoView.getItems().add("Population: " + board[click1X][click1Y].getPopulation());
                if (board[click1X][click1Y].getPiece().getName().equals("silo") || board[click1X][click1Y].getPiece().getName().equals("airbase")) {
                    squareInfoView.getItems().add("Troops:");
                    for (BoardPiece piece : board[click1X][click1Y].getTroops()) {
                        squareInfoView.getItems().add(piece.getName());
                    }
                }
            } else {
                secondClick = (ImageView) (event.getSource());
                click2Y = GridPane.getRowIndex(secondClick);
                click2X = GridPane.getColumnIndex(secondClick);
                if (click1X == click2X && click1Y == click2Y){
                    resultsLabel.setText("You can't fight yourself!");
                } else if (board[click1X][click1Y].getOwner() != 0 && board[click2X][click2Y].getOwner() != 0 &&board[click1X][click1Y].getOwner() == board[click2X][click2Y].getOwner() && board[click1X][click1Y].getHealth() != -1 && board[click2X][click2Y].getHealth() != -1){
                    resultsLabel.setText("You can't fight yourself!");
                } else {
                    checkResult();
                }
                firstClick = null;
                secondClick = null;
            }
        }
    }
    public void checkResult() {
        if (selectedPiece2 != null) {
            BoardSquare first = board[click1X][click1Y];
            BoardSquare second = board[click2X][click2Y];
            if (second.getHealth() == -1) {
                if (selectedPiece2.getName().equals("fighter") || selectedPiece2.getName().equals("bomber")) {
                    if (board[click2X][click2Y].getImage() == lblue) {
                        board[click2X][click2Y] = new BoardSquare(click2X, click2Y, board[click2X][click2Y].getName(), board[click2X][click2Y].getImage(), playerTurn % 4 + 1, selectedPiece2);
                        first.removeTroops(selectedPiece2);
                    } else {
                        board[click2X][click2Y] = new BoardSquare(click2X, click2Y, board[click2X][click2Y].getName(), board[click2X][click2Y].getImage(), playerTurn % 4 + 1, selectedPiece2);
                        first.removeTroops(selectedPiece2);
                    }
                }
                if (selectedPiece2.getName().equals("nuke")) {
                    if (board[click2X][click2Y].getImage() == lblue) {
                        resultsLabel.setText("You can't nuke from water");
                    } else{
                        if (defconLevel <= 2) {
                            board[click2X][click2Y] = new BoardSquare(click2X, click2Y, board[click2X][click2Y].getName(), board[click2X][click2Y].getImage(), playerTurn % 4 + 1, selectedPiece2);
                            first.removeTroops(selectedPiece2);
                        } else {
                            resultsLabel.setText("You can't nuke before DEFCON 2");
                        }
                    }
                }
            }
        } else if (selectedPiece != null){
            if (selectedPiece.getName().equals("fighter") || selectedPiece.getName().equals("bomber")){
                if (board[click1X][click1Y].getPiece().getName().equals("airbase")){
                    board[click1X][click1Y].setTroops(selectedPiece);
                    currentPlayers.get(playerTurn % 4).removePieces(selectedPiece);
                } else {
                    resultsLabel.setText("Invalid placement");
                }
            } else if (selectedPiece.getName().equals("nuke")){
                if (board[click1X][click1Y].getPiece().getName().equals("silo")){
                    board[click1X][click1Y].setTroops(selectedPiece);
                    currentPlayers.get(playerTurn % 4).removePieces(selectedPiece);
                } else {
                    resultsLabel.setText("Invalid placement");
                }
            } else if (selectedPiece.getName().equals("battleship") || selectedPiece.getName().equals("carrier") || selectedPiece.getName().equals("sub")){
                if (board[click1X][click1Y].getImage() == lblue){
                    board[click1X][click1Y] = new BoardSquare(click1X, click1Y, boardSquareNames[0], board[click1X][click1Y].getImage(), playerTurn% 4 + 1, selectedPiece);
                    currentPlayers.get(playerTurn % 4).removePieces(selectedPiece);
                } else {
                    resultsLabel.setText("Invalid placement");
                }
            } else if (board[click1X][click1Y].getImage() != lblue) {
                    board[click1X][click1Y] = new BoardSquare(click1X, click1Y, boardSquareNames[0], board[click1X][click1Y].getImage(), playerTurn% 4 + 1, selectedPiece);
                    currentPlayers.get(playerTurn % 4).removePieces(selectedPiece);
            } else {
                resultsLabel.setText("Invalid placement");
            }
        } else {
            BoardSquare first = board[click1X][click1Y];
            BoardSquare second = board[click2X][click2Y];
            if (second.getHealth() == -1) {
                if (first.getHealth() != -1){
                    if (first.getPiece().getName().equals("fighter") || first.getPiece().getName().equals("bomber") || first.getPiece().getName().equals("battleship") || first.getPiece().getName().equals("carrier") || first.getPiece().getName().equals("sub")) {
                        if (first.getPiece().getName().equals("battleship") || first.getPiece().getName().equals("carrier") || first.getPiece().getName().equals("sub")) {
                            if (board[click2X][click2Y].getImage() == lblue) {
                                board[click2X][click2Y] = new BoardSquare(click2X, click2Y, board[click2X][click2Y].getName(), board[click2X][click2Y].getImage(), board[click1X][click1Y].getOwner(), board[click1X][click1Y].getPiece());
                                board[click1X][click1Y] = new BoardSquare(click1X, click1Y, board[click1X][click1Y].getName(), board[click1X][click1Y].getImage(), board[click1X][click1Y].getOwner(), -1);
                            } else {
                                resultsLabel.setText("Invalid placement");
                            }
                        } else {
                            board[click2X][click2Y] = new BoardSquare(click2X, click2Y, board[click2X][click2Y].getName(), board[click2X][click2Y].getImage(), board[click1X][click1Y].getOwner(), board[click1X][click1Y].getPiece());
                            board[click1X][click1Y] = new BoardSquare(click1X, click1Y, board[click1X][click1Y].getName(), board[click1X][click1Y].getImage(), board[click1X][click1Y].getOwner(), -1);
                        }

                    } else {
                        resultsLabel.setText("You can only move planes or ships and shoot missiles from silos");
                    }
                } else {
                    resultsLabel.setText("You can only move planes or ships and shoot missiles from silos");
                }
            } else {
                if (defconLevel <= 2) {
                    if (first.getPiece().getName().equals("nuke")){
                        attack(first, second);
                        board[click1X][click1Y] = new BoardSquare(click1X, click1Y, board[click1X][click1Y].getName(), board[click1X][click1Y].getImage(), board[click1X][click1Y].getOwner(), -1);
                    }
                }
                if (defconLevel <= 3) {
                    if (Math.abs(click1X - click2X) == 1 || Math.abs(click1Y - click2Y) == 1) {
                        attack(first, second);
                    }
                } else {
                    resultsLabel.setText("You cannot fight before DEFCON 3");
                }
            }
        }
        refreshGridPane();
    }

    private void attack(BoardSquare first, BoardSquare second) {
        second.changeHealth(-1 * (int) (Math.random() * 20 + 1) * first.getPiece().getPower());
        second.setPopulation(second.getPopulation() - (int) (Math.random() * 20) * first.getPiece().getPower());
        if (second.getHealth() <= 0) {
            second.nullifyPiece();
        }
    }

    public void handlePlaceImage() {
        int index = piecesView.getSelectionModel().getSelectedIndex();

        selectedPiece = currentPlayers.get(playerTurn % 4).getPieces().get(index);
        System.out.println(selectedPiece.getName());
    }

    public void refreshGridPane() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].getPiece() != null){
                    boardImages[i][j].setImage(board[i][j].getImage2());
                } else {
                    boardImages[i][j].setImage(board[i][j].getImage());
                }
            }
        }

        piecesView.getItems().clear();
        for (BoardPiece piece: currentPlayers.get(playerTurn % 4).getPieces()) {
            piecesView.getItems().add(piece.getName());
        }
    }

    public void handleClearSelection() {
        selectedPiece = null;
        selectedPiece2 = null;
        firstClick = null;
        secondClick = null;
    }

    public void handleGetInfo() {
        int index = squareInfoView.getSelectionModel().getSelectedIndex();

        selectedPiece2 = board[click1X][click1Y].getTroops().get(index - 5);
        System.out.println(selectedPiece2.getName());
    }

    private void endGame() {
        int p1pop = 0;
        int p2pop = 0;
        int p3pop = 0;
        int p4pop = 0;

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].getImage() == red)  {
                    p1pop += board[i][j].getPopulation();
                }
                if (board[i][j].getImage() == dblue)  {
                    p2pop += board[i][j].getPopulation();
                }
                if (board[i][j].getImage() == purple)  {
                    p3pop += board[i][j].getPopulation();
                }
                if (board[i][j].getImage() == orange)  {
                    p4pop += board[i][j].getPopulation();
                }
            }
        }

        gpane.setVisible(false);
        piecesView.setVisible(false);
        squareInfoView.setVisible(false);
        startButton.setVisible(false);
        clearButton.setVisible(false);
        turnButton.setVisible(false);
        timerLabel.setVisible(false);

        if (p1pop > p2pop && p1pop > p3pop && p1pop > p4pop) {
            resultsLabel.setText(currentPlayers.get(0).getName() + " is the winner");
            System.out.println(currentPlayers.get(0).getName());
        } else if (p2pop > p1pop && p2pop > p3pop && p2pop > p4pop) {
            resultsLabel.setText(currentPlayers.get(1).getName() + " is the winner");
        } else if (p1pop > p2pop && p1pop > p3pop && p1pop > p4pop) {
            resultsLabel.setText(currentPlayers.get(2).getName() + " is the winner");
        } else if (p1pop > p2pop && p1pop > p3pop && p1pop > p4pop) {
            resultsLabel.setText(currentPlayers.get(3).getName() + " is the winner");
        } else {
            resultsLabel.setText("There was a tie, so Nobody is the winner");
        }

    }

    public void turnChange() {
        playerTurn++;
        refreshGridPane();
        resultsLabel.setText("It is " + currentPlayers.get(playerTurn%4).getName() + "'s turn");
    }
}
