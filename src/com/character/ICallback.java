package com.character;

/**
 * @author mjsch
 */
public interface ICallback
{
    /**
     * action-Method must be implemented for each new action (item, magic or skill)
     * Describes behaviour of a Character's action
     * @param target An action's effect is always being applied to a specific Character.
     */
    void action(Character target);
}
