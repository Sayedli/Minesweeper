/** @author Sunny Ali
 *  Minesweeper
 */

import java.io.*;
import java.util.Scanner;

/**
 * The MineSweeper class represents a Minesweeper game.
 */
public class MineSweeper {

    /**
     * Constructs a MineSweeper instance and initiates the Minesweeper game.
     *
     * @param input The Scanner object to read input for the Minesweeper game.
     */
    public MineSweeper(Scanner input) {
        generateField(input);
    }
    /**
     * Generates minesweepr fields based on the provided input.
     *
     * @param input The Scanner object to read input for the Minesweeper game.
     */
    private void generateField(Scanner input) {
        int fieldCase = 1;
        int row = input.nextInt();
        int col = input.nextInt();

        while (!(row == 0 || col == 0)) {
            input.nextLine();
            int[][] field = new int[row][col];

            for (int i = 0; i < row; i++) {
                char[] fieldRow = input.nextLine().toCharArray();
                for (int j = 0; j < fieldRow.length; j++) {
                    if (fieldRow[j] == '*') {
                        incrementAdjacent(field, i, j);
                    }
                }
            }

            printField(fieldCase, field);

            row = input.nextInt();
            col = input.nextInt();
            if (!(row == 0 || col == 0))
                System.out.println();
            fieldCase++;
        }
    }
    /**
     * Increments the adjacent cells around a mine cell in the Minesweeper grid.
     *
     * @param field The Minesweeper grid.
     * @param row   The row index of the mine cell.
     * @param col   The column index of the mine cell.
     */
    private void incrementAdjacent(int[][] field, int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                try {
                    if (field[i][j] != -1)
                        field[i][j]++;
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    /**
     * Prints the Minesweeper field.
     *
     * @param fieldCase The field case number.
     * @param field     The Minesweeper grid.
     */
    private void printField(int fieldCase, int[][] field) {
        System.out.println("Field #" + fieldCase + ":");
        for (int[] row : field) {
            for (int cell : row) {
                if (cell == -1)
                    System.out.print("*");
                else
                    System.out.print(cell);
            }
            System.out.println();
        }
    }
    /**
     * The entry point of the Minesweeper game.
     *
     * @param args The command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("minesweeper_input.txt"));
            new MineSweeper(input);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
