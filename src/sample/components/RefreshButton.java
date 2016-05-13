package sample.components;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class RefreshButton {

    private Button refreshButton;

    public RefreshButton() {
        this.refreshButton = new Button("Refresh");
    }

    public Button init() {
        this.refreshButton.setLayoutX(10);
        this.refreshButton.setLayoutY(580);

        return this.refreshButton;
    }

    public void addAction(EventHandler<ActionEvent> event) {
        refreshButton.setOnAction(event);
    }
}
