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

public class Controller implements Callback
{
    BufferedImage[] imgArray;
    BufferedImage img;
    Gradient g;
    Thread t;

    InternalRenderer rend;

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

        drag.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                refresh((int)newValue.getWidth(), (int)newValue.getHeight());
            }
        });
    }

    synchronized void refresh(int x, int y)
    {
        if(t != null)
            if(t.isAlive())t.interrupt();

        //System.out.printf("X: %d, Y:%d\n", x, y);

        rend = new InternalRenderer(this, g, x, y);
        t = new Thread(rend);
        t.start();
    }

    @Override
    public Object call(Object param) {
        double before = System.nanoTime();
        Image convertedImage = SwingFXUtils.toFXImage(rend.img, null);
        System.out.println("Time taken to do image conversion: " + (System.nanoTime() - before));

        imageView.setImage(convertedImage);
        return null;
    }


}
