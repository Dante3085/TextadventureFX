package com.ui.gameMenu;

import com.Main.Main;
import javafx.animation.*;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Unites Nodes into a Pane layout that makes thing like uniform Animations easier. Also does other administrative tasks.
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
     * Determines if this GameMenu is limited in the number of it's Nodes.
     */
    private boolean m_isLimited = false;

    /**
     * If m_isLimited = true , this variable determines how many Nodes the GameMenu can have.
     */
    private int m_maxSize = 0;

    /**
     * Stores TranslateTransitions for forward uniformAnimation.
     */
    private ArrayList<TranslateTransition> m_ttForward = new ArrayList<TranslateTransition>();

    /**
     * Stores TranslateTransitions for backwards uniformAnimation.
     */
    private ArrayList<TranslateTransition> m_ttBackwards = new ArrayList<TranslateTransition>();

    /**
     * ParallelTransition for forward {@code uniformAnimation}.
     */
    private ParallelTransition m_ptForward = new ParallelTransition();

    /**
     * ParallelTransition for backwards {@code uniformAnimation}.
     */
    private ParallelTransition m_ptBackwards = new ParallelTransition();

    /**
     * Current AnimConfig.
     */
    private AnimConfig m_current;

    /**
     * Determines whether this GameMenu is collapsed or expanded.
     */
    private boolean m_isCollapsed = true;

    /**
     * Creates empty {@code GameMenu} with passed name. Empty means no elements.
     * @param name Name of GameMenu.
     */
    public GameMenu(String name)
    {
        setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);

        m_name = name;
    }

    /**
     * Creates GameMenu with passed name and passed List of Elements.
     * @param name Name of GameMenu.
     * @param elements Initial List of Elements for GameMenu.
     */
    public GameMenu(String name, List<Node> elements)
    {
        setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);

        m_name = name;
        for (Node g: elements)
                getChildren().add(g);
    }

    public void dockTop()
    {
        this.setTranslateY(0);
    }

    public void dockRight()
    {
        double biggestWidth = Double.MIN_VALUE;
        for (Node n : getChildren())
        {
            if (n instanceof GameMenuButton)
            {
                if (((GameMenuButton) n).width() > biggestWidth)
                    biggestWidth = ((GameMenuButton) n).width();
            }
            else
                throw new UnsupportedOperationException();
        }
        this.setTranslateX(Main.mainWindow.getWidth() - biggestWidth);
    }

    public void dockBottom()
    {
        double height = 0;
        for (Node n : getChildren())
        {
            if (n instanceof GameMenuButton)
                height += ((GameMenuButton) n).height();
            else
                throw new UnsupportedOperationException();
        }
        this.setTranslateY(Main.mainWindow.getHeight() - height);
    }

    public void dockLeft()
    {
        this.setTranslateX(0);
    }

    /**
     * Expand GameMenu when it's collapsed.
     */
    public void forward()
    {
        m_ptForward.play();
        m_isCollapsed = true;
    }

    /**
     * Backwards uniformAnimation.
     */
    public void backward()
    {
        m_ptBackwards.play();
        m_isCollapsed = false;
    }

    /**
     * Applies the properties of the passed AnimConfig to all TranslateTransitions.
     * @param config Passed AnimConfig.
     */
    public void customizeUniformAnimation(AnimConfig config)
    {
        m_current = config;
        // TODO: Sind die TranslateTransitions in der SequentialTransition gleich zu denen in der ParallelTransition ?
        if (m_ptForward.getChildren().isEmpty())
            m_log.warning("@customizeUniformAnimation(" + config.toString() +"): There are no Animations.");

        for (Animation a : m_ptForward.getChildren())
        {
            TranslateTransition forward = (TranslateTransition) a;
            forward.setDuration(Duration.millis(config.duration));

            forward.setFromX(config.fromX);
            forward.setToX(config.toX);

            forward.setFromY(config.fromY);
            forward.setToY(config.toY);

            forward.setFromZ(config.fromZ);
            forward.setToZ(config.toZ);
        }

        for (Animation a : m_ptBackwards.getChildren())
        {
            TranslateTransition backward = (TranslateTransition) a;
            backward.setDuration(Duration.millis(config.duration));

            // For backwards Animation 'to' and 'from' are reversed.
            backward.setFromX(config.toX);
            backward.setToX(config.fromX);

            backward.setFromY(config.toY);
            backward.setToY(config.fromY);

            backward.setFromZ(config.toZ);
            backward.setToZ(config.fromZ);
        }
    }

    /**
     * Adds the passed {@code Node} to the GameMenu's / Layout's {@code ObservableList} of {@code Nodes}.
     * Also creates TranslateTransition with element as Node and adds that TranslateTransition to this GameMenu's SequentialTransition and ParallelTransition.
     * <br>If this {@code GameMenu} 'is limited' and 'maxSize' of Nodes has been reached,
     * this method will only log that fact to the console.
     * @param element Passed Nodes.
     */
    public void addElement(Node element)
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
    private void addElementToLayout(Node element, Pane layout)
    {
        if (element == null)
        {
            m_log.severe("@addElementToLayout(" + element.toString() + ", " + layout.toString() + "): Passed element was null. Abort further execution.");
            return;
        }

        // Add element to GameMenu VBox.
        layout.getChildren().add(element);

        // Register new forward and backward TranslateTransition for new element.
        TranslateTransition forward = new TranslateTransition(new Duration(1000), element);
        forward.setFromX(element.getTranslateX());
        forward.setToX(element.getTranslateX() + 300);
        m_ptForward.getChildren().add(forward);

        TranslateTransition backward = new TranslateTransition(new Duration(1000), element);
        backward.setFromX(element.getTranslateX() + 300);
        backward.setToX(element.getTranslateX());
        m_ptBackwards.getChildren().add(backward);
    }

    /**
     * Looks for a GameMenuElement by it's id and if existent in this GameMenu, returns it.
     * @param id Identifier for GameMenuElement to return.
     * @return GameMenuElement to find and return.
     */
/*    public Node getElement(final String id)
    {
        for (Node n : getChildren())
        {
            if (n instanceof GameMenuElement)
            {
                if (((GameMenuElement) n).hash().equals(id))
                    return n;
            }
            else
                m_log.info("@getElement(" + id + "): Node '" + n.toString() + " is not a GameMenuElement.");
        }
        m_log.warning("@getElement(" + id + "): GameMenu '" + m_name + "' does not contain a Node with passed id.");
        return null;
    }*/

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
