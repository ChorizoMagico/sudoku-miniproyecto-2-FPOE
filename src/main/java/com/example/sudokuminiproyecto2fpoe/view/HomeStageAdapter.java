package com.example.sudokuminiproyecto2fpoe.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Abstract base class for the Sudoku game's main stage/window implementation.
 * Implements the stage initialization contract and provides singleton access.
 * <p>
 * Manages the primary application window with fixed size and custom branding.
 */
public abstract class HomeStageAdapter extends Stage implements iHomeStage {

    /** Singleton instance of the home stage */
    protected static HomeStage instance;

    /** The main scene displayed in the stage */
    protected Scene scene;

    /** FXMLLoader for loading UI components */
    protected FXMLLoader fxmlLoader;

    /**
     * Initializes and displays the primary application stage with:
     * <ul>
     *   <li>Fixed title "Sudoku Intergaláctico"</li>
     *   <li>Non-resizable window</li>
     *   <li>Custom application icon</li>
     *   <li>Pre-configured scene content</li>
     * </ul>
     * Note: Requires scene to be set before calling this method.
     */
    @Override
    public void initStage() {
        setTitle("Sudoku Intergaláctico");
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/sudokuminiproyecto2fpoe/favicon.png"))));
        setScene(scene);
        show();
    }

    /**
     * Provides global access to the singleton stage instance.
     * Creates new instance if none exists (lazy initialization).
     *
     * @return The singleton HomeStage instance
     * @throws IOException If FXML loading fails during instance creation
     */
    public static HomeStage getInstance() throws IOException {
        if(instance == null){
            instance = new HomeStage();
        }
        return instance;
    }
}