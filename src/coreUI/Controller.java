package coreUI;

import com.tiggerbiggo.prima.core.Builder;
import com.tiggerbiggo.prima.core.float2;
import com.tiggerbiggo.prima.graphics.Gradient;
import com.tiggerbiggo.prima.presets.MapTypes;
import com.tiggerbiggo.prima.presets.TransformTypes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

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
        drag = new DraggablePane();
        drag.setPrefSize(100, 100);
        imageView = new ImageView();

        g = new Gradient(Color.black, Color.red, true);


        drag.getChildren().add(imageView);
        nodePane.getChildren().add(drag);

        imageView.fitHeightProperty().bind(drag.heightProperty());
        imageView.fitWidthProperty().bind(drag.widthProperty());

    }
}
