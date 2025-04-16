package com.example.sudokuminiproyecto2fpoe.model;

public interface iChecks {


    void setCounter();

    boolean smallBlocksChecks(int row, int col, String number);

    boolean horizontalChecks(int row, int col, String number);

    boolean verticalChecks(int row, int col, String number);

    boolean allChecks(int row, int col, String number);

    String isGameOver();

}
