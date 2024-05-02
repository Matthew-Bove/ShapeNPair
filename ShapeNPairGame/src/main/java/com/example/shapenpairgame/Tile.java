package com.example.shapenpairgame;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

public class Tile {
    private String shape;
    private String color;

    public Tile(String shape, String color) {
        setShape(shape);
        setColor(color);
    }

    public String getShape() {
        return shape;
    }

    public static List<String> getValidShapes()
    {
        return Arrays.asList("square", "triangle", "circle", "star", "diamond");
    }

    /**
     * Valid shapes for a tile are "square", "triangle", "circle", "star", "diamond"
     * @param shape
     */
    public void setShape(String shape) {
        shape = shape.toLowerCase();
        if(getValidShapes().contains(shape))
        this.shape = shape;
        else
            throw new IllegalArgumentException(shape + " is not a valid shape. It must be one of " + getValidShapes());
    }

    public String getColor() {
        return color;
    }

    public static List<String> getValidColor() {
        return Arrays.asList("red", "orange", "yellow", "green", "blue", "purple");
    }
    /**
     * Valid colors for a tile are "red", "orange", "yellow", "green", "blue", "purple"
     * @param color
     */
    public void setColor(String color) {
        color = color.toLowerCase();
        if(getValidColor().contains(color))
        this.color = color;
        else
            throw new IllegalArgumentException(color + " is not a valid color. It must be one of " + getValidColor());
    }

    public String toString() {
        return color + " " + shape;
    }



    /**
     * This method will return an Image that represents the tile
     */

    public Image getImage(){
        String pathName = "images/" + shape + "_" + color + ".png";
        return new Image(Tile.class.getResourceAsStream(pathName));
    }

    public Image getBackOfCard() {
        return new Image(Tile.class.getResourceAsStream("images/z_tile_background.png"));
    }
}
