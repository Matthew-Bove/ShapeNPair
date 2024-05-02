package com.example.shapenpairgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ShapeNPairController implements Initializable {

    @FXML
    private Label correctGuessesLabel;

    @FXML
    private Label guessesLabel;

    @FXML
    private FlowPane imagesFlowPane;

    private ArrayList<MatchedTile> tilesInGame;

    private MatchedTile tileA, tileB;

    private Player player1 = new Player("Player 1");

    @FXML
    void playAgain() {
        tileA = null;
        tileB = null;

        TileDeck deck = new TileDeck();
        deck.shuffle();
        tilesInGame = new ArrayList<>();

        for(int i = 0; i < imagesFlowPane.getChildren().size()/2; i++) {
            Tile tileDealt = deck.dealTopTile();
            tilesInGame.add(new MatchedTile(tileDealt.getShape(), tileDealt.getColor()));
            tilesInGame.add(new MatchedTile(tileDealt.getShape(), tileDealt.getColor()));
        }

        Collections.shuffle(tilesInGame);
        flipAllCards();
        player1.resetGuesses();
        updateStats();
        // System.out.println(tilesInGame);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeImageView();
        playAgain();
    }

    /**
     * This will add a number to each ImageView and set the image to be the back of a Card.
     */
    private void initializeImageView() {
        for(int i = 0; i < imagesFlowPane.getChildren().size(); i++){
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            imageView.setImage(new Image(Tile.class.getResourceAsStream("images/z_tile_background.png")));
            imageView.setUserData(i);

            //register a click listener
            imageView.setOnMouseClicked(event -> {
                flipTile((int) imageView.getUserData());
            });
        }
    }

    /**
     * This will flip the cards that are not matched to their backs.
     */

    private void flipAllCards() {
        for (int i = 0; i < tilesInGame.size(); i++) {
            ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(i);
            MatchedTile tile = tilesInGame.get(i);

            if(tile.isMatched())
                imageView.setImage(tile.getImage());
            else
                imageView.setImage(tile.getBackOfCard());
        }
    }

    private void flipTile(int tileIndex)
    {
        if(tileA == null && tileB == null)
            flipAllCards();

        ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(tileIndex);
        if(tileA == null)
        {
            tileA = tilesInGame.get(tileIndex);
            imageView.setImage(tileA.getImage());
        }
        else if(tileB == null){
            player1.IncorrectGuess();
            tileB = tilesInGame.get(tileIndex);
            imageView.setImage(tileB.getImage());
            checkForMatch();
            updateStats();
        }
    }

    private void updateStats() {
        correctGuessesLabel.setText(Integer.toString(player1.getCorrectGuesses()));
        guessesLabel.setText(Integer.toString(player1.getTotalGuesses()));
    }

    private void checkForMatch() {
        if(tileA.isSameTile(tileB)){
            player1.CorrectGuess();
            tileA.setMatched(true);
            tileB.setMatched(true);
        }
        tileA = null;
        tileB = null;
    }

}