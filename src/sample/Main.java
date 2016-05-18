package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.components.*;
import sample.domain.GeneralResults;
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
    private GeneralResultsLabel generalResultsLabel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(Reference.TITLE);
        initRootLayout();

        GeneralResults generalResults = getData();

        resultTable.setData(generalResults.getResults());
        generalResultsLabel.setText(generalResults.getServerLoad().doubleValue(),
                generalResults.getAverageTimeInQueue().doubleValue(),
                generalResults.getPoissonCounter(),
                generalResults.getErlangCounter());
        setActions();
    }

    private void setActions() {
        EventHandler<ActionEvent> refresh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                resultTable.clearTableData();

                GeneralResults generalResults = getData();

                resultTable.setData(generalResults.getResults());
                generalResultsLabel.setText(generalResults.getServerLoad().doubleValue(),
                        generalResults.getAverageTimeInQueue().doubleValue(),
                        generalResults.getPoissonCounter(),
                        generalResults.getErlangCounter());
            }
        };

        refreshButton.addAction(refresh);
    }

    private GeneralResults getData() {
        SimModel model = new SimModel();
        GeneralResults generalResults = model.run();
        return generalResults;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initRootLayout() {
        centerComponent = new CenterComponent();
        topComponent = new TopComponent();
        resultTable = new ResultTable();
        refreshButton = new RefreshButton();
        generalResultsLabel = new GeneralResultsLabel();

        root = new BorderPane();
        root.setCenter(centerComponent.init());
        root.setTop(topComponent.init());

        centerComponent.addComponent(resultTable.init());
        centerComponent.addComponent(refreshButton.init());
        centerComponent.addComponent(generalResultsLabel.init());

        scene = new Scene(root, Reference.MAIN_WINDOW_WIDTH, Reference.MAIN_WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
