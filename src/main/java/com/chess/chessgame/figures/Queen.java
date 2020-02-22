package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Figure {
    private FigureColor color;
    private Image whiteQueen = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteQueen.png");
    private Image blackQueen = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackQueen.png");

    public Queen(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color==FigureColor.WHITE ? whiteQueen : blackQueen);
        return img;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        List<PossibleMove> moves = new ArrayList<>();
        for(int n=0;n<7;n++) {
            moves.add(new PossibleMove(false, false,false, n+1,n+1));
        }
        for(int n=0;n<7;n++) {
            moves.add(new PossibleMove(false, false,false ,n+1,0-(n+1)));
        }
        for(int n=0;n<7;n++) {
            moves.add(new PossibleMove(false, false,false, 0-(n+1),0-(n+1)));
        }
        for(int n=0;n<7;n++) {
            moves.add(new PossibleMove(false, false,false, 0-(n+1),n+1));
        }
        for(int n=0; n<7; n++) {
            moves.add(new PossibleMove(false,false,false, n+1,0));
        }
        for(int n=0; n<7; n++) {
            moves.add(new PossibleMove(false,false,false,0-(n+1),0));
        }
        for(int n=0; n<7; n++) {
            moves.add(new PossibleMove(false,false, false,0,0-(n+1)));
        }
        for(int n=0; n<7; n++) {
            moves.add(new PossibleMove(false,false, false,0,n+1));
        }
        return moves;
    }
}
//        moves.add(new PossibleMove(false, false,false,1,1));
//        moves.add(new PossibleMove(false, true,2,2));
//        moves.add(new PossibleMove(false, true,3,3));
//        moves.add(new PossibleMove(false, true,4,4));
//        moves.add(new PossibleMove(false, true,5,5));
//        moves.add(new PossibleMove(false, true,6,6));
//        moves.add(new PossibleMove(false, true,7,7));
//        moves.add(new PossibleMove(false, true,1,-1));
//        moves.add(new PossibleMove(false, true,2,-2));
//        moves.add(new PossibleMove(false, true,3,-3));
//        moves.add(new PossibleMove(false, true,4,-4));
//        moves.add(new PossibleMove(false, true,5,-5));
//        moves.add(new PossibleMove(false, true,6,-6));
//        moves.add(new PossibleMove(false, true,7,-7));
//        moves.add(new PossibleMove(false, true,-1,-1));
//        moves.add(new PossibleMove(false, true,-2,-2));
//        moves.add(new PossibleMove(false, true,-3,-3));
//        moves.add(new PossibleMove(false, true,-4,-4));
//        moves.add(new PossibleMove(false, true,-5,-5));
//        moves.add(new PossibleMove(false, true,-6,-6));
//        moves.add(new PossibleMove(false, true,-7,-7));
//        moves.add(new PossibleMove(false, true,-1,1));
//        moves.add(new PossibleMove(false, true,-2,2));
//        moves.add(new PossibleMove(false, true,-3,3));
//        moves.add(new PossibleMove(false, true,-4,4));
//        moves.add(new PossibleMove(false, true,-5,5));
//        moves.add(new PossibleMove(false, true,-6,6));
//        moves.add(new PossibleMove(false, true,-7,7));
//        moves.add(new PossibleMove(false,true, 1,0));
//        moves.add(new PossibleMove(false,true, 2,0));
//        moves.add(new PossibleMove(false,true, 3,0));
//        moves.add(new PossibleMove(false,true, 4,0));
//        moves.add(new PossibleMove(false,true, 5,0));
//        moves.add(new PossibleMove(false,true, 6,0));
//        moves.add(new PossibleMove(false,true, 7,0));
//        moves.add(new PossibleMove(false,true, -1,0));
//        moves.add(new PossibleMove(false,true, -2,0));
//        moves.add(new PossibleMove(false,true, -3,0));
//        moves.add(new PossibleMove(false,true, -4,0));
//        moves.add(new PossibleMove(false,true, -5,0));
//        moves.add(new PossibleMove(false,true, -6,0));
//        moves.add(new PossibleMove(false,true, -7,0));
//        moves.add(new PossibleMove(false,true, 0,-1));
//        moves.add(new PossibleMove(false,true, 0,-2));
//        moves.add(new PossibleMove(false,true, 0,-3));
//        moves.add(new PossibleMove(false,true, 0,-4));
//        moves.add(new PossibleMove(false,true, 0,-5));
//        moves.add(new PossibleMove(false,true, 0,-6));
//        moves.add(new PossibleMove(false,true, 0,-7));
//        moves.add(new PossibleMove(false,true, 0,1));
//        moves.add(new PossibleMove(false,true, 0,2));
//        moves.add(new PossibleMove(false,true, 0,3));
//        moves.add(new PossibleMove(false,true, 0,4));
//        moves.add(new PossibleMove(false,true, 0,5));
//        moves.add(new PossibleMove(false,true, 0,6));
//        moves.add(new PossibleMove(false,true, 0,7));


