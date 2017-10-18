package coreUI;

import com.tiggerbiggo.prima.core.Builder;
import com.tiggerbiggo.prima.core.float2;
import com.tiggerbiggo.prima.graphics.Gradient;
import com.tiggerbiggo.prima.presets.MapTypes;
import com.tiggerbiggo.prima.presets.TransformTypes;
import javafx.util.Callback;

import java.awt.image.BufferedImage;

class InternalRenderer implements Runnable{
    Builder b;
    Callback c;
    Gradient g;
    public BufferedImage img;

    public InternalRenderer(Callback c, Gradient g, int x, int y)
    {
        b = new Builder(6, x, y);
        this.c = c;
        this.g = g;
    }

    @Override
    public void run()
    {
        try {
            img = b.fullBuild(
                    new float2(0, 0),
                    new float2(5, 5),
                    MapTypes.REGULAR,
                    TransformTypes.MAGNETISM,
                    g);
        }
        catch(Exception e){}
        if(!Thread.interrupted()) c.call(null);
    }
}
