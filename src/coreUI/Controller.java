package coreUI;

import coreUI.Components.PrimaPane;
import com.tiggerbiggo.prima.graphics.Gradient;
import coreUI.settingsPanes.PrimaSettingsPane;
import coreUI.settingsPanes.SettingsPane;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller
{
    BufferedImage[] imgArray;
    BufferedImage img;
    Gradient g;
    Thread t;

    SettingsPane settings = null;

    @FXML
    Pane nodePane;

    @FXML
    AnchorPane settingsHolder;

    @FXML
    public void initialize(){

        g = new Gradient(Color.black, Color.red, true);


        for(int i=0; i<3; i++) {
            DraggablePane drag = new DraggablePane(30, 30, 300, 300);
            drag.setPrefSize(100, 100);
            drag.getChildren().add(new PrimaPane());
            nodePane.getChildren().add(drag);
        }

        try {
            new ControlRelay(this);
        }
        catch(Exception e){}
    }

    public void changeSettingsPane(SettingsPane p)
    {
        if(settings != null)
        {
            try {
                settingsHolder.getChildren().remove(settings);
            }
            catch (Exception e){}
        }
        settings = p;
        settingsHolder.getChildren().add(settings);
        settings.layout();
    }
}
