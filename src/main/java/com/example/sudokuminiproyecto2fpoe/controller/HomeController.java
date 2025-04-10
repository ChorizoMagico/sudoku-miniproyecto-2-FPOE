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
        initializeBoardCells();
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
                board.get(i).add(field);
                handleTextFields(i,j);
                sudokuGrid.add(field, j, i);
                }
            }
    }

    private void handleTextFields(int row, int col) {
        board.get(row).get(col).textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.matches("")){
                    board.get(row).get(col).setStyle("-fx-border-color:  #457B9D;");
                }

                if (!newValue.matches("[1-6]?")) {
                    board.get(row).get(col).setText(oldValue);
                }
                else{
                    if(!newValue.isEmpty()){
                        if(!checks.allChecks(row, col, newValue)){
                            board.get(row).get(col).setStyle("-fx-border-color: red;");
                        }
                        else{
                            board.get(row).get(col).setStyle("-fx-border-color: green;");
                            board.get(row).get(col).setDisable(true);
                        }
                    }
                    errorLabel.setText("Aviso: "+checks.isGameOver());
                }

            }
        });
    }


    @FXML
    void handleHelp(ActionEvent event) {


    }


}
