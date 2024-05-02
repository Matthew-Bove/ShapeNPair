package com.example.shapenpairgame;

public class MatchedTile extends Tile {
    private boolean matched;

    public MatchedTile(String shape, String color) {
        super(shape, color);
        this.matched = false; // when the game starts, it cant be matched
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    /**
     * This method returns true if 2 MatchedTile objects have the same shape and color.
     */

    public boolean isSameTile(Tile otherTile) {
        return(this.getColor().equals(otherTile.getColor()) && this.getShape().equals(otherTile.getShape()));
    }
}
