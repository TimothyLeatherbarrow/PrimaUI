package coreUI.settingsPanes;

import com.tiggerbiggo.prima.core.Builder;
import com.tiggerbiggo.prima.core.float2;
import com.tiggerbiggo.prima.presets.TransformTypes;
import coreUI.Components.PrimaPane;
import coreUI.eventHandlers.joyMovedEvent;
import coreUI.settingsPanes.customPanes.XYPane;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PrimaSettingsPane extends SettingsPane{

    PrimaPane p;


    ChoiceBox<TransformTypes> types;
    XYPane offset, scale;

    Button save;

    public PrimaSettingsPane(PrimaPane p)
    {
        super();
        if(p == null) return;

        this.p = p;

        types = new ChoiceBox<>();
        types.getItems().setAll(TransformTypes.values());
        getChildren().add(types);

        types.valueProperty().addListener(new ChangeListener<TransformTypes>() {
            @Override
            public void changed(ObservableValue<? extends TransformTypes> observable, TransformTypes oldValue, TransformTypes newValue) {
                p.getBuilder().setTransformType(newValue);
                p.refreshImage();
            }
        });

        AnchorPane.setTopAnchor(types, 15.0);
        AnchorPane.setLeftAnchor(types, 15.0);
        AnchorPane.setRightAnchor(types, 15.0);

        offset = new XYPane(false);
        offset.setLayoutY(60);
        getChildren().add(offset);
        offset.setMul(float2.ONE);
        offset.doLayout();

        offset.addEventHandler(joyMovedEvent.JOY_MOVED, new EventHandler<joyMovedEvent>() {
            @Override
            public void handle(joyMovedEvent event) {
                p.getBuilder().setOffset(offset.getResult());
                p.refreshImage();
            }
        });

        scale = new XYPane(true);
        scale.setLayoutY(200);
        getChildren().add(scale);
        scale.setMul(new float2(0.01f, 0.01f));
        scale.doLayout();

        scale.addEventHandler(joyMovedEvent.JOY_MOVED, new EventHandler<joyMovedEvent>() {
            @Override
            public void handle(joyMovedEvent event) {
                p.getBuilder().setScale(scale.getResult());
                p.refreshImage();

            }
        });

        scale.addEventHandler(joyMovedEvent.JOY_FINISHED, new EventHandler<joyMovedEvent>() {
            @Override
            public void handle(joyMovedEvent event) {
                scale.setMul(p.getBuilder().getScale());
                scale.setMul(new float2(scale.getMul().getX()*0.01f, scale.getMul().getY()*0.01f));
                offset.setMul(scale.getMul());
            }
        });

        save = new Button("Save");

        AnchorPane.setBottomAnchor(save, 15.0);
        AnchorPane.setLeftAnchor(save, 15.0);
        AnchorPane.setRightAnchor(save, 15.0);


        updateFields();

        layout();
    }

    void updateFields()
    {
        types.setValue(p.getBuilder().getTransformType());
    }

}
