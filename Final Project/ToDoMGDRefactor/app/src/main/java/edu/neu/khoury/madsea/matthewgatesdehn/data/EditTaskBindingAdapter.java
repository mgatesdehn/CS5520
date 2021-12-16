package edu.neu.khoury.madsea.matthewgatesdehn.data;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditTaskBindingAdapter {
    private static final String TAG = "EditTaskBindingAdapter";

    public interface textListener {
       public void updateText();
    }

    @BindingAdapter("dispatchOnLoseFocus")
    public static void dispatchOnLoseFocus(EditText view, final View.OnFocusChangeListener listener) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View focusedView, boolean hasFocus) {
                EditText editText = (EditText) focusedView;
                if (!hasFocus){
                    Log.d(TAG, "Lost focus and calling listener.\nCalling listner's updateText with the following text: "+editText.getText().toString());
                    listener.onFocusChange(editText, false);
                }
            }});
        }

    @BindingAdapter("dispatchOnLoseFocus")
    public static void dispatchOnLoseFocus(CheckBox view, final View.OnFocusChangeListener listener) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View focusedView, boolean hasFocus) {
                CheckBox checkBox = (CheckBox) focusedView;
                if (!hasFocus){
                    Log.d(TAG, "Lost focus and calling checkbox listener with:\n"+String.valueOf(checkBox.isChecked()));
                    listener.onFocusChange(checkBox, false);
                }
            }});
    }
    }




