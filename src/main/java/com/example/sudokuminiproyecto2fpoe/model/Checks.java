package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.*;


public class Checks {
    private ArrayList<ArrayList<TextField>> board;
    public int counter;
    private String errorMessageHorizontal;
    private String errorMessageVertical;
    private String errorMessageLocal;
    private String errorMessage;

    public Checks(ArrayList<ArrayList<TextField>> board) {
        this.board = board;
        counter = 0;
        errorMessageHorizontal = "";
        errorMessageVertical = "";
        errorMessageLocal = "";
        errorMessage = "";
    }

    public void setCounter(){
        counter++;
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

        for( int i = rowOrigin; i < 2+rowOrigin; i++) {
            for( int j = colOrigin; j < 3+colOrigin; j++) {
                if(board.get(i).get(j).getText().equals(number) && !(i == row && j == col)) {
                    errorMessageLocal = "Número repetido en ["+(i+1)+"]["+(1+j)+"]\n";
                    System.out.println(errorMessageLocal);
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
                System.out.println(errorMessageHorizontal);
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
                System.out.println(errorMessageVertical);
                return false;
            }
        }
        return true;
    }

    public boolean allChecks(int row, int col, String number) {
        errorMessage = "";
        Boolean verticalValid = verticalChecks(row, col, number);
        Boolean horizontalValid = horizontalChecks(row, col, number);
        Boolean blocksValid = smallBlocksChecks(row, col, number);
        if(!verticalValid || !horizontalValid || !blocksValid) {
            errorMessage = errorMessageLocal+errorMessageHorizontal+errorMessageVertical;
            return false;
        }
        return true;
    }

    public String isGameOver(){
        if(counter >= 36)
        {
            errorMessage = "Ganaste, chupon!";
        }
        return errorMessage;
    }

    public class Positions{
        public ArrayList<Integer> numberOfPositions;

        public Positions() {
            numberOfPositions = new ArrayList<>();

            for(int i = 1; i < 37; i++) {
                numberOfPositions.add(i);
            }
        }

        public void clearPositions(int row, int col){
            numberOfPositions.set((row * 6) + col, 0);
        }

        public void fillRandomNumbers(int row, int col) {
            Random random = new Random();
            int numberRow = random.nextInt(0, 2);
            int numberCol = random.nextInt(0, 3);
            int number = random.nextInt(1, 7);
            if(board.get(row+numberRow).get(col+numberCol).getText().isEmpty() && allChecks(row+numberRow, col+numberCol, String.valueOf(number)) ) {
                board.get(row+numberRow).get(col+numberCol).setText(String.valueOf(number));
                clearPositions(row+numberRow, col+numberCol);
            }
            else{
                fillRandomNumbers(row, col);
            }
        }

        public void fillAllBlocks(){
            for(int i = 0; i < 2; i++) {
                fillRandomNumbers(0, 0);
                fillRandomNumbers(0, 3);
                fillRandomNumbers(2, 0);
                fillRandomNumbers(2, 3);
                fillRandomNumbers(4, 0);
                fillRandomNumbers(4, 3);
            }
        }

        public void helpFunctionality() {
            Random random = new Random();

            List<Integer> emptyCells = new ArrayList<>();
            for (int i = 0; i < 36; i++) {
                if (numberOfPositions.get(i) != 0) {
                    emptyCells.add(i);
                }
            }

            if (emptyCells.isEmpty()) {
                return;
            }

            Collections.shuffle(emptyCells);

            for (int numberCell : emptyCells) {
                int row = numberCell / 6;
                int col = numberCell % 6;

                List<Integer> possibleNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
                Collections.shuffle(possibleNumbers);

                for (int num : possibleNumbers) {
                    String strNum = String.valueOf(num);
                    if (allChecks(row, col, strNum))
                    {
                        board.get(row).get(col).setText(strNum);
                        clearPositions(row, col);
                        return;
                    }
                }
            }
            //errorMessageVertical= "No se pudo encontrar una ayuda válida deje de oprimir el boton de ayuda.")
        }
    }

}
