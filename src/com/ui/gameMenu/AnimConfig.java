package com.ui.gameMenu;

/**
 * Struct like class to encapsulate Parameters for configurating GameMenu's uniform Animations.
 */
public class AnimConfig
{
    private final String m_name;
    public int duration;
    public double fromX, fromY, fromZ;
    public double toX, toY, toZ;

    public AnimConfig(String name)
    {
        m_name = name;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName());
        builder.append(" ");
        builder.append(m_name);
        builder.append("[");

        builder.append(Integer.toString(duration));
        builder.append(", ");
        builder.append(Double.toString(fromX));
        builder.append(", ");
        builder.append(Double.toString(fromY));
        builder.append(", ");
        builder.append(Double.toString(fromZ));
        builder.append(", ");
        builder.append(Double.toString(toX));
        builder.append(", ");
        builder.append(Double.toString(toY));
        builder.append(", ");
        builder.append(Double.toString(toZ));

        builder.append("]");

        return builder.toString();
    }
}