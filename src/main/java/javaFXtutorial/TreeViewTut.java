package javaFXtutorial;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class TreeViewTut extends Application{
    Stage window;
    TreeView<String> tree;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("AS");

        TreeItem<String> root, mine, her;

        root = new TreeItem<>();
        root.setExpanded(true);

        mine = makeBranch("mine", root);
        makeBranch("Programming", mine);
        makeBranch("Gaming", mine);
        makeBranch("Music", mine);

        her = makeBranch("her", root);
        makeBranch("Painting", her);
        makeBranch("Me", her);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue != null) {
                System.out.println(newValue.getValue());
            }
        });


        StackPane layout = new StackPane();
        layout.getChildren().addAll(tree);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }

    public TreeItem<String> makeBranch(String name, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(name);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}
