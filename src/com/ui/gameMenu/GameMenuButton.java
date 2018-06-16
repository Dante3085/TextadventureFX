package com.ui.gameMenu;

import com.Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * Button for the UI.
 * @author mjsch
 */
public class GameMenuButton extends StackPane implements GameMenuElement
{
    private Text m_text = new Text();
    private Rectangle m_rec = new Rectangle();

    public GameMenuButton()
    {
        // Improve Performance TODO: Might not do anything.
        this.setCache(true);
        this.setCacheShape(true);
        this.setCacheHint(CacheHint.SPEED);

        // Init text
        m_text.setText("NO_TEXT");
        m_text.setFont(new Font(20));
        m_text.setFill(Color.WHITE);
        double textWidth = m_text.getLayoutBounds().getWidth();

        // Init StackPane
        this.setWidth(textWidth);
        this.setMaxWidth(textWidth);
        this.setMinWidth(textWidth);

        // Init Rectangle
        m_rec.setWidth(this.getWidth() + 30);
        m_rec.setHeight(50);
        m_rec.setOpacity(0.6);
        m_rec.setFill(Color.BLACK);
        m_rec.setEffect(new GaussianBlur(3.5));

        // Set Text alignment in StackPane. Applies for all StackPanes that this Node is / will be in.
        StackPane.setAlignment(m_text, Pos.CENTER_LEFT);

        // Init Mouse-Interaction with GameMenuButton
        this.setOnMouseEntered(event ->
        {
            m_rec.setTranslateX(20);
            m_rec.setFill(Color.WHITE);

            m_text.setTranslateX(30);
            m_text.setFill(Color.BLACK);
        });

        this.setOnMouseExited(event ->
        {
            m_rec.setTranslateX(0);
            m_rec.setFill(Color.BLACK);

            m_text.setTranslateX(0);
            m_text.setFill(Color.WHITE);
        });

        DropShadow drop = new DropShadow(50, Color.BLUE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));

        // Init StackPane with text and rec as children
        getChildren().addAll(m_rec, m_text);
    }

    public GameMenuButton(String name, double width, double height)
    {
        /*setCache(true);
        setCacheShape(true);
        setCacheHint(CacheHint.SPEED);

        this.setWidth(width);
        this.setMaxWidth(width);
        this.setHeight(height);

        this.name = name;

        text_name = new Text(this.name);
        text_name.setFont(text_name.getFont().font(20));
        text_name.setFill(Color.WHITE);
        text_name.setTranslateX(20);

        rec_bg = new Rectangle(this.getWidth(), this.getHeight());
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
        setOnMouseReleased(event -> setEffect(null));*/
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

/*    public void setText_name(Text text_name)
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

    public Text getText_name()
    {
        return text_name;
    }

    public Rectangle getRec_bg()
    {
        return rec_bg;
    }

    @Override
    public String id()
    {
        return this.text_name.getText();
    }

    @Override
    public double height()
    {
        return rec_bg.getHeight();
    }

    @Override
    public double width()
    {
        return rec_bg.getWidth();
    }

    @Override
    public String toString()
    {
        return GameMenuButton.class.getName() + "[" + this.text_name.getText() + ", " + getWidth() + ", " + getHeight() + "]";
    }*/

    @Override
    public String id()
    {
        return this.m_text.getText();
    }

    @Override
    public double height()
    {
        return this.getHeight();
    }

    @Override
    public double width()
    {
        return this.getWidth();
    }
}