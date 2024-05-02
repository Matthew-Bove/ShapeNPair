package com.example.shapenpairgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the welcome screen
        FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("welcome-screen.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        WelcomeController welcomeController = welcomeLoader.getController();

        // Set the stage for the WelcomeController
        welcomeController.setStage(primaryStage);

        // Load the game screen (not setting it yet)
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("shapenpair-game.fxml"));
        Parent gameRoot = gameLoader.load();
        Scene gameScene = new Scene(gameRoot);

        // Set up an event handler in the WelcomeScreenController to transition to the game screen
        welcomeController.setGameScene(gameScene);

        Scene welcomeScene = new Scene(welcomeRoot);
        primaryStage.setScene(welcomeScene);
        primaryStage.setTitle("Shape 'n' Pair!");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
