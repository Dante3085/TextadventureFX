package com.ui.gameMenu;

import javafx.scene.Node;

public interface GameMenuElement
{
    /**
     * Every GameMenuElement needs to be able to return some form of id. This id should be immutable.
     * @return id.
     */
    String id();
}
