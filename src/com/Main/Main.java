package com.Main;

import com.character.Character;
import com.character.Skill;
import com.combat.Combat;
import com.ui.*;
import com.ui.gameMenu.CharacterView;
import com.ui.gameMenu.GameMenu;
import com.ui.gameMenu.GameMenuButton;
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
    GameMenu menu = new GameMenu("Test");

    TextArea ta;
    public static Console console;

    Character Auron;
    Character Tidus;

    boolean gmbsVisible = true;

    Line line;

    TurnOrderView turnOrderView = new TurnOrderView();

    boolean buttonsShifted = false;

    TranslateTransition tt_btn_turn              = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_initTurnOrderView = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_items             = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_abilities         = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_equip             = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_status            = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_options           = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_console           = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_exit              = new TranslateTransition(new Duration(500));
    TranslateTransition tt_btn_attackTidus       = new TranslateTransition(new Duration(500));

    SequentialTransition st_buttons;

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

        menu.addElement(new GameMenuButton("Button1", 100, 50));
        menu.addElement(new GameMenuButton("Button2", 100, 50));
        menu.addElement(new GameMenuButton("Button3", 100, 50));
        menu.addElement(new GameMenuButton("Button4", 100, 50));
        menu.addElement(new GameMenuButton("Button5", 100, 50));
        menu.addElement(new GameMenuButton("Button6", 100, 50));

        menu.setTranslateX(1000);

        TTextArea tTextArea = new TTextArea();
        root.getChildren().addAll(turnOrderView, tTextArea, menu);

        CharacterView auron = new CharacterView("Auron", "2000", "167");
        auron.setTranslateX(200);
        auron.setTranslateY(200);
        root.getChildren().add(auron);
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
                    if (!buttonsShifted)
                        st_buttons.play();
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
     * Assembles Textadventure's main menu with all the buttons and their functionality. Menus are represented by instances of the 'GameMenu' class.
     */
    private void setupMainMenu()
    {
        /*mainMenu.addButton(new GameMenuButton("Turn", 450, 55              ));
        mainMenu.addButton(new GameMenuButton("Start Combat", 450, 55));
        mainMenu.addButton(new GameMenuButton("Items", 450, 55             ));
        mainMenu.addButton(new GameMenuButton("Abilities", 450, 55         ));
        mainMenu.addButton(new GameMenuButton("Equip", 450, 55             ));
        mainMenu.addButton(new GameMenuButton("Status", 450, 55            ));
        mainMenu.addButton(new GameMenuButton("Options", 450, 55           ));
        mainMenu.addButton(new GameMenuButton("Console", 450, 55           ));
        mainMenu.addButton(new GameMenuButton("Exit", 450, 55              ));
        mainMenu.addButton(new GameMenuButton("Attack Tidus", 450, 55      ));

        GameMenuButton pointer;

        pointer = mainMenu.getButton("Turn");
        pointer.setTranslateX(scene.widthProperty().doubleValue()  + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() +   50);
        pointer.setOnMouseClicked(event ->
        {
            turnOrderView.turn();
        });

        pointer = mainMenu.getButton("Start Combat");
        pointer.setTranslateX(scene.widthProperty().doubleValue()  + 1500);
        pointer.setTranslateY(scene.heightProperty().doubleValue() +   80);
        pointer.setOnMouseClicked(event ->
        {
            new Thread(new Combat(turnOrderView)).start();
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
            if (console.getTextArea().isVisible())
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

        // mainMenu.setAutoResizeButtons(true);
        root.getChildren().add(mainMenu);*/
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

    private void setupMenuAnimations()
    {
        /*tt_btn_turn.setNode             (mainMenu.getButton("Turn"              )); tt_btn_turn.setToX             (offset);
        tt_btn_initTurnOrderView.setNode(mainMenu.getButton("Init TurnOrderView")); tt_btn_initTurnOrderView.setToX(offset);
        tt_btn_items.setNode            (mainMenu.getButton("Items"             )); tt_btn_items.setToX            (offset);
        tt_btn_abilities.setNode        (mainMenu.getButton("Abilities"         )); tt_btn_abilities.setToX        (offset);
        tt_btn_equip.setNode            (mainMenu.getButton("Equip"             )); tt_btn_equip.setToX            (offset);
        tt_btn_status.setNode           (mainMenu.getButton("Status"            )); tt_btn_status.setToX           (offset);
        tt_btn_options.setNode          (mainMenu.getButton("Options"           )); tt_btn_options.setToX          (offset);
        tt_btn_console.setNode          (mainMenu.getButton("Console"           )); tt_btn_console.setToX          (offset);
        tt_btn_exit.setNode             (mainMenu.getButton("Exit"              )); tt_btn_exit.setToX             (offset);
        tt_btn_attackTidus.setNode      (mainMenu.getButton("Attack Tidus"      )); tt_btn_attackTidus.setToX      (offset);

        st_buttons = new SequentialTransition(tt_btn_turn, tt_btn_initTurnOrderView, tt_btn_items, tt_btn_abilities, tt_btn_equip, tt_btn_status, tt_btn_options, tt_btn_console,
                                              tt_btn_exit, tt_btn_attackTidus                                                                                                   );*/
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
}
