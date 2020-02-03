package javaFXtutorial;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChoiceBoxTut extends Application{
    Stage window;
    Button button;
    Scene scene;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        button = new Button("Click me");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Peaches");
        choiceBox.getItems().addAll("Bacon", "Ham", "Meat");
        choiceBox.setValue("Apples");

        //How to listening for changes
        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(newValue) );

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20,20));
        layout.getChildren().addAll(choiceBox, button);

        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }


}
