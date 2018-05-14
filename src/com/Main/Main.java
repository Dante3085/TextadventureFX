package com.Main;

/**
 *
 */

import com.character.Character;
import com.character.Skill;
import com.ui.Console;
import com.ui.GameMenu;
import com.ui.GameMenuButton;
import javafx.animation.ScaleTransition;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author mjsch
 * Main Class of Textadventure.
 */
public class Main extends Application
{
    public static Stage mainWindow;
    private Pane root = new Pane();
    private Scene scene = new Scene(root);
    private ImageView imgView;

    GameMenu mainMenu = new GameMenu("MainMenu");

    TextArea ta;
    public static Console console;

    Character Auron;
    Character Tidus;

    boolean gmbsVisible = true;

    Line line;

    public static void main(String[] args)
    {
        launch(args);
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

    public void setupTranslateTransitions()
    {
//        tt_gmbResume.setToX(-30);
//        tt_gmbLvlUp.setToX(-30);
//        tt_gmbItems.setToX(-30);
//        tt_gmbAbilities.setToX(-30);
//        tt_gmbEquip.setToX(-30);
//        tt_gmbStatus.setToX(-30);
//        tt_gmbOptions.setToX(-30);
//        tt_gmbExit.setToX(-30);
    }

    public void gameMenuExpandCollapse(String action)
    {
//        if (action.equals("expand"))
//        {
//            tt_gmbResume.setToX(-30);
//            tt_gmbLvlUp.setToX(-30);
//            tt_gmbItems.setToX(-30);
//            tt_gmbAbilities.setToX(-30);
//            tt_gmbEquip.setToX(-30);
//            tt_gmbStatus.setToX(-30);
//            tt_gmbOptions.setToX(-30);
//            tt_gmbExit.setToX(-30);
//
//            /*gameMenu.setVisible(true);
//            ft_gameMenu.setFromValue(0);
//            ft_gameMenu.setToValue(1);
//
//            ft_textArea.setFromValue(1);
//            ft_textArea.setToValue(0);
//            st.setOnFinished(event ->
//            {
//                gameMenu.setVisible(true);
//                ta.setVisible(false);
//            });
//
//            ft_gameMenu.play();
//            ft_textArea.play();
//            st.play();*/
//            lineAnimationExpandCollapse(1, 0.25);
//            /*helpView.controlTextScrolling(true, 10000);*/
//
//            gmbsVisible = true;
//        }
//
//        else if (action.equals("collapse"))
//        {
//            tt_gmbResume.setToX(1900);
//            tt_gmbLvlUp.setToX(1900);
//            tt_gmbItems.setToX(1900);
//            tt_gmbAbilities.setToX(1900);
//            tt_gmbEquip.setToX(1900);
//            tt_gmbStatus.setToX(1900);
//            tt_gmbOptions.setToX(1900);
//            tt_gmbExit.setToX(1900);
//
//            /*ft_gameMenu.setFromValue(1);
//            ft_gameMenu.setToValue(0);
//
//            ft_textArea.setFromValue(0);
//            ft_textArea.setToValue(1);
//            st.setOnFinished(event ->
//            {
//                gameMenu.setVisible(false);
//                ta.setVisible(true);
//            });
//
//            st.play();
//            ft_gameMenu.play();
//            ft_textArea.play();*/
//            lineAnimationExpandCollapse(0, 0.25);
//            /*helpView.controlTextScrolling(false, 0);*/
//
//            gmbsVisible = false;
//        }
    }

    public void setupSceneKeys()
    {
        scene.setOnKeyPressed(event ->
        {
//            if (event.getCode() == KeyCode.ESCAPE)
//            {
//                if (gmbsVisible == false)
//                    gameMenuExpandCollapse("expand");
//                else
//                    gameMenuExpandCollapse("collapse");
//            }

            switch (event.getCode())
            {
                case ESCAPE:
                {
                    if (mainMenu.isVisible() == true)
                        mainMenu.setVisible(false);
                    else
                        mainMenu.setVisible(true);
                    break;
                }

                case CIRCUMFLEX:
                {
                    if (console.getTextArea().isVisible() == true)
                        console.getTextArea().setVisible(false);
                    else
                        console.getTextArea().setVisible(true);
                    break;
                }
            }
        });
    }

    /**
     * @deprecated
     */
    public void setAutoResize(boolean on)
    {
//        if (on == true)
//        {
//            scene.widthProperty().addListener((observable, oldValue, newValue) ->
//            {
//                imgView.setFitWidth(newValue.doubleValue());
//                gmb_resume.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_lvlup.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_items.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_abilities.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_equip.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_status.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_options.setTranslateX(scene.widthProperty().doubleValue() - 350);
//                gmb_exit.setTranslateX(scene.widthProperty().doubleValue() - 350);
//            });     // Update imgView size when scene is resized.
//
//            scene.heightProperty().addListener((observable, oldValue, newValue) ->
//            {
//                imgView.setFitHeight(newValue.doubleValue());
//            });
//        }
    }

    /**
     * Assembles Textadventure's main menu with all the buttons and their functionality. Menus are represented by instances of the 'GameMenu' class.
     */
    public void setupMainMenu()
    {

        mainMenu.addButton(new GameMenuButton("Resume", 450, 55));
        mainMenu.addButton(new GameMenuButton("Level Up", 450, 55));
        mainMenu.addButton(new GameMenuButton("Items", 450, 55));
        mainMenu.addButton(new GameMenuButton("Abilities", 450, 55));
        mainMenu.addButton(new GameMenuButton("Equip", 450, 55));
        mainMenu.addButton(new GameMenuButton("Status", 450, 55));
        mainMenu.addButton(new GameMenuButton("Options", 450, 55));
        mainMenu.addButton(new GameMenuButton("Console", 450, 55));
        mainMenu.addButton(new GameMenuButton("Exit", 450, 55));
        mainMenu.addButton(new GameMenuButton("Attack Tidus", 450, 55));

        GameMenuButton pointer;

        pointer = mainMenu.getButton("Resume");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 50);
        pointer.setOnMouseClicked(event ->
        {
        });

        pointer = mainMenu.getButton("Level Up");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 80);
        pointer.setOnMouseClicked(event ->
        {

        });

        pointer = mainMenu.getButton("Items");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 110);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Items Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
        });

        pointer = mainMenu.getButton("Abilities");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 140);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Abilities Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
        });

        pointer = mainMenu.getButton("Equip");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 170);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Equip Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
        });

        pointer = mainMenu.getButton("Status");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 200);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Status Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
        });

        pointer = mainMenu.getButton("Options");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 230);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Options Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
        });

        pointer = mainMenu.getButton("Console");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 260);
        pointer.setOnMouseClicked(event ->
        {
            if (console.getTextArea().isVisible() == true)
                console.getTextArea().setVisible(false);
            else
                console.getTextArea().setVisible(true);
        });

        pointer = mainMenu.getButton("Exit");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 290);
        pointer.setOnMouseClicked(event ->
        {
            try
            {
                console.writeToConsole("Exit Button clicked.");
            } catch (NullPointerException e)
            {
                System.out.println("CONSOLE_NOT_INITIALIZED");
                e.printStackTrace();
            }
            mainWindow.setFullScreen(false);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES)
                System.exit(0);
            else
                mainWindow.setFullScreen(true);
        });

        pointer = mainMenu.getButton("Attack Tidus");
        pointer.setTranslateX(scene.widthProperty().doubleValue() + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() + 320);
        pointer.setOnMouseClicked(event ->
        {
            Auron.action_skill(Tidus, Skill.BLOODLETTER);
        });

        mainMenu.setAutoResizeButtons(true);
        root.getChildren().add(mainMenu);
    }

    public String getStackTrace(final Throwable throwable)
    {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    /**
     * @deprecated
     */
    public void setupButtons()
    {
//        gmb_resume = new GameMenuButton("Resume", 450, 55);
//        gmb_resume.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_resume.setTranslateY(scene.heightProperty().doubleValue() + 50);
//
//        gmb_lvlup = new GameMenuButton("Level Up", 450, 55);
//        gmb_lvlup.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_lvlup.setTranslateY(scene.heightProperty().doubleValue() + 125);
//
//        gmb_items = new GameMenuButton("Items", 450, 55);
//        gmb_items.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_items.setTranslateY(scene.heightProperty().doubleValue() + 200);
//
//        gmb_abilities = new GameMenuButton("Abilities", 450, 55);
//        gmb_abilities.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_abilities.setTranslateY(scene.heightProperty().doubleValue() + 275);
//
//        gmb_equip = new GameMenuButton("Equip", 450, 55);
//        gmb_equip.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_equip.setTranslateY(scene.heightProperty().doubleValue() + 350);
//
//        gmb_status = new GameMenuButton("Status", 450, 55);
//        gmb_status.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_status.setTranslateY(scene.heightProperty().doubleValue() + 425);
//
//        gmb_options = new GameMenuButton("Options", 450, 55);
//        gmb_options.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_options.setTranslateY(scene.heightProperty().doubleValue() + 500);
//
//        gmb_exit = new GameMenuButton("Exit", 450, 55);
//        gmb_exit.setTranslateX(scene.widthProperty().doubleValue() + 1500);
//        gmb_exit.setTranslateY(scene.heightProperty().doubleValue() + 575);
//        gmb_exit.setOnMouseClicked(event ->
//        {
//            mainWindow.setFullScreen(false);
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Close ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
//            alert.showAndWait();
//
//            if (alert.getResult() == ButtonType.YES)
//                System.exit(0);
//            else
//                mainWindow.setFullScreen(true);
//        });
    }

    /**
     *
     */
    public void setupImages()
    {
        imgView = new ImageView(new Image(getClass().getResource("../res/DarkSpace.jpg").toExternalForm())); // Muss mit ".." eine Ordnerebene höher gehen, um res zu finden.
        root.getChildren().add(imgView);
    }

    /**
     * Assembles mainWindow components and shows mainWindow.
     */
    public void initWindow()
    {
        mainWindow.setScene(scene);
        mainWindow.setTitle("Textadventure - The Game");
        mainWindow.setFullScreen(true);
        mainWindow.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        mainWindow.show();
    }

    void initConsole()
    {
        ta = new TextArea();
        console = new Console(ta);
        root.getChildren().add(ta);
    }

    // I decided to outsource all the parts of window assemblement to seperate methods. This makes it easy to read the program's flow in the start() method.
    // Sichtbare Elemente werden anscheinend übereinander gepackt. Werden die Buttons zuerst erstellt und danach das Hintergrundbild, so werden die Buttons von dem Bild überdeckt.
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainWindow = primaryStage;

        setupImages();
        setupMainMenu();
        setupSceneKeys();
        initWindow();
        initConsole();

        Auron = new Character("Auron", 2000, 100, 458, 120, 40, 20, 10);
        Tidus = new Character("Tidus", 2000, 100, 100, 120, 40, 20, 10);
    }
}