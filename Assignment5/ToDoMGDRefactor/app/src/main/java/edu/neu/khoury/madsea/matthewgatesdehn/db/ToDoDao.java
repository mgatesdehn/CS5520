package edu.neu.khoury.madsea.matthewgatesdehn.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
@Dao
public interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo toDo);

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getAllToDos();

    @Update
    void updateToDo(ToDo toDo);

    @Delete
    void deleteToDo(ToDo toDo);

    @Query("SELECT MAX(id) FROM todo_table")
    LiveData<Long> getMaxId();
}
