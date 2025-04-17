package com.example.sudokuminiproyecto2fpoe.view;

import com.example.sudokuminiproyecto2fpoe.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Concrete implementation of the Sudoku game's main stage.
 * Handles the loading and initialization of the primary game interface.
 * <p>
 * Extends the base adapter to provide specific FXML loading behavior.
 */
public class HomeStage extends HomeStageAdapter {

    /**
     * Constructs the main application stage by:
     * <ol>
     *   <li>Loading the FXML layout from "home-view.fxml"</li>
     *   <li>Creating a new Scene with the loaded layout</li>
     *   <li>Initializing the stage via parent class</li>
     * </ol>
     *
     * @throws IOException If the FXML file cannot be loaded
     */
    public HomeStage() throws IOException {
        fxmlLoader = new FXMLLoader(
                Main.class.getResource("home-view.fxml")
        );
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        initStage();
    }
}