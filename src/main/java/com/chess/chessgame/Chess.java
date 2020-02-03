package com.chess.chessgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chess extends Application {

    private Image imageback = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\background.jpg");
    private Image board = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\chessboard.jpg");

    private Image whiteRook = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteRook.jpg");
    private Image whiteKnight = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteKnight.jpg");
    private Image whiteBishop = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteBishop.jpg");
    private Image whiteQueen = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteQueen.jpg");
    private Image whiteKing = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whiteKing.jpg");
    private Image whitePawn = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\whitePawn.jpg");

    private Image blackRook = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackRook.jpg");
    private Image blackKnight = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackKnight.jpg");
    private Image blackBishop = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackBishop.jpg");
    private Image blackQueen = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackQueen.jpg");
    private Image blackKing = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackKing.jpg");
    private Image blackPawn = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\blackPawn.jpg");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(600,600, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        BackgroundSize chessboardSize = new BackgroundSize(400, 400, false, false, true, false);
        BackgroundImage chessboardImg = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, chessboardSize);
        Background chessboardBackgroud = new Background(chessboardImg);

        GridPane centerField = new GridPane();
        centerField.setBackground(chessboardBackgroud);
        centerField.setMinSize(400,400);
        centerField.setMaxSize(400,400);

        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            centerField.getColumnConstraints().add(column);
            centerField.getRowConstraints().add(row);
        }

        ImageView imgWhiteRookL = new ImageView();
        imgWhiteRookL.setImage(whiteRook);

        ImageView imgWhiteKnightL = new ImageView();
        imgWhiteKnightL.setImage(whiteKnight);

        ImageView imgWhiteBishopL = new ImageView();
        imgWhiteBishopL.setImage(whiteBishop);

        ImageView imgWhiteKing = new ImageView();
        imgWhiteKing.setImage(whiteKing);

        ImageView imgWhiteQueen = new ImageView();
        imgWhiteQueen.setImage(whiteQueen);

        ImageView imgWhiteBishopR = new ImageView();
        imgWhiteBishopR.setImage(whiteBishop);

        ImageView imgWhiteKnightR = new ImageView();
        imgWhiteKnightR.setImage(whiteKnight);

        ImageView imgWhiteRookR = new ImageView();
        imgWhiteRookR.setImage(whiteRook);

        ImageView imgWhitePawn1 = new ImageView();
        imgWhitePawn1.setImage(whitePawn);

        ImageView imgWhitePawn2 = new ImageView();
        imgWhitePawn2.setImage(whitePawn);

        ImageView imgWhitePawn3 = new ImageView();
        imgWhitePawn3.setImage(whitePawn);

        ImageView imgWhitePawn4 = new ImageView();
        imgWhitePawn4.setImage(whitePawn);

        ImageView imgWhitePawn5 = new ImageView();
        imgWhitePawn5.setImage(whitePawn);

        ImageView imgWhitePawn6 = new ImageView();
        imgWhitePawn6.setImage(whitePawn);

        ImageView imgWhitePawn7 = new ImageView();
        imgWhitePawn7.setImage(whitePawn);

        ImageView imgWhitePawn8 = new ImageView();
        imgWhitePawn8.setImage(whitePawn);

        centerField.add(imgWhiteRookL, 0, 0);
        centerField.add(imgWhiteKnightL, 1, 0);
        centerField.add(imgWhiteBishopL, 2, 0);
        centerField.add(imgWhiteKing, 3, 0);
        centerField.add(imgWhiteQueen, 4, 0);
        centerField.add(imgWhiteBishopR, 5, 0);
        centerField.add(imgWhiteKnightR, 6, 0);
        centerField.add(imgWhiteRookR, 7, 0);
        centerField.add(imgWhitePawn1, 0, 1);
        centerField.add(imgWhitePawn2, 1, 1);
        centerField.add(imgWhitePawn3, 2, 1);
        centerField.add(imgWhitePawn4, 3, 1);
        centerField.add(imgWhitePawn5, 4, 1);
        centerField.add(imgWhitePawn6, 5, 1);
        centerField.add(imgWhitePawn7, 6, 1);
        centerField.add(imgWhitePawn8, 7, 1);

        ImageView imgBlackRookL = new ImageView();
        imgBlackRookL.setImage(blackRook);

        ImageView imgBlackKnightL = new ImageView();
        imgBlackKnightL.setImage(blackKnight);

        ImageView imgBlackBishopL = new ImageView();
        imgBlackBishopL.setImage(blackBishop);

        ImageView imgBlackKing = new ImageView();
        imgBlackKing.setImage(blackKing);

        ImageView imgBlackQueen = new ImageView();
        imgBlackQueen.setImage(blackQueen);

        ImageView imgBlackBishopR = new ImageView();
        imgBlackBishopR.setImage(blackBishop);

        ImageView imgBlackKnightR = new ImageView();
        imgBlackKnightR.setImage(blackKnight);

        ImageView imgBlackRookR = new ImageView();
        imgBlackRookR.setImage(blackRook);

        ImageView imgBlackPawn1 = new ImageView();
        imgBlackPawn1.setImage(blackPawn);

        ImageView imgBlackPawn2 = new ImageView();
        imgBlackPawn2.setImage(blackPawn);

        ImageView imgBlackPawn3 = new ImageView();
        imgBlackPawn3.setImage(blackPawn);

        ImageView imgBlackPawn4 = new ImageView();
        imgBlackPawn4.setImage(blackPawn);

        ImageView imgBlackPawn5 = new ImageView();
        imgBlackPawn5.setImage(blackPawn);

        ImageView imgBlackPawn6 = new ImageView();
        imgBlackPawn6.setImage(blackPawn);

        ImageView imgBlackPawn7 = new ImageView();
        imgBlackPawn7.setImage(blackPawn);

        ImageView imgBlackPawn8 = new ImageView();
        imgBlackPawn8.setImage(blackPawn);


        centerField.add(imgBlackRookL, 0, 7);
        centerField.add(imgBlackKnightL, 1, 7);
        centerField.add(imgBlackBishopL, 2, 7);
        centerField.add(imgBlackKing, 3, 7);
        centerField.add(imgBlackQueen, 4, 7);
        centerField.add(imgBlackBishopR, 5, 7);
        centerField.add(imgBlackKnightR, 6, 7);
        centerField.add(imgBlackRookR, 7, 7);
        centerField.add(imgBlackPawn1, 0, 6);
        centerField.add(imgBlackPawn2, 1, 6);
        centerField.add(imgBlackPawn3, 2, 6);
        centerField.add(imgBlackPawn4, 3, 6);
        centerField.add(imgBlackPawn5, 4, 6);
        centerField.add(imgBlackPawn6, 5, 6);
        centerField.add(imgBlackPawn7, 6, 6);
        centerField.add(imgBlackPawn8, 7, 6);

        GridPane leftField = new GridPane();
        leftField.setMinSize(100, 400);
        leftField.setMaxSize(100, 400);

        GridPane rightField = new GridPane();
        rightField.setMinSize(100, 400);
        rightField.setMaxSize(100, 400);

        HBox topField = new HBox();
        topField.setMinSize(600, 100);
        topField.setMaxSize(600, 100);

        HBox bottomField = new HBox();
        bottomField.setMinSize(600, 100);
        bottomField.setMaxSize(600, 100);


        BorderPane border = new BorderPane();
        border.setBackground(background);
        border.setCenter(centerField);
        border.setLeft(leftField);
        border.setRight(rightField);
        border.setTop(topField);
        border.setBottom(bottomField);

        Scene scene = new Scene(border, 600, 600, Color.WHITE);

        primaryStage.setTitle("ChessGame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
