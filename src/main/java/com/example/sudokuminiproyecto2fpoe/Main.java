package com.example.sudokuminiproyecto2fpoe;
import com.example.sudokuminiproyecto2fpoe.view.HomeStage;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main entry point for the Sudoku Intergaláctico JavaFX application.
 * Extends JavaFX's {@link Application} class to bootstrap the game.
 */
public class Main extends Application {

    /**
     * Standard Java entry method that launches the JavaFX application.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * JavaFX application start method.
     * Initializes and displays the primary game stage using the singleton {@link HomeStage}.
     *
     * @param primaryStage The default primary stage (unused in favor of {@link HomeStage})
     * @throws IOException If the FXML layout file cannot be loaded
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        HomeStage.getInstance();
    }
}
