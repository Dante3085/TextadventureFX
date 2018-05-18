package com.ui;

import com.Main.Main;
import com.character.Character;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * UI-Element for Combat. Shows the player the turn-order of Characters.
 * @author mjsch
 */
public class TurnOrderView extends StackPane
{
    private boolean undo = false;

    private ArrayList<Character> turnOrder = new ArrayList<>();
    private ArrayList<TurnOrderRec> turnOrderRecs = new ArrayList<>();

    public TurnOrderView()
    {
        turnOrderRecs.add(new TurnOrderRec(50, 50, "FIRST", Position.FIRST));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "SECOND", Position.SECOND));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "THIRD", Position.THIRD));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "FOURTH", Position.FOURTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "FIFTH", Position.FIFTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "SIXTH", Position.SIXTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "SEVENTH", Position.SEVENTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, "EIGHTH", Position.EIGHTH));

        for (TurnOrderRec r : turnOrderRecs)
            this.getChildren().add(r);

        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    /**
     * Changes the visual appearance after one turn. Every Rectangle is going to move one step up in the cycle.
     */
    public void turn()
    {
        for (TurnOrderRec r : turnOrderRecs)
            r.moveUp(2000);
    }

    /**
     * Moves a Rectangle instance to the passed Position 'pos' in 'duration' seconds.
     * @param r Passed Rectangle instance that is going to be moved.
     * @param pos Position that the Rectangle instance is going to be moved to. Specified by private enum 'Position'.
     * @param duration Duration of move animation.
     */
    private void moveRectangle(TranslateTransition animation, Rectangle r, Position pos, int duration)
    {
        animation.setNode(r);
        animation.setFromY(r.getTranslateY());
        animation.setToY(pos.value());
        animation.setDuration(new Duration(duration * 1000));
        animation.play();
    }

    public void setTurnOrder(ArrayList<Character> turnOrder)
    {
        if (turnOrder.size() != 8)
            return;

    }
}
