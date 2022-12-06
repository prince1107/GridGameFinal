package com.example.gridgamefinal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class HelloController {

    @FXML
    GridPane gpane;
    private ArrayList<Player> currentPlayers = new ArrayList<>();
    private BoardSquare[][] board = new BoardSquare[15][15];
    private ImageView[][] boardImages = new ImageView[15][15];

    @FXML
    private ImageView b0000, b0001, b0002, b0003, b0004, b0005, b0006, b0007, b0008, b0009, b0010, b0011, b0012, b0013, b0014, b0100, b0101, b0102, b0103, b0104, b0105, b0106, b0107, b0108, b0109, b0110, b0111, b0112, b0113, b0114, b0200, b0201, b0202, b0203, b0204, b0205, b0206, b0207, b0208, b0209, b0210, b0211, b0212, b0213, b0214, b0300, b0301, b0302, b0303, b0304, b0305, b0306, b0307, b0308, b0309, b0310, b0311, b0312, b0313, b0314, b0400, b0401, b0402, b0403, b0404, b0405, b0406, b0407, b0408, b0409, b0410, b0411, b0412, b0413, b0414, b0500, b0501, b0502, b0503, b0504, b0505, b0506, b0507, b0508, b0509, b0510, b0511, b0512, b0513, b0514, b0600, b0601, b0602, b0603, b0604, b0605, b0606, b0607, b0608, b0609, b0610, b0611, b0612, b0613, b0614, b0700, b0701, b0702, b0703, b0704, b0705, b0706, b0707, b0708, b0709, b0710, b0711, b0712, b0713, b0714, b0800, b0801, b0802, b0803, b0804, b0805, b0806, b0807, b0808, b0809, b0810, b0811, b0812, b0813, b0814, b0900, b0901, b0902, b0903, b0904, b0905, b0906, b0907, b0908, b0909, b0910, b0911, b0912, b0913, b0914, b1000, b1001, b1002, b1003, b1004, b1005, b1006, b1007, b1008, b1009, b1010, b1011, b1012, b1013, b1014, b1100, b1101, b1102, b1103, b1104, b1105, b1106, b1107, b1108, b1109, b1110, b1111, b1112, b1113, b1114, b1200, b1201, b1202, b1203, b1204, b1205, b1206, b1207, b1208, b1209, b1210, b1211, b1212, b1213, b1214, b1300, b1301, b1302, b1303, b1304, b1305, b1306, b1307, b1308, b1309, b1310, b1311, b1312, b1313, b1314, b1400, b1401, b1402, b1403, b1404, b1405, b1406, b1407, b1408, b1409, b1410, b1411, b1412, b1413, b1414;
    private String[] boardSquareNames = {"b1", "b2", "b3", "b4", "r1", "r2", "r3", "r4","unowned"};
    private  int playerTurn=0;
    Image red,orange,purple,dblue,lblue,airbase,fighter,bomber,sam,silo,nuke,radar,fleet,battleship,carrier,sub;
    public HelloController(){
        FileInputStream redd,orangee,purplee,dbluee,lbluee,airbasee,fighterr,bomberr,samm,siloo,nukee,radarr,fleett,battleshipp,carrierr,subb;
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
            airbase = new Image(airbasee);
            fighterr = new FileInputStream("src/main/Pictures/Fighter.png");
            fighter = new Image(fighterr);
            bomberr = new FileInputStream("src/main/Pictures/Bomber.png");
            bomber = new Image(bomberr);

            samm = new FileInputStream("src/main/Pictures/Sam.png");
            sam = new Image(samm);
            siloo = new FileInputStream("src/main/Pictures/Silo.png");
            silo = new Image(siloo);
            nukee = new FileInputStream("src/main/Pictures/Nuke.png");
            nuke = new Image(nukee);
            radarr = new FileInputStream("src/main/Pictures/Radar.png");
            radar = new Image(radarr);

            fleett = new FileInputStream("src/main/Pictures/Fleet.png");
            fleet = new Image(fleett);
            battleshipp = new FileInputStream("src/main/Pictures/Battleship.png");
            battleship = new Image(battleshipp);
            carrierr = new FileInputStream("src/main/Pictures/Carrier.png");
            carrier = new Image(carrierr);
            subb = new FileInputStream("src/main/Pictures/Sub.png");
            sub = new Image(subb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new BoardSquare(i,j,boardSquareNames[0],red,0,2);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new BoardSquare(i,14-j,boardSquareNames[1],orange,0,2);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new BoardSquare(14-i,j,boardSquareNames[2],dblue,0,2);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new BoardSquare(14-i,14-j,boardSquareNames[3],purple,0,2);
            }
        }
        //board pieces

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new BoardSquare(i,j,boardSquareNames[8],lblue,2,0);
                }

            }
        }
        currentPlayers.add(new Player("player 1"));
        currentPlayers.add(new Player("Player 2"));
        for (int i = 0; i < 4; i++) {
            currentPlayers.get(0).setOwned(board[0][i]);
        }
        for (int i = 0; i < 4; i++) {
            currentPlayers.get(1).setOwned(board[3][i]);
        }



    }
    @FXML
    protected void handleStart(ActionEvent event){
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
        boardImages[12][11]=b1411;
        boardImages[14][12]=b1412;
        boardImages[14][13]=b1413;
        boardImages[14][14]=b1414;

        for (int i = 0; i <boardImages.length; i++) {
            for (int j = 0; j < boardImages.length; j++) {
                boardImages[i][j].setImage(board[i][j].getImage());
            }
        }

    }
    ImageView firstClick;
    ImageView secondClick;
    int click1X,click2X,click1Y,click2Y;
    @FXML
    private void handleClickImage(MouseEvent event) {
        System.out.println(event);
        if(firstClick == null){
////            click1X = GridPane.getRowIndex(((ImageView) event.getSource()));
            firstClick = (ImageView) (event.getSource());
//            System.out.println(firstClick);
            click1X = GridPane.getRowIndex(firstClick);
            click1Y = GridPane.getColumnIndex(firstClick);
//            int imgClicked = GridPane.getColumnIndex((ImageView) event.getSource());
//            System.out.println(imgClicked);

        }else{
            secondClick = (ImageView) (event.getSource());
            click2X = GridPane.getRowIndex(secondClick);
            click2Y = GridPane.getColumnIndex(secondClick);
            checkResult();
            firstClick =null;
            secondClick = null;
        }

    }
    public void checkResult(){
        BoardSquare first = board[click1X][click1Y];
        BoardSquare second =board[click2X][click2Y];
        if (first.getPower()>second.getPower()){
            first.changeLocation(click2X,click2Y);
            board[click1X][click1Y]=new BoardSquare(click1X,click1Y,boardSquareNames[8],red,2,0);
            board[click2X][click2Y] = first;
            boardImages[click1X][click1Y].setImage(red);
            boardImages[click2X][click2Y].setImage(first.getImage());
        }else{
            currentPlayers.get(0).removeFromOwned(first);
            boardImages[click1X][click1Y].setImage(red);
            board[click1X][click1Y]=new BoardSquare(click1X,click1Y,boardSquareNames[8],red,2,0);
        }
    }

}
