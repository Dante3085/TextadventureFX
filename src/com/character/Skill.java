package com.character;

/**
 * The Skill enum represents the set of all Character skills.
 * @author mjsch
 */
public enum Skill
{
    HEAVY_SWING(10),
    QUICK_SWING(20),
    BLOODLETTER(30);

    int revengeModifier;

    private Skill(int revengeModifier)
    {
        this.revengeModifier = revengeModifier;
    }

    public int revengeModifier()
    {
        return revengeModifier;
    }
}
