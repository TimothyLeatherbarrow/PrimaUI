package coreUI.settingsPanes;

import com.tiggerbiggo.prima.core.Builder;
import com.tiggerbiggo.prima.presets.TransformTypes;
import coreUI.Components.PrimaPane;
import coreUI.settingsPanes.customPanes.XYPane;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
    XYPane offset;

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

        offset = new XYPane();
        offset.setLayoutY(60);
        getChildren().add(offset);

        AnchorPane.setLeftAnchor(offset, 15.0);
        AnchorPane.setRightAnchor(offset, 15.0);

        updateFields();
    }

    void updateFields()
    {
        types.setValue(p.getBuilder().getTransformType());
    }

}
