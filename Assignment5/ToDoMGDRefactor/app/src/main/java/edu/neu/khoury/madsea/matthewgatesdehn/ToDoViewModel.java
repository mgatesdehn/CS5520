package edu.neu.khoury.madsea.matthewgatesdehn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDoRepository;

public class ToDoViewModel extends ViewModel {
    private static final String TAG = "ToDoViewModel";
    private boolean areEditing;
    private WorkManager workManager;
    private ToDoRepository repo;
    private final LiveData<List<ToDo>> toDoList;
    public MutableLiveData<ToDo> editToDo;

    public ToDoViewModel(ToDoRepository repo, WorkManager workManager) {
        this.repo = repo;
        if (editToDo == null) {
            editToDo = new MutableLiveData<>();
            editToDo.setValue(new ToDo());
        }
        toDoList = repo.getAllToDos();
        this.workManager = workManager;

    }

    public void saveTask() {
        if (areEditing) {
            repo.updateToDo(editToDo.getValue());

            if (editToDo.getValue().isRemindMe()) {
                createNotification(editToDo.getValue().getId());
            } else {
                deleteNotification(editToDo.getValue().getId());
            }

        } else {
            if (editToDo.getValue().isRemindMe()) {
                createNotification(repo.getNextId());
            }
            else{
                deleteNotification(repo.getNextId());
            }
            Log.d(TAG, "New todo with nextid: "+Long.toString(repo.getNextId()));

            ToDo newToDo = new ToDo();
            Log.d(TAG, "Creating new todo with edit todo: "+editToDo.getValue().toString());
            newToDo.setTitle(editToDo.getValue().getTitle());
            newToDo.setDescription(editToDo.getValue().getDescription());
            newToDo.setTags(editToDo.getValue().getTags());
            newToDo.setDueDate(editToDo.getValue().getDueDate());
            newToDo.setRemindMe(editToDo.getValue().isRemindMe());
            newToDo.setRemindMeDate(editToDo.getValue().getRemindMeDate());
            newToDo.setDone(editToDo.getValue().isDone());
            newToDo.setId(repo.getNextId());
            repo.addToDo(newToDo);
        }
    }

    public void setEditing(ToDo toDo) {
        editToDo.setValue(toDo);
        areEditing = true;
    }

    public void setCreating() {
        editToDo.setValue(new ToDo());
        Log.d(TAG, "SetCreating");
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

        private final WorkManager workManager;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((ToDoApp) application).getRepository();
            workManager = ((ToDoApp) application).getWorkManager();
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ToDoViewModel(mRepository, workManager);
        }
    }

    public void deleteNotification(long id){
        String workTag = "remindMeWorkTag_" + Long.toString(id);
        workManager.cancelAllWorkByTag(workTag);
    }

    public void createNotification(long id) {
        String workTag = "remindMeWorkTag_" + Long.toString(id);
        LocalDateTime remindMeDate = editToDo.getValue().getRemindMeDate();
        long delay = remindMeDate.toEpochSecond(ZoneOffset.UTC) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        Log.d(TAG, "Setting notification with delay of (seconds): "+ Long.toString(delay));
        workManager.cancelAllWorkByTag(workTag);
        OneTimeWorkRequest notificationRequest =
                new OneTimeWorkRequest.Builder(RemindMeWorker.class)
                        .setInputData(createInputDataForRemindMe(id))
                        .setInitialDelay(delay, TimeUnit.SECONDS)
                        .addTag(workTag)
                        .build();

        workManager.enqueue(notificationRequest);

    }

    private Data createInputDataForRemindMe(long id) {

        Data.Builder builder = new Data.Builder();
        builder.putString(RemindMeWorker.KEY_TITLE, editToDo.getValue().getTitle());
        builder.putString(RemindMeWorker.KEY_DESCRIPTION, editToDo.getValue().getDescription());
        builder.putLong(RemindMeWorker.KEY_ID, id);

        return builder.build();
    }

}
