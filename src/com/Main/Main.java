package com.Main;

import com.character.Character;
import com.combat.Combat;
import com.ui.*;
import com.ui.gameMenu.*;
import com.ui.combat.turnOrderView.TurnOrderView;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
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

import java.util.ArrayList;
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

    private GameMenu mainMenu;

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

        mainMenu = GameMenuBuilder.buildMainMenu();
        // Add mainMenu layout and all layouts of mainMenus subMenus.
        root.getChildren().addAll(mainMenu.layout());
        for (GameMenu g : mainMenu.subMenus())
            root.getChildren().add(g.layout());
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
                    mainMenu.collapse();
                    break;
                }

                case ALT:
                {
                    mainMenu.expand();
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
