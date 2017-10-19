package coreUI;

import coreUI.settingsPanes.SettingsPane;

public class ControlRelay {

    private static ControlRelay ourInstance = null;
    private Controller c;

    public static ControlRelay getInstance() {
        return ourInstance;
    }

    public ControlRelay(Controller c) throws Exception
    {
        if(ourInstance == null) ourInstance = this;
        else throw new Exception("Illegal Operation: Singleton already defined");
        this.c = c;
    }

    public void changeSettingsPane(SettingsPane p)
    {
        c.changeSettingsPane(p);
    }
}
