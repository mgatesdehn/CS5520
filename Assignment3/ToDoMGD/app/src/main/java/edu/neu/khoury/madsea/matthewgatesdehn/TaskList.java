package edu.neu.khoury.madsea.matthewgatesdehn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class TaskList {

    private static final String LOG_TAG = TaskList.class.getSimpleName();
    public static final String TASK_TITLE_KEY = "task_title";
    public static final String TASK_DESCRIPTION_KEY = "task_description";
    public static final String TASK_TAG_KEY = "task_tag";
    public static final String TASK_DUE_DATE_KEY = "task_deadline";
    public static final String TASK_REMIND_ME_KEY = "task_remind_me";
    public static final String TASK_REMIND_ME_DATE_KEY = "task_remind_me_date";
    public static final String TASK_ID_KEY = "task_id";
    private static TaskList taskList;
    private ArrayList<Bundle> tasks;
    private ArrayList<Integer> ids;
    private int numTasks;

    private TaskList(Context context) {
        numTasks = 0;
        tasks = new ArrayList<Bundle>();
        ids = new ArrayList<Integer>();
    }

    public static TaskList getTaskList(Context context) {
        if (taskList == null) {
            taskList = new TaskList(context);
        }
        return taskList;
    }

    public static Bundle createDefaultTaskBundle(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString(TASK_TITLE_KEY, context.getResources().getString(R.string.default_task_title));
        bundle.putString(TASK_DESCRIPTION_KEY, context.getResources().getString(R.string.default_description));
        bundle.putString(TASK_TAG_KEY, context.getResources().getString(R.string.default_tag));
        bundle.putString(TASK_DUE_DATE_KEY, context.getResources().getString(R.string.default_due_date));
        bundle.putBoolean(TASK_REMIND_ME_KEY, context.getResources().getBoolean(R.bool.default_remind_me));
        bundle.putString(TASK_REMIND_ME_DATE_KEY, context.getResources().getString(R.string.default_remind_me_date));

        return bundle;
    }

    public void addTask(Bundle taskBundle) {
        Log.d(LOG_TAG, "Adding Task with id: " + String.valueOf(numTasks));
        taskBundle.putInt(TASK_ID_KEY, numTasks);
        tasks.add(taskBundle);
        ids.add(numTasks);
        numTasks++;
    }

    public void deleteTask(int id) {

        int idx = ids.indexOf(id);
        tasks.remove(idx);
        ids.remove(idx);
        Log.d(LOG_TAG, "TaskList contains: " + tasks.toString());
        Log.d(LOG_TAG, "ID list contains: " + ids.toString());
    }

    public ArrayList<Bundle> getTaskBundles() {
        ArrayList<Bundle> outArrayList = new ArrayList<>(tasks.size());
        for (Bundle bundle :
                tasks) {
            Bundle newBundle = new Bundle();
            newBundle.putAll(bundle);
            outArrayList.add(newBundle);
        }

        return outArrayList;
    }

    public Bundle getTaskBundle(int taskId) {
        int idx = ids.indexOf(taskId);
        Bundle newBundle = new Bundle();
        newBundle.putAll(tasks.get(idx));
        return newBundle;
    }

    public void replaceTaskBundle(Bundle editedTaskBundle) {
        int id = editedTaskBundle.getInt(TASK_ID_KEY);
        int idx = ids.indexOf(id);
        tasks.set(idx, editedTaskBundle);
    }
}
