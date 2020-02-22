package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class King implements  Figure {
    private FigureColor color;
    private Image whiteKing = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteKing.png");
    private Image blackKing = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackKing.png");

    public King(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color == FigureColor.WHITE ? whiteKing : blackKing);
        return img;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        List<PossibleMove> moves = new ArrayList<>();
        moves.add(new PossibleMove(false, false, false, 1, 0));
        moves.add(new PossibleMove(false, false, false, -1, 0));
        moves.add(new PossibleMove(false, false, false,0, 1));
        moves.add(new PossibleMove(false, false, false,0, -1));
        moves.add(new PossibleMove(false, false, false,1, -1));
        moves.add(new PossibleMove(false, false, false,-1, -1));
        moves.add(new PossibleMove(false, false, false, 1, 1));
        moves.add(new PossibleMove(false, false, false,-1, 1));
        return moves;
    }
}
