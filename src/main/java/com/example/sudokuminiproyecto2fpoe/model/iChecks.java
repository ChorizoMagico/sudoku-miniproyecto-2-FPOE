package com.example.sudokuminiproyecto2fpoe.model;
/**
 * @author Juan Arias
 * @author Steven Aragón
 * @version 1.0
 * Defines validation methods for Sudoku game rules.
 * This interface encapsulates all necessary checks for maintaining Sudoku game integrity.
 */
public interface iChecks {

    /**
     * Updates the game's move counter.
     * Typically increments after each valid player move.
     */
    void setCounter();

    /**
     * Validates if a number can be placed in a 2x3 sub-block (small block) without duplicates.
     *
     * @param row The row index (0-5) of the cell being checked
     * @param col The column index (0-5) of the cell being checked
     * @param number The candidate number (as String) to validate
     * @return true if the number doesn't violate sub-block rules, false otherwise
     */
    boolean smallBlocksChecks(int row, int col, String number);

    /**
     * Validates if a number can be placed in its row without duplicates.
     *
     * @param row The row index (0-5) to check across
     * @param col The column index (0-5) of the cell being checked (unused in row check)
     * @param number The candidate number (as String) to validate
     * @return true if the number doesn't exist in the row, false otherwise
     */
    boolean horizontalChecks(int row, int col, String number);

    /**
     * Validates if a number can be placed in its column without duplicates.
     *
     * @param row The row index (0-5) of the cell being checked (unused in column check)
     * @param col The column index (0-5) to check down
     * @param number The candidate number (as String) to validate
     * @return true if the number doesn't exist in the column, false otherwise
     */
    boolean verticalChecks(int row, int col, String number);

    /**
     * Performs all validation checks (small block, row, and column) simultaneously.
     *
     * @param row The row index (0-5) of the cell being checked
     * @param col The column index (0-5) of the cell being checked
     * @param number The candidate number (as String) to validate
     * @return true only if the number passes all validation checks
     */
    boolean allChecks(int row, int col, String number);

    /**
     * Determines if the game has been completed successfully.
     *
     * @return A status message indicating either:
     *         - Game completion (all cells filled correctly), or
     *         - Remaining moves count if game isn't over
     */
    String isGameOver();
}
