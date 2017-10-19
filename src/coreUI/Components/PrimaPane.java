package coreUI.Components;

import com.tiggerbiggo.prima.core.Builder;
import coreUI.ControlRelay;
import coreUI.settingsPanes.PrimaSettingsPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.image.BufferedImage;

public class PrimaPane extends Pane{

    ImageView view;
    Builder b;
    Thread t = null;

    public PrimaPane(Builder b, int width, int height)
    {
        super();

        if(b == null) b = new Builder();

        this.b = b;

        AnchorPane.setRightAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);


        setStyle("-fx-background-color: #000000");

        view = new ImageView();
        getChildren().add(view);

        view.fitHeightProperty().bind(heightProperty());
        view.fitWidthProperty().bind(widthProperty());

        this.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                double w, h;
                w = newValue.getWidth();
                h = newValue.getHeight();

                setPrefSize(w, h);

                refreshImage();
            }
        });

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ControlRelay.getInstance().changeSettingsPane(new PrimaSettingsPane(PrimaPane.this));
            }
        });
    }

    public PrimaPane()
    {
        this(new Builder(), 100, 100);
    }

    public Builder getBuilder()
    {
        return b;
    }

    public synchronized void refreshImage()
    {
        int x, y;
        x = (int)getWidth();
        y = (int)getHeight();
        if(t != null) t.interrupt();

        t = new Thread(){
            @Override
            public void run() {
                BufferedImage i = b.build(x, y);
                if(isInterrupted()) return;
                view.setImage(SwingFXUtils.toFXImage(i, null));
            }
        };

        t.start();
    }
}
