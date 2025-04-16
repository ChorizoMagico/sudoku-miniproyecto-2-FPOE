package com.example.sudokuminiproyecto2fpoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public interface iHomeController {

    @FXML
    void handleHelp(ActionEvent event);

    void handleTextFields(int row, int col);

    void initializeBoardCells();

}
