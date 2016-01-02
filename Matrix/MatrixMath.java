public class MatrixMath
{
    static String[] errorMessages = {"Cannot Add Matrices with Unequal Dimensions"};
    public static class MatrixMathException extends Exception {
        public MatrixMathException(String message) {
            super(message);
        }
    }
    public static double add(Matrix a, Matrix b) throws MatrixMathException {
        int[] dim1 = a.getDimensions();
        int[] dim2 = b.getDimensions();
        if (dim1[0] != dim2[0] || dim1[1] != dim2[1]) {
            throw new MatrixMathException(errorMessages[0]);
        }
        double[][] dat1 = a.getData();
        double[][] dat2 = b.getData();
        double sum = 0;
        for (int i = 0; i < dat1.length; i++) {
            for (int j = 0; j < dat1[0].length; j++) {
                sum += (dat1[i][j] + dat2[i][j]);
            }
        }
        return sum;
    }
}
