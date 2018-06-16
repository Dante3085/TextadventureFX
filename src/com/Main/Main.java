package com.Main;

import com.character.Character;
import com.character.Skill;
import com.combat.Combat;
import com.ui.*;
import com.ui.gameMenu.*;
import com.ui.combat.turnOrderView.TurnOrderView;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Level;
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

    // GameMenu mainMenu = new GameMenu("MainMenu");
    GameMenu menu = new GameMenu("menu1");
    GameMenu menu2 = new GameMenu("menu2");

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
        setupMainMenu      ();
        // setupMenuAnimations();
        setupSceneKeys     ();
        setupFpsView       ();
        initWindow         ();
        setupConsole       ();

        menu.addElement(new GameMenuButton("Menu1_Button1", 100, 50));
        menu.addElement(new GameMenuButton("Menu1_Button2", 100, 50));
        menu.addElement(new GameMenuButton("Menu1_Button3", 100, 50));
        menu.addElement(new GameMenuButton("Menu1_Button4", 100, 50));
        menu.addElement(new GameMenuButton("Menu1_Button5", 100, 50));
        menu.addElement(new GameMenuButton("Menu1_Button6", 100, 50));
        menu.setTranslateX(500);
        menu.setTranslateY(200);

        AnimConfig config = new AnimConfig("Test");
        config.duration = 500;
        config.fromX = 1;
        config.toX = 500;
        menu.customizeUniformAnimation(config);

        ((GameMenuButton)menu.getElement("Menu1_Button1")).setOnMouseClicked(event ->
        {
            console.writeToConsole(config.toString());
            console.writeToConsole(menu.toString());
        });

        root.getChildren().addAll(turnOrderView, menu);
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
                    menu.playUniformAnimation(AnimType.PARALLEL);
                    menu2.playUniformAnimation(AnimType.SEQUENTIAL);
                }

                case CIRCUMFLEX:
                {

                }
            }
        });
    }

    /**
     * Assembles Textadventure's main menu with all the buttons and their functionality. Menus are represented by instances of the 'GameMenu' class.
     */
    private void setupMainMenu()
    {

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
        FPS_View fps_view = new FPS_View();
        root.getChildren().add(fps_view);
    }
}
