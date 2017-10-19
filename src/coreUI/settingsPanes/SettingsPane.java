package coreUI.settingsPanes;

import javafx.scene.layout.AnchorPane;

public abstract class SettingsPane extends AnchorPane{

    public SettingsPane()
    {
        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this, 0.0);
        AnchorPane.setLeftAnchor(this, 0.0);
        AnchorPane.setRightAnchor(this, 0.0);
    }

}
