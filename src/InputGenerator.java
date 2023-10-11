import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InputGenerator {
    private final String outputFileName = "." + File.separator + "minefield_output.txt";
    private List<char[][]> mineList = new ArrayList<>();
    private String mineField = "";
    private char[][] mineFieldArr;

    public InputGenerator(int rows, int columns, double minePercentage) throws IOException {
        generateMinefield(rows, columns, minePercentage);
        buildMinefieldString();
        writeFile();
    }

    private void generateMinefield(int rows, int columns, double minePercentage) {
        char[][] fieldArray = new char[rows][columns];
        Random random = new Random();
        double r;
        for (int i = 0; i < fieldArray.length; i++) {
            for (int j = 0; j < fieldArray[i].length; j++) {
                r = random.nextDouble();
                if (r < minePercentage) {
                    fieldArray[i][j] = '*';
                } else {
                    fieldArray[i][j] = '.';
                }
            }
        }
        mineFieldArr = fieldArray;
        mineList.add(fieldArray);
    }

    private void buildMinefieldString() {
        StringBuilder str = new StringBuilder();
        for (char[][] array : mineList) {
            if (array.length != 0) {
                str.append(array.length).append(" ").append(array[0].length).append("\n");
            } else {
                str.append("0 0\n");
            }
            for (char[] chars : array) {
                for (char aChar : chars) {
                    str.append(aChar);
                }
                str.append("\n");
            }
        }
        mineField = str.toString();
    }

    private void writeFile() throws IOException {
        try (PrintStream out = new PrintStream(new FileOutputStream(outputFileName))) {
            System.out.println(mineField);
        }
    }
}
