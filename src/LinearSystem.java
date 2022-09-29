/**
 * This class contains several operations for matrices.
 * 
 * @author Emir Yuksel
 * @version 1.0
 */
public final class LinearSystem {

    private LinearSystem() {

    }

    /**
     * Method for forward substitution.
     * 
     * @param L
     * @param b
     * @return y
     */

    public static int[] forwardSubstitution(int[][] L, int[] b) {
        int[] y = new int[b.length];

        y[0] = b[0] / L[0][0];

        for (int i = 1; i < b.length; i++) {
            double firstMult = 1.0 / L[i][i];
            double secondMult;
            int resultSum = 0;
            for (int k = 0; k < i; k++) {
                resultSum = L[i][k] * y[k] + resultSum;
            }
            secondMult = b[i] - resultSum;
            y[i] = (int) (firstMult * secondMult);
        }

        return y;
    }

    /**
     * Method for backward substitution.
     * 
     * @param R
     * @param y
     * @return x
     */

    public static int[] backwardSubstitution(int[][] R, int[] y) {
        int[] x = new int[y.length];
        int n = y.length - 1;
        x[n] = y[n] / R[n][n];
        for (int i = n - 1; i >= 0; i--) {
            double firstMult = 1.0 / R[i][i];
            double secondMult;
            int resultSum = 0;
            for (int k = i + 1; k <= n; k++) {
                resultSum = R[i][k] * x[k] + resultSum;
            }

            secondMult = y[i] - resultSum;
            x[i] = (int) (firstMult * secondMult);
        }

        return x;
    }

    /**
     * Method to solve a linear system of equations.
     * 
     * @param L
     * @param R
     * @param b
     * @return x
     */

    public static int[] solve(int[][] L, int[][] R, int[] b) {
        int[] y = forwardSubstitution(L, b);

        int[] x = backwardSubstitution(R, y);

        return x;
    }

}