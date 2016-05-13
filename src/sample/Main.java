package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.components.CenterComponent;
import sample.components.RefreshButton;
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
    private RefreshButton refreshButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Reference.TITLE);
        initRootLayout();
        resultTable.setData(getData());
        setActions();
    }

    private void setActions() {
        EventHandler<ActionEvent> refresh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultTable.clearTableData();
                resultTable.setData(getData());
            }
        };

        refreshButton.addAction(refresh);
    }

    private List<Result> getData() {
        SimModel model = new SimModel();
        List<Result> results = model.run();
        return results;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initRootLayout() {
        centerComponent = new CenterComponent();
        topComponent = new TopComponent();
        resultTable = new ResultTable();
        refreshButton = new RefreshButton();

        root = new BorderPane();
        root.setCenter(centerComponent.init());
        root.setTop(topComponent.init());

        centerComponent.addComponent(resultTable.init());
        centerComponent.addComponent(refreshButton.init());

        scene = new Scene(root, Reference.MAIN_WINDOW_WIDTH, Reference.MAIN_WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
