package com.example.sudokuminiproyecto2fpoe.controller;

/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Interface defining the controller methods for the Sudoku game's home screen.
 * This interface handles user interactions and board initialization.
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface iHomeController {

    /**
     * Handles the help button action event.
     *
     * @param event The ActionEvent triggered by the help button.
     */
    @FXML
    void handleHelp(ActionEvent event);

    /**
     * Processes user input in Sudoku grid text fields.
     *
     * @param row The row index of the text field (0-based).
     * @param col The column index of the text field (0-based).
     */
    void handleTextFields(int row, int col);

    /**
     * Initializes the Sudoku board cells with default or saved values.
     * Sets up the initial state of the game grid.
     */
    void initializeBoardCells();

}