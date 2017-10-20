package coreUI;

import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DraggablePane extends AnchorPane
{
    boolean dragged = false;
    double dx, dy , startW, startH;


    public DraggablePane(int minW, int minH, int maxW, int maxH)
    {
        super();

        dx = dy = 0;

        this.setMinSize(minW, minH);
        this.setMaxSize(maxW, maxH);

        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event)
            {
                event.consume();
                if(dragged == false) {
                    dx = event.getX();
                    dy = event.getY();
                    startH = Math.min(
                            Math.max(
                                    getPrefHeight(),
                                    getMinHeight()),
                            getMaxHeight());

                    startW = Math.min(
                            Math.max(
                                    getPrefWidth(),
                                    getMinWidth()),
                            getMaxWidth());
                    dragged = true;
                }
                else {
                    if(event.isPrimaryButtonDown()) {
                        setLayoutX(event.getX() + getLayoutX() - dx);
                        setLayoutY(event.getY() + getLayoutY() - dy);

                        if(getLayoutX() <0) setLayoutX(0);
                        if(getLayoutY() <0) setLayoutY(0);
                    }
                    else if(event.isSecondaryButtonDown())
                    {
                        setPrefSize(
                                event.getX() + startW - dx,
                                event.getY() + startH - dy
                        );
                    }
                }
                getParent().layout();
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
