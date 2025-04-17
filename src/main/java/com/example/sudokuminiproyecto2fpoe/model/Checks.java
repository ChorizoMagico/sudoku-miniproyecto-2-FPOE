package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.*;


/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Concrete implementation of Sudoku game validation logic.
 * Extends the base ChecksAdapter to provide game initialization functionality.
 */
public class Checks extends ChecksAdapter {

    /**
     * Constructs a new Sudoku game validator with the specified game board.
     * Initializes all validation state with:
     * - Empty error messages
     * - Move counter set to zero
     * - Reference to the game board UI components
     *
     * @param board The 6x6 grid of TextFields representing the Sudoku game board
     */
    public Checks(ArrayList<ArrayList<TextField>> board) {
        this.board = board;
        counter = 0;
        errorMessageHorizontal = "";
        errorMessageVertical = "";
        errorMessageLocal = "";
        errorMessage = "";
    }
}