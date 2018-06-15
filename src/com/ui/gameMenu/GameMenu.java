package com.ui.gameMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * Describes what a menu is in the game.
 * @author mjsch
 */
public class GameMenu extends VBox // TODO: FlowPane mit Orientation.HORIZONTAL ? Öffent automatisch neue Zeilen.
{
    private static final Logger m_log = Logger.getLogger(GameMenu.class.getName());

    private String m_name;
    private boolean m_isLimited = false;
    private int m_maxSize = 0;
    // private ObservableList<GameMenuElement> m_elements;
    // private Pane m_layout;

    /**
     * Creates empty GameMenu with passed name. Empty means no elements.
     * <br>Structure of GameMenu will be decided by passed layout.
     * @param name Name of GameMenu.
     // * @param layout Layout in which GameMenu will structure it's elements.
     */
    public GameMenu(String name/*, Pane layout*/)
    {
        m_name = name;
        this.setSpacing(5);
       //  m_elements = FXCollections.observableArrayList();
        // m_layout = layout;
    }

    /**
     * Creates GameMenu with passed name and passed List of Elements.
     * @param name Name of GameMenu.
     * @param elements Initial List of Elements for GameMenu.
     // * @param layout Layout in which GameMenu will structure it's elements.
     */
    public GameMenu(String name, List<GameMenuElement> elements/*, Pane layout*/)
    {
        m_name = name;
        // m_elements = FXCollections.observableArrayList(elements);
        for (GameMenuElement g: elements)
            addElementToLayout(g);
    }

    /**
     * Changes the current layout to the passed one
     * <br>If necessary, adjusts elements.
     * @param layout New Layout.
     */
    public void setLayout(Pane layout)
    {
        // m_layout = layout;
    }

    /**
     * Adds the passed GameMenuElement to the GameMenu's ObservableList of GameMenuElements.
     * <br>If this GameMenu 'is limited' and 'maxSize' of GameMenuElements has been reached,
     * this method will only log that fact to console.
     * @param element Passed GameMenuElement.
     */
    public void addElement(GameMenuElement element)
    {
        if (!isLimited())
            addElementToLayout(element);
        else
        {
            if (getChildren().size() >= m_maxSize)
            {
                m_log.warning("@addElement(" + element.toString() + "): The GameMenu '" + m_name + "' has reached it's limit of '" + m_maxSize + "' elements." +
                        "\n\t     - Aborted further execution of this method.");
                return;
            }
            addElementToLayout(element);
        }
    }

    /**
     * Help-Method for addElement. Encapsulates safe typecasting logic.
     * @param element Element to add to layout.
     */
    private void addElementToLayout(GameMenuElement element)
    {
        // TODO: Das Casting deutet darauf hin, dass die Typhierarchie nicht richtig durchdacht ist.
        Object type = element.getClass();
        if (type.equals(GameMenuButton.class))
            getChildren().add((GameMenuButton)element);
        else if (type.equals(CharacterView.class))
            getChildren().add((CharacterView)element);
        else
            m_log.warning("The type '" + type.toString() + "' is not supported.");
    }

    /**
     * // TODO: JAVADOC
     * @param index
     * @return
     */
    public GameMenuElement getElement(int index)
    {
        if (index < 0 || index > getChildren().size() - 1)
        {
            m_log.warning("@getElement(" + index + "): The passed index is invalid." +
                    "\n\t     - Aborted further execution of this method.");
            return null;
        }

        return (GameMenuElement) getChildren().get(index);
    }

    /**
     * Looks for a GameMenuElement by it's id and if existent in this GameMenu, returns it.
     * @param id Identifier for GameMenuElement to return.
     * @return GameMenuElement to find and return.
     */
    public GameMenuElement getElement(final String id)
    {
        /*for (GameMenuElement g : getChildren())
            if (g.id().equals(id))
                return g;
        m_log.warning("@getElement(" + id + "): GameMenu '" + m_name + "' does not contain an element with passed id.");*/
        return null;
    }

    /**
     * Checks if number of elements in this GameMenu is limited.
     * <br>This method is necessary to encapsulate the logic that if maxSize is -1, there is no limit to the number of elements.
     * @return True if max number of elements is smaller or equal to 0. Otherwise false.
     */
    public boolean isLimited()
    {
        return m_isLimited;
    }

    /**
     * Sets if this GameMenu is limited in the number of it's elements.
     * @param isLimited True, GameMenu is limited in the number of it's elements. False, GameMenu is not limited in the number of it's elements.
     */
    public void setIsLimited(boolean isLimited)
    {
        m_isLimited = isLimited;
    }

    /**
     * Returns maximum possible number of Elements in this GameMenu.
     * @return Maximum possible number of Elements in this GameMenu.
     */
    public int maxSize()
    {
        return m_maxSize;
    }

    /**
     * Sets maximum possible number of Elements in this GameMenu.
     * @param maxSize New maximum possible number of Elements in this GameMenu.
     */
    public void setMaxSize(int maxSize)
    {
        m_maxSize = maxSize;
    }

    /**
     * Returns a String with all there is to know about the GameMenu. // TODO: JAVADOC
     * @return String with information.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName());
        builder.append(" ");
        builder.append(m_name);
        builder.append("[");

        for (int i = 0; i < getChildren().size(); i++)
        {
            /*if (i == getChildren().size() - 1)
                builder.append(((GameMenuElement)getChildren().get(i)).id);
            else
            {
                builder.append(getChildren().get(i).id());
                builder.append(", ");
            }*/
        }

        builder.append("]");

        return builder.toString();
    }
}
