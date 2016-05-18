package sample.components;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import sample.system.Reference;

public class RefreshButton {

    private Button refreshButton;

    public RefreshButton() {
        this.refreshButton = new Button("Refresh");
    }

    public Button init() {
        this.refreshButton.setLayoutX(Reference.BUTTON_X);
        this.refreshButton.setLayoutY(Reference.BUTTON_Y);

        return this.refreshButton;
    }

    public void addAction(EventHandler<ActionEvent> event) {
        refreshButton.setOnAction(event);
    }
}
