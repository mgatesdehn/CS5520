package edu.neu.khoury.madsea.matthewgatesdehn.db;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
@Dao
public interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ToDo toDo);

    @Query("SELECT * FROM todo_table ORDER BY position DESC")
    LiveData<List<ToDo>> getAllToDos();

    @Update
    void updateToDo(ToDo toDo);

    @Delete
    void deleteToDo(ToDo toDo);

    @Query("SELECT MAX(id) FROM todo_table")
    LiveData<Long> getMaxId();

    @Query("SELECT MAX(position) FROM todo_table")
    LiveData<Long> getMaxPosition();

    @Query("UPDATE todo_table SET position = position - 1 WHERE position > :pos")
    void deleteFixup(int pos);

    @Query("UPDATE todo_table SET position = position + 1 WHERE position >= :lower AND position < :upper")
    void incrementBetween(int lower, int upper);

    @Query("UPDATE todo_table SET position = position - 1 WHERE position >= :lower AND position < :upper")
    void decrementBetween(int lower, int upper);

    @Transaction
    public default void deleteAndFixup(ToDo todo) {
        deleteToDo(todo);
        deleteFixup(todo.getPosition());
    }

    @Query("UPDATE todo_table SET position = :toPosition WHERE position == :fromPosition")
    void updatePosition(int fromPosition, int toPosition);

    @Transaction
    default void moveToDo(int fromPosition, int toPosition){
        Log.d("ToDoDao", "toDoDao: moveToDo(from "+fromPosition+", to "+toPosition+")");
        if (fromPosition > toPosition){
            updatePosition(fromPosition, -1);
            incrementBetween(toPosition, fromPosition);
            updatePosition(-1, toPosition);
        }
        else{
            updatePosition(fromPosition,-1);
            decrementBetween(fromPosition + 1, toPosition + 1);
            updatePosition(-1, toPosition);
        }
    }
}
