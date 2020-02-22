package com.chess.chessgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chess extends Application {

    private Image imageback = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\background.jpg");
    private Image board = new Image("file:C:\\Users\\adeks\\Documents\\Development\\Projects\\chess-game\\src\\main\\resources\\chessboard.jpg");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BackgroundSize backgroundSize = new BackgroundSize(600, 600, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        BackgroundSize chessboardSize = new BackgroundSize(400, 400, false, false, true, false);
        BackgroundImage chessboardImg = new BackgroundImage(board, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, chessboardSize);
        Background chessboardBackgroud = new Background(chessboardImg);

        GridPane centerField = new GridPane();
        centerField.setBackground(chessboardBackgroud);
        centerField.setMinSize(400, 400);
        centerField.setMaxSize(400, 400);

        for (int i = 0; i < 8; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            centerField.getColumnConstraints().add(column);
            centerField.getRowConstraints().add(row);
        }

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
        Board board = new Board();
        board.initBoard();
        Game game = new Game(WhosTurn.WHITE_TURN, board, centerField);
        game.displayBoard();

        centerField.setOnMouseClicked(event -> {
            System.out.println(event.getX() + "," + event.getY());
            int x = (int) (event.getX() / 50);
            int y = (int) (event.getY() / 50);
            System.out.println(x + "," + y);
            game.handleClick(x, y);
        });
        primaryStage.setTitle("ChessGame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
