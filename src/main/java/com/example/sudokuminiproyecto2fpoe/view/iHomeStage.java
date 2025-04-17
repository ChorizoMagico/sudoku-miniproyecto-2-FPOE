package com.example.sudokuminiproyecto2fpoe.view;

/**
 * Defines the contract for initializing a stage/window in the Sudoku application.
 * Implementing classes must provide stage initialization logic.
 */
public interface iHomeStage {

    /**
     * @author Juan Arias
     * @author Steven Aragón
     * @version 1.0
     * Initializes and configures the application stage/window.
     * Typically includes:
     * - Setting the stage title
     * - Configuring scene dimensions
     * - Loading FXML components
     * - Setting event handlers
     */
    void initStage();
}