package edu.neu.khoury.madsea.matthewgatesdehn;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int NEW_TASK_REQUEST = 1;
    public static final int EDIT_TASK_REQUEST = 2;
    private static final String TASK_LIST_KEY = "task_list";
    private TaskList taskList;
    private LinearLayout.LayoutParams taskLayoutParams;
    private static final int TASK_MARGIN = 4;
    private ViewGroup taskGroupScrollable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int px = (int) Utils.convertDpToPx(TASK_MARGIN, this);
        taskLayoutParams.setMargins(0, px, 0, px);

        taskGroupScrollable = findViewById(R.id.task_list);
        taskList = TaskList.getTaskList(this);
        initializeTaskView();

    }

    private void initializeTaskView() {
        taskGroupScrollable.removeAllViews();
        for (Bundle task : taskList.getTaskBundles()
        ) {
            createNewTaskView(task);
        }
    }

    public void createTask(View view) {
        Log.d(LOG_TAG, "New Task button clicked.");
        Intent intent = new Intent(this, EditTaskActivity.class);
        startActivityForResult(intent, NEW_TASK_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.d(LOG_TAG, "Received successful new task response. Creating new task.");
                Bundle newTaskBundle = data.getExtras();
                if (newTaskBundle == null) {
                    Log.d(LOG_TAG, "No bundle received. Creating default new task.");
                    newTaskBundle = TaskList.createDefaultTaskBundle(this);
                }
                taskList.addTask(newTaskBundle);
                Log.d(LOG_TAG, "Added new task bundle to taskList.");

                createNewTaskView(newTaskBundle);
            }
        } else if (requestCode == EDIT_TASK_REQUEST) {
            if (resultCode == RESULT_OK) {
                Log.d(LOG_TAG, "Received edited task bundle. ");
                Bundle editedTaskBundle = data.getExtras();
                updateTaskView(editedTaskBundle);
                taskList.replaceTaskBundle(editedTaskBundle);
            }
        }

    }

    private void updateTaskView(Bundle taskBundle) {
        View taskView = findViewById(taskBundle.getInt(TaskList.TASK_ID_KEY));
        ((TextView) taskView.findViewById(R.id.task_title)).setText(taskBundle.getString(TaskList.TASK_TITLE_KEY));
        ((TextView) taskView.findViewById(R.id.task_due_date)).setText(taskBundle.getString(TaskList.TASK_DUE_DATE_KEY));
    }

    private void createNewTaskView(Bundle newTaskBundle) {
        Log.d(LOG_TAG, "Inflating Layout");
        LayoutInflater myInflater = getLayoutInflater();
        View newTaskView = myInflater.inflate(R.layout.task_layout, taskGroupScrollable, false);
        taskGroupScrollable.addView(newTaskView);
        Log.d(LOG_TAG, "Adding params.");
        newTaskView.setLayoutParams(taskLayoutParams);
        newTaskView.setId(newTaskBundle.getInt(TaskList.TASK_ID_KEY));
        Log.d(LOG_TAG, "Setting task view text.");
        ((TextView) newTaskView.findViewById(R.id.task_title)).setText(newTaskBundle.getString(TaskList.TASK_TITLE_KEY));
        ((TextView) newTaskView.findViewById(R.id.task_due_date)).setText(newTaskBundle.getString(TaskList.TASK_DUE_DATE_KEY));
    }

    public void editTask(View view) {
        int TaskId = view.getId();
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtras(taskList.getTaskBundle(TaskId));
        startActivityForResult(intent, EDIT_TASK_REQUEST);
    }

    public void editTaskSpace(View view) {
        editTask((View) view.getParent());
    }

    public void editTaskTitleDate(View view) {
        int TaskId = ((View) view.getParent().getParent()).getId();
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtras(taskList.getTaskBundle(TaskId));
        startActivityForResult(intent, EDIT_TASK_REQUEST);
    }

    public void deleteTask(View view) {
        taskGroupScrollable.removeView((View) view.getParent());
        Log.d(LOG_TAG, "Delete view with ID: " + String.valueOf(((View) view.getParent()).getId()));
        taskList.deleteTask(((View) view.getParent()).getId());
    }
}