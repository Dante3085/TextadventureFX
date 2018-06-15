package com.ui.gameMenu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Display for important Character information in GameMenus.
 * @author mjsch
 */
public class CharacterView extends StackPane implements GameMenuElement
{
    private String name;
    private String hpValue;
    private String mpValue;

    private Rectangle rec_bg;

    private Polygon polygon_bg;

    private Circle circle_lvlUpPoints;

    private Text text_name;

    private Text text_hp;
    private Text text_hpValue;
    private Text text_mp;
    private Text text_mpValue;
    private Text text_lvlUpPoints;

    public CharacterView(String name, String hpValue, String mpValue)
    {
        this.name = name;
        this.hpValue = hpValue;
        this.mpValue = mpValue;

        // POLYGON
        polygon_bg = new Polygon
                (
                        30, 60,
                        850, 60,
                        875, 40,
                        875, 20,
                        850, 0,
                        30, 0,
                        0, 20,
                        0, 40
                );

        polygon_bg.setOpacity(0.6);
        polygon_bg.setFill(Color.BLACK);
        polygon_bg.setEffect(new GaussianBlur(3.5));

        // RECTANGLE
        rec_bg = new Rectangle(900, 55);
        rec_bg.setOpacity(0.6);
        rec_bg.setFill(Color.BLACK);
        rec_bg.setEffect(new GaussianBlur(3.5));

        // NAME
        text_name = new Text(name);
        text_name.setFont(text_name.getFont().font(20));
        text_name.setFill(Color.WHITE);
        text_name.setTranslateX(rec_bg.getWidth() * (1.0 / 25.0));

        // HP
        text_hp = new Text("HP");
        text_hp.setFont(text_hp.getFont().font(18));
        text_hp.setFill(Color.DARKRED);
        text_hp.setTranslateX(rec_bg.getWidth() * (5.0 / 9.0));
        text_hp.setTranslateY(rec_bg.getHeight() * (-14 / rec_bg.getHeight()));

        // HP Value
        text_hpValue = new Text(hpValue);
        text_hpValue.setFont(text_hpValue.getFont().font(text_hpValue.getFont().getFamily(), FontWeight.NORMAL, 18));
        text_hpValue.setFill(Color.WHITE);
        text_hpValue.setTranslateX(rec_bg.getWidth() * (5.0 / 8.5));
        text_hpValue.setTranslateY(rec_bg.getHeight() * (-14 / rec_bg.getHeight()));

        // MP
        text_mp = new Text("MP");
        text_mp.setFont(text_mp.getFont().font(18));
        text_mp.setFill(Color.LIGHTSKYBLUE);
        text_mp.setTranslateX(rec_bg.getWidth() * (19.0 / 30.0));
        text_mp.setTranslateY(rec_bg.getHeight() * (11 / rec_bg.getHeight()));

        // MP Value
        text_mpValue = new Text(mpValue);
        text_mpValue.setFont(text_mpValue.getFont().font(text_mpValue.getFont().getFamily(), FontWeight.NORMAL, 18));
        text_mpValue.setFill(Color.WHITE);
        text_mpValue.setTranslateX(rec_bg.getWidth() * (19.0 / 28.4));
        text_mpValue.setTranslateY(rec_bg.getHeight() * (11 / rec_bg.getHeight()));

        // Circle LvlUpPoints
        circle_lvlUpPoints = new Circle(500, 50, 27.5);
        circle_lvlUpPoints.setOpacity(1);
        circle_lvlUpPoints.setTranslateX(rec_bg.getWidth() / 1.033);

        // Text LvlUpPoints
        text_lvlUpPoints = new Text("5");
        text_lvlUpPoints.setFont(text_lvlUpPoints.getFont().font(text_lvlUpPoints.getFont().getFamily(), FontWeight.NORMAL, 18));
        text_lvlUpPoints.setFill(Color.WHITE);
        text_lvlUpPoints.setTranslateX(rec_bg.getWidth() / 1.0062);
        text_lvlUpPoints.setTranslateY(rec_bg.getHeight() / -500);

        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(polygon_bg, text_name, text_hp, text_hpValue, text_mp, text_mpValue, circle_lvlUpPoints, text_lvlUpPoints);

        setOnMouseEntered(event ->
        {
            polygon_bg.setFill(Color.WHEAT);

            text_hp.setFill(Color.BLACK);
            text_hpValue.setFill(Color.BLACK);
            text_mp.setFill(Color.BLACK);
            text_mpValue.setFill(Color.BLACK);
            text_name.setFill(Color.BLACK);
            text_lvlUpPoints.setFill(Color.BLACK);

            circle_lvlUpPoints.setFill(Color.ANTIQUEWHITE);
        });

        setOnMouseExited(event ->
        {
            polygon_bg.setFill(Color.BLACK);

            text_hp.setFill(Color.DARKRED);
            text_hpValue.setFill(Color.WHITE);
            text_mp.setFill(Color.LIGHTSKYBLUE);
            text_mpValue.setFill(Color.WHITE);
            text_name.setFill(Color.WHITE);
            text_lvlUpPoints.setFill(Color.WHITE);

            circle_lvlUpPoints.setFill(Color.BLACK);
        });

        DropShadow drop = new DropShadow(50, Color.LIGHTGOLDENRODYELLOW);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    public Rectangle getRec_bg()
    {
        return this.rec_bg;
    }
    public Polygon getPolygon_bg()
    {
        return this.polygon_bg;
    }
    public Circle getCircle_lvlUpPoints()
    {
        return this.circle_lvlUpPoints;
    }
    public Text getText_name()
    {
        return text_name;
    }
    public Text getText_hp()
    {
        return text_hp;
    }
    public Text getText_hpValue()
    {
        return text_hpValue;
    }
    public Text getText_mp()
    {
        return text_mp;
    }
    public Text getText_mpValue()
    {
        return text_mpValue;
    }
    public Text getText_lvlUpPoints()
    {
        return text_lvlUpPoints;
    }

    @Override
    public String id()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return name + "[HP: " + hpValue + ", MP: " + mpValue + "]";
    }
}
