package com.chess.chessgame.figures;

import java.util.ArrayList;
import java.util.List;

public class None implements Figure {
    @Override
    public FigureColor getColor() {
        return FigureColor.NONE;
    }

    @Override
    public List<PossibleMove> getPossibleMoves() {
        return new ArrayList<>();
    }
}
