package com.chess.chessgame.figures;

import javafx.scene.image.ImageView;

import java.util.List;

public interface Figure {
    default ImageView getImage(){
        return null;
    }
    FigureColor getColor();
    List<PossibleMove> getPossibleMoves();
}
