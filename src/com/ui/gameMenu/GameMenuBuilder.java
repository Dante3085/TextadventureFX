package com.ui.gameMenu;

import com.Main.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * GameMenuBuilder for streamlining creation of complex GameMenu Objects.
 */
public class GameMenuBuilder
{
    public static GameMenu buildMainMenu()
    {
        // ObservableList to temporarily save the different menu's children ObservableLists.
        ObservableList<Node> children;

        // Create GameMenus
        GameMenu mainMenu = new GameMenu(new VBox(), "MainMenu");
        GameMenu levelUpMenu = new GameMenu(new VBox(), "LevelUpMenu");
        GameMenu itemsMenu = new GameMenu(new VBox(), "ItemsMenu");
        GameMenu abilitiesMenu = new GameMenu(new VBox(), "AbilitiesMenu");
        GameMenu equipMenu = new GameMenu(new VBox(), "EquipMenu");
        GameMenu statusMenu = new GameMenu(new VBox(), "StatusMenu");
        GameMenu optionMenu = new GameMenu(new VBox(), "OptionMenu");
        GameMenu soundMenu = new GameMenu(new VBox(), "SoundMenu");
        GameMenu videoMenu = new GameMenu(new VBox(), "VideoMenu");

        // Put all other menus as subMenus of mainMenu.
        mainMenu.subMenus().add(levelUpMenu);
        mainMenu.subMenus().add(itemsMenu);
        mainMenu.subMenus().add(abilitiesMenu);
        mainMenu.subMenus().add(equipMenu);
        mainMenu.subMenus().add(statusMenu);
        mainMenu.subMenus().add(optionMenu);

        // Put video- and soundMenu as subMenus of optionMenu.
        optionMenu.subMenus().add(soundMenu);
        optionMenu.subMenus().add(videoMenu);

        // mainMenu
        mainMenu.addElement(new GameMenuButton("Resume", 200, 50   ));
        mainMenu.addElement(new GameMenuButton("Level Up", 200, 50 ));
        mainMenu.addElement(new GameMenuButton("Items", 200, 50    ));
        mainMenu.addElement(new GameMenuButton("Abilities", 200, 50));
        mainMenu.addElement(new GameMenuButton("Equip", 200, 50    ));
        mainMenu.addElement(new GameMenuButton("Status", 200, 50   ));
        mainMenu.addElement(new GameMenuButton("Options", 200, 50  ));
        mainMenu.addElement(new GameMenuButton("Exit", 200, 50     ));

        // Temporarily save children of mainMenu.
        children = mainMenu.layout().getChildren();

        // Set functionality for mainMenu Buttons.
        children.get(6).setOnMouseClicked(event ->
        {
            mainMenu.collapse();
            optionMenu.expand();
        });

        children.get(7).setOnMouseClicked(event ->
        {
            System.exit(1);
        });

        // Position mainMenu at right edge of window with no offset.
        mainMenu.putRight(0);

        // Set values for moving the mainMenu back- and forward.
        mainMenu.getTtForward().setByX(250);
        mainMenu.getTtBackward().setByX(-250);

        // optionsMenu
        optionMenu.addElement(new GameMenuButton("BACK", 200, 50));
        optionMenu.addElement(new GameMenuButton("Sound", 200, 50));
        optionMenu.addElement(new GameMenuButton("Video", 200, 50));

        // Temporarily save children of optionMenu.
        children = optionMenu.layout().getChildren();

        // Set functionality for optionMenu Buttons.
        children.get(0).setOnMouseClicked(event ->
        {
            optionMenu.collapse();
            mainMenu.expand();
        });

        children.get(1).setOnMouseClicked(event ->
        {
            optionMenu.collapse();
            soundMenu.expand();
        });

        children.get(2).setOnMouseClicked(event ->
        {
            optionMenu.collapse();
            videoMenu.expand();
        });

        // Position optionMenu at right edge of window with 250 offset that hides the menu offscreen.
        optionMenu.putRight(250);

        optionMenu.getTtForward().setByX(250);
        optionMenu.getTtBackward().setByX(-250);

        // soundMenu
        soundMenu.addElement(new GameMenuButton("BACK", 200, 50));

        // Temporarily save children of soundMenu.
        children = soundMenu.layout().getChildren();

        // Set functionality for soundMenu Buttons.
        children.get(0).setOnMouseClicked(event ->
        {
            soundMenu.collapse();
            optionMenu.expand();
        });

        soundMenu.putRight(250);

        soundMenu.getTtForward().setByX(250);
        soundMenu.getTtBackward().setByX(-250);

        // videoMenu
        videoMenu.addElement(new GameMenuButton("BACK", 200, 50));

        children = videoMenu.layout().getChildren();

        children.get(0).setOnMouseClicked(event ->
        {
            videoMenu.collapse();
            optionMenu.expand();
        });

        videoMenu.putRight(250);

        videoMenu.getTtForward().setByX(250);
        videoMenu.getTtBackward().setByX(-250);

        return mainMenu;
    }
}
