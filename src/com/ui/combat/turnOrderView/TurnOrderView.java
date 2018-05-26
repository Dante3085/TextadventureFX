package com.ui.combat.turnOrderView;

import com.character.Character;
import com.exceptions.InvalidTurnOrderException;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
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

    // Above constructors are only help methods to shorten the size of constructors. They aren't meant to be reusable.

    /**
     * Adds 8 TurnOrderRecs to corresponding ArrayList 'turnOrderRecs'.
     * Adds those 8 TurnOrderRecs to StackPane as well.
     */
    private void setupTurnOrderRecs()
    {
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(0).getName(), Position.FIRST));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(1).getName(), Position.SECOND));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(2).getName(), Position.THIRD));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(3).getName(), Position.FOURTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(4).getName(), Position.FIFTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(5).getName(), Position.SIXTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(6).getName(), Position.SEVENTH));
        turnOrderRecs.add(new TurnOrderRec(50, 50, this.turnOrder.get(7).getName(), Position.EIGHTH));

        for (TurnOrderRec r : turnOrderRecs)
            this.getChildren().add(r);

        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    /**
     * Constructor without Parameters. Creates turnOrder ArrayList with Character Objects from scratch.
     */
    public TurnOrderView()
    {
        for (int i = 0; i < 8; i++)
            turnOrder.add(new Character("Name[" + i + "]", 1000, 100, 2, 3434, 2, 3, 1));

        setupTurnOrderRecs();

        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    /**
     * Identical to parameterless constructor, except that turnOrder ArrayList with Character Objects is passed as parameter.
     * @param turnOrder
     */
    public TurnOrderView(ArrayList<Character> turnOrder)
    {
        this.turnOrder = turnOrder;

        setupTurnOrderRecs();

        for (TurnOrderRec r : turnOrderRecs)
            this.getChildren().add(r);

        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    /**
     * Moves every TurnOrderRec to it's initial position.
     * @param duration
     */
    public void init(double duration)
    {
        for (TurnOrderRec r : turnOrderRecs)
            r.init(duration);
    }

    /**
     * Moves every TurnOrderRec up one position.
     */
    public void turn()
    {
        for (TurnOrderRec r : turnOrderRecs)
            r.moveUp(1000);
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

    /**
     * Sets the order of the TurnOrderRecs by the sorted turnOrder ArrayList passed to this method.
     * // TODO: Getting Character objects as parameters is unnecessary, since only their speed attribute is relevant.
     * // TODO: Unsorted turnOrder ArrayList could be passed as an argument. Faulty design!
     * // TODO: Update Method to reorganize the order of TurnOrderRecs is necessary.
     * @param turnOrder
     */
    public void setTurnOrder(ArrayList<Character> turnOrder)
    {
        if (turnOrder.size() != 8)
        {
            try
            {
                throw new InvalidTurnOrderException("turnOrder.size() has to be 8.");
            } catch (InvalidTurnOrderException e)
            {
                e.printStackTrace();
            }
            return;
        }
        this.turnOrder = turnOrder;
    }
}
