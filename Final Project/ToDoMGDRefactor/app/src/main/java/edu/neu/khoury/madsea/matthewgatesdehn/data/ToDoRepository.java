package edu.neu.khoury.madsea.matthewgatesdehn.data;


import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;


import edu.neu.khoury.madsea.matthewgatesdehn.db.ToDoDao;
import edu.neu.khoury.madsea.matthewgatesdehn.db.ToDoRoomDatabase;

public class ToDoRepository {

    private ToDoDao toDoDao;
    private final LiveData<List<ToDo>> allToDos;
    private final LiveData<Long> maxId;
    private final LiveData<Long> maxPosition;
    private final ToDoRoomDatabase toDoDatabase;
    private Long nextId;

    private static ToDoRepository singleton;

    private ToDoRepository(final ToDoRoomDatabase database) {
        toDoDatabase = database;
        toDoDao = database.toDoDao();

        maxId = toDoDao.getMaxId();
        maxPosition = toDoDao.getMaxPosition();
        allToDos = toDoDao.getAllToDos();
        maxId.observeForever(list -> {});
        maxPosition.observeForever(list -> {});
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
            toDoDao.deleteAndFixup(toDo);
        });
    }

    public void updateNextId(){

    }

    public long getNextId(){
        return (maxId.getValue()==null) ? 1 : maxId.getValue() + 1;}

    public int getNextPosition() {
        return (maxPosition.getValue()==null) ? 1 : (int)(maxPosition.getValue() + 1);}

    public void moveToDo(int fromPosition, int toPosition) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            toDoDao.moveToDo(fromPosition,toPosition);
        });
    }
}
