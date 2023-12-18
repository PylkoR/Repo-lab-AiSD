package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

public class RodCutterWithSolution{

    public RodCutterResult cutRod(int[] prices, int rodLength) {

        RodCutterResult results = new RodCutterResult(rodLength);

        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= rodLength; i++) {

            for (int j = 1; j <= i; j++) {
                result = Integer.max(result, prices[j - 1] + results.getMaxSumResults(i-j));
            }

            results.addSolutions(i, result);
            results.addMaxSumResults(i, result);
        }

        return results;
    }
}
