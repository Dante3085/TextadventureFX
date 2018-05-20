package com.ui;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    //private Text text;
    private Position position;
    private TranslateTransition ttRec;
    private TranslateTransition ttText;
    private Label label;

    /**
     * Default constructor with default values.
     */
    public TurnOrderRec()
    {
        ttRec = new TranslateTransition();
        ttText = new TranslateTransition();
        this.width = 10;
        this.height = 10;
        //this.text.setText("DEFAULT");
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

        // Objekte initialisieren
        ttRec = new TranslateTransition();
        ttText = new TranslateTransition();
        rectangle = new Rectangle(width, height);
        //this.text = new Text(text);
        this.label = new Label(text);
        this.position = position;

        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.6);
        ttRec.setNode(rectangle);
        ttText.setNode(label);

        //this.text.setTranslateY(this.position.value());
        //rectangle.setTranslateY(this.position.value());
        //label.setTranslateY(this.position.value());
        //this.setTranslateY(this.position.value());

        this.getChildren().addAll(rectangle, label);
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
        ttText.setFromY(label.getTranslateY());
        ttText.setToY(position.value());
        ttText.setDuration(Duration.millis(duration));

        ttRec.play();
        ttText.play();
    }

    /**
     * Moves the TurnOrderRec up one position in 'duration' time
     * @param duration
     */
    public void moveUp(double duration)
    {
        // Rectangle
        ttRec.setFromY(rectangle.getTranslateY());
        ttRec.setToY(position.next().value());
        ttRec.setDuration(Duration.millis(duration));

        // TextLabel
        ttText.setFromY(label.getTranslateY());
        ttText.setToY(position.next().value());
        ttText.setDuration(Duration.millis(duration));

        ttRec.play();
        ttText.play();

        position = position.next();
    }
}
