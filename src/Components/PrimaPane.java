package Components;

import com.tiggerbiggo.prima.core.Builder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PrimaPane extends Pane{

    ImageView view;
    Builder b;
    Thread t;

    public PrimaPane()
    {
        super();

        this.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                view.setFitWidth(getWidth());
                view.setFitHeight(getHeight());
            }
        });
    }
}
