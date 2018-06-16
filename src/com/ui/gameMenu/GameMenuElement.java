package com.ui.gameMenu;

public interface GameMenuElement
{
    public static int id = 0;

    /**
     *
     * @return
     */
    String id();

    /**
     * Returns height of this GameMenuElement.
     * @return height
     */
    double height();

    /**
     * Returns width of this GameMenuElement.
     * @return width
     */
    double width();
}
