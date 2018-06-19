package com.Main;

import com.character.Character;
import com.ui.*;
import com.ui.gameMenu.*;
import com.ui.combat.turnOrderView.TurnOrderView;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Logger;

/**
 * Main Class of Textadventure.
 * @author mjsch
 */
public class Main extends Application
{
    public static Stage mainWindow;
    private Pane root = new Pane();
    private Scene scene;
    private ImageView imgView;

    private final int offset = 1900;

    private GameMenu mainMenu = new GameMenu(new AnchorPane(), "mainMenu");
    private GameMenu optionsMenu = new GameMenu(new TilePane(),"optionsMenu");

    TextArea ta;
    public static Console console;

    Character Auron;
    Character Tidus;

    boolean gmbsVisible = true;

    Line line;

    TurnOrderView turnOrderView = new TurnOrderView();

    private static final Logger m_log = Logger.getLogger("Logger");

    public static void main(String[] args)
    {
        launch(args);
    }

    // I decided to outsource all the parts of window assemblement to seperate methods. This makes it easy to read the program's flow in the start() method.
    // Sichtbare Elemente werden anscheinend übereinander gepackt. Werden die Buttons zuerst erstellt und danach das Hintergrundbild, so werden die Buttons von dem Bild überdeckt.
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainWindow = primaryStage;
        scene = new Scene(root);

        setupImages        ();
        setupSceneKeys     ();
        setupFpsView       ();
        initWindow         ();
        setupConsole       ();
        setupMainMenu      ();

        root.getChildren().addAll(mainMenu.layout(), optionsMenu.layout());
    }

    /**
     * Assembles Textadventure's main mainMenu with all the buttons and their functionality. Menus are represented by instances of the 'GameMenu' class.
     */
    private void setupMainMenu()
    {
        AnimConfig config = new AnimConfig("config");
        config.toX = 250;
        config.duration = 250;

        ObservableList<Node> children;

        /*optionsMenu.setIsLimited(false);
        optionsMenu.addElement(new GameMenuButton("BACK", 200, 50   ));
        optionsMenu.addElement(new GameMenuButton("Option1", 200, 50));
        optionsMenu.addElement(new GameMenuButton("Option2", 200, 50));

        children = optionsMenu.layout().getChildren();

        children.get(0).setOnMouseClicked(event ->
        {
            optionsMenu.forward();
            mainMenu.backward();
        });

        //optionsMenu.dockRight();
        optionsMenu.customizeUniformAnimation(config);
        optionsMenu.forward();*/

        mainMenu.setIsLimited(false);
        mainMenu.addElement(new GameMenuButton("Resume", 200, 50   ));
        mainMenu.addElement(new GameMenuButton("Level Up", 200, 50 ));
        mainMenu.addElement(new GameMenuButton("Items", 200, 50    ));
        mainMenu.addElement(new GameMenuButton("Abilities", 200, 50));
        mainMenu.addElement(new GameMenuButton("Equip", 200, 50    ));
        mainMenu.addElement(new GameMenuButton("Status", 200, 50   ));
        mainMenu.addElement(new GameMenuButton("Options", 200, 50  ));
        mainMenu.addElement(new GameMenuButton("Exit", 200, 50     ));

        mainMenu.layout().setTranslateX(500);

        children = mainMenu.layout().getChildren();

        children.get(6).setOnMouseClicked(event ->
        {
            mainMenu.forward();
            optionsMenu.backward();
        });

        children.get(7).setOnMouseClicked(event ->
        {
            System.exit(1);
        });

        //mainMenu.dockRight();
        mainMenu.customizeUniformAnimation(config);
    }

    public void addLine(double x, double y)
    {
        line = new Line(x, y, x, y + 900);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }


    public void lineAnimationExpandCollapse(int expand, double seconds)
    {
        ScaleTransition st = new ScaleTransition(Duration.seconds(seconds), line);
        if (expand == 1)
        {
            st.setToY(1);
            st.setToX(1);
            st.play();
        }

        else if (expand == 0)
        {
            st.setToY(0);
            st.setToX(1);
            st.play();
        }
    }

    private void setupSceneKeys()
    {
        scene.setOnKeyPressed(event ->
        {
            switch (event.getCode())
            {
                case ESCAPE:
                {
                    mainMenu.forward();
                    break;
                }

                case ALT:
                {
                    mainMenu.backward();
                    break;
                }
            }
        });
    }

    private void setupImages()
    {
        imgView = new ImageView(new Image(getClass().getResource("../res/DarkSpace.jpg").toExternalForm())); // Muss mit ".." eine Ordnerebene höher gehen, um res zu finden.
        root.getChildren().add(imgView);
    }

    /**
     * Assembles mainWindow components and shows mainWindow.
     */
    private void initWindow()
    {
        mainWindow.setScene(scene);
        mainWindow.setTitle("Textadventure - The Game");
        mainWindow.setFullScreen(true);
        mainWindow.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        mainWindow.show();
    }

    private void setupConsole()
    {
        ta = new TextArea();
        console = new Console(ta);
        root.getChildren().add(ta);
    }

    private void setupFpsView()
    {
        FPSView fps_view = new FPSView();
        root.getChildren().add(fps_view);
    }
}
