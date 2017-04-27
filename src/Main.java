import sudoku.Sudoku;
import sudoku.SudokuSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");


        Integer[] dataArray =
                {
                        1, 2, 3, 4, 5, 6, 7, 0, 0,
                        7, 8, 9, 1, 2, 3, 4, 0, 0,
                        4, 5, 6, 7, 8, 9, 1, 0, 0,
                        9, 1, 2, 3, 4, 5, 6, 0, 0,
                        6, 7, 8, 9, 1, 2, 3, 0, 0,
                        3, 4, 5, 6, 7, 8, 9, 0, 0,
                        8, 9, 1, 2, 3, 4, 5, 0, 0,
                        5, 6, 7, 8, 9, 1, 2, 0, 0,
                        2, 3, 4, 5, 6, 7, 8, 0, 0
                };

        List<Integer> data = new ArrayList<Integer>(Arrays.asList(dataArray));
//        Sudoku sudoku = new Sudoku(data);
        Sudoku sudoku = new Sudoku();
        SudokuSolver sudokuSolver = new SudokuSolver();
        boolean solvable = sudokuSolver.hasOneSolution(sudoku);
        System.out.println("Solvable = " + solvable);
        System.out.println(sudoku);
    }
}
