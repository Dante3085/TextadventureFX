package com.ui.gameMenu;

import com.Main.Main;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Button for the UI.
 * @author mjsch
 */
public class GameMenuButton extends StackPane
{
    private Text text_name;
    private Rectangle rec_bg;

    private double width;
    private double heigth;

    public GameMenuButton(String name, double width, double height)
    {
        this.width = width;
        this.heigth = height;

        text_name = new Text(name);
        text_name.setFont(text_name.getFont().font(20));
        text_name.setFill(Color.WHITE);
        text_name.setTranslateX(20);

        rec_bg = new Rectangle(width, height);
        rec_bg.setOpacity(0.6);
        rec_bg.setFill(Color.BLACK);
        rec_bg.setEffect(new GaussianBlur(3.5));

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(rec_bg, text_name);

        DropShadow drop = new DropShadow(50, Color.BLUE);
        drop.setInput(new Glow());

        setOnMouseEntered(event ->
        {
            rec_bg.setTranslateX(10);
            text_name.setTranslateX(30);
            rec_bg.setFill(Color.WHITE);
            text_name.setFill(Color.BLACK);
        });

        setOnMouseExited(event ->
        {
            rec_bg.setTranslateX(0);
            text_name.setTranslateX(20);
            rec_bg.setFill(Color.BLACK);
            text_name.setFill(Color.WHITE);
        });

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    public void setAutoResize(boolean on)
    {
        if (on == true)
        {
            Main.mainWindow.widthProperty().addListener((observable, oldValue, newValue) ->
            {
                this.setTranslateX(Main.mainWindow.widthProperty().doubleValue() - 200);
            });     // Update imgView size when scene is resized.
        }
    }

    public void setText_name(Text text_name)
    {
        this.text_name = text_name;
    }
    public void setBackground(Color c)
    {
        rec_bg.setFill(c);
    }

    public String getName()
    {
        return this.text_name.getText();
    }

    @Override
    public String toString()
    {
        return this.text_name.getText() + "[" + width + ", " + heigth + "]";
    }
}