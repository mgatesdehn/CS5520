package edu.neu.khoury.madsea.matthewgatesdehn;

import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListBindingAdapter {

    @BindingAdapter("formatAndDisplayDate")
    public static void formatAndDisplayDate(EditText view, final LocalDateTime dateTime) {

        String outString = displayDateHelper(dateTime);

        view.setText(outString);
    }
    @BindingAdapter("formatAndDisplayDate")
    public static void formatAndDisplayDate(TextView view, final LocalDateTime dateTime) {

        String outString = displayDateHelper(dateTime);

        view.setText(outString);
    }

    private static String displayDateHelper(LocalDateTime dateTime){
        //Get current date time
        LocalDateTime now = LocalDateTime.now();
        String dateString = "";

        if (dateTime.toLocalDate().equals(now.toLocalDate())){
            dateString = "Today";
        }
        else if (dateTime.toLocalDate().compareTo(now.toLocalDate())==1){
            dateString = "Tomorrow";
        }
        else if (dateTime.toLocalDate().compareTo(now.toLocalDate())==-1){
            dateString = "Yesterday";
        }
        else if (dateTime.getYear() == now.getYear()){
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("MMM dd");
            dateString = dateTime.format(dayFormatter);
        }
        else{
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("MMM dd, YYYY");
            dateString = dateTime.format(dayFormatter);
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = dateTime.format(timeFormatter);

        return dateString + " at " + formattedTime;
    }
}
