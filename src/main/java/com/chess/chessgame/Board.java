package com.chess.chessgame;

import com.chess.chessgame.figures.*;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<BoardRow> rows = new ArrayList<>();
    private int blackKingX = 4;
    private int blackKingY = 0;
    private int whiteKingX = 4;
    private int whiteKingY = 7;

    public Board() {
        for (int n = 0; n < 8; n++)
            rows.add(new BoardRow());
    }

    public Board(Board board) {
        for (int n = 0; n < 8; n++)
            rows.add(new BoardRow());
        for(int x=0;x<8;x++) {
            for(int y=0;y<8;y++) {
                Figure figure = board.getFigure(x,y);
                Figure newFigure = new None();
                if(figure instanceof Pawn) {
                    newFigure = new Pawn(figure.getColor());
                } else if (figure instanceof Rook) {
                    newFigure = new Rook(figure.getColor());
                } else if (figure instanceof Knight) {
                    newFigure = new Knight(figure.getColor());
                } else if (figure instanceof Bishop) {
                    newFigure = new Bishop(figure.getColor());
                } else if (figure instanceof King) {
                    newFigure = new King(figure.getColor());
                } else if (figure instanceof Queen) {
                    newFigure = new Queen(figure.getColor());
                }
                setFigure(x,y,newFigure);
            }
        }
    }

    public int getBlackKingX() {
        return blackKingX;
    }

    public int getBlackKingY() {
        return blackKingY;
    }

    public int getWhiteKingX() {
        return whiteKingX;
    }

    public int getWhiteKingY() {
        return whiteKingY;
    }

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
            } else if (getFigure(x1, y1) instanceof King && getFigure(x1, y1).getColor() == FigureColor.BLACK) {
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

    public GameResult checkingMoveResult (int x, int y) {
        GameResult gameResult = GameResult.NORMAL;
        if(getFigure(x,y).getColor() == FigureColor.BLACK && isMoveForWhitePossible() == false && checkingIsWhiteKingChecked(whiteKingX, whiteKingY) == true) {
                    System.out.println("Is white move possible? " + isMoveForWhitePossible());
                    System.out.println("Check Mate - BLACK WINS");
                    gameResult = GameResult.BLACKMAT;
                } else if(getFigure(x,y).getColor() == FigureColor.WHITE && isMoveForBlackPossible() == false && checkingIsBlackKingChecked(blackKingX, blackKingY) == true) {
                    System.out.println("Is black move possible? " + isMoveForBlackPossible());
                    System.out.println("Check Mate - WHITE WINS");
                    gameResult = GameResult.WHITEMAT;
                } else if(getFigure(x,y).getColor() == FigureColor.BLACK && isMoveForWhitePossible() == false && checkingIsWhiteKingChecked(whiteKingX, whiteKingY) == false) {
                    System.out.println("Is white move possible? " + isMoveForWhitePossible());
                    System.out.println("PAT - IT'S DRAW");
                    gameResult = GameResult.PAT;
                } else if(getFigure(x,y).getColor() == FigureColor.WHITE && isMoveForBlackPossible() == false && checkingIsBlackKingChecked(blackKingX, blackKingY) == false) {
                    System.out.println("Is black move possible? " + isMoveForBlackPossible());
                    System.out.println("PAT - IT'S DRAW");
                    gameResult = GameResult.PAT;
                } else if(getFigure(x,y).getColor() == FigureColor.BLACK && isMoveForWhitePossible() == true && checkingIsWhiteKingChecked(whiteKingX, whiteKingY) == true) {
                    System.out.println("Is white move possible? " + isMoveForWhitePossible());
                    System.out.println("Check White");
                    gameResult = GameResult.BLACKCHECK;
                } else if(getFigure(x,y).getColor() == FigureColor.WHITE && isMoveForBlackPossible() == true && checkingIsBlackKingChecked(blackKingX, blackKingY) == true) {
                    System.out.println("Is black move possible? " + isMoveForBlackPossible());
                    System.out.println("Check Black");
                    gameResult = GameResult.WHITECHECK;
                }
        return gameResult;
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
        //król rusza się na pole  v
        //ruch figury nie może spowodować szacha swojego króla
        //ruch nie powoduje uniknięcia szacha v
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
        } else if (getFigure(x1, y1) instanceof Pawn && y1 - y2 > 1 && getFigure(x1, y1).getColor() == FigureColor.WHITE && y1 != 6) {
            return false;
        } else if (getFigure(x1, y1) instanceof Pawn && y2 - y1 > 1 && getFigure(x1, y1).getColor() == FigureColor.BLACK && y1 != 1) {
            return false;
        } else if (getFigure(x1, y1) instanceof King && getFigure(x1, y1).getColor() == FigureColor.WHITE && checkingIsWhiteKingChecked(x2,y2) == true) {
            return false;
        } else if (getFigure(x1, y1) instanceof King && getFigure(x1,y1).getColor() == FigureColor.BLACK && checkingIsBlackKingChecked(x2,y2) == true) {
            return false;
//        } else if (getFigure(x1,y1).getColor() == FigureColor.WHITE && checkingIsWhiteKingChecked(whiteKingX,whiteKingY) == true) {
//            return false;
//        } else if (getFigure(x1,y1).getColor() == FigureColor.BLACK && checkingIsBlackKingChecked(blackKingX, blackKingY) == true) {
//            return false;
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

    private boolean checkingIsWhiteKingChecked(int x, int y) {
        boolean isWhiteKingChecked = false;
        for (int n = 1; n < 7; n++) {
                if (x+n<8 && getFigure(x + n, y) instanceof Rook && getFigure(x + n, y).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && y+n<8 && getFigure(x - n, y) instanceof Rook && getFigure(x - n, y).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (y+n<8 && getFigure(x, y + n) instanceof Rook && getFigure(x, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (y-n>-1 && getFigure(x, y - n) instanceof Rook && getFigure(x, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x+n<8 && y-n>-1 && getFigure(x + n, y - n) instanceof Bishop && getFigure(x + n, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x+n<8 && y+n<8 && getFigure(x + n, y + n) instanceof Bishop && getFigure(x + n, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && y-n>-1 && getFigure(x - n, y - n) instanceof Bishop && getFigure(x - n, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && y+n<8 && getFigure(x - n, y + n) instanceof Bishop && getFigure(x - n, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x+n<8 && getFigure(x + n, y) instanceof Queen && getFigure(x + n, y).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && getFigure(x - n, y) instanceof Queen && getFigure(x - n, y).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (y+n<8 && getFigure(x, y + n) instanceof Queen && getFigure(x, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (y-n>-1 && getFigure(x, y - n) instanceof Queen && getFigure(x, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x+n<8 && y-n>-1 && getFigure(x + n, y - n) instanceof Queen && getFigure(x + n, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x+n<8 && y+n<8 && getFigure(x + n, y + n) instanceof Queen && getFigure(x + n, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x+k,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && y-n>-1 && getFigure(x - n, y - n) instanceof Queen && getFigure(x - n, y - n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y-k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                } else if (x-n>-1 && y+n<8 && getFigure(x - n, y + n) instanceof Queen && getFigure(x - n, y + n).getColor() == FigureColor.BLACK) {
                    isWhiteKingChecked = true;
                    if (n==1) {
                        isWhiteKingChecked = true;
                    } for (int k=1; k<n; k++) {
                        if (getFigure(x-k,y+k).getColor() != FigureColor.NONE) {
                            isWhiteKingChecked = false;
                        }
                    }
                }
            }
            if (x-1>-1 && y-1>-1 && getFigure(x - 1, y - 1) instanceof Pawn && getFigure(x - 1, y - 1).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x+1<8 && y-1>-1 && getFigure(x + 1, y - 1) instanceof Pawn && getFigure(x + 1, y - 1).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x+1<8 && y-2>-1 && getFigure(x + 1, y - 2) instanceof Knight && getFigure(x + 1, y - 2).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x+1<8 && y+2<8 && getFigure(x + 1, y + 2) instanceof Knight && getFigure(x + 1, y + 2).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x-1>-1 && y-2>-1 && getFigure(x - 1, y - 2) instanceof Knight && getFigure(x - 1, y - 2).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x-1>-1 && y+2<8 && getFigure(x - 1, y + 2) instanceof Knight && getFigure(x - 1, y + 2).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            } else if (x+2<8 && y-1>-1 && getFigure(x + 2, y - 1) instanceof Knight && getFigure(x + 2, y - 1).getColor() == FigureColor.BLACK) {
                isWhiteKingChecked = true;
            }
        return isWhiteKingChecked;
    }
    private boolean checkingIsBlackKingChecked(int x, int y) {
        boolean isBlackKingChecked = false;
        for (int n = 1; n < 7; n++) {
            if (x+n<8 && getFigure(x + n, y) instanceof Rook && getFigure(x + n, y).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && y+n<8 && getFigure(x - n, y) instanceof Rook && getFigure(x - n, y).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (y+n<8 && getFigure(x, y + n) instanceof Rook && getFigure(x, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (y-n>-1 && getFigure(x, y - n) instanceof Rook && getFigure(x, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x+n<8 && y-n>-1 && getFigure(x + n, y - n) instanceof Bishop && getFigure(x + n, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x+n<8 && y+n<8 && getFigure(x + n, y + n) instanceof Bishop && getFigure(x + n, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && y-n>-1 && getFigure(x - n, y - n) instanceof Bishop && getFigure(x - n, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && y+n<8 && getFigure(x - n, y + n) instanceof Bishop && getFigure(x - n, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x+n<8 && getFigure(x + n, y) instanceof Queen && getFigure(x + n, y).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && getFigure(x - n, y) instanceof Queen && getFigure(x - n, y).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (y+n<8 && getFigure(x, y + n) instanceof Queen && getFigure(x, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (y-n>-1 && getFigure(x, y - n) instanceof Queen && getFigure(x, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x+n<8 && y-n>-1 && getFigure(x + n, y - n) instanceof Queen && getFigure(x + n, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x+n<8 && y+n<8 && getFigure(x + n, y + n) instanceof Queen && getFigure(x + n, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x+k,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && y-n>-1 && getFigure(x - n, y - n) instanceof Queen && getFigure(x - n, y - n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y-k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            } else if (x-n>-1 && y+n<8 && getFigure(x - n, y + n) instanceof Queen && getFigure(x - n, y + n).getColor() == FigureColor.WHITE) {
                isBlackKingChecked = true;
                if (n==1) {
                    isBlackKingChecked = true;
                } for (int k=1; k<n; k++) {
                    if (getFigure(x-k,y+k).getColor() != FigureColor.NONE) {
                        isBlackKingChecked = false;
                    }
                }
            }
        }
        if (x-1>-1 && y-1>-1 && getFigure(x - 1, y + 1) instanceof Pawn && getFigure(x - 1, y + 1).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x+1<8 && y-1>-1 && getFigure(x + 1, y + 1) instanceof Pawn && getFigure(x + 1, y + 1).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x+1<8 && y-2>-1 && getFigure(x + 1, y - 2) instanceof Knight && getFigure(x + 1, y - 2).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x+1<8 && y+2<8 && getFigure(x + 1, y + 2) instanceof Knight && getFigure(x + 1, y + 2).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x-1>-1 && y-2>-1 && getFigure(x - 1, y - 2) instanceof Knight && getFigure(x - 1, y - 2).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x-1>-1 && y+2<8 && getFigure(x - 1, y + 2) instanceof Knight && getFigure(x - 1, y + 2).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        } else if (x+2<8 && y-1>-1 && getFigure(x + 2, y - 1) instanceof Knight && getFigure(x + 2, y - 1).getColor() == FigureColor.WHITE) {
            isBlackKingChecked = true;
        }
        return isBlackKingChecked;
    }

    public boolean isMoveForWhitePossible() {
        boolean isMovePossible = false;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if (getFigure(x, y).getColor() == FigureColor.WHITE) {
                    List<PossibleMove> tempMoveList = getFigure(x,y).getPossibleMoves();
                    for (PossibleMove tempMove: tempMoveList) {
                        Board simBoard = new Board(this);
                        simBoard.move(WhosTurn.WHITE_TURN,x,y,x+tempMove.getDx(),y+tempMove.getDy());
                        if (checkingIsWhiteKingChecked(simBoard.getWhiteKingX(),simBoard.getWhiteKingY()) == false) {
                            isMovePossible = true;
                        }
                    }
                }
            }
        return isMovePossible;
    }

    public boolean isMoveForBlackPossible() {
        boolean isMovePossible = false;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if (getFigure(x, y).getColor() == FigureColor.BLACK) {
                    List<PossibleMove> tempMoveList = getFigure(x,y).getPossibleMoves();
                    Board simBoard = new Board(this);
                    for (PossibleMove tempMove: tempMoveList) {
                        simBoard.move(WhosTurn.BLACK_TURN,x,y,x+tempMove.getDx(),y+tempMove.getDy());
                        if (checkingIsBlackKingChecked(simBoard.getBlackKingX(),simBoard.getBlackKingY()) == false) {
                            isMovePossible = true;
                        }
                    }
                }
            }
        return isMovePossible;
    }

    public void bestMinMaxMove() {
        int evaluation = -10001;
        PossibleMove bestMove = null;
        int bestMoveX = 0;
        int bestMoveY = 0;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if (getFigure(x, y).getColor() == FigureColor.BLACK) {
                    List<PossibleMove> tempMoveList = getFigure(x, y).getPossibleMoves();
                    for (PossibleMove tempMove : tempMoveList) {
                        Board tempBoard = new Board(this);
                        if(tempBoard.move(WhosTurn.BLACK_TURN, x, y, x + tempMove.getDx(), y + tempMove.getDy())) {
                            int score = boardEvaluation(tempBoard);
                            if (score > evaluation) {
                                evaluation = score;
                                bestMove = tempMove;
                                bestMoveX = x;
                                bestMoveY = y;
                            }
                        }
                    }
                }
            }
        move(WhosTurn.BLACK_TURN, bestMoveX,bestMoveY, bestMoveX + bestMove.getDx(), bestMoveY+bestMove.getDy());
    }

    public int boardEvaluation(Board tempBoard) {
        int evaluationScore = 0;
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++) {
                if (tempBoard.getFigure(x, y) instanceof Pawn && tempBoard.getFigure(x, y).getColor() == FigureColor.BLACK) {
                    evaluationScore = evaluationScore + 1;
                } else if (tempBoard.getFigure(x, y) instanceof Pawn && tempBoard.getFigure(x, y).getColor() == FigureColor.WHITE) {
                    evaluationScore = evaluationScore - 1;
                } else if (tempBoard.getFigure(x, y) instanceof Rook && tempBoard.getFigure(x, y).getColor() == FigureColor.BLACK) {
                    evaluationScore = evaluationScore + 2;
                } else if (tempBoard.getFigure(x, y) instanceof Rook && tempBoard.getFigure(x, y).getColor() == FigureColor.WHITE) {
                    evaluationScore = evaluationScore - 2;
                } else if (tempBoard.getFigure(x, y) instanceof Bishop && tempBoard.getFigure(x, y).getColor() == FigureColor.BLACK) {
                    evaluationScore = evaluationScore + 3;
                } else if (tempBoard.getFigure(x, y) instanceof Bishop && tempBoard.getFigure(x, y).getColor() == FigureColor.WHITE) {
                    evaluationScore = evaluationScore - 3;
                } else if (tempBoard.getFigure(x, y) instanceof Knight && tempBoard.getFigure(x, y).getColor() == FigureColor.BLACK) {
                    evaluationScore = evaluationScore + 3;
                } else if (tempBoard.getFigure(x, y) instanceof Knight && tempBoard.getFigure(x, y).getColor() == FigureColor.WHITE) {
                    evaluationScore = evaluationScore - 3;
                } else if (tempBoard.getFigure(x, y) instanceof Queen && tempBoard.getFigure(x, y).getColor() == FigureColor.BLACK) {
                    evaluationScore = evaluationScore + 6;
                } else if (tempBoard.getFigure(x, y) instanceof Queen && tempBoard.getFigure(x, y).getColor() == FigureColor.WHITE) {
                    evaluationScore = evaluationScore - 6;
                }
            }
//        for (int x = 0; x < 8; x++)
//            for (int y = 0; y < 8; y++) {
//                System.out.println(x+","+y);
//                if (tempBoard.checkingMoveResult(x, y) == GameResult.BLACKCHECK) {
//                    evaluationScore = evaluationScore + 50;
//                } else if (tempBoard.checkingMoveResult(x, y) == GameResult.WHITECHECK) {
//                    evaluationScore = evaluationScore - 50;
//                } else if (tempBoard.checkingMoveResult(x, y) == GameResult.BLACKMAT) {
//                    evaluationScore = evaluationScore + 10000;
//                } else if (tempBoard.checkingMoveResult(x, y) == GameResult.WHITEMAT) {
//                    evaluationScore = evaluationScore - 10000;
//                }
//            }
        return evaluationScore;
    }
}
