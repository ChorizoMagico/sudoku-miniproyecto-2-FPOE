package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Checks {
    private ArrayList<ArrayList<TextField>> board;
    int counter;

    public Checks(ArrayList<ArrayList<TextField>> board) {
        this.board = board;
        counter = 0;
    }

    private boolean smallBlocksChecks(int row, int col, String number) {
        int rowOrigin = row;
        int colOrigin = col;

        if(row % 2 == 1){
            rowOrigin--;
        }

        if(col % 3 > 0){
            colOrigin = col - (col % 3);
        }

        for( int i = rowOrigin; i < 2; i++) {
            for( int j = colOrigin; j < 3; j++) {
                if(board.get(i).get(j).getText().equals(number) && i!=row && j!=col) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean horizontalChecks(int row, int col, String number) {
        for( int i = 0; i < 6; i++) {
            if(board.get(row).get(i).getText().equals(number) && i!=col) {
                return false;
            }
        }
        return true;
    }

    private boolean verticalChecks(int row, int col, String number) {
        for( int i = 0; i < 6; i++) {
            if(board.get(i).get(col).getText().equals(number) && i!=row) {
                return false;
            }
        }
        return true;
    }

    private boolean allChecks(int row, int col, String number) {
        if(!verticalChecks(row, col, number) || !smallBlocksChecks(row, col, number) || !horizontalChecks(row, col, number)) {
            return false;
        }
        counter++;
        return true;
    }
}
