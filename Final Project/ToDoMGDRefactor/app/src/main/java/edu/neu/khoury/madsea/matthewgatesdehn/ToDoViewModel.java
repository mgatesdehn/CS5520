package edu.neu.khoury.madsea.matthewgatesdehn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDoRepository;

public class ToDoViewModel extends ViewModel {
    private static final String TAG = "ToDoViewModel";
    public static final String TODO_SHARED_PREFERENCES_KEY = "todo_shared_preferences";
    private static final String SAVED_TAGS_KEY = "saved_tags";
    private boolean areEditing;
    private WorkManager workManager;
    private ToDoRepository repo;
    private final LiveData<List<ToDo>> toDoList;
    private MutableLiveData<List<ToDo>> filteredToDoList;
    public MutableLiveData<ToDo> editToDo;
    private HashMap<String,Boolean> filteredTags;
    private boolean showAll;

    public ToDoViewModel(ToDoRepository repo, WorkManager workManager) {
        this.repo = repo;
        showAll = true;

        if (editToDo == null) {
            editToDo = new MutableLiveData<>();
            editToDo.setValue(new ToDo());
        }

        if (filteredToDoList == null){
            filteredToDoList = new MutableLiveData<>();
        }

        toDoList = repo.getAllToDos();
        toDoList.observeForever((list)->{
            filterToDoList();
        });

        filteredToDoList.setValue(toDoList.getValue());

        if(filteredTags == null){
            filteredTags = new HashMap<String, Boolean>();
        }
        this.workManager = workManager;
    }

    public void filterToDoList() {
        if (showAll){
            filteredToDoList.setValue(toDoList.getValue());
            return;
        }
        Set<String> filters = new HashSet<>();
        for (String tag: filteredTags.keySet()) {
            if (filteredTags.get(tag)==true){
                filters.add(tag);
            }
        }

        List<ToDo> outList = new ArrayList<>();

        for (ToDo todo: toDoList.getValue()){
            if (!Collections.disjoint(filters, todo.getTags())){
                outList.add(todo);
            }
        }

        filteredToDoList.setValue(outList);
        Log.d(TAG, "filteredToDoList: "+filteredToDoList.getValue().toString());
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
            newToDo.setPosition(repo.getNextPosition());
            newToDo.setLocation(editToDo.getValue().getLocation());
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

    public void updateTags(){

        if (toDoList.getValue().isEmpty()){
            filteredTags.clear();
            return;
        }

        Set<String> allTags = new HashSet<String>();

        for (ToDo todo: toDoList.getValue()
             ) {
            allTags.addAll(todo.getTags());
        }

        filteredTags.keySet().retainAll(allTags);
        allTags.removeAll(filteredTags.keySet());

        for (String tag: allTags
             ) {
            filteredTags.put(tag, false);
        }
    }

    public LiveData<List<ToDo>> getToDoList() {
        return filteredToDoList;
    }

    public void updateToDo(ToDo toDo) {
        repo.updateToDo(toDo);
    }

    public HashMap<String,Boolean> getFilteredTags(){
        updateTags();
        return filteredTags;
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

    public boolean getShowAll() {
        return showAll;
    }

    public void setShowAll(boolean b) {
        showAll = b;
    }

    public void moveToDo(int fromPosition, int toPosition) {
        repo.moveToDo(fromPosition, toPosition);
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

}
