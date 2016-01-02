import java.util.*;
public class Matrix
{
    private double[][] data;
    int rows, cols;
    
    public Matrix(int rows, int cols) {
        //constructor for matrix of 0's
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }
    public Matrix(int rows, int cols, double[][] members)
    {
        //constructor for matrix with inputted elements
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
        for (int i = 0; i < members.length; i++) {
            for (int j = 0; j < members[i].length; j++) {
                data[i][j] = members[i][j];
            }
        }
    }
    public Matrix(int rows, int cols, int randMax) {
        //constructor for matrix with random elements <= randMax
        Random r = new Random();
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = (double)r.nextInt(randMax + 1);
            }
        }
    }
    public int[] getDimensions() {
        return new int[]{rows, cols};
    }
    public double[] getRow(int row) {
        return data[row-1];
    }
    public double[] getColumn(int col) {
        double[] rv = new double[rows];
        for (int i = 0; i < rows; i++) {
            rv[i] = data[i][col-1];
        }
        return rv;
    }
    public double[][] getData() {
        double[][] rvs = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rvs[i][j] = data[i][j];
            }
        }
        return rvs;
    }
}
