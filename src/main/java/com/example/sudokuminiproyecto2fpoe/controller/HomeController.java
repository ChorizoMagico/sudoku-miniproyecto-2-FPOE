package com.example.sudokuminiproyecto2fpoe.controller;

import com.example.sudokuminiproyecto2fpoe.model.Checks;
import javafx.fxml.FXML;



/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Concrete implementation of the Sudoku home controller.
 * Extends the base adapter class to provide game initialization functionality.
 */
public class HomeController extends HomeControllerAdapter {

    /**
     * Initializes the Sudoku game components.
     * Automatically called by JavaFX after FXML loading.
     * Performs three key operations:
     * 1. Creates the visual board cells (via parent class)
     * 2. Initializes the game logic checker
     * 3. Prepares the initial board state with pre-filled positions
     */
    @FXML
    private void initialize() {
        initializeBoardCells();
        checks = new Checks(board);
        positions = checks.new Positions();
        positions.fillAllBlocks();
    }
}