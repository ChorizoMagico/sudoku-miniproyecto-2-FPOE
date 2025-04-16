package com.example.sudokuminiproyecto2fpoe.controller;

import com.example.sudokuminiproyecto2fpoe.model.Checks;
import javafx.fxml.FXML;

public class HomeController extends HomeControllerAdapter {

    @FXML
    private void initialize() {
        initializeBoardCells();
        checks = new Checks(board);
        positions = checks.new Positions();
        positions.fillAllBlocks();
    }
}