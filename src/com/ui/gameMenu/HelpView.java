package com.ui.gameMenu;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Display that shows usefull information in menus for the player.
 * @author mjsch
 */
public class HelpView extends StackPane
{
    // TIMELINE
    Timeline timeline;

    // RECTANGLE
    private Rectangle rec_bg;

    // TEXT
    private Text text_helpLabel;
    private Text text_helpContent;



    public HelpView(double width, double height, String initHelpText)
    {
        // TIMELINE
        timeline = new Timeline();

        // RECTANGLE
        rec_bg = new Rectangle(width, height);
        rec_bg.setFill(Color.BLACK);
        rec_bg.setStroke(Color.BLACK);
        rec_bg.setOpacity(0.5);

        // TEXT
        text_helpLabel = new Text("HELP");
        text_helpLabel.setFill(Color.WHITESMOKE);
        text_helpLabel.setFont(text_helpLabel.getFont().font(20));
        text_helpLabel.setTranslateX(-650);
        text_helpLabel.setTranslateY(-22);

        text_helpContent = new Text("Hover over menu element to receive information");
        text_helpContent.setFont(text_helpContent.getFont().font(20));
        text_helpContent.setTranslateX(-120);

        getChildren().addAll(rec_bg, text_helpLabel, text_helpContent);
    }

    public void controlTextScrolling(boolean start, double duration)
    {
        if (start == true)
        {
            text_helpContent.setTranslateX(-120);
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(text_helpContent.translateXProperty(),  text_helpContent.getX() - 600)),
                    new KeyFrame(new Duration(duration),
                            new KeyValue(text_helpContent.translateXProperty(),  650)));
            timeline.setCycleCount(100);
            timeline.play();
        }

        else if (start == false)
        {
            timeline.stop();
        }
    }

    public void setHelpText(String alias)
    {
        switch (alias)
        {
            case "lists": text_helpContent.setText("Hover over menu element to receive information."); break;
            case "resume": text_helpContent.setText("Closes the Menu and resumes the game."); break;
            case "lvlup": text_helpContent.setText("Spend Lvl Up Points to strengthen you'r characters."); break;
            case "items": text_helpContent.setText("Look at items and use them."); break;
            case "abilities": text_helpContent.setText("Look at abilities, use some of them and switch out abilities."); break;
            case "equip": text_helpContent.setText("Equip and unequip Weapons, Armors and Accessories."); break;
            case "status": text_helpContent.setText("Look at lists information about a character."); break;
            case "options": text_helpContent.setText("Change the animation speed of menu elements. Adjust sound and video settings."); break;
        }
    }
}
