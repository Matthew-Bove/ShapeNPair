package com.example.shapenpairgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    private Stage stage;
    private Stage stage2;
    private Scene gameScene;
    private Scene gameScene2;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    //
    public void setStage(Stage stage, Stage stage2) {
        this.stage = stage;
        this.stage2 = stage2;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }
    //

    @FXML
    private void handleQuitButton(ActionEvent event) {
        // Should Exit game
    }
    @FXML
    private void handlePlayer1Button(ActionEvent event) {
        // Enters into 1 player mode
       stage.setScene(gameScene);
    }

    @FXML
    void handlePlayer2Button(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shapenpair-game2PlayerMode.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //get the Stage object from the ActionEvent
         stage2 = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle("ShapeNPair! VS.");
        stage.setScene(scene);
        stage.show();}

}
