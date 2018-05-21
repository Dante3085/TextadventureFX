package com.ui;

import javafx.scene.control.TextArea;

/**
 * Main TextArea of the Textadventure.
 * @author mjsch
 */
public class TTextArea extends TextArea
{
    /**
     * Default Constructor.
     */
    public TTextArea()
    {
        // Look
        this.setOpacity(0.6);

        // Position
        this.setTranslateX(200);
        this.setTranslateY(200);
    }
}
