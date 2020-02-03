package javaFXtutorial;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CheckBoxTut extends Application {
    Stage window;
    Scene scene;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        CheckBox box1 = new CheckBox("Tuna");
        CheckBox box2 = new CheckBox("Ham");
        box2.setSelected(true);

        button = new Button("Order now");
        button.setOnAction(e -> handleOptions(box1, box2));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(button, box1, box2);

        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    private void handleOptions(CheckBox box1, CheckBox box2) {
        String message = "Users order:\n";

        if(box1.isSelected()) {
            message += "Tuna\n";
        }
        if(box2.isSelected()) {
            message += "Ham\n";
        }
        System.out.println(message);
    }
}