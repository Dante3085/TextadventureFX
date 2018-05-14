package com.ui;

import com.character.Character;
import javafx.geometry.Insets;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TurnOrderView extends StackPane
{
    private Text text_name;
    private Rectangle rec_bg;
    private ArrayList<Rectangle> turnOrderElements;

    public TurnOrderView(/*ArrayList<Character> turnOrder,*/ double width)
    {
        text_name = new Text("Turn Order");
        rec_bg = new Rectangle(width, 500);
        rec_bg.setFill(Color.BLUE);
        rec_bg.setOpacity(.3);

        turnOrderElements = new ArrayList<>();

        getChildren().addAll(text_name, rec_bg);
        this.setTranslateX(50);
        this.setTranslateY(50);
    }

    public void setTurnOrder(ArrayList<Character> turnOrder)
    {

    }
}
