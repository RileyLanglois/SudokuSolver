/* Welcome to Sudoku Solver!
 - This is where all of the logic is located
 - Refer to comments for deeper understanding
 - This current iteration is using the Backtracking Brute Force method
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MainSolver {

    public static void main(String args[]) {
        String csvFile = "test1Puzzle.txt"; //FIXME
        int[][] givenPuzzle = csvHandler(csvFile);
        int[][] solvedPuzzle = puzzleSolver(givenPuzzle);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(solvedPuzzle[row][col]);
            }
        }
    }

    /**
     * This method is used to determine if the previously placed number is valid in the sudoku grid.
     *
     * @param given = the given puzzle
     * @param row = the row of the currNum
     * @param col = the col of the currNum
     * @param currNum = the number just added to the puzzle
     * @return false if number is invalid, true if number is valid
     */
    public static boolean isValid(int[][] given, int row, int col, int currNum) {

        // Iterate over shared columns
        for (int c = 0; c < 9; c++) {
            if (given[row][c] == currNum) {
                return false;
            }
        }

        // Iterate over shared rows
        for (int r = 0; r < 9; r++) {
            if (given[r][col] == currNum) {
                return false;
            }
        }

        // Determine what box the currNum is in
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        // Iterate the box and ensure the number is unique
        for (int checkR = boxRow; checkR < boxRow + 3; checkR++) {
            for (int checkC = boxCol; checkC < boxCol + 3; checkC++) {
                if (checkR == row && checkC == col) {
                    continue;
                }

                if (given[checkR][checkC] == currNum) {
                    return false;
                }
            }
        }

        // If all else passed, return true
        return true;
    }

    public static int[][] puzzleSolver(int[][] given) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int currNum = given[row][col];
                if (currNum == 0) {
                    for (int i = 1; i < 10; i++) {
                        if(isValid(given, row, col, i)) {
                            given[row][col] = i;
                            System.out.println("Added number: " + i);
                            puzzleSolver(given);
                        } else {
                            given[row][col] = 0;
                        }
                    }
                }

            }
        }

        return given;
    }
    public static int[][] csvHandler(String file) {

        int[][] puzzle = new int[9][9];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int rowIndex = 0;

            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",");

                System.out.println("Row " + (rowIndex+1));

                for (int i = 0; i < cols.length; i++) {

                    //FIXME
                    //Not needed if remove dashes from input file
                    if (cols[i].equals("-")) {
                        cols[i] = "0";
                    }


                    puzzle[rowIndex][i] = Integer.parseInt(cols[i]);
                    System.out.println(" Columns " + (i+1) + ": " + cols[i]);
                }
                rowIndex++;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return puzzle;
    }
}
