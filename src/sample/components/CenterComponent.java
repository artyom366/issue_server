package sample.components;


import javafx.scene.layout.AnchorPane;

public class CenterComponent {

    private AnchorPane center;

    public CenterComponent() {
        this.center = new AnchorPane();;
    }

    public AnchorPane init() {

        return center;
    }
}
