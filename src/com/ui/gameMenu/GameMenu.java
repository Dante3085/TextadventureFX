package com.ui.gameMenu;

import com.Main.Main;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
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
     * Stores TranslateTransitions for GameMenuElement Animation.
     */
    private ArrayList<TranslateTransition> m_translateTransitions = new ArrayList<TranslateTransition>();

    /**
     * ParallelTransition for {@code uniformTransition}.
     */
    private ParallelTransition m_parallelTransition = new ParallelTransition();

    /**
     * SequentialTransition for {@code uniformTransition}.
     */
    private SequentialTransition m_sequentialTransition = new SequentialTransition();

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
            if (g instanceof Node)
                getChildren().add((Node) g);
    }

    public void dockTop()
    {
        this.setTranslateY(0);
    }

    public void dockRight()
    {

    }

    public void dockBottom()
    {
        /*double height = 0;
        for (Node g : getChildren())
        {
            if (g instanceof GameMenuButton)
                height += ((GameMenuButton) g).width();
        }
        this.setTranslateY(Main.mainWindow.getHeight() - 200);*/
    }

    public void dockLeft()
    {

    }

    /**
     * // TODO: JAVADOC
     * @param type
     */
    public void playUniformAnimation(AnimType type)
    {
        switch (type)
        {
            case SEQUENTIAL:
            {
                m_sequentialTransition.play();
                break;
            }

            case PARALLEL:
            {
                m_parallelTransition.play();
                break;
            }
        }
    }

    /**
     * Applies the properties of the passed AnimConfig to all TranslateTransitions.
     * @param config Passed AnimConfig.
     */
    public void customizeUniformAnimation(AnimConfig config)
    {
        // TODO: Sind die TranslateTransitions in der SequentialTransition gleich zu denen in der ParallelTransition ?
        if (m_sequentialTransition.getChildren().isEmpty())
        {
            m_log.warning("@customizeUniformAnimation(");
        }

        for (Animation a : m_sequentialTransition.getChildren())
        {
            TranslateTransition t = (TranslateTransition) a;
            t.setDuration(new Duration(config.duration));
            t.setFromX(config.fromX);
            t.setToX(config.toX);
            t.setFromY(config.fromY);
            t.setToY(config.toY);
            t.setFromZ(config.fromZ);
            t.setToZ(config.toZ);
        }
    }

    /**
     * Adds the passed {@code GameMenuElement} to the GameMenu's / Layout's {@code ObservableList} of {@code GameMenuElements}.
     * Also creates TranslateTransition with element as Node and adds that TranslateTransition to this GameMenu's SequentialTransition and ParallelTransition.
     * <br>If this {@code GameMenu} 'is limited' and 'maxSize' of GameMenuElements has been reached,
     * this method will only log that fact to the console.
     * @param element Passed GameMenuElement.
     */
    public void addElement(GameMenuElement element)
    {
        if (!isLimited())
                addElementToLayout(element, this);
        else
        {
            if (getChildren().size() >= m_maxSize)
            {
                m_log.warning("@addElement(" + element.toString() + "): The GameMenu '" + m_name + "' has reached it's limit of '" + m_maxSize + "' elements." +
                        "\n\t     - Aborted further execution of this method.");
            }
            else
                addElementToLayout(element, this);
        }
    }

    /**
     * Adds element to ObservableList of passed layout.
     * <br>Also registers a TranslateTransitions for that element and adds it to this GameMenu's SequentialTransition and ParallelTransition.
     * <br>Default for TranslateTransitions: from TranslateX to TranslateX + 300.
     * @param element Element to add to layout.
     * @param layout Layout to add element to.
     */
    private void addElementToLayout(GameMenuElement element, Pane layout)
    {
        if (element instanceof Node)
        {
            layout.getChildren().add((Node) element);
            TranslateTransition t = new TranslateTransition(new Duration(1000), (Node) element);
            t.setFromX(((Node) element).getTranslateX());
            t.setToX(((Node) element).getTranslateX() + 300);
            m_parallelTransition.getChildren().add(t);
            m_sequentialTransition.getChildren().add(t);
        }
    }

    /**
     * // TODO: JAVADOC
     * // TODO: Get methods might be obsolete, since getChildren() is available.
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
        for (Node g : getChildren())
            if (((GameMenuElement)g).id().equals(id))
                return (GameMenuElement) g;
        m_log.warning("@getElement(" + id + "): GameMenu '" + m_name + "' does not contain an element with passed id.");
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
            if (i == getChildren().size() - 1)
                builder.append(((GameMenuElement)getChildren().get(i)).id());
            else
            {
                builder.append(((GameMenuElement)getChildren().get(i)).id());
                builder.append(", ");
            }
        }

        builder.append("]");

        return builder.toString();
    }
}
