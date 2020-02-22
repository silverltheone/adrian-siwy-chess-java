package com.chess.chessgame.figures;

public class PossibleMove {
    private boolean moveOver;
    private boolean withHit;
    private boolean withoutHit;
    private int dx;
    private int dy;

    public PossibleMove(boolean moveOver, boolean withHit, boolean withoutHit, int dx, int dy) {
        this.moveOver = moveOver;
        this.withHit = withHit;
        this.withoutHit = withoutHit;
        this.dx = dx;
        this.dy = dy;
    }

    public boolean isMoveOver() {
        return moveOver;
    }

    public boolean isWithHit() {
        return withHit;
    }

    public boolean isWithoutHit() {
        return  withoutHit;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
