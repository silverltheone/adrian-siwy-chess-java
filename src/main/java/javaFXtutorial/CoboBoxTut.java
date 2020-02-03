package javaFXtutorial;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CoboBoxTut extends Application{
    Stage window;
    Scene scene;
    Button button;
    ComboBox<String> comboBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        button = new Button("click me");

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Mask",
                "When you where sleeping",
                "Love Actually"
        );
        comboBox.setPromptText("What is you're favourite movie?");
        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));
        comboBox.setEditable(true);

        button.setOnAction(e -> printMovie());


        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(comboBox, button);

        scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    private void printMovie() {
        System.out.println(comboBox.getValue());
    }
}
