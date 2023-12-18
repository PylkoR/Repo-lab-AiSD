package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterResult {

    private int[] maxSumResults;
    private int[] solutions;

    public RodCutterResult(int rodLength) {
        this.maxSumResults = new int[rodLength + 1];
        this.solutions = new int[rodLength + 1];
    }

    public void addSolutions(int index, int value) {
        solutions[index] = value;
    }

    public void addMaxSumResults(int index, int value) {
        maxSumResults[index] = value;
    }

    public int getMaxSumResults(int index) {
        return maxSumResults[index];
    }

    public int[] getSolutions() {
        return solutions;
    }
}
