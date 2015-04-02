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


public class SudokuSolver extends ActionBarActivity implements View.OnClickListener {

    Button mButtons[][];
    Button mClearButton;
    Button mSolveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku_solver);

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
        for (int i = 0; i < 9; i ++)
        {
            for (int j = 0; j < 9; j ++) {
                Button b = (Button) ((ViewGroup) v[i]).getChildAt(j);
                b.setText(String.valueOf(i) + '_' + String.valueOf(j));
                //b.setText("");

                mButtons[i][j] = b;
            }
        }
        //onClear();

        mClearButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_button:
                onClear();
                break;
            case R.id.solve_button:
                break;
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
