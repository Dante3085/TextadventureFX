package com.ui;

import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GameMenu extends VBox
{
    private String name;

    private ArrayList<GameMenuButton> buttons = new ArrayList<GameMenuButton>();
    private ArrayList<Slider> sliders = new ArrayList<Slider>();
    private ArrayList<CharacterView> characterViews = new ArrayList<CharacterView>();

    private Rectangle rec_bg;

//    private VBox menu_mainMenu;
//    private VBox menu_optionsMenu;
//    private VBox menu_animationSpeedMenu;
//    private VBox menu_soundMenu;
//    private VBox menu_characterViews;
//    private VBox menu_helpView;
//    private VBox menu_animatedMenuSymbol;
//
//    private Slider slider_animSpeed_gameMenu;
//    private Slider slider_animSpeed_mainMenu;
//    private Slider slider_animSpeed_optionsMenu;
//    private Slider slider_animSpeed_animationSpeedMenu;
//    private Slider slider_animSpeed_soundMenu;
//    private Slider slider_sound_mainTheme;
//
//    private double animSpeed_menu_gameMenu = 0.25;
//    private double animSpeed_menu_mainMenu = 0.25;
//    private double animSpeed_menu_optionsMenu = 0.25;
//    private double animSpeed_menu_animationSpeedMenu = 0.25;
//    private double animSpeed_menu_soundMenu = 0.25;

    public GameMenu(String name)
    {
        this.name = name;
//        // MENUS
//        menu_mainMenu = new VBox(10);            // MainMenu              {RESUME, Level Up, Abilities, Equip, Status, OPTIONS, EXIT}
//        menu_optionsMenu = new VBox(10);         // OptionsMenu           {BACK, ANIMATION SPEED, SOUND, VIDEO}
//        menu_animationSpeedMenu = new VBox(10);  // AnimationSpeedMenu    {}
//        menu_soundMenu = new VBox(10);           // SoundMenu             {}
//        menu_characterViews = new VBox(10);
//        menu_helpView = new VBox(0);
//        menu_animatedMenuSymbol = new VBox(0);
//
//        // MAIN MENU
//        menu_mainMenu.setTranslateX(1500);
//        menu_mainMenu.setTranslateY(150);
//        menu_mainMenu.setSpacing(50);
//
//        // OPTIONS MENU
//        menu_optionsMenu.setTranslateX(1500);
//        menu_optionsMenu.setTranslateY(150);
//        menu_optionsMenu.setSpacing(50);
//
//        // ANIMATION SPEED MENU
//        menu_animationSpeedMenu.setTranslateX(1500);
//        menu_animationSpeedMenu.setTranslateY(150);
//        menu_animationSpeedMenu.setSpacing(50);
//
//        // SOUND MENU
//        menu_soundMenu.setTranslateX(1500);
//        menu_soundMenu.setTranslateY(150);
//        menu_soundMenu.setSpacing(50);
//
//        // CHARACTER VIEW MENU
//        menu_characterViews.setTranslateX(250);
//        menu_characterViews.setTranslateY(150);
//        menu_characterViews.setSpacing(60);
//
//        // HELP VIEW MENU
//        menu_helpView.setTranslateX(0);
//        menu_helpView.setTranslateY(50);
//
//        // ANIMATED MENU SYMBOL MENU
//        menu_animatedMenuSymbol.setTranslateX(50);
//        menu_animatedMenuSymbol.setTranslateY(100);
//
//        // PUT MENUS IN OFFSET AREA
//        menu_optionsMenu.setTranslateX(offset);
//        menu_animationSpeedMenu.setTranslateX(offset);
//        menu_soundMenu.setTranslateX(offset);
//
//        // MEDIA
//        media_wanderingFlame = new Media(new File("res/FFX_WanderingFlame.mp3").toURI().toString());
//        media_toZanarkand = new Media(new File("res/Final_ToZanarkand.mp3").toURI().toString());
//        media_otherworld = new Media(new File("res/FFX_Otherworld.mp3").toURI().toString());
//
//        // CHARACTER VIEW
//        char1 = new CharacterView("Tidus", "2000/3000", "1000/1500");
//        char2 = new CharacterView("Auron", "9000/9999", "1000/1500");
//        char3 = new CharacterView("Wakka", "2000/3000", "1000/1500");
//        char4 = new CharacterView("Lulu", "1000/3000", "/999");
//        char5 = new CharacterView("Rikku", "2000/3000", "1000/1500");
//        char6 = new CharacterView("Yuna", "2000/3000", "1000/1500");
//        char7 = new CharacterView("Kimahri", "5000/5500", "1000/1500");
//
//        // HELP VIEW
//        helpView = new HelpView(1920, 25, "This is a test");
//
//        // ANIMATED MENU SYMBOL
//        animatedMenuSymbol = new AnimatedMenuSymbol();
//
//        // SLIDER
//        slider_animSpeed_gameMenu = new Slider();
//        slider_animSpeed_mainMenu = new Slider();
//        slider_animSpeed_optionsMenu = new Slider();
//        slider_animSpeed_animationSpeedMenu = new Slider();
//        slider_animSpeed_soundMenu = new Slider();
//        slider_sound_mainTheme = new Slider();
//
//        // MEDIA PLAYER
//        mediaPlayer_wanderingFlame = new MediaPlayer(media_wanderingFlame);
//        mediaPlayer_toZanarkand = new MediaPlayer(media_toZanarkand);
//        mediaPlayer_otherworld = new MediaPlayer(media_otherworld);
//
//        // GAME MENU BUTTON
//        btnResume = new GameMenuButton("RESUME", 450, 55);
//        //btnResume.setCache(true); btnResume.setCacheShape(true); btnResume.setCacheHint(CacheHint.SPEED);
//        btnLvlUp = new GameMenuButton("Level Up", 450, 55);
//        btnItems = new GameMenuButton("Items", 450, 55);
//        btnAbilities = new GameMenuButton("Abilities", 450, 55);
//        btnEquip = new GameMenuButton("Equip", 450, 55);
//        btnStatus = new GameMenuButton("Status", 450, 55);
//        btnOptions = new GameMenuButton("OPTIONS", 450, 55);
//        btnExit = new GameMenuButton("EXIT", 450, 55);
//        btnBack = new GameMenuButton("BACK", 450, 55);
//        btnAnimSpeed = new GameMenuButton("ANIMATION SPEED", 450, 55);
//        btnSound = new GameMenuButton("SOUND", 450, 55);
//        btnVideo = new GameMenuButton("VIDEO", 450, 55);
//        btnBack2 = new GameMenuButton("BACK", 450, 55);
//        btnBack3 = new GameMenuButton("BACK", 450, 55);
//        btnMusic_wanderingFlame = new GameMenuButton("FFX - Wandering Flame", 450, 55);
//        btnMusic_otherworld = new GameMenuButton("FFX - Otherworld", 450, 55);
//        btnMusic_toZanarkand = new GameMenuButton("FFX - To Zanarkand", 450, 55);
//
//        btnResume.setOnMouseClicked(event ->
//        {
//            FadeTransition ft = new FadeTransition(Duration.seconds(animSpeed_menu_gameMenu), gameMenu);
//            ft.setFromValue(1);
//            ft.setToValue(0);
//            ft.setOnFinished(event1 -> setVisible(false));
//            ft.play();
//            lineAnimationExpandCollapse(0, 0.25);
//
//            helpView.controlTextScrolling(false, 0);
//        });
//        btnResume.setOnMouseEntered(event ->
//        {
//            btnResume.getRec_bg().setTranslateX(10);
//            btnResume.getText_name().setTranslateX(30);
//            btnResume.getRec_bg().setFill(Color.WHITE);
//            btnResume.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("resume");
//        });
//
//        btnResume.setOnMouseExited(event ->
//        {
//            btnResume.getRec_bg().setTranslateX(0);
//            btnResume.getText_name().setTranslateX(20);
//            btnResume.getRec_bg().setFill(Color.BLACK);
//            btnResume.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnLvlUp.setOnMouseClicked(event ->
//        {
//
//        });
//        btnLvlUp.setOnMouseEntered(event ->
//        {
//            btnLvlUp.getRec_bg().setTranslateX(10);
//            btnLvlUp.getText_name().setTranslateX(30);
//            btnLvlUp.getRec_bg().setFill(Color.WHITE);
//            btnLvlUp.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("lvlup");
//        });
//        btnLvlUp.setOnMouseExited(event ->
//        {
//            btnLvlUp.getRec_bg().setTranslateX(0);
//            btnLvlUp.getText_name().setTranslateX(20);
//            btnLvlUp.getRec_bg().setFill(Color.BLACK);
//            btnLvlUp.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnItems.setOnMouseClicked(event ->
//        {
//
//        });
//        btnItems.setOnMouseEntered(event ->
//        {
//            btnItems.getRec_bg().setTranslateX(10);
//            btnItems.getText_name().setTranslateX(30);
//            btnItems.getRec_bg().setFill(Color.WHITE);
//            btnItems.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("items");
//        });
//        btnItems.setOnMouseExited(event ->
//        {
//            btnItems.getRec_bg().setTranslateX(0);
//            btnItems.getText_name().setTranslateX(20);
//            btnItems.getRec_bg().setFill(Color.BLACK);
//            btnItems.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnAbilities.setOnMouseClicked(event ->
//        {
//
//        });
//        btnAbilities.setOnMouseEntered(event ->
//        {
//            btnAbilities.getRec_bg().setTranslateX(10);
//            btnAbilities.getText_name().setTranslateX(30);
//            btnAbilities.getRec_bg().setFill(Color.WHITE);
//            btnAbilities.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("abilities");
//        });
//        btnAbilities.setOnMouseExited(event ->
//        {
//            btnAbilities.getRec_bg().setTranslateX(0);
//            btnAbilities.getText_name().setTranslateX(20);
//            btnAbilities.getRec_bg().setFill(Color.GOLD);
//            btnAbilities.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnEquip.setOnMouseClicked(event ->
//        {
//
//        });
//        btnEquip.setOnMouseEntered(event ->
//        {
//            btnEquip.getRec_bg().setTranslateX(10);
//            btnEquip.getText_name().setTranslateX(30);
//            btnEquip.getRec_bg().setFill(Color.WHITE);
//            btnEquip.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("equip");
//        });
//        btnEquip.setOnMouseExited(event ->
//        {
//            btnEquip.getRec_bg().setTranslateX(0);
//            btnEquip.getText_name().setTranslateX(20);
//            btnEquip.getRec_bg().setFill(Color.BLACK);
//            btnEquip.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnStatus.setOnMouseClicked(event ->
//        {
//
//        });
//        btnStatus.setOnMouseEntered(event ->
//        {
//            btnStatus.getRec_bg().setTranslateX(10);
//            btnStatus.getText_name().setTranslateX(30);
//            btnStatus.getRec_bg().setFill(Color.WHITE);
//            btnStatus.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("status");
//        });
//        btnStatus.setOnMouseExited(event ->
//        {
//            btnStatus.getRec_bg().setTranslateX(0);
//            btnStatus.getText_name().setTranslateX(20);
//            btnStatus.getRec_bg().setFill(Color.BLACK);
//            btnStatus.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnOptions.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_optionsMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_mainMenu), menu_mainMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt1.setToX(menu_mainMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_mainMenu));
//        });
//        btnOptions.setOnMouseEntered(event ->
//        {
//            btnOptions.getRec_bg().setTranslateX(10);
//            btnOptions.getText_name().setTranslateX(30);
//            btnOptions.getRec_bg().setFill(Color.WHITE);
//            btnOptions.getText_name().setFill(Color.BLACK);
//
//            helpView.setHelpText("options");
//        });
//        btnOptions.setOnMouseExited(event ->
//        {
//            btnOptions.getRec_bg().setTranslateX(0);
//            btnOptions.getText_name().setTranslateX(20);
//            btnOptions.getRec_bg().setFill(Color.BLACK);
//            btnOptions.getText_name().setFill(Color.WHITE);
//
//            helpView.setHelpText("lists");
//        });
//        btnExit.setOnMouseClicked(event -> System.exit(0));
//        btnBack.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_mainMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_mainMenu), menu_mainMenu);
//            tt1.setToX(menu_optionsMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_optionsMenu));
//        });
//        btnAnimSpeed.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_animationSpeedMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_animationSpeedMenu), menu_animationSpeedMenu);
//            tt1.setToX(menu_optionsMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_optionsMenu));
//        });
//        btnSound.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_soundMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_soundMenu), menu_soundMenu);
//            tt1.setToX(menu_optionsMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_optionsMenu));
//        });
//        btnVideo.setOnMouseClicked(event ->
//        {
//            mainWindow.setFullScreen(true);
//            printResolution();
//        });
//        btnBack2.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_optionsMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_animationSpeedMenu), menu_animationSpeedMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt1.setToX(menu_animationSpeedMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_animationSpeedMenu));
//        });
//        btnBack3.setOnMouseClicked(event ->
//        {
//            getChildren().add(menu_optionsMenu);
//
//            TranslateTransition tt = new TranslateTransition(Duration.seconds(animSpeed_menu_soundMenu), menu_soundMenu);
//            tt.setToX(offset);
//
//            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(animSpeed_menu_optionsMenu), menu_optionsMenu);
//            tt1.setToX(menu_soundMenu.getTranslateX());
//
//            tt.play();
//            tt1.play();
//
//            tt.setOnFinished(event1 -> getChildren().remove(menu_soundMenu));
//        });
//        btnMusic_wanderingFlame.setOnMouseClicked(event ->
//        {
//            mediaPlayer_toZanarkand.stop();
//            mediaPlayer_otherworld.stop();
//
//            mediaPlayer_wanderingFlame.play();
//        });
//        btnMusic_otherworld.setOnMouseClicked(event ->
//        {
//            mediaPlayer_toZanarkand.stop();
//            mediaPlayer_wanderingFlame.stop();
//
//            mediaPlayer_otherworld.play();
//        });
//        btnMusic_toZanarkand.setOnMouseClicked(event ->
//        {
//            mediaPlayer_otherworld.stop();
//            mediaPlayer_wanderingFlame.stop();
//
//            mediaPlayer_toZanarkand.stop();
//            mediaPlayer_toZanarkand.play();
//        });
//
//        rec_bg = new Rectangle();
//        rec_bg.setFill(Color.GREY);
//        rec_bg.setOpacity(0.4);
//
//        mediaPlayer_wanderingFlame.setVolume(0.25);
//        mediaPlayer_toZanarkand.setVolume(0.25);
//        mediaPlayer_otherworld.setVolume(0.25);
//
//        slider_sound_mainTheme.setShowTickLabels(true);
//        slider_sound_mainTheme.setShowTickMarks(true);
//        slider_sound_mainTheme.getStylesheets().add("CSS/Slider.css");
//        slider_sound_mainTheme.setValue(mediaPlayer_wanderingFlame.getVolume() * Math.pow(10, 2));
//        slider_sound_mainTheme.valueProperty().addListener((observable, oldValue, newValue) ->
//        {
//            System.out.println("NewSliderValue: " + (newValue.doubleValue() * Math.pow(10, -2)));
//            double newVolume = newValue.doubleValue() * Math.pow(10, -2);
//            mediaPlayer_wanderingFlame.setVolume(newVolume);
//            mediaPlayer_toZanarkand.setVolume(newVolume);
//            mediaPlayer_otherworld.setVolume(newVolume);
//        });
//        slider_animSpeed_gameMenu.setShowTickLabels(true);
//        slider_animSpeed_gameMenu.setShowTickMarks(true);
//        slider_animSpeed_gameMenu.getStylesheets().add("CSS/Slider.css");
//        slider_animSpeed_mainMenu.setShowTickLabels(true);
//        slider_animSpeed_mainMenu.setShowTickMarks(true);
//        slider_animSpeed_mainMenu.getStylesheets().add("CSS/Slider.css");
//        slider_animSpeed_optionsMenu.setShowTickLabels(true);
//        slider_animSpeed_optionsMenu.setShowTickMarks(true);
//        slider_animSpeed_optionsMenu.getStylesheets().add("CSS/Slider.css");
//        slider_animSpeed_animationSpeedMenu.setShowTickLabels(true);
//        slider_animSpeed_animationSpeedMenu.setShowTickMarks(true);
//        slider_animSpeed_animationSpeedMenu.getStylesheets().add("CSS/Slider.css");
//        slider_animSpeed_soundMenu.setShowTickLabels(true);
//        slider_animSpeed_soundMenu.setShowTickMarks(true);
//        slider_animSpeed_soundMenu.getStylesheets().add("CSS/Slider.css");
//        slider_sound_mainTheme.setShowTickLabels(true);
//        slider_sound_mainTheme.setShowTickMarks(true);
//        slider_sound_mainTheme.getStylesheets().add("CSS/Slider.css");
//
//        menu_mainMenu.getChildren().addAll(btnResume, btnLvlUp, btnItems, btnAbilities, btnEquip, btnStatus, btnOptions, btnExit);
//        menu_optionsMenu.getChildren().addAll(btnBack, btnAnimSpeed, btnSound, btnVideo);
//        menu_animationSpeedMenu.getChildren().addAll(btnBack2, slider_animSpeed_gameMenu, slider_animSpeed_mainMenu, slider_animSpeed_optionsMenu, slider_animSpeed_animationSpeedMenu,
//                slider_animSpeed_soundMenu);
//        menu_soundMenu.getChildren().addAll(btnBack3, btnMusic_wanderingFlame, btnMusic_toZanarkand, btnMusic_otherworld, slider_sound_mainTheme);
//        menu_characterViews.getChildren().addAll(char1, char2, char3, char4, char5, char6, char7);
//        menu_helpView.getChildren().addAll(helpView);
//        menu_animatedMenuSymbol.getChildren().addAll(animatedMenuSymbol);
//
//        this.getChildren().addAll(rec_bg, menu_mainMenu, menu_characterViews, menu_helpView, menu_animatedMenuSymbol);
    }

    public void setAutoResizeButtons(boolean on)
    {
        if (on == true)
        {
            for (GameMenuButton b: buttons)
                b.setAutoResize(true);
        }

        else
        {
            for (GameMenuButton b: buttons)
                b.setAutoResize(false);
        }
    }

    public void addButton(GameMenuButton button)
    {
        buttons.add(button);
        this.getChildren().add(button);
    }
    public void printButtonsSymbolically()
    {

    }
    public void printButtons()
    {

    }
    public GameMenuButton getButton(String name)
    {
        for (int i = 0; i < buttons.size(); i++)
            if (buttons.get(i).getName().equals(name))
                return buttons.get(i);
        System.out.println("@getButton(" + name + ") | Der Button '" + name + "' existiert nicht. |");
        return  null;
    }

    public void addSlider(Slider slider)
    {
        sliders.add(slider);
        this.getChildren().add(slider);
    }
    public void printSlidersSymbolically()
    {

    }
    public void printSliders()
    {

    }

    public void addCharacterView (CharacterView characterView)
    {
        characterViews.add(characterView);
        this.getChildren().add(characterView);
    }
    public void printCharacterViewsSymbolically()
    {
    }
    public void printCharacterViews()
    {
    }

    public void printGameMenu()
    {
        System.out.println(name + "[Buttons: " + buttons.size() + ", Sliders: " + sliders.size() + ", CharacterViews: " + characterViews.size() + "]\n");
    }
    public void printGameMenuSymbolically()
    {
        String s = "";

        s += "\t- ";
        for (int i = 0; i < buttons.size() - 1; i++)
            s += buttons.get(i).toString() + " -> ";
        s += buttons.get(buttons.size() - 1).toString();

        s += "\n\t- ";
        for (int i = 0; i < sliders.size() - 1; i++)
            s += sliders.get(i).toString() + " -> ";
        s += sliders.get(sliders.size() - 1).toString();

        s += "\n\t- ";
        for (int i = 0; i < characterViews.size() - 1; i++)
            s += characterViews.get(i).toString() + " -> ";
        s += characterViews.get(characterViews.size() - 1).toString();

        System.out.println(name + "[Buttons: " + buttons.size() + ", Sliders: " + sliders.size() + ", CharacterViews: " + characterViews.size() + "]");
        System.out.println(s);
    }
}
