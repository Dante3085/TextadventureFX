package com.ui.gameMenu;

import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * Unites GameMenuElements into a Pane layout that makes thing like uniform Animations easier. Also does other administrative tasks.
 * @author mjsch
 */
public class GameMenu extends VBox // TODO: FlowPane mit Orientation.HORIZONTAL ? Öffent automatisch neue Zeilen.
{
    /**
     * Logger of this class used for logging status, warning and error information to console and / or files.
     */
    private static final Logger m_log = Logger.getLogger(GameMenu.class.getName());

    /**
     * Name of the GameMenu.
     */
    private String m_name;

    /**
     * Determines if this GameMenu is limited in the number of it's GameMenuElements.
     */
    private boolean m_isLimited = false;

    /**
     * If m_isLimited = true , this variable determines how many GameMenuElements the GameMenu can have.
     */
    private int m_maxSize = 0;

    /**
     * Stores TranslateTransitions of GameMenuElements in a way that creates a structural connection between GameMenuElement and it's TranslateTransition.
     */
    private HashMap<String, TranslateTransition>  m_translateTransitions = new HashMap<String, TranslateTransition>();

    public enum AnimType
    {
        NORMAL,
        SEQUENTIAL;
    }

    public class UniformAnimation
    {
        // TODO: From and To are 3 dimensional (x, y, z).
        ArrayList<TranslateTransition> m_translateTransitions;
        Transition m_type;
        double m_from;
        double m_to;
        double m_duration;

        public UniformAnimation(AnimType type, double from, double to, double duration)
        {
            m_from = from;
            m_to = to;
            m_duration = duration;
            m_translateTransitions = new ArrayList<TranslateTransition>();

            if (type == AnimType.NORMAL)
                m_type = null;
            else
        }

        public UniformAnimation(AnimType type, ArrayList<TranslateTransition> translateTransitions, double from, double to, double duration)
        {
            m_translateTransitions = translateTransitions;
            m_from = from;
            m_to = to;
            m_duration = duration;

            // TODO: SCHLECHT. Schleife wiederholt sich.
            if (type == AnimType.SEQUENTIAL)
            {
                m_type = new SequentialTransition();
            }
        }

        public void addElement(TranslateTransition translateTransition, GameMenuElement element)
        {
            Object type = element.getClass();
            if (type.equals(GameMenuButton.class))
                translateTransition.setNode((GameMenuButton)element);
            else if (type.equals(CharacterView.class))
                translateTransition.setNode((GameMenuButton)element);
            else
            {
                GameMenu.m_log.warning("@addElement(" + translateTransition.toString() + ", " + element.toString() + "): The type of passed element '" + type.toString() + "' is not supported." +
                        "\n\t    - Aborted further execution of this method.");
                return;
            }
            translateTransition.setFromX(m_from);
            translateTransition.setToX(m_from);
            translateTransition.setDuration(new Duration(m_duration));
        }
    }

    /**
     * Creates empty {@code GameMenu} with passed name. Empty means no elements.
     * @param name Name of GameMenu.
     */
    public GameMenu(String name)
    {
        m_name = name;
    }

    /**
     * Creates GameMenu with passed name and passed List of Elements.
     * @param name Name of GameMenu.
     * @param elements Initial List of Elements for GameMenu.
     */
    public GameMenu(String name, List<GameMenuElement> elements)
    {
        m_name = name;
        for (GameMenuElement g: elements)
            addElementToLayout(g);
    }

    public void playUniformAnimation()
    {

    }

    public void createUniformAnimation()
    {

    }

/*    *//**
     * Changes the current layout to the passed one
     * <br>If necessary, adjusts elements.
     * @param layout New Layout.
     *//*
    public void setLayout(Pane layout)
    {
        // m_layout = layout;
    }*/

    /**
     * Adds the passed {@code GameMenuElement} to the GameMenu's / Layout's {@code ObservableList} of {@code GameMenuElements}.
     * <br>After that, a new {@code TranslateTransition} is registered in a {@code HashMap} with the GameMenuElement's {@code id} as key.
     * <br>If this {@code GameMenu} 'is limited' and 'maxSize' of GameMenuElements has been reached,
     * this method will only log that fact to the console.
     * @param element Passed GameMenuElement.
     */
    public void addElement(GameMenuElement element)
    {
        if (!isLimited())
        {
            addElementToLayout(element);
        }
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
        {
            getChildren().add((GameMenuButton) element);
            m_translateTransitions.put(element.id(), new TranslateTransition(new Duration(0), (GameMenuButton)element));
        }
        else if (type.equals(CharacterView.class))
        {
            getChildren().add((CharacterView) element);
            m_translateTransitions.put(element.id(), new TranslateTransition(new Duration(0), (CharacterView)element));
        }
        else
            m_log.warning("The type of passed element '" + type.toString() + "' is not supported.");
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
