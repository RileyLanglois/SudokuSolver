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

    }

    public int nextVarCalc(int[][] given) {
        
    }

    public static int[][] puzzleSolver(int[][] given) {

        int nextVar = nextVarCalc(given);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int currNum = given[i][j];
                if (currNum == 0) {
                    given[i][j] = nextVar;
                }

            }
        }
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
