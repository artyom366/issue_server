package sample.components;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.domain.Issue;
import sample.domain.Result;
import sample.system.Reference;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultTable {

    private TableView<Result> table;

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
                currentIssueProcessingTimeEnd,
                queueLength,
                issuesInQueue);

        ObservableList<Result> data = FXCollections.observableList(results);
        table.setItems(data);
    }
}
