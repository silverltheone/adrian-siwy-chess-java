package javaFXtutorial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenusTut extends Application {
    Stage window;
    BorderPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        Menu fileMenu = new Menu("_File");

        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> System.out.println("Create a new file"));
        fileMenu.getItems().add(newFile);

        fileMenu.getItems().add(new MenuItem("New..."));
        fileMenu.getItems().add(new MenuItem("Open..."));
        fileMenu.getItems().add(new MenuItem("Save..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Settings..."));
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(new MenuItem("Exit..."));

        Menu edutMenu = new Menu("_Edit");
        edutMenu.getItems().add(new MenuItem("Copy..."));
        edutMenu.getItems().add(new MenuItem("Edit..."));

        MenuItem paste = new MenuItem("Paste...");
        paste.setOnAction(e -> System.out.println("Pasting"));
        paste.setDisable(true);
        edutMenu.getItems().add(paste);

        Menu helpMenu = new Menu("Help");

        CheckMenuItem showLines = new CheckMenuItem("Show line numbers");
        showLines.setOnAction(e -> {
            if(showLines.isSelected())
                System.out.println("Program will display line numbers");
            else
                System.out.println("Hiding line numbers");
        });

        CheckMenuItem autoSave = new CheckMenuItem("Auto Save"); 
        autoSave.setSelected(true);

        helpMenu.getItems().addAll(showLines, autoSave);

        Menu difficultyMenu = new Menu("Difficulty");
        ToggleGroup difficultyToggle = new ToggleGroup();
        RadioMenuItem easy = new RadioMenuItem("Easy");
        RadioMenuItem medium = new RadioMenuItem("Medium");
        RadioMenuItem hard = new RadioMenuItem("Hard");
        easy.setToggleGroup(difficultyToggle);
        medium.setToggleGroup(difficultyToggle);
        hard.setToggleGroup(difficultyToggle);
        difficultyMenu.getItems().addAll(easy, medium, hard);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, edutMenu, helpMenu, difficultyMenu);

        layout = new BorderPane();
        layout.setTop(menuBar);
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
}
