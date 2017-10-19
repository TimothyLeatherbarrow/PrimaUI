package coreUI;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DraggablePane extends AnchorPane
{
    boolean dragged = false;
    double dx, dy , startW, startH;


    public DraggablePane()
    {
        super();

        dx = dy = 0;

        this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                if(dragged == false) {
                    dx = event.getX();
                    dy = event.getY();
                    startH = getPrefHeight();
                    startW = getPrefWidth();
                    dragged = true;
                }
                else {
                    if(event.isPrimaryButtonDown()) {
                        setLayoutX(event.getX() + getLayoutX() - dx);
                        setLayoutY(event.getY() + getLayoutY() - dy);
                    }
                    else if(event.isSecondaryButtonDown())
                    {
                        System.out.println("RMB");
                        setPrefSize(
                                event.getX() + startW - dx,
                                event.getY() + startH - dy
                        );
                    }
                }
            }
        });

        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragged = false;
            }
        });
    }
}
