package dgalizzi.github.com.sudokusolver;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.widget.Toast.*;


public class SudokuSolver extends ActionBarActivity implements View.OnClickListener {

    Button[][] mButtons;
    Button mClearButton;
    Button mSolveButton;

    Solver mSolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_solver);

        mSolver = new Solver();
        mClearButton = (Button) findViewById(R.id.clear_button);
        mSolveButton = (Button) findViewById(R.id.solve_button);

        int[] linear_layout_ids = {R.id.buttons_1, R.id.buttons_2, R.id.buttons_3,
                                   R.id.buttons_4, R.id.buttons_5, R.id.buttons_6,
                                   R.id.buttons_7, R.id.buttons_8, R.id.buttons_9};
        LinearLayout[] v = new LinearLayout[9];
        for (int i = 0; i < linear_layout_ids.length; i++) {
            v[i] = (LinearLayout) findViewById(linear_layout_ids[i]);
        }



        mButtons = new Button[9][9];
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                Button b = (Button) ((ViewGroup) v[i]).getChildAt(j);
                b.setText(String.valueOf(i) + '_' + String.valueOf(j));
                //b.setText("");b.setHeight(b.getWidth());
                mButtons[i][j] = b;

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;
                        String s = b.getText().toString();
                        int n;
                        if (s == "") {
                            n = 1;
                        } else {
                            n = Integer.parseInt(s);
                            n++;
                        }
                        if (n == 10) {
                            n = 0;
                            b.setText(String.valueOf(""));
                        } else {
                            b.setText(String.valueOf(n));
                        }
                    }
                });
            }
        }
        //onClear();

        mClearButton.setOnClickListener(this);
        mSolveButton.setOnClickListener(this);


        // Test case
        String case1 = "040702800000000209928040000003807010000020000070901400000050193506000000009104085";
        String case_solution = "145792836367518249928643571453867912891425367672931458784256193516389724239174685";

        String case2 = "000400800000300000380070049470020300900000005005040096690080057000004000007005000";
        case1 = case2;
        //case1 = case_solution;
        int n;
        for (int i = 0; i < case1.length(); i ++) {
            n = Integer.parseInt(String.valueOf(case1.charAt(i)));
            if (n != 0)
                mButtons[i/9][i%9].setText(String.valueOf(n));
            else
                mButtons[i/9][i%9].setText("");
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_button:
                onClear();
                break;
            case R.id.solve_button:
                onSolve();
                break;
        }
    }

    private void onSolve() {
        int [][] sudoku = new int[9][9];
        String s;
        int n;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                s = mButtons[i][j].getText().toString();
                if (s == "") {
                    n = 0;
                } else {
                    n = Integer.parseInt(s);
                }
                sudoku[i][j] = n;
            }
        }



        if (mSolver.Solve(sudoku)) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    mButtons[i][j].setText(String.valueOf(sudoku[i][j]));
                }
            }
        } else {
            Toast.makeText(this, "No solution", Toast.LENGTH_LONG).show();
        }

    }

    public void onClear() {
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                mButtons[i][j].setText("");
            }
        }

    }


}
