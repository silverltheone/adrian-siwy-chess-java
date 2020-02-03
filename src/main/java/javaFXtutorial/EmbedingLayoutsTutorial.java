package javaFXtutorial;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EmbedingLayoutsTutorial extends Application {
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("New window");

        HBox topMenu = new HBox();
        Button buttonA = new Button("File");
        Button buttonB = new Button("Edit");
        Button buttonC = new Button("View");
        topMenu.getChildren().addAll(buttonA, buttonB, buttonC);

        HBox botMenu = new HBox();
        Button buttonAa = new Button("Filea");
        Button buttonBb = new Button("Editb");
        Button buttonCc = new Button("Viewc");
        botMenu.getChildren().addAll(buttonAa, buttonBb, buttonCc);

        VBox leftMenu = new VBox();
        Button buttonD = new Button("D");
        Button buttonE = new Button("E");
        Button buttonF = new Button("F");
        leftMenu.getChildren().addAll(buttonD, buttonE, buttonF);

        VBox rightMenu = new VBox();
        Button buttonDd = new Button("Dd");
        Button buttonEe = new Button("Ee");
        Button buttonFf = new Button("Ff");
        rightMenu.getChildren().addAll(buttonDd, buttonEe, buttonFf);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);
        borderPane.setBottom(botMenu);


        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();
    }
}
