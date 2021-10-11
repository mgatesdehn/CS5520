package edu.neu.khoury.madsea.matthewgatesdehn.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ToDoRoomDatabase extends RoomDatabase {

    public abstract ToDoDao toDoDao();

    private static volatile ToDoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ToDoRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "todo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
