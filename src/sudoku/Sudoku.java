package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sudoku {

    private final Random rng = new Random();
    List<NumberGroup> rows;
    List<NumberGroup> columns;
    List<NumberGroup> squares;
    List<SudokuCell> data;
    private int placeCounter;

    public Sudoku(List<Integer> data) throws IllegalArgumentException {
        createNumberGroups();
        initData(data);
    }

    public Sudoku(Sudoku toCheck) {
        createNumberGroups();
        this.data = new ArrayList<>(toCheck.data);
    }

    public Sudoku() {
        createNumberGroups();
        generate();

    }

    public Sudoku(long seed) {
        rng.setSeed(seed);
        createNumberGroups();
        generate();
    }

    private void generate() {
        createEmptyData();
        placeRandom(0, 0);
        System.out.println("placeCounter = " + placeCounter);
        unsolve();
    }

    // Removes enough numbers to meet the difficulty requirement.
    private void unsolve() {

    }

    private void createEmptyData() {
        data = new ArrayList<>(81);
        for (int i = 0; i < 81; i++) {
            data.add(new SudokuCell(0));
        }
    }

    private int randomFrom(List<Integer> valids) {
        int r = (int) (Math.random() * valids.size());
        return valids.remove(r);
    }

    private List<Integer> validNumbers(int x, int y) {
        List<Integer> valids = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (canPlace(i, x, y)) {
                valids.add(i);
            }
        }
        return valids;
    }

    private boolean placeRandom(int x, int y) {
        // If past last row
        if (y > 8) {
            return true;
        }

        List<Integer> valids = validNumbers(x, y);
        if (valids.isEmpty()) {
            return false;
        } else {
            boolean ok = false;
            while (!ok && !valids.isEmpty()) {
                int num = randomFrom(valids);
                place(num, x, y);
                int nx = x + 1;
                int ny = y;
                if (nx > 8) {
                    ny++;
                    nx = 0;
                }
                ok = placeRandom(nx, ny);
                if (!ok) {
                    remove(x, y);
                }
            }
            return ok;
        }

    }

    private void initData(List<Integer> data) throws IllegalArgumentException {
        this.data = new ArrayList<SudokuCell>(81);
        if (data.size() == 81) {
            for (int i = 0; i < 81; i++) {
                int y = i / 9;
                int x = i - (9 * y);
                int num = data.get(i);
                if (num < 0 || num > 9) {
                    throw new IllegalArgumentException("Data must contain only numbers 0 to 9, got " + num);
                }
                this.data.add(new SudokuCell(num));
                rows.get(y).add(num);
                columns.get(x).add(num);
                int thing = (y / 3) * 3 + x / 3;
                squares.get(thing).add(num);
            }
        } else {
            throw new IllegalArgumentException("Illegal sudoku size " + data.size() + ", must be 81");
        }
    }

    private void createNumberGroups() {
        rows = new ArrayList<>(9);
        columns = new ArrayList<>(9);
        squares = new ArrayList<>(9);
        for (int i = 0; i < 9; i++) {
            rows.add(new NumberGroup());
            columns.add(new NumberGroup());
            squares.add(new NumberGroup());
        }
    }

    public boolean canPlace(int num, int x, int y) {
        boolean value = false;
        try {
            value = !(rows.get(y).has(num) ||
                    columns.get(x).has(num) ||
                    squares.get(getSquareIndex(x, y)).has(num));
        } catch (Exception e) {
            System.out.println("y");
        }
        return value;
    }

    private int getSquareIndex(int x, int y) {
        return (y / 3) * 3 + x / 3;
    }

    public void place(int num, int x, int y) {
        this.placeCounter++;
        data.get(y * 9 + x).setNum(num);
        rows.get(y).add(num);
        columns.get(x).add(num);
        squares.get(getSquareIndex(x, y)).add(num);
    }

    public int get(int x, int y) {
        return data.get(y * 9 + x).getNum();
    }

    public boolean isEmpty(int x, int y) {
        return get(x, y) == 0;
    }

    public void remove(int x, int y) {
        int num = data.get(y * 9 + x).getNum();
        rows.get(y).remove(num);
        columns.get(x).remove(num);
        squares.get(getSquareIndex(x, y)).remove(num);
        place(0, x, y);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                sb.append(get(x, y));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
