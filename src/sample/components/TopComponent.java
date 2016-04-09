package sample.components;

import javafx.scene.layout.AnchorPane;

public class TopComponent {

    private AnchorPane top;

    public TopComponent() {
        this.top = new AnchorPane();
    }

    public AnchorPane init() {
        return top;
    }


}
