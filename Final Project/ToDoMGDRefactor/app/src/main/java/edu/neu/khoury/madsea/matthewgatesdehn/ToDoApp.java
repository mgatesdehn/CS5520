package edu.neu.khoury.madsea.matthewgatesdehn;

import android.app.Application;
import android.util.Log;

import androidx.work.WorkManager;

import java.util.concurrent.Executor;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDoRepository;
import edu.neu.khoury.madsea.matthewgatesdehn.db.ToDoRoomDatabase;

public class ToDoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ToDoApp","Created ToDoApp.");

    }

    public ToDoRoomDatabase getDatabase() {
        return ToDoRoomDatabase.getInstance(this);
    }

    public ToDoRepository getRepository() {
        return ToDoRepository.getInstance(getDatabase());
    }

    public WorkManager getWorkManager() { return WorkManager.getInstance(this);
    }
}
