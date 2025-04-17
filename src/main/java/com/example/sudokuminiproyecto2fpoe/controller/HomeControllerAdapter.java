package com.example.sudokuminiproyecto2fpoe.controller;

import com.example.sudokuminiproyecto2fpoe.model.Checks;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Abstract adapter class implementing the Sudoku home controller interface.
 * Provides base functionality for board initialization, text field handling, and help features.
 */
public abstract class HomeControllerAdapter implements iHomeController {

    /** Label for displaying error messages to the user */
    @FXML
    protected Label errorLabel;

    /** Button that triggers the help functionality */
    @FXML
    protected Button helpButton;

    /** GridPane containing the Sudoku board UI elements */
    @FXML
    protected GridPane sudokuGrid;

    /** 2D ArrayList representing the Sudoku board's text fields */
    protected ArrayList<ArrayList<TextField>> board;

    /** Validator for Sudoku game rules */
    protected Checks checks;

    /** Position tracker for Sudoku cells */
    protected Checks.Positions positions;

    /**
     * Initializes the 6x6 Sudoku board with styled text fields.
     * Creates and configures each TextField with:
     * - Light blue borders
     * - Dark blue text
     * - Centered alignment
     * - Fixed dimensions (100x100)
     * - Bold font (size 20)
     */
    @Override
    public void initializeBoardCells() {
        board = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                TextField field = new TextField();
                field.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-border-color: #BDE0FE;" +  // Light blue
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 10px;" +
                                "-fx-background-radius: 10px;" +
                                "-fx-pref-width: 100;" +
                                "-fx-pref-height: 100;" +
                                "-fx-text-fill: #1D3557;" +       // Dark blue
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 20px;"
                );
                field.setAlignment(Pos.CENTER);

                board.get(i).add(field);
                handleTextFields(i, j);
                sudokuGrid.add(field, j, i);
            }
        }
    }

    /**
     * Handles text input validation and styling for Sudoku cells.
     * Implements:
     * - Numeric input restriction (1-6)
     * - Visual feedback for valid/invalid entries
     * - Game state updates on valid moves
     *
     * @param row The row index (0-5) of the cell
     * @param col The column index (0-5) of the cell
     */
    @Override
    public void handleTextFields(int row, int col) {
        board.get(row).get(col).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    board.get(row).get(col).setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-border-color: #BDE0FE;" +  // Light blue
                                    "-fx-border-width: 1px;" +
                                    "-fx-border-radius: 10px;" +
                                    "-fx-background-radius: 10px;" +
                                    "-fx-pref-width: 100;" +
                                    "-fx-pref-height: 100;" +
                                    "-fx-text-fill: #1D3557;" +       // Dark blue
                                    "-fx-font-weight: bold;" +
                                    "-fx-font-size: 20px;"
                    );
                }
                else{
                    if (!newValue.matches("[1-6]?")) {
                        board.get(row).get(col).setText(oldValue);
                    }
                    else{
                        if(!checks.allChecks(row, col, newValue)){
                            board.get(row).get(col).setStyle(
                                    "-fx-background-color: transparent;" +
                                            "-fx-border-color: #FF7F7F;" + // Coral (error)
                                            "-fx-border-width: 1px;" +
                                            "-fx-border-radius: 10px;" +
                                            "-fx-background-radius: 10px;" +
                                            "-fx-pref-width: 100;" +
                                            "-fx-pref-height: 100;" +
                                            "-fx-text-fill: #1D3557;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-font-size: 20px;"
                            );
                        } else {
                            board.get(row).get(col).setStyle(
                                    "-fx-background-color: #E0FBFC;" +        // Light cyan
                                            "-fx-border-color: #80CED6;" +    // Turquoise
                                            "-fx-border-width: 1px;" +
                                            "-fx-border-radius: 10px;" +
                                            "-fx-background-radius: 10px;" +
                                            "-fx-pref-width: 100;" +
                                            "-fx-pref-height: 100;" +
                                            "-fx-text-fill: #000000;" +       // Black text
                                            "-fx-font-weight: bold;" +
                                            "-fx-font-size: 20px;" +
                                            "-fx-opacity: 1;"                 // Full visibility
                            );
                            board.get(row).get(col).setDisable(true);
                            positions.clearPositions(row, col);
                            checks.setCounter();
                        }
                    }
                    errorLabel.setText("Aviso: " + checks.isGameOver());
                }
            }
        });
    }

    /**
     * Handles the help button click event.
     * Delegates to the Positions class's help functionality.
     *
     * @param event The ActionEvent triggered by the button click
     */
    @Override
    @FXML
    public void handleHelp(ActionEvent event) {
        positions.helpFunctionality();
    }
}