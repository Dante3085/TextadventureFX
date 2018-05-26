package com.ui.combat.turnOrderView;

/**
 * Enum that defines all the positions that a TurnOrderRec can be in.
 * @author mjsch
 */
public enum Position
{
    FIRST(0),
    SECOND(100, FIRST),
    THIRD(200, SECOND),
    FOURTH(300, THIRD),
    FIFTH(400, FOURTH),
    SIXTH(500, FIFTH),
    SEVENTH(600, SIXTH),
    EIGHTH(700, SEVENTH),

    DEFAULT(800);

    private double numVal;
    private Position next;

    private Position(double numVal)
    {
        this.numVal = numVal;
    }

    /**
     * Enum constructors have to be private.
     * They are only used within this block.
     * Internally initializes numVal with Constructor for every enum value. (Look above)
     * @param numVal int Value used by enums.
     */
    private Position(double numVal, Position next)
    {
        this.numVal = numVal;
        this.next = next;
    }

    /**
     * Returns next position of cycle.
     * @return
     */
    public Position next()
    {
        if (this.equals(FIRST))
            return EIGHTH;
        if (this.equals(DEFAULT))
            return FIRST;
        return next;
    }

    /**
     * Returns numerical value of position.
     * @return
     */
    public double value()
    {
        return numVal;
    }
}
