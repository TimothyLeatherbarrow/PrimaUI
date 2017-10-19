package coreUI;

import coreUI.Components.PrimaPane;
import com.tiggerbiggo.prima.graphics.Gradient;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller
{
    BufferedImage[] imgArray;
    BufferedImage img;
    Gradient g;
    Thread t;

    DraggablePane drag;

    @FXML
    Pane nodePane;

    ImageView imageView;

    @FXML
    public void initialize()
    {
        drag = new DraggablePane(30, 30, 300, 300);
        drag.setPrefSize(100, 100);
        imageView = new ImageView();

        g = new Gradient(Color.black, Color.red, true);

        drag.getChildren().add(new PrimaPane());
        drag.setPrefSize(100, 100);
        nodePane.getChildren().add(drag);

        imageView.fitHeightProperty().bind(drag.heightProperty());
        imageView.fitWidthProperty().bind(drag.widthProperty());

    }
}
