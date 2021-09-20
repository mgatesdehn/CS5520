package edu.neu.mad_sea.matthew_gatesdehn.homeworklesson22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count;
    private TextView mCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCountView = findViewById(R.id.counterView);
        count = 0;

        if (savedInstanceState != null){
            count = savedInstanceState.getInt("count_value");
            mCountView.setText(String.valueOf(count));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count_value",count);
            }

    public void countUp(View view) {
        count ++;
        mCountView.setText(String.valueOf(count));
    }
}