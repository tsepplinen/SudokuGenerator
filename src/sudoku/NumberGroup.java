package sudoku;

/**
 * Created by tonis on 2017-04-27.
 */
public class NumberGroup {

    private boolean[] values;

    public NumberGroup() {
        values = new boolean[10];
    }

    public void add(int num) {
        values[num] = true;
    }

    public void remove(int num) {
        values[num] = false;
    }

    public boolean has(int num) {
        return values[num];
    }
}
