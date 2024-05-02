package com.example.shapenpairgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TileDeck {
    private ArrayList<Tile> tiles;

    public TileDeck() {
        this.tiles = new ArrayList<>();
        List<String> shapes = Tile.getValidShapes();
        List<String> colors = Tile.getValidColor();

        for (String shape : shapes) {
            for (String color : colors) {
                tiles.add(new Tile(shape, color));
            }
        }
    }
    /**
     * This method will shuffle the tiles.
     */

    public void shuffle() {
        Collections.shuffle(tiles);
    }

    /**
     * This method will return the top tile from the deck.
     * If the deck is empty, it will return null.
     */

    public Tile dealTopTile() {
        if(!tiles.isEmpty())
            return tiles.removeFirst();
        else
            return null;
    }

    /**
     * This method returns the number of tiles left in the deck.
     */

    public int getNumOfCards() {
        return tiles.size();
    }
}

