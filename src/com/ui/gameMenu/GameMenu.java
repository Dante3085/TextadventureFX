package com.ui.gameMenu;

import com.Main.Main;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Unites Nodes into a Pane layout that makes thing like uniform Animations easier. Also does other administrative tasks.
 * @author mjsch
 */
public class GameMenu
{
    /**
     * Logger of this class used for logging status, warning and error information to console and / or files.
     */
    private static final Logger m_log = Logger.getLogger(GameMenu.class.getName());

    /**
     * Layout of this GameMenu.
     */
    private Pane m_layout;

    /**
     * Stores GameMenus that are subordiante to this GameMenu.
     */
    private ArrayList<GameMenu> m_subMenus = new ArrayList<>();

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

    // TODO: Vorwärts muss nicht immer collapsing bedeuten. Dasselbe gilt für die backward Transition.
    /**
     * TranslateTransition for moving the GameMenu forward (i.e. collapsing it).
     */
    private TranslateTransition m_ttForward;

    /**
     * TranslateTranistion for moving the GameMenu backward (i.e expanding it).
     */
    private TranslateTransition m_ttBackward;

    // TODO: Vielleicht unnötige Verkapselung. Einfach TranslateTransition direkt zurückgeben in Getter?
    /**
     * Current AnimConfig.
     */
    private AnimConfig m_current;

    /**
     * Determines whether this GameMenu is collapsed or expanded.
     */
    private boolean m_isCollapsed = true;

    /**
     * Stores biggest width value of all GameMenuElements.
     */
    private double m_biggestWidth;

    /**
     * Constructs GameMenu with passed layout and passed name.
     * <br>Also constructs a 'forward' and 'backward' TranslateTransition for the passed layout. Default Duration is 500 ms.
     * @param layout LayoutPane
     * @param name Name
     */
    public GameMenu(Pane layout, String name)
    {
        m_layout = layout;
        m_name = name;

        m_ttForward = new TranslateTransition(Duration.millis(500), m_layout);
        m_ttBackward = new TranslateTransition(Duration.millis(500), m_layout);
    }

    /**
     * Constructs GameMenu with passed layout (Pane), name and List of Nodes. List of Nodes is passed to layout's ObservableList.
     * <br>Also constructs a collapse and expand TranslateTransition for layout. Default Duration is 500 ms.
     * @param layout Layout Pane
     * @param name Name
     * @param elements List of Nodes
     */
    public GameMenu(Pane layout, String name, List<Node> elements)
    {
        m_layout = layout;
        m_layout.getChildren().addAll(elements);
        m_name = name;

        m_ttForward = new TranslateTransition(Duration.millis(500), m_layout);
        m_ttBackward = new TranslateTransition(Duration.millis(500), m_layout);
    }

    public TranslateTransition getTtForward()
    {
        return m_ttForward;
    }

    public TranslateTransition getTtBackward()
    {
        return m_ttBackward;
    }

    public void dockTop()
    {
        m_layout.setTranslateY(0);
    }

    /**
     * Docks this GameMenu to the right edge of the window.
     * <br>Meaning that everytime the window is resized, the GameMenu's position is also updated so that it still is at the right edge.
     */
    public void dockRight()
    {
        // TODO: boolean that signals if new elements was added.
        updateBiggestWidth();
        m_layout.setTranslateX(Main.mainWindow.getWidth() - m_biggestWidth);
        Main.mainWindow.widthProperty().addListener((observable, oldValue, newValue) ->
        {
            m_layout.setTranslateX(newValue.doubleValue() - m_biggestWidth);
        });
    }

    /**
     * Places the GameMenu at the right edge of the window once.
     * <br>Passed offset offsets GameMenu from right edge of window position by it's value.
     * @param offset Offsets GameMenu from right edge position by it's value.
     */
    public void putRight(int offset)
    {
        updateBiggestWidth();
        m_layout.setTranslateX(Main.mainWindow.getWidth() - m_biggestWidth + offset);
    }

    /**
     * Goes through all GameMenuElements and stores width value of broadest Element in "m_biggestWidth".
     */
    public void updateBiggestWidth()
    {
        m_biggestWidth = Double.MIN_VALUE;
        for (Node n : m_layout.getChildren())
        {
            if (n instanceof GameMenuButton)
            {
                if (((GameMenuButton) n).width() > m_biggestWidth)
                    m_biggestWidth = ((GameMenuButton) n).width();
            }
            else
                throw new UnsupportedOperationException();
        }
    }

    public void dockBottom()
    {
        double height = 0;
        for (Node n : m_layout.getChildren())
        {
            if (n instanceof GameMenuButton)
                height += ((GameMenuButton) n).height();
            else
                throw new UnsupportedOperationException();
        }
        m_layout.setTranslateY(Main.mainWindow.getHeight() - height);
    }

    public void dockLeft()
    {
        m_layout.setTranslateX(0);
    }

    /**
     * Expand GameMenu when it's collapsed.
     */
    public void collapse()
    {
        m_ttForward.play();
        m_isCollapsed = true;
    }

    /**
     * Backwards uniformAnimation.
     */
    public void expand()
    {
        m_ttBackward.play();
        m_isCollapsed = false;
    }

    /**
     * Applies the properties of the passed AnimConfig to all TranslateTransitions.
     * @param config Passed AnimConfig.
     */
    public void customizeUniformAnimation(AnimConfig config)
    {
        m_current = config;

        // Set collapse TranslateTransition
        m_ttForward.setDuration(Duration.millis(config.duration));

        m_ttForward.setFromX(config.fromX);
        m_ttForward.setToX(config.toX);

        m_ttForward.setFromY(config.fromY);
        m_ttForward.setToY(config.toY);

        m_ttForward.setFromZ(config.fromZ);
        m_ttForward.setToZ(config.toZ);

        // Set expand TranslateTransition.
        m_ttBackward.setDuration(Duration.millis(config.duration));

        m_ttBackward.setFromX(config.toX);
        m_ttBackward.setToX(config.fromX);

        m_ttBackward.setFromY(config.toY);
        m_ttBackward.setToY(config.fromY);

        m_ttBackward.setFromZ(config.toZ);
        m_ttBackward.setToZ(config.fromZ);
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
                addElementToLayout(element, m_layout);
        else
        {
            if (m_layout.getChildren().size() >= m_maxSize)
            {
                m_log.warning("@addElement(" + element.toString() + "): The GameMenu '" + m_name + "' has reached it's limit of '" + m_maxSize + "' elements." +
                        "\n\t     - Aborted further execution of this method.");
            }
            else
                addElementToLayout(element, m_layout);
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

        // Register new collapse and expand TranslateTransition for new element. // TODO: Register TranslateTransition for each new GameMenuElement.
        /*TranslateTransition collapse = new TranslateTransition(new Duration(1000), element);
        collapse.setFromX(element.getTranslateX());
        collapse.setToX(element.getTranslateX() + 300);
        m_ptForward.getChildren().add(collapse);

        TranslateTransition expand = new TranslateTransition(new Duration(1000), element);
        expand.setFromX(element.getTranslateX() + 300);
        expand.setToX(element.getTranslateX());
        m_ptBackwards.getChildren().add(expand);*/
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
     * Returns layout of this GameMenu.
     * @return layout
     */
    public Pane layout()
    {
        return m_layout;
    }

    /**
     * Returns ArrayList with GameMenus that are subordinate to this GameMenu.
     * @return SubMenus.
     */
    public ArrayList<GameMenu> subMenus()
    {
        return m_subMenus;
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

        for (int i = 0; i < m_layout.getChildren().size(); i++)
        {
            if (i == m_layout.getChildren().size() - 1)
                builder.append(((GameMenuElement)m_layout.getChildren().get(i)).id());
            else
            {
                builder.append(((GameMenuElement)m_layout.getChildren().get(i)).id());
                builder.append(", ");
            }
        }

        builder.append("]");

        return builder.toString();
    }
}
