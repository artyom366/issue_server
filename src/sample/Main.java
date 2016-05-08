package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.components.CenterComponent;
import sample.components.ResultTable;
import sample.components.TopComponent;
import sample.domain.Result;
import sample.service.SimModel;
import sample.system.Reference;

import java.util.List;

public class Main extends Application {

    private CenterComponent centerComponent;
    private TopComponent topComponent;

    private Stage primaryStage;
    private BorderPane root;
    private Scene scene;

    private ResultTable resultTable;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Reference.TITLE);
        initRootLayout();
        getData();
    }

    private void getData() {
        resultTable = new ResultTable();
        List<Result> results = SimModel.run();

        resultTable.setData(results);
        centerComponent.addComponent(resultTable.init());
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

        scene = new Scene(root, Reference.MAIN_WINDOW_WIDTH, Reference.MAIN_WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
