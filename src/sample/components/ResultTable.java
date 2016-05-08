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

        TableColumn<Result, Issue.type> issueType = new TableColumn<>("Type");
        issueType.setCellValueFactory(new PropertyValueFactory<>("issueType"));

        TableColumn<Result, Double> currentTime = new TableColumn<>("Time");
        currentTime.setCellValueFactory(new PropertyValueFactory<>("currentTime"));

        TableColumn<Result, String> isAvailableServer = new TableColumn<>("Server");
        isAvailableServer.setCellValueFactory(new PropertyValueFactory<>("isAvailableServer"));

        TableColumn<Result, Double> poissonIssueGenerationTime = new TableColumn<>("Poisson");
        poissonIssueGenerationTime.setCellValueFactory(new PropertyValueFactory<>("poissonIssueGenerationTime"));

        TableColumn<Result, Double> erlangIssueGenerationTime = new TableColumn<>("Erlang");
        erlangIssueGenerationTime.setCellValueFactory(new PropertyValueFactory<>("erlangIssueGenerationTime"));

        TableColumn<Result, Double> currentIssueProcessingTimeEnd = new TableColumn<>("Processed");
        currentIssueProcessingTimeEnd.setCellValueFactory(new PropertyValueFactory<>("currentIssueEndProcessingTime"));

        TableColumn<Result, Integer> queueLength = new TableColumn<>("Queue");
        queueLength.setCellValueFactory(new PropertyValueFactory<>("queueLength"));

        TableColumn<Result, List<String>> issuesInQueue = new TableColumn<>("List");
        issuesInQueue.setCellValueFactory(new PropertyValueFactory<>("issuesInQueue"));

        table.getColumns().addAll(eventColumn, issueType, currentTime, isAvailableServer, poissonIssueGenerationTime, erlangIssueGenerationTime, currentIssueProcessingTimeEnd, queueLength, issuesInQueue);

        ObservableList<Result> data = FXCollections.observableList(results);
        table.setItems(data);
    }
}
