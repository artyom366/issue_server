package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.components.CenterComponent;
import sample.components.TopComponent;
import sample.system.Reference;

public class Main extends Application {

    private CenterComponent centerComponent;
    private TopComponent topComponent;

    private Stage primaryStage;
    private BorderPane root;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Reference.TITLE);
        initRootLayout();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initRootLayout() {
        centerComponent = new CenterComponent();
        topComponent = new TopComponent();

        root = new BorderPane();
        root.setCenter(centerComponent.init());
        root.setTop(topComponent.init());

        scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
