package com.ui;

import javafx.animation.TranslateTransition;
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
    private Text text = new Text();
    private Position position;
    private TranslateTransition translateTransition = new TranslateTransition();

    /**
     * Default constructor with default values.
     */
    public TurnOrderRec()
    {
        this.width = 10;
        this.height = 10;
        this.text.setText("DEFAULT");
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
        rectangle = new Rectangle(width, height);
        this.text.setText(text);
        this.position = position;
        this.text.setTranslateY(this.position.value());
        rectangle.setTranslateY(this.position.value());
        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.6);

        this.getChildren().addAll(rectangle, this.text);
        translateTransition.setNode(this);
    }

    public void moveUp(double duration)
    {
        translateTransition.setFromY(position.value());
        translateTransition.setToY(position.next().value());
        translateTransition.setDuration(new Duration(duration));
        translateTransition.play();
    }
}
