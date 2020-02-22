package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Figure {
    private FigureColor color;
    private Image whiteRook = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteRook.png");
    private Image blackRook = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackRook.png");

    public Rook(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color==FigureColor.WHITE ? whiteRook : blackRook);
        return img;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        List<PossibleMove> moves = new ArrayList<>();
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
