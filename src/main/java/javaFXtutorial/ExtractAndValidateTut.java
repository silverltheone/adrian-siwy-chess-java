package javaFXtutorial;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExtractAndValidateTut extends Application{
    Scene scene;
    Stage window;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        TextField nameImput = new TextField();
        button = new Button("Click me");
        button.setOnAction(e-> isInt(nameImput, nameImput.getText()));

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(nameImput, button);

        scene = new Scene(layout,300,250);
        window.setScene(scene);
        window.show();
    }

    private boolean isInt(TextField input, String message) {
        try {
            int age = Integer.parseInt(input.getText());
            System.out.println("User is: " + age);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error: " + message + " is not number");
            return false;
        }
    }
}
