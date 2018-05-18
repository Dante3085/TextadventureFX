package com.character;

/**
 * Makes it possible to pass
 * @author mjsch
 */
public interface ICallback
{
    /**
     * action-Method must be implemented for each new skill.
     * Describes behaviour of a Character's skill.
     * @param target A skill's action is always being applied to a specific Character.
     */
    void action(Character target);
}
