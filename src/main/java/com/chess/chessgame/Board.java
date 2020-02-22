package com.chess.chessgame;

import com.chess.chessgame.figures.*;
import javafx.scene.effect.Lighting;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardRow> rows = new ArrayList<>();
    private int blackKingX = 4;
    private int blackKingY = 0;
    private int whiteKingX = 4;
    private int whiteKingY = 7;


    public Figure getFigure(int x, int y) {
        return rows.get(y).getCols().get(x);
    }

    public void setFigure(int x, int y, Figure figure) {
        rows.get(y).getCols().add(x, figure);
        rows.get(y).getCols().remove(x + 1);
    }

    public boolean move(WhosTurn whosTurn, int x1, int y1, int x2, int y2) {
        boolean result = false;
        if (moveIsValid(whosTurn, x1, y1, x2, y2)) {
            if (getFigure(x1, y1) instanceof King && getFigure(x1, y1).getColor() == FigureColor.WHITE) {
                Figure hitted = getFigure(x2, y2);
                if (!(hitted instanceof None)) {
                    //dodać figurę hitted do listy zbitych
                }
                Figure figure = getFigure(x1, y1);
                setFigure(x2, y2, figure);
                setFigure(x1, y1, new None());
                result = true;
                whiteKingX = x2;
                whiteKingY = y2;
                System.out.println("White King is: " + "x= " + whiteKingX + " " +  "y= " + whiteKingY);
            } else if (getFigure(x1,y1) instanceof King && getFigure(x1,y1).getColor() == FigureColor.BLACK) {
                Figure hitted = getFigure(x2, y2);
                if (!(hitted instanceof None)) {
                    //dodać figurę hitted do listy zbitych
                }
                Figure figure = getFigure(x1, y1);
                setFigure(x2, y2, figure);
                setFigure(x1, y1, new None());
                result = true;
                blackKingX = x2;
                blackKingY = y2;
                System.out.println("Black King is: " + "x= " + blackKingX + " " +  "y= " + blackKingY);
            } else {
                Figure hitted = getFigure(x2, y2);
                if (!(hitted instanceof None)) {
                    //dodać figurę hitted do listy zbitych
                }
                Figure figure = getFigure(x1, y1);
                setFigure(x2, y2, figure);
                setFigure(x1, y1, new None());
                result = true;
            }
        }
        return result;
    }


    private boolean moveIsValid(WhosTurn whosTurn, int x1, int y1, int x2, int y2) {
        //FALSE
        //tura jest biała a figura czarna v
        //na x2,y2 jest figura tego samego koloru v
        //jeśli x2 lub y2 wystaje poza plansze v
        //ruchu nie ma na liście możliwych ruchów v
        //ruch ma bicie na false a na x2,y2 jest figura przeciwna,v
        //ruch ma over na false a pomiędzy x1,y1 oraz x2,y2 znajduje się jakaolwiek figura v
        //figura to pion a po skosie nie ma przeciwnika v
        //pion rusza się o dwa pola tylko z Pola startowego v
        //król rusza się na pole szachowane
        //król nie może stać obok króla


        if (whosTurn == WhosTurn.WHITE_TURN && getFigure(x1, y1).getColor() == FigureColor.BLACK) {
            return false;
        } else if ((whosTurn == WhosTurn.BLACK_TURN && getFigure(x1, y1).getColor() == FigureColor.WHITE)) {
            return false;
        } else if (x2 > 7 || y2 > 7) {
            return false;
        } else if (x2 < 0 || y2 < 0) {
            return false;
        } else if (getFigure(x1, y1).getColor() == getFigure(x2, y2).getColor()) {
            return false;
        } else if (!isMoveIn(getFigure(x1, y1).getPossibleMoves(), x1, y1, x2, y2)) {
            return false;
        } else if (checkIsMoveWitouthHit(getFigure(x1, y1).getPossibleMoves(), x1, y1, x2, y2) && getFigure(x1, y1).getColor() != getFigure(x2, y2).getColor() && getFigure(x2, y2).getColor() != FigureColor.NONE) {
            return false;
        } else if (checkIsMoveWithHit(getFigure(x1, y1).getPossibleMoves(), x1, y1, x2, y2) && getFigure(x2, y2).getColor() == FigureColor.NONE) {
            return false;
        } else if (!checkIsMoveOver(getFigure(x1, y1).getPossibleMoves(), x1, y1, x2, y2) && !checkColision(x1, y1, x2, y2)) {
            return false;
        } else if (getFigure(x1, y1) instanceof Pawn && y1-y2>1 && getFigure(x1,y1).getColor()==FigureColor.WHITE && y1!=6) {
            return false;
        }else if (getFigure(x1, y1) instanceof Pawn && y2-y1>1 && getFigure(x1,y1).getColor()==FigureColor.BLACK && y1!=1) {
            return false;
        } else if (checkingIsWhiteKingChecked() == true && getFigure(x1,y1) instanceof King && getFigure(x1,y1).getColor() == FigureColor.WHITE) {
            return false;
        }
        return true;
    }



    private boolean checkIsMoveOver(List<PossibleMove> possibleMoves, int x1, int y1, int x2, int y2) {
        boolean checkIsMoveOver = false;
        for (PossibleMove move : possibleMoves) {
            if (x1 + move.getDx() == x2 && y1 + move.getDy() == y2)
                checkIsMoveOver = move.isMoveOver();
        }
        return checkIsMoveOver;
    }


    private boolean checkIsMoveWithHit(List<PossibleMove> possibleMoves, int x1, int y1, int x2, int y2) {
        boolean checkIsMoveWithHit = false;
        for (PossibleMove move : possibleMoves) {
            if (x1 + move.getDx() == x2 && y1 + move.getDy() == y2)
                checkIsMoveWithHit = move.isWithHit();
        }
        return checkIsMoveWithHit;
    }

    private boolean checkIsMoveWitouthHit(List<PossibleMove> possibleMoves, int x1, int y1, int x2, int y2) {
        boolean checkIsMoveWithoutHit = false;
        for (PossibleMove move : possibleMoves) {
            if (x1 + move.getDx() == x2 && y1 + move.getDy() == y2)
                checkIsMoveWithoutHit = move.isWithoutHit();
        }
        return checkIsMoveWithoutHit;
    }

    private boolean isMoveIn(List<PossibleMove> possibleMoves, int x1, int y1, int x2, int y2) {
        boolean isMoveIn = false;
        for (PossibleMove move : possibleMoves) {
            if (x1 + move.getDx() == x2 && y1 + move.getDy() == y2)
                isMoveIn = true;
        }
        return isMoveIn;
    }

    private int checkDirection(int x1, int y1, int x2, int y2) {
        // 1 - right
        // 2 - left
        // 3 - up
        // 4 - down
        // 5 - up/right
        // 6 - up/left
        // 7 - down/left
        // 8 - down/rught
        if (x1 - x2 < 0 && y1 - y2 == 0) {
            return 1;
        } else if (x1 - x2 > 0 && y1 - y2 == 0) {
            return 2;
        } else if (x1 - x2 == 0 && y1 - y2 > 0) {
            return 3;
        } else if (x1 - x2 == 0 && y1 - y2 < 0) {
            return 4;
        } else if (x1 - x2 < 0 && y1 - y2 > 0) {
            return 5;
        } else if (x1 - x2 > 0 && y1 - y2 > 0) {
            return 6;
        } else if (x1 - x2 > 0 && y1 - y2 < 0) {
            return 7;
        } else {
            return 8;
        }
    }

    public boolean checkColision(int x1, int y1, int x2, int y2) {
        boolean pathClear = true;

        if (x1 - x2 == 1) {
            pathClear = true;
        }
        if (x1 - x2 == -1) {
            pathClear = true;
        }
        if (y1 - y2 == 1) {
            pathClear = true;
        }
        if (y1 - y2 == -1) {
            pathClear = true;
        }
        if (checkDirection(x1, y1, x2, y2) == 1) {
            for (int n = 0; n + x1 + 1 < x2; n++) {
                if (getFigure(x1 + n + 1, y1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 2) {
            for (int n = 0; x1 - n - 1 > x2; n++) {
                if (getFigure(x1 - n - 1, y1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 3) {
            for (int n = 0; y1 - n - 1 > y2; n++) {
                if (getFigure(x1, y1 - n - 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 4) {
            for (int n = 0; y1 + n + 1 < y2; n++) {
                if (getFigure(x1, y1 + n + 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 5) {
            for (int n = 0; x1 + n + 1 < x2; n++) {
                if (getFigure(x1 + n + 1, y1 - n - 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 6) {
            for (int n = 0; x1 - n - 1 > x2; n++) {
                if (getFigure(x1 - n - 1, y1 - n - 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 7) {
            for (int n = 0; x1 - n - 1 > x2; n++) {
                if (getFigure(x1 - n - 1, y1 + n + 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        if (checkDirection(x1, y1, x2, y2) == 8) {
            for (int n = 0; x1 + n + 1 < x2; n++) {
                if (getFigure(x1 + n + 1, y1 + n + 1).getColor() != FigureColor.NONE) {
                    pathClear = false;
                }
            }
        }
        return pathClear;
    }


    public Board() {
        for (int n = 0; n < 8; n++)
            rows.add(new BoardRow());
    }

    public void initBoard() {
        for (int n = 0; n < 8; n++) {
            setFigure(n, 1, new Pawn(FigureColor.BLACK));
            setFigure(n, 6, new Pawn(FigureColor.WHITE));
        }
        setFigure(0, 0, new Rook(FigureColor.BLACK));
        setFigure(7, 0, new Rook(FigureColor.BLACK));
        setFigure(0, 7, new Rook(FigureColor.WHITE));
        setFigure(7, 7, new Rook(FigureColor.WHITE));
        setFigure(1, 0, new Knight(FigureColor.BLACK));
        setFigure(6, 0, new Knight(FigureColor.BLACK));
        setFigure(1, 7, new Knight(FigureColor.WHITE));
        setFigure(6, 7, new Knight(FigureColor.WHITE));
        setFigure(2, 0, new Bishop(FigureColor.BLACK));
        setFigure(5, 0, new Bishop(FigureColor.BLACK));
        setFigure(2, 7, new Bishop(FigureColor.WHITE));
        setFigure(5, 7, new Bishop(FigureColor.WHITE));
        setFigure(3, 0, new Queen(FigureColor.BLACK));
        setFigure(3, 7, new Queen(FigureColor.WHITE));
        setFigure(4, 0, new King(FigureColor.BLACK));
        setFigure(4, 7, new King(FigureColor.WHITE));
    }

    private boolean checkingIsWhiteKingChecked () {
        boolean isWhiteKingChecked = false;
        for (int n=1; n<8; n++) {
            if (getFigure(whiteKingX + n, whiteKingY) instanceof Rook && getFigure(whiteKingX + n, whiteKingY).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
            isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY) instanceof Rook && getFigure(whiteKingX - n, whiteKingY).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX, whiteKingY + n) instanceof Rook && getFigure(whiteKingX, whiteKingY + n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX, whiteKingY - n) instanceof Rook && getFigure(whiteKingX, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX + n, whiteKingY - n) instanceof Bishop && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX + n, whiteKingY + n) instanceof Bishop && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY - n) instanceof Bishop && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY + n) instanceof Bishop && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX + n, whiteKingY) instanceof Queen && getFigure(whiteKingX + n, whiteKingY).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY) instanceof Queen && getFigure(whiteKingX - n, whiteKingY).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX, whiteKingY + n) instanceof Queen && getFigure(whiteKingX, whiteKingY + n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX, whiteKingY - n) instanceof Queen && getFigure(whiteKingX, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX + n, whiteKingY - n) instanceof Queen && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX + n, whiteKingY + n) instanceof Queen && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY - n) instanceof Queen && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            } else if (getFigure(whiteKingX - n, whiteKingY + n) instanceof Queen && getFigure(whiteKingX + n, whiteKingY - n).getColor() != getFigure(whiteKingX, whiteKingY).getColor()) {
                isWhiteKingChecked = true;
            }
        }
        if (getFigure(whiteKingX - 1, whiteKingY -1) instanceof Pawn && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX +1, whiteKingY -1) instanceof Pawn && getFigure(whiteKingX+1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX +1, whiteKingY -2) instanceof Knight && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX +1, whiteKingY +2) instanceof Knight && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX -1, whiteKingY -2) instanceof Knight && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX -1, whiteKingY +2) instanceof Knight && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        } else if (getFigure(whiteKingX +2, whiteKingY -1) instanceof Knight && getFigure(whiteKingX-1, whiteKingY-1).getColor() != FigureColor.WHITE) {
            isWhiteKingChecked = true;
        }
        System.out.println(isWhiteKingChecked);
        return isWhiteKingChecked;
    }
}
