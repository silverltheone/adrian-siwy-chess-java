package com.chess.chessgame.figures;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Figure {
    private Image blackPawn = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackPawn.png");
    private Image whitePawn = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whitePawn.png");
    private FigureColor color;

    public Pawn(FigureColor color) {
        this.color = color;
    }

    @Override
    public ImageView getImage() {
        ImageView img = new ImageView();
        img.setImage(color==FigureColor.WHITE ? whitePawn : blackPawn);
        return img;
    }

    @Override
    public FigureColor getColor() {
        return color;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        List<PossibleMove> moves = new ArrayList<>();
        getStandardPawnMoves(moves, color==FigureColor.WHITE ? -1 : 1);
        return moves;
    }

    private void getStandardPawnMoves(List<PossibleMove> moves, int direction) {
        for(int n=1; n<3; n++) {
            moves.add(new PossibleMove(false, false, true,0, direction * n));
        }
        moves.add(new PossibleMove(false, true, false,-1, direction));
        moves.add(new PossibleMove(false, true, false,1, direction));
    }
}
