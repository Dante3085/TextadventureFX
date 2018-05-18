package com.ui;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * Displays fps.
 * @author mjsch
 */
public class FPS_View extends StackPane {

    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0 ;
    private boolean arrayFilled = false ;
    private Label label;
    AnimationTimer frameRateMeter;

    boolean enableMsg = false;

    public FPS_View()
    {
        label = new Label();
        label.setTextFill(Color.ROYALBLUE);
        label.setFont(label.getFont().font(20));
        frameRateMeter = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;

                if (frameTimeIndex == 0)
                {
                    arrayFilled = true ;
                }

                if (arrayFilled)
                {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    label.setText(String.format("Current frame rate: %.3f", frameRate));
                    if (enableMsg == true)
                        System.out.println(String.format("Current frame rate: %.3f", frameRate));
                }
            }
        };
        getChildren().add(label);
        initFPSView(false);
    }

    private void initFPSView(boolean msg)
    {
        this.enableMsg = msg;
        frameRateMeter.start();
    }
}