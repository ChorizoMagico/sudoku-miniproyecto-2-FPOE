package com.example.sudokuminiproyecto2fpoe.model;

import javafx.scene.control.TextField;

import java.util.*;

/**
 * * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0* Abstract adapter class implementing the Sudoku game validation logic.
 * Provides base functionality for checking Sudoku rules and managing game state.
 */
public abstract class ChecksAdapter implements iChecks {

    /** 2D list representing the Sudoku game board text fields */
    protected ArrayList<ArrayList<TextField>> board;

    /** Counter tracking the number of valid moves made */
    protected int counter;

    /** Error message for horizontal (row) validation failures */
    protected String errorMessageHorizontal;

    /** Error message for vertical (column) validation failures */
    protected String errorMessageVertical;

    /** Error message for local block validation failures */
    protected String errorMessageLocal;

    /** Combined error message for all validation failures */
    protected String errorMessage;

    /**
     * Increments the move counter by one.
     * Called after each successful valid move.
     */
    @Override
    public void setCounter() {
        counter++;
    }

    /**
     * Validates if a number can be placed in its 2x3 sub-block without duplicates.
     *
     * @param row The row index (0-5) of the cell being checked
     * @param col The column index (0-5) of the cell being checked
     * @param number The candidate number as String (1-6)
     * @return true if valid placement, false if duplicate exists in sub-block
     */
    @Override
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
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Validates if a number can be placed in its row without duplicates.
     *
     * @param row The row index (0-5) to validate
     * @param col The column index (0-5) being checked (excluded from validation)
     * @param number The candidate number as String (1-6)
     * @return true if valid placement, false if duplicate exists in row
     */
    @Override
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

    /**
     * Validates if a number can be placed in its column without duplicates.
     *
     * @param row The row index (0-5) being checked (excluded from validation)
     * @param col The column index (0-5) to validate
     * @param number The candidate number as String (1-6)
     * @return true if valid placement, false if duplicate exists in column
     */
    @Override
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

    /**
     * Performs comprehensive validation (sub-block, row, and column checks).
     *
     * @param row The row index (0-5) of the cell
     * @param col The column index (0-5) of the cell
     * @param number The candidate number as String (1-6)
     * @return true only if all validation checks pass
     */
    @Override
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

    /**
     * Determines if the game has been won (all cells filled).
     *
     * @return Victory message if counter reaches 36 (6x6 grid),
     *         otherwise returns current error message
     */
    @Override
    public String isGameOver(){
        if(counter >= 36)
        {
            errorMessage = "Ganaste, chupon!";
        }
        return errorMessage;
    }

    /**
     * Inner class handling Sudoku cell position management.
     * Manages available positions and provides helper functionality.
     */
    public class Positions {
        /** List tracking available cell positions (1-36) */
        public ArrayList<Integer> numberOfPositions;

        /**
         * Initializes position tracker with all 36 cells (1-6 numbering).
         */
        public Positions() {
            numberOfPositions = new ArrayList<>();

            for(int i = 1; i < 37; i++) {
                numberOfPositions.add(i);
            }
        }

        /**
         * Marks a cell position as filled.
         *
         * @param row The row index (0-5) of the filled cell
         * @param col The column index (0-5) of the filled cell
         */
        public void clearPositions(int row, int col){
            numberOfPositions.set((row * 6) + col, 0);
        }

        /**
         * Attempts to fill a random valid number in a 2x3 block.
         *
         * @param row The base row index (0, 2, or 4) of the block
         * @param col The base column index (0 or 3) of the block
         */
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

        /**
         * Fills random valid numbers in all six 2x3 blocks.
         * Called during game initialization.
         */
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

        /**
         * Provides hint functionality by filling a random valid number.
         * Processes empty cells in random order until finding a valid placement.
         */
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
        }
    }
}