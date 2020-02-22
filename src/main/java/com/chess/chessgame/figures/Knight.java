package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Figure {
    private FigureColor color;
    private Image whiteKnight = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteKnight.png");
    private Image blackKnight = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackKnight.png");

    public Knight(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color == FigureColor.WHITE ? whiteKnight : blackKnight);
        return img;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        List<PossibleMove> moves = new ArrayList<>();
        moves.add(new PossibleMove(true, false, false,2, -1));
        moves.add(new PossibleMove(true, false, false,2, 1));
        moves.add(new PossibleMove(true, false, false,-2, -1));
        moves.add(new PossibleMove(true, false, false,-2, 1));
        moves.add(new PossibleMove(true, false, false,1, -2));
        moves.add(new PossibleMove(true, false, false,-1, -2));
        moves.add(new PossibleMove(true, false, false,1, 2));
        moves.add(new PossibleMove(true, false, false,-1, 2));
        return moves;
    }


}
