package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.*;


public class Checks extends ChecksAdapter{

    public Checks(ArrayList<ArrayList<TextField>> board) {
        this.board = board;
        counter = 0;
        errorMessageHorizontal = "";
        errorMessageVertical = "";
        errorMessageLocal = "";
        errorMessage = "";
    }

}
