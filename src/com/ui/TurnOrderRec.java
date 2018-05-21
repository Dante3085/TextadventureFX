package com.ui;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Represents an element of the "TurnOrderView".
 * @author mjsch
 */
public class TurnOrderRec extends StackPane
{
    private double width;
    private double height;

    private Rectangle rectangle;
    private Position position;
    private TranslateTransition ttRec;
    private TranslateTransition ttText;
    private Label text;

    /**
     * Default constructor with default values.
     */
    public TurnOrderRec()

    {
        ttRec = new TranslateTransition();
        ttText = new TranslateTransition();
        this.width = 10;
        this.height = 10;
        this.position = Position.DEFAULT;
    }

    /**
     * Constructor creates TurnOrderRec with passed values.
     * @param width
     * @param height
     * @param text
     * @param position
     */
    public TurnOrderRec(double width, double height, String text, Position position)
    {
        this.setWidth(width);
        this.setHeight(height);

        // Initialize Objects.
        ttRec = new TranslateTransition();
        ttText = new TranslateTransition();
        rectangle = new Rectangle(width, height);
        this.text = new Label(text);
        this.position = position;

        // Set basic appearance of rectangle and text.
        rectangle.setFill(Color.GREY);
        rectangle.setOpacity(0.1);
        this.text.setTextFill(Color.INDIANRED);

        // Set rectangle and text as nodes of both TranslateTransitions.
        ttRec.setNode(rectangle);
        ttText.setNode(this.text);

        // Add rectangle and text to StackPane.
        this.getChildren().addAll(rectangle, this.text);
    }

    /**
     * Moves the TurnOrderRec in it's initial position.
     * @param duration
     */
    public void init(double duration)
    {
        // Rectangle
        ttRec.setFromY(rectangle.getTranslateY());
        ttRec.setToY(position.value());
        ttRec.setDuration(Duration.millis(duration));

        // TextLabel
        ttText.setFromY(text.getTranslateY());
        ttText.setToY(position.value());
        ttText.setDuration(Duration.millis(duration));

        ttRec.play();
        ttText.play();
    }

    /**
     * Moves the TurnOrderRec up one position in 'duration' time.
     * @param duration
     */
    public void moveUp(double duration)
    {
        // Rectangle
        ttRec.setFromY(rectangle.getTranslateY());
        ttRec.setToY(position.next().value());
        ttRec.setDuration(Duration.millis(duration));

        // TextLabel
        ttText.setFromY(text.getTranslateY());
        ttText.setToY(position.next().value());
        ttText.setDuration(Duration.millis(duration));

        ttRec.play();
        ttText.play();

        position = position.next();
    }
}
