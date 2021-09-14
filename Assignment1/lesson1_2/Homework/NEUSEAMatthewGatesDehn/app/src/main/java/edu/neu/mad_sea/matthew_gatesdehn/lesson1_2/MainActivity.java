package edu.neu.mad_sea.matthew_gatesdehn.lesson1_2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.CountedCompleter;

import edu.neu.mad_sea.matthew_gatesdehn.lesson1_2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_messgae, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        mCount++;
        if (binding.showCount != null)
            binding.showCount.setText(Integer.toString(mCount));

            ColorStateList csl;

            if (mCount % 2 == 0)
                csl = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_200));

            else
                csl = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black));

            view.setBackgroundTintList(csl);
            binding.buttonZero.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.teal_700)));


    }

    public void zeroCounter(View view) {
        mCount = 0;
        if (binding.showCount != null)
            binding.showCount.setText(Integer.toString(mCount));

        view.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
    }
}