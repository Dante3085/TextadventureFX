package com.character;

/**
 * The Magic enum represents the set of all Character magic.
 * @author mjsch
 */
public enum Magic
{
    FIRE(5);

    int revengeModifier;

    private Magic(int revengeModifier)
    {
        this.revengeModifier = revengeModifier;
    }

    public int revengeModifier()
    {
        return revengeModifier;
    }
}
