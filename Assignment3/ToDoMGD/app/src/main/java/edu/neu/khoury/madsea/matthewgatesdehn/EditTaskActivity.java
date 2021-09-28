package edu.neu.khoury.madsea.matthewgatesdehn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Array;
import java.util.Arrays;

public class EditTaskActivity extends AppCompatActivity {

    private static final String LOG_TAG = EditTaskActivity.class.getSimpleName();
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText dueDateEditText;
    private EditText remindMeDateEditText;
    private CheckBox remindMeCheckbox;
    private Spinner tagsDropdownSpinner;
    private Bundle newTaskBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        tagsDropdownSpinner = (Spinner) findViewById(R.id.tags_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tag_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tagsDropdownSpinner.setAdapter(adapter);

        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        descriptionEditText = (EditText) findViewById(R.id.description_edit_text);
        dueDateEditText = (EditText) findViewById(R.id.due_date_edit_text);
        remindMeDateEditText = (EditText) findViewById(R.id.remind_me_date_edit_text);
        remindMeCheckbox = (CheckBox) findViewById(R.id.remind_me_checkbox);

        Bundle taskBundle = getIntent().getExtras();
        if (taskBundle != null) {
            this.newTaskBundle = taskBundle;
            titleEditText.setText(newTaskBundle.getString(TaskList.TASK_TITLE_KEY));
            descriptionEditText.setText(newTaskBundle.getString(TaskList.TASK_DESCRIPTION_KEY));
            dueDateEditText.setText(newTaskBundle.getString(TaskList.TASK_DUE_DATE_KEY));
            remindMeDateEditText.setText(newTaskBundle.getString(TaskList.TASK_REMIND_ME_DATE_KEY));
            remindMeCheckbox.setChecked(newTaskBundle.getBoolean(TaskList.TASK_REMIND_ME_KEY));
            String[] tag_array = getResources().getStringArray(R.array.tag_array);
            String tag = newTaskBundle.getString(TaskList.TASK_TAG_KEY);
            int idx = Utils.indexOfStringArray(tag_array, tag);
            Log.d(LOG_TAG,"Index of "+tag+": "+String.valueOf(idx));
            Log.d(LOG_TAG, Arrays.toString(tag_array));
            tagsDropdownSpinner.setSelection(idx);
        } else {
            newTaskBundle = new Bundle();
        }
    }


    public void saveTask(View view) {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String dueDate = dueDateEditText.getText().toString();
        String remindMeDate = remindMeDateEditText.getText().toString();
        boolean remindMe = remindMeCheckbox.isChecked();
        String tag = tagsDropdownSpinner.getSelectedItem().toString();

        newTaskBundle.putString(TaskList.TASK_TITLE_KEY, title);
        newTaskBundle.putString(TaskList.TASK_DESCRIPTION_KEY, description);
        newTaskBundle.putString(TaskList.TASK_DUE_DATE_KEY, dueDate);
        newTaskBundle.putString(TaskList.TASK_REMIND_ME_DATE_KEY, remindMeDate);
        newTaskBundle.putBoolean(TaskList.TASK_REMIND_ME_KEY, remindMe);
        newTaskBundle.putString(TaskList.TASK_TAG_KEY, tag);

        Intent newTaskReply = new Intent();
        newTaskReply.putExtras(newTaskBundle);
        setResult(RESULT_OK, newTaskReply);
        finish();
    }

    public void cancelTask(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}