package edu.neu.khoury.madsea.matthewgatesdehn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDoRepository;

public class ToDoViewModel extends ViewModel {
    private static final String TAG = "ToDoViewModel";
    private boolean areEditing;


    private ToDoRepository repo;
    private final LiveData<List<ToDo>> toDoList;
    public MutableLiveData<ToDo> editToDo;

    public ToDoViewModel(ToDoRepository repo) {
        this.repo = repo;
        if (editToDo == null) {
            editToDo = new MutableLiveData<>();
            editToDo.setValue(new ToDo());
        }
        toDoList = repo.getAllToDos();
    }

    public void saveTask() {
        if (areEditing) {
            repo.updateToDo(editToDo.getValue());
        } else {
            ToDo newToDo = new ToDo();
            newToDo.setTitle(editToDo.getValue().getTitle());
            newToDo.setDescription(editToDo.getValue().getDescription());
            newToDo.setTags(editToDo.getValue().getTags());
            newToDo.setDueDate(editToDo.getValue().getDueDate());
            newToDo.setRemindMe(editToDo.getValue().isRemindMe());
            newToDo.setRemindMeDate(editToDo.getValue().getRemindMeDate());
            newToDo.setDone(editToDo.getValue().isDone());
            repo.addToDo(newToDo);
        }
    }

    public void setEditing(ToDo toDo) {
        editToDo.setValue(toDo);
        areEditing = true;
    }

    public void setCreating() {
        editToDo.setValue(new ToDo());
        areEditing = false;
    }

    public void setEditRemindMe(boolean remindMe){
        editToDo.getValue().setRemindMe(remindMe);
        editToDo.setValue(editToDo.getValue());
    }

    public void deleteToDo(ToDo toDo) {
        repo.deleteToDo(toDo);
    }

    public LiveData<List<ToDo>> getToDoList() {
        return toDoList;
    }

    public void updateToDo(ToDo toDo) {
        repo.updateToDo(toDo);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final ToDoRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((ToDoApp) application).getRepository();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ToDoViewModel(mRepository);
        }
    }
}
