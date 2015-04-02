package dgalizzi.github.com.sudokusolver;

/**
 * Created by dgalizzi on 4/2/15.
 */
public class Solver {

    int[][] mSudoku;

    Solver() {
        mSudoku = new int[9][9];
    }

    private boolean Solve(int i, int j, int[][] initial) {
        if (i == 8 && j == 9) return true;
        if (j == 9) { j = 0; i ++; }
        if (initial[i][j] != 0) return Solve(i, j+1, initial);
        for (int n = 1; n <= 9; n ++) {
            mSudoku[i][j] = n;
            if (isValid()) {
                if (Solve(i, j + 1, initial))
                    return true;
            }
        }
        mSudoku[i][j] = 0;
        return false;
    }

    private boolean Solve() {
        int[][] initial = new int[9][9];
        for (int i = 0; i < 9; i ++)
            for (int j = 0; j < 9; j ++)
                initial[i][j] = mSudoku[i][j];
        return Solve(0, 0, initial);
    }

    public boolean Solve(int[][] sudoku) {
        mSudoku = sudoku;
        if (!isValid())
            return false; // TODO: Throw exception?

        return Solve();
    }

    public boolean isValid() {
        int n;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                n = mSudoku[i][j];
                if (n == 0) continue;

                // Check horizontal
                for (int k = 0; k < 9; k ++)
                {
                    if (k == j || mSudoku[i][k] == 0)
                        continue;
                    if (mSudoku[i][k] == n)
                        return false; // duplicate
                }

                // Check vertical
                for (int k = 0; k < 9; k ++)
                {
                    if (k == i || mSudoku[k][j] == 0)
                        continue;
                    if (mSudoku[k][j] == n)
                        return false; // duplicate
                }

                // Check block
                int block_i = (i/3)*3;
                int block_j = (j/3)*3;
                for (int a = block_i; a < block_i + 3; a ++) {
                    for (int b = block_j; b < block_j + 3; b ++) {
                        if ( (a == i && b == j) || mSudoku[a][b] == 0)
                            continue;
                        if (mSudoku[a][b] == n)
                            return false;
                    }

                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] sudoku) {
        mSudoku = sudoku;
        return isValid();
    }
}
