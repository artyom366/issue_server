package sample.components;


import javafx.scene.control.Label;
import sample.system.Reference;

public class GeneralResultsLabel {

    private Label bottomLabel;

    public GeneralResultsLabel() {
        this.bottomLabel = new Label();
    }

    public Label init() {
        this.bottomLabel.setLayoutX(Reference.LABEL_X);
        this.bottomLabel.setLayoutY(Reference.LABEL_Y);

        return this.bottomLabel;
    }

    public void setText(double serverAvailability, double averageTimeInQueue, int poissonCounter, int erlangCounter) {
        this.bottomLabel.setText(String.format("Avail: %s  Avg: %s  Poisson: %s  Erlang: %s",
                serverAvailability,
                averageTimeInQueue,
                poissonCounter,
                erlangCounter));
    }
}
