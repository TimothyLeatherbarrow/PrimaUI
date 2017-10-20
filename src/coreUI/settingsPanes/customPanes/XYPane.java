package coreUI.settingsPanes.customPanes;

import com.tiggerbiggo.prima.core.float2;
import coreUI.eventHandlers.joyMovedEvent;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class XYPane extends Pane {

    Circle joystick;

    float2 mul = null;
    float2 offset = float2.ZERO;

    boolean autoScale, isDragging = false;

    SimpleDoubleProperty xProperty, yProperty;


    public XYPane(boolean autoScale)
    {
        this.autoScale = autoScale;
        xProperty = new SimpleDoubleProperty(0);
        yProperty = new SimpleDoubleProperty(0);

        joystick = new Circle(8);

        getChildren().add(joystick);

        //prefHeightProperty().bind();

        setPrefHeight(getPrefWidth());


        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setPrefHeight(newValue.doubleValue());
                doLayout();
            }
        });

        joystick.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isSecondaryButtonDown()&&!isDragging) reset();

                isDragging = true;
                double x = event.getX();
                double y = event.getY();
                if(x>=0 && x<=getWidth()) {
                    joystick.setCenterX(x);
                    xProperty.setValue(x-(getWidth()/2));
                }

                if(y>=0 && y<=getHeight()) {
                    joystick.setCenterY(y);
                    yProperty.setValue(y-(getHeight()/2));
                }
                fireEvent(new joyMovedEvent(joyMovedEvent.JOY_MOVED));
            }
        });

        joystick.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isDragging = false;
                offset = getResult();
                doLayout();
                fireEvent(new joyMovedEvent(joyMovedEvent.JOY_FINISHED));
            }
        });

        joystick.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isSecondaryButtonDown())
                {
                    if(isDragging) {
                        offset = getResult();
                        fireEvent(new joyMovedEvent(joyMovedEvent.JOY_FINISHED));
                    }
                    else {
                        reset();
                        fireEvent(new joyMovedEvent(joyMovedEvent.JOY_MOVED));
                    }
                }
            }
        });

        AnchorPane.setLeftAnchor(this, 15.0);
        AnchorPane.setRightAnchor(this, 15.0);

        joystick.toFront();
        setStyle("-fx-background-color: #777777");
    }

    public void doLayout()
    {
        double cX, cY;

        cX = getWidth()/2;
        cY = getHeight()/2;

        System.out.println(cX);
        joystick.setCenterX(cX);
        joystick.setCenterY(cY);
    }

    public Circle getJoystick()
    {
        return joystick;
    }

    public float2 getMul()
    {
        return mul;
    }
    public void setMul(float2 mul)
    {
        this.mul = mul;
    }

    public SimpleDoubleProperty getXProperty() {
        return xProperty;
    }

    public SimpleDoubleProperty getYProperty() {
        return yProperty;
    }

    public float2 getResult()
    {
        float2 toReturn = new float2(
                (xProperty.floatValue() * mul.getX())+offset.getX(),
                (yProperty.floatValue() * mul.getY())+offset.getY());
        System.out.println("Getting Result: " + xProperty.floatValue());
        return toReturn;
    }

    public void reset()
    {
        xProperty.setValue(1);
        yProperty.setValue(1);
        mul = new float2(0.05f, 0.05f);
        offset = float2.ZERO;
        doLayout();
    }
}
