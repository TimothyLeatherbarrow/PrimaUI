package coreUI.settingsPanes;

import com.tiggerbiggo.prima.core.Builder;
import com.tiggerbiggo.prima.presets.TransformTypes;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PrimaSettingsPane extends SettingsPane{

    Builder b;

    ChoiceBox<TransformTypes> types;


    public PrimaSettingsPane(Builder b)
    {
        super();
        if(b == null) b = new Builder();

        this.b = b;
    }

    void updateFields()
    {
        types.setValue(b.getTransformType());
    }

}
