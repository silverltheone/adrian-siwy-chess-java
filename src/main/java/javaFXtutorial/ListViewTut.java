package javaFXtutorial;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewTut extends Application{
    Stage window;
    Scene scene;
    Button button;
    ListView<String> listView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        button = new Button("Click me");
        button.setOnAction(e -> buttonClick());

        listView = new ListView<>();
        listView.getItems().addAll("Iron Man", "Titanic", "Surrogates");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(listView, button);

        scene = new Scene(layout, 300,250);
        window.setScene(scene);
        window.show();
    }

    private void buttonClick() {
        String message = "";
        ObservableList<String> movies;
        movies = listView.getSelectionModel().getSelectedItems();
        for(String m: movies) {
            message += m + "\n";
        }
        System.out.println(message);
    }
}
