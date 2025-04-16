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
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class HomeControllerAdapter implements iHomeController{

    @FXML
    protected Label errorLabel;

    @FXML
    protected Button helpButton;

    @FXML
    protected GridPane sudokuGrid;

    protected ArrayList<ArrayList<TextField>> board;
    protected Checks checks;
    protected Checks.Positions positions;

    @Override
    public void initializeBoardCells() {
        board = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < 6; j++) {
                TextField field = new TextField();
                field.setStyle(
                        "-fx-background-color: transparent;" +
                                "-fx-border-color: #BDE0FE;" +  // Azul claro suave
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 10px;" +
                                "-fx-background-radius: 10px;" +
                                "-fx-pref-width: 100;" +
                                "-fx-pref-height: 100;" +
                                "-fx-text-fill: #1D3557;" +       // Azul oscuro
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

    @Override
    public void handleTextFields(int row, int col) {
        board.get(row).get(col).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    board.get(row).get(col).setStyle(
                            "-fx-background-color: transparent;" +
                                    "-fx-border-color: #BDE0FE;" +  // Azul claro suave
                                    "-fx-border-width: 1px;" +
                                    "-fx-border-radius: 10px;" +
                                    "-fx-background-radius: 10px;" +
                                    "-fx-pref-width: 100;" +
                                    "-fx-pref-height: 100;" +
                                    "-fx-text-fill: #1D3557;" +       // Azul oscuro
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
                                            "-fx-border-color: #FF7F7F;" + // Coral suave (error)
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
                                    "-fx-background-color: #E0FBFC;" +        // Celeste claro playero
                                            "-fx-border-color: #80CED6;" +            // Turquesa suave
                                            "-fx-border-width: 1px;" +
                                            "-fx-border-radius: 10px;" +
                                            "-fx-background-radius: 10px;" +
                                            "-fx-pref-width: 100;" +
                                            "-fx-pref-height: 100;" +
                                            "-fx-text-fill: #000000;" +               // Texto negro
                                            "-fx-font-weight: bold;" +
                                            "-fx-font-size: 20px;" +
                                            "-fx-opacity: 1;"                         // Asegura que no se vea desvanecido
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

    @Override
    @FXML
    public void handleHelp(ActionEvent event) {
        positions.helpFunctionality();
    }
}
