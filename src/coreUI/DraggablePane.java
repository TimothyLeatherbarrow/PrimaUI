package coreUI;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;

public class DraggablePane extends Pane
{
    boolean dragged = false;
    double dx, dy , startW, startH;


    public DraggablePane()
    {
        super();

        dx = dy = 0;

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
