package edu.neu.khoury.madsea.matthewgatesdehn.data;


import androidx.lifecycle.LiveData;

import java.util.List;


import edu.neu.khoury.madsea.matthewgatesdehn.db.ToDoDao;
import edu.neu.khoury.madsea.matthewgatesdehn.db.ToDoRoomDatabase;

public class ToDoRepository {

    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;
    private final ToDoRoomDatabase toDoDatabase;

    private static ToDoRepository singleton;

    private ToDoRepository(final ToDoRoomDatabase database) {
        toDoDatabase = database;
        toDoDao = database.toDoDao();
        allToDos = toDoDao.getAllToDos();
    }

    public static ToDoRepository getInstance(final ToDoRoomDatabase database) {
        if (singleton == null) {
            singleton = new ToDoRepository(database);
        }
        return singleton;
    }

    public void addToDo(ToDo newToDo) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            toDoDao.insert(newToDo);
        });
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return allToDos;
    }

    public void updateToDo(ToDo toDo) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            toDoDao.updateToDo(toDo);
        });
    }

    public void deleteToDo(ToDo toDo) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            toDoDao.deleteToDo(toDo);
        });
    }
}
