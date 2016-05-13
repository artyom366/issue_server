package sample.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.domain.Issue;
import sample.domain.Result;
import sample.system.Reference;

import java.math.BigDecimal;
import java.util.List;

public class ResultTable {

    private TableView<Result> table;
    private ObservableList<Result> data;

    public ResultTable() {
        this.table = new TableView<>();
        this.table.setPrefHeight(Reference.TABLE_HEIGHT);
        this.table.setPrefWidth(Reference.TABLE_WIDTH);
    }

    public TableView<Result> init() {
        return table;
    }

    public void setData(List<Result> results) {


        TableColumn<Result, Integer> eventColumn = new TableColumn<>("#");
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("event"));
        eventColumn.setPrefWidth(50);

        TableColumn<Result, Issue.type> issueType = new TableColumn<>("Type");
        issueType.setCellValueFactory(new PropertyValueFactory<>("issueType"));
        issueType.setPrefWidth(85);

        TableColumn<Result, BigDecimal> currentTime = new TableColumn<>("Time");
        currentTime.setCellValueFactory(new PropertyValueFactory<>("currentTime"));
        currentTime.setPrefWidth(85);

        TableColumn<Result, String> isAvailableServer = new TableColumn<>("Server");
        isAvailableServer.setCellValueFactory(new PropertyValueFactory<>("isAvailableServer"));
        isAvailableServer.setPrefWidth(85);

        TableColumn<Result, BigDecimal> poissonIssueGenerationTime = new TableColumn<>("Poisson");
        poissonIssueGenerationTime.setCellValueFactory(new PropertyValueFactory<>("poissonIssueGenerationTime"));
        poissonIssueGenerationTime.setPrefWidth(85);

        TableColumn<Result, BigDecimal> erlangIssueGenerationTime = new TableColumn<>("Erlang");
        erlangIssueGenerationTime.setCellValueFactory(new PropertyValueFactory<>("erlangIssueGenerationTime"));
        erlangIssueGenerationTime.setPrefWidth(85);

        TableColumn<Result, BigDecimal> currentIssueProcessingTime = new TableColumn<>("Processing");
        currentIssueProcessingTime.setCellValueFactory(new PropertyValueFactory<>("currentIssueProcessingTime"));
        currentIssueProcessingTime.setPrefWidth(85);

        TableColumn<Result, BigDecimal> currentIssueProcessingTimeEnd = new TableColumn<>("End");
        currentIssueProcessingTimeEnd.setCellValueFactory(new PropertyValueFactory<>("currentIssueEndProcessingTime"));
        currentIssueProcessingTimeEnd.setPrefWidth(85);

        TableColumn<Result, Integer> queueLength = new TableColumn<>("Queue");
        queueLength.setCellValueFactory(new PropertyValueFactory<>("queueLength"));
        queueLength.setPrefWidth(85);

        TableColumn<Result, String> issuesInQueue = new TableColumn<>("List");
        issuesInQueue.setCellValueFactory(new PropertyValueFactory<>("issuesInQueueScoreView"));
        issuesInQueue.setPrefWidth(135);

        table.getColumns().addAll(eventColumn,
                issueType,
                currentTime,
                isAvailableServer,
                poissonIssueGenerationTime,
                erlangIssueGenerationTime,
                currentIssueProcessingTime,
                currentIssueProcessingTimeEnd,
                queueLength,
                issuesInQueue);

        data = FXCollections.observableList(results);
        table.setItems(data);
    }

    public void clearTableData() {
        table.getItems().clear();
    }
}
