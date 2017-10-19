package coreUI.Components;

import coreUI.DraggablePane;

public abstract class Component extends DraggablePane{

    public Component(int minW, int minH, int maxW, int maxH)
    {
        super(minW, minH, maxW, maxH);
    }

    public Component()
    {
        this(10, 10, 100, 100);
    }
}
