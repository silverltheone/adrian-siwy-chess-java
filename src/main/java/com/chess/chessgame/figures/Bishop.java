package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Figure {
    private Image whiteBishop = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteBishop.png");
    private Image blackBishop = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackBishop.png");
    private FigureColor color;

    public Bishop(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color==FigureColor.WHITE ? whiteBishop : blackBishop);
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
        return moves;
    }
}
