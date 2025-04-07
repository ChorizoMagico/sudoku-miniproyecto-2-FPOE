package com.example.sudokuminiproyecto2fpoe.controller;

import com.example.sudokuminiproyecto2fpoe.model.Checks;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static java.util.regex.Pattern.matches;

public class HomeController {

    @FXML
    private Label errorLabel;

    @FXML
    private GridPane sudokuGrid;

    private ArrayList<ArrayList<TextField>> board;
    private Checks checks;

    @FXML
    private void initialize() {
        checks = new Checks(board);
    }


    private void initializeBoardCells()
    {
        board = new ArrayList<>();
        for(int i = 0; i<6; i++)
        {
            board.add(new ArrayList<>());
            for(int j = 0; j<3; j++){
                TextField field = new TextField();
                field.setStyle("-fx-background-color: transparent;"+
                                "-fx-border-color:  #457B9D;"+
                                "-fx-pref-width: 151;"+
                                "-fx-pref-height: 108;");
                handleTextFields(i,j);
                board.get(i).add(field);
                sudokuGrid.add(field, j, i);
                }
            }
    }

    private void handleTextFields(int row, int col) {
        board.get(row).get(col).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[1-6]?")) {
                    board.get(row).get(col).setText(oldValue);
                }
            }
        });
    }


    @FXML
    void handleHelp(ActionEvent event) {


    }


}
