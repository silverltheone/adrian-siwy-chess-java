package com.chess.chessgame;

import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Game {
    private WhosTurn whosTurn;
    private Board board;
    private GridPane gridPane;
    private int oldX=-1;
    private int oldY=-1;

    public Game(WhosTurn whosTurn,Board board, GridPane gridPane) {
        this.whosTurn = whosTurn;
        this.board = board;
        this.gridPane = gridPane;

    }

    public void handleClick(int x, int y) {
        if(oldX==-1){
            oldX=x;
            oldY=y;
            displayBoard(oldX, oldY);
        } else {
            if(board.move(whosTurn,oldX,oldY,x,y))
                whosTurn=turnSwitch(whosTurn);
            oldX=-1;
            oldY=-1;
            displayBoard();
        }
    }

    public void displayBoard() {
        displayBoard(-1,-1);
    }

    public void displayBoard(int hx, int hy) {
        gridPane.getChildren().clear();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                ImageView image = board.getFigure(x, y).getImage();
                if(x==hx && y==hy) {
                    gridPane.add(new Rectangle(50, 50, Color.CYAN),x,y);
                }
                if (image != null)
                    gridPane.add(image, x, y);
            }
    }

    public WhosTurn getWhosTurn() {
        return whosTurn;
    }

    public WhosTurn turnSwitch(WhosTurn whosTurn) {
        if (whosTurn == WhosTurn.WHITE_TURN) {
            return WhosTurn.BLACK_TURN;
        } else {
            return WhosTurn.WHITE_TURN;
        }
    }
}
