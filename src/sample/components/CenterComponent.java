package sample.components;


import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;

public class CenterComponent {

    private AnchorPane center;

    public CenterComponent() {
        this.center = new AnchorPane();
    }

    public AnchorPane init() {
        return this.center;
    }

    public void addComponent(Control control) {
        this.center.getChildren().addAll(control);
    }
}
