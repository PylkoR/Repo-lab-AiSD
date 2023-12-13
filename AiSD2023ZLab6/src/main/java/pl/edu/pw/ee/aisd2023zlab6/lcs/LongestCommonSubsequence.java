package pl.edu.pw.ee.aisd2023zlab6.lcs;

import static pl.edu.pw.ee.aisd2023zlab6.lcs.Cell.Direction.*;

public class LongestCommonSubsequence {

    public String findLcs(String topText, String leftText) {
        int topTextLength = topText.length();
        int leftTextLength = leftText.length();
        String lcs;

        Cell[][] matrix = initializeMatrix(leftTextLength + 1, topTextLength + 1);

        for (int row = 1; row <= leftTextLength; row++) {
            for (int col = 1; col <= topTextLength; col++) {
                if (leftText.charAt(row - 1) == topText.charAt(col - 1)) {
                    matrix[row][col].setValue(matrix[row - 1][col - 1].getValue() + 1);
                    matrix[row][col].setDirection(DIAGONAL);
                } else {
                    if (matrix[row][col - 1].getValue() > matrix[row - 1][col].getValue()) {
                        matrix[row][col].setValue(matrix[row][col - 1].getValue());
                        matrix[row][col].setDirection(LEFT);
                    } else {
                        matrix[row][col].setValue(matrix[row - 1][col].getValue());
                        matrix[row][col].setDirection(TOP);
                    }
                }
            }
        }

        lcs = findLcsInMatrix(matrix, topText);

        return lcs;
    }

    private Cell[][] initializeMatrix(int rows, int cols) {
        Cell[][] matrix = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Cell();
            }
        }

        return matrix;
    }

    private String findLcsInMatrix(Cell[][] matrix, String topText) {
        StringBuilder lcs = new StringBuilder();
        int rowId = matrix.length - 1;
        int colId = matrix[0].length - 1;

        while (matrix[rowId][colId].getDirection() != NONE) {
            if (matrix[rowId][colId].getDirection() == DIAGONAL) {
                lcs.append(topText.charAt(colId - 1));
            }

            switch (matrix[rowId][colId].getDirection()) {
                case DIAGONAL:
                    rowId--;
                    colId--;
                    break;
                case LEFT:
                    colId--;
                    break;
                case TOP:
                    rowId--;
                    break;
            }
        }
        lcs.reverse();

        return lcs.toString();
    }
}
