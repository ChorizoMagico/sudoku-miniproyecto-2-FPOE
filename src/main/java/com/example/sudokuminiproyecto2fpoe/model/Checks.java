package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Checks {
    private ArrayList<ArrayList<TextField>> board;
    int counter;
    String errorMessageHorizontal;
    String errorMessageVertical;
    String errorMessageLocal;
    String errorMessage;

    public Checks(ArrayList<ArrayList<TextField>> board) {
        this.board = board;
        counter = 0;
        errorMessageHorizontal = "";
        errorMessageVertical = "";
        errorMessageLocal = "";
        errorMessage = "";
    }

    public boolean smallBlocksChecks(int row, int col, String number) {
        errorMessageLocal = "";
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
                    errorMessageLocal= "Número repetido en ["+(i+1)+"]["+(1+j)+"]\n";
                    return false;
                }
            }
        }
        return true;
    }

    public boolean horizontalChecks(int row, int col, String number) {
        errorMessageHorizontal = "";
        for( int i = 0; i < 6; i++) {
            if(board.get(row).get(i).getText().equals(number) && i!=col) {
                errorMessageHorizontal= "Número repetido en ["+(row+1)+"]["+(1+i)+"]\n";
                return false;
            }
        }
        return true;
    }

    public boolean verticalChecks(int row, int col, String number) {
        errorMessageVertical = "";
        for( int i = 0; i < 6; i++) {
            if(board.get(i).get(col).getText().equals(number) && i!=row) {
                errorMessageVertical= "Número repetido en ["+(i+1)+"]["+(1+col)+"]\n";
                return false;
            }
        }
        return true;
    }

    public boolean allChecks(int row, int col, String number) {
        errorMessage = "";
        if(!verticalChecks(row, col, number) || !smallBlocksChecks(row, col, number) || !horizontalChecks(row, col, number)) {
            errorMessage = errorMessageLocal+errorMessageHorizontal+errorMessageVertical;
            return false;
        }
        counter++;
        return true;
    }

    public String isGameOver(){
        if(counter >= 36)
        {
            errorMessage = "Ganaste, chupon!";
        }
        return errorMessage;
    }

}
