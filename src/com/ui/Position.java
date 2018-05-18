package com.ui;

/**
 * Enum that defines all the positions that a TurnOrderRec can be in.
 * @author mjsch
 */
public enum Position
{
    FIRST(0, "FIRST"),
    SECOND(100, FIRST),
    THIRD(200, SECOND),
    FOURTH(300, THIRD),
    FIFTH(400, FOURTH),
    SIXTH(500, FIFTH),
    SEVENTH(600, SIXTH),
    EIGHTH(700, SEVENTH),

    DEFAULT(1000, "DEFAULT");

    private int numVal;
    private String current;
    private Position next;

    private Position(int numVal, String current)
    {
        this.numVal = numVal;
        this.current = current;
    }

    /**
     * Enum constructors have to be private.
     * They are only used within this block.
     * Internally initializes numVal with Constructor for every enum value. (Look above)
     * @param numVal int Value used by enums.
     */
    private Position(int numVal, Position next)
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
        if (current != null)
        {
            if (current.equals("FIRST"))
                return EIGHTH;
        }
        return next;
    }

    /**
     * Returns numerical value of position.
     * @return
     */
    public int value()
    {
        return numVal;
    }
}
