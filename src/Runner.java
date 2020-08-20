public class Runner {


    public static void main(String[] args) {
        int[][] grid =  {{0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,1,0,0,0,0,0,0},
                        {0,0,0,0,0,0,2,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0}};

        SudokuGrid sudokuGrid = new SudokuGrid(grid);
        sudokuGrid.trouveSolution(0,0);
    }
}
