package javaFXtutorial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ThirdClass extends Application {
    Stage window;
    Button button1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("JavaFX tutorial winodw");

        button1 = new Button("Click me");
        button1.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirmation needet", "Do you really want to?");
            System.out.println(result);
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button1);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }
}
