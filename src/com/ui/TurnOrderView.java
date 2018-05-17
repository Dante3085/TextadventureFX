package com.ui;

import com.Main.Main;
import com.character.Character;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class TurnOrderView extends StackPane
{
    private boolean undo = false;

    private ArrayList<Character> turnOrder = new ArrayList<>();
    private ArrayList<Rectangle> turnOrderRecs = new ArrayList<>();
    //private ArrayList<Text> turnOrderRecsText; // TODO: Put text on turnOrderRecs.
    //private ArrayList<TranslateTransition> turnOrderRecsAnimations;

    private TranslateTransition t1 = new TranslateTransition();
    private TranslateTransition t2 = new TranslateTransition();
    private TranslateTransition t3 = new TranslateTransition();
    private TranslateTransition t4 = new TranslateTransition();
    private TranslateTransition t5 = new TranslateTransition();
    private TranslateTransition t6 = new TranslateTransition();
    private TranslateTransition t7 = new TranslateTransition();
    private TranslateTransition t8 = new TranslateTransition();

    public TurnOrderView()
    {

        for (int i = 0; i < 8; i++)
        {
            turnOrderRecs.add(new Rectangle(50, 50));
            /*turnOrderRecsAnimations.add(new TranslateTransition());*/
        }

        int c = 0;
        for (Rectangle r : turnOrderRecs)
        {
            r.setFill(Color.RED);
            r.setOpacity(0.6);
            r.setTranslateY(c);
            c += 100;
            this.getChildren().add(r);
        }

        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    /**
     * Changes the visual appearance after one turn. Every Rectangle is going to move one step up in the cycle.
     */
    private void turn()
    {
        // TODO: Move each rectangle one position up. If position is 'FIRST', move rectangle to 'EIGHTH'.
        // TODO: As of know it is not possible to iterate through all turnOrderRecs with enhanced for loop and move them up one position. This is a deficiency in the implementation.
    }

    private void setInitialOrder()
    {
        moveRectangle(turnOrderRecs.get(0), t1, Position.FIRST, 1);
        moveRectangle(turnOrderRecs.get(1), t2, Position.SECOND, 1);
        moveRectangle(turnOrderRecs.get(2), t3, Position.THIRD, 1);
        moveRectangle(turnOrderRecs.get(3), t4, Position.FOURTH, 1);
        moveRectangle(turnOrderRecs.get(4), t5, Position.FIFTH, 1);
        moveRectangle(turnOrderRecs.get(5), t6, Position.SIXTH, 1);
        moveRectangle(turnOrderRecs.get(6), t7, Position.SEVENTH, 1);
        moveRectangle(turnOrderRecs.get(7), t8, Position.EIGHTH, 1);
    }

    // TODO: Delete method 'test()' after testing.
    public void test()
    {
        if (!undo)
        {
            moveRectangle(turnOrderRecs.get(0), t1, Position.EIGHTH, 1);
            moveRectangle(turnOrderRecs.get(1), t2, Position.FIRST, 1);
            moveRectangle(turnOrderRecs.get(2), t3, Position.SECOND, 1);
            moveRectangle(turnOrderRecs.get(3), t4, Position.THIRD, 1);
            moveRectangle(turnOrderRecs.get(4), t5, Position.FOURTH, 1);
            moveRectangle(turnOrderRecs.get(5), t6, Position.FIFTH, 1);
            moveRectangle(turnOrderRecs.get(6), t7, Position.SIXTH, 1);
            moveRectangle(turnOrderRecs.get(7), t8, Position.SEVENTH, 1);
            undo = true;
            return;
        }
        moveRectangle(turnOrderRecs.get(0), t1, Position.FIRST, 1);
        moveRectangle(turnOrderRecs.get(1), t2, Position.SECOND, 1);
        moveRectangle(turnOrderRecs.get(2), t3, Position.THIRD, 1);
        moveRectangle(turnOrderRecs.get(3), t4, Position.FOURTH, 1);
        moveRectangle(turnOrderRecs.get(4), t5, Position.FIFTH, 1);
        moveRectangle(turnOrderRecs.get(5), t6, Position.SIXTH, 1);
        moveRectangle(turnOrderRecs.get(6), t7, Position.SEVENTH, 1);
        moveRectangle(turnOrderRecs.get(7), t8, Position.EIGHTH, 1);
        undo = false;
    }

    /**
     * Moves a Rectangle instance to the passed Position 'pos' in 'duration' seconds.
     * @param r Passed Rectangle instance that is going to be moved.
     * @param pos Position that the Rectangle instance is going to be moved to. Specified by private enum 'Position'.
     * @param duration Duration of move animation.
     */
    private void moveRectangle(Rectangle r, TranslateTransition animation, Position pos, int duration)
    {
        animation.setNode(r);
        animation.setFromY(r.getTranslateY());
        animation.setToY(pos.numVal);
        animation.setDuration(new Duration(duration * 1000));
        animation.play();
    }

    public void setTurnOrder(ArrayList<Character> turnOrder)
    {
        if (turnOrder.size() != 8)
            return;

    }

    private enum Position
    {
        FIRST(0),
        SECOND(100),
        THIRD(200),
        FOURTH(300),
        FIFTH(400),
        SIXTH(500),
        SEVENTH(600),
        EIGHTH(700),
        TEST(900);

        private int numVal;

        /**
         * Enum constructors have to be private.
         * They are only used within this block.
         * Internally initializes numVal with Constructor for every enum value. (Look above)
         * @param numVal int Value used by enums.
         */
        private Position(int numVal)
        {
            this.numVal = numVal;
        }
    }
}
