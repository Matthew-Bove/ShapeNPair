package com.example.shapenpairgame;

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

public class ShapeNPairController2playerMode implements Initializable {

    @FXML
    private Label player1GuessesLabel;

    @FXML
    private Label player1CorrectGuessesLabel;

    @FXML
    private Label player2GuessesLabel;

    @FXML
    private Label player2CorrectGuessesLabel;

    @FXML
    private FlowPane imagesFlowPane;

    private ArrayList<MatchedTile> tilesInGame;

    private MatchedTile tileA, tileB;

    private boolean player2Turn = false;

    private Player player1 = new Player("Player 1");
    private Player player2 = new Player("Player 2");

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
        player2.resetGuesses();
        updateStats();
        player2Turn = false;
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

            // if it is not player 2's turn it must be player 1's they make the wrong guess it is now player 2's turn
            // and vice versa
            if(!player2Turn){
                player1.IncorrectGuess();
            }
            else{
                player2.IncorrectGuess();
            }
            tileB = tilesInGame.get(tileIndex);
            imageView.setImage(tileB.getImage());
            checkForMatch();
            player2Turn = !player2Turn;
            updateStats();
        }
    }

    private void updateStats() {
        player1CorrectGuessesLabel.setText(Integer.toString(player1.getCorrectGuesses()));
        player1GuessesLabel.setText(Integer.toString(player1.getTotalGuesses()));
        player2CorrectGuessesLabel.setText(Integer.toString(player2.getCorrectGuesses()));
        player2GuessesLabel.setText(Integer.toString(player2.getTotalGuesses()));
    }

    private void checkForMatch() {
        if(tileA.isSameTile(tileB)){

            // if it is not player 2's turn it is player 1's, the correct guess goes to the player that guessed
            // correctly
            if(!player2Turn){
                player1.CorrectGuess();
                player2Turn = false;
            }
            else{
                player2.CorrectGuess();
                player2Turn = true;
            }
            tileA.setMatched(true);
            tileB.setMatched(true);
        }
        tileA = null;
        tileB = null;
    }

}