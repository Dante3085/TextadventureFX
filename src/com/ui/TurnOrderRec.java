package com.ui;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
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
    private Text text;
    private Position position;
    private TranslateTransition translateTransition;

    /**
     * Default constructor with default values.
     */
    public TurnOrderRec()
    {
        translateTransition = new TranslateTransition();
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
        this.setWidth(width);
        this.setHeight(height);

        // Objekte initialisieren
        translateTransition = new TranslateTransition();
        rectangle = new Rectangle(width, height);
        this.text = new Text(text);
        this.position = position;

        rectangle.setFill(Color.RED);
        rectangle.setOpacity(0.6);
        translateTransition.setNode(this);

        this.text.setTranslateY(this.position.value());
        rectangle.setTranslateY(this.position.value());
        //this.setTranslateY(this.position.value());

        this.getChildren().addAll(rectangle, this.text);
    }

    public void moveUp(double duration)
    {
        //System.out.println("position.value() = " + position.value() + ", position.next().value() = " + position.next().value() + ", rectanlge.getY() = " + rectangle.getY() + ", rectangle.getTranslateY() + " + rectangle.getTranslateY());
        System.out.println(position.value() + " -> " + position.next().value() + " | rectangle.getY() = " + rectangle.getY() + ", rectangle.getTranslateY() = " + rectangle.getTranslateY()
                           + ", this.getTranslateY() = " + this.getTranslateY());
        //translateTransition.setFromY(position.value());
        translateTransition.setToY(position.next().value());
        translateTransition.setDuration(Duration.millis(duration));
        translateTransition.play();
        position = position.next();
        System.out.println("NACHER position.current = " + position + "\n");

        //System.out.println("NACHHER position.value() = " + position.value() + ", position.next().value() = " + position.next().value() + ", rectanlge.getY() = " + rectangle.getY() + ", rectangle.getTranslateY() + " + rectangle.getTranslateY() + "/n");
    }
}
