package coreUI.settingsPanes.customPanes;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class XYPane extends Pane{

    Circle joystick;
    Line hLine, vLine;

    public XYPane()
    {
        joystick = new Circle(8);

        hLine = new Line();
        vLine = new Line();

        getChildren().add(joystick);
        getChildren().add(hLine);
        getChildren().add(vLine);

        prefHeightProperty().bind(widthProperty());

        boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                dolayout();
            }
        });
        joystick.toFront();
    }

    public void dolayout()
    {
        double cX, cY;

        cX = getWidth()/2;
        cY = getHeight()/2;

        joystick.setCenterX(cX);
        joystick.setCenterY(cY);

        hLine.setStartX(0);
        hLine.setStartY(cY);
        hLine.setEndX(cX*2);
        hLine.setEndY(cY);

        vLine.setStartX(cX);
        vLine.setStartY(0);
        vLine.setEndX(cX);
        vLine.setEndY(cY*2);
    }
}
