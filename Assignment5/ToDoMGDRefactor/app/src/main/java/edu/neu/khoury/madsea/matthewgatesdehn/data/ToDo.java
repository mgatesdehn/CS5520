package edu.neu.khoury.madsea.matthewgatesdehn.data;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity(tableName = "todo_table")
public class ToDo {
    @PrimaryKey()
    @NonNull
    private long id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private LocalDateTime dueDate;

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    private LocalDateTime remindMeDate;

    @NonNull
    private Set<String> tags;

    @NonNull
    private boolean remindMe;

    @NonNull
    private boolean done;

    public ToDo(ToDo toDo) {
        setId(toDo.getId());
        setTags(toDo.getTags());
        setTitle(toDo.getTitle());
        setDescription((toDo.getDescription()));
        setDueDate(toDo.getDueDate());
        setRemindMe(toDo.isRemindMe());
        setRemindMeDate(toDo.getRemindMeDate());
        setDone(toDo.isDone());
    }

    public ToDo() {
        title = "";
        description = "";
        dueDate = LocalDateTime.now();
        remindMeDate = LocalDateTime.now();
        tags = new HashSet<String>();
        remindMe = false;
        done = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRemindMe() {
        return remindMe;
    }

    public void setRemindMe(boolean remindMe) {
        this.remindMe = remindMe;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getRemindMeDate() {
        return remindMeDate;
    }

    public void setRemindMeDate(LocalDateTime remindMeDate) {
        this.remindMeDate = remindMeDate;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof ToDo)) {
            return false;
        }

        ToDo otherToDo = (ToDo) o;

        return title == otherToDo.getTitle()
                && description == otherToDo.description
                && tags.equals(otherToDo.tags)
                && dueDate.isEqual(otherToDo.dueDate)
                && remindMe == otherToDo.remindMe
                && remindMeDate.isEqual(otherToDo.remindMeDate);
    }

    public static final DiffUtil.ItemCallback<ToDo> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ToDo>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull ToDo oldToDo, @NonNull ToDo newToDo) {
                    return oldToDo == newToDo;
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull ToDo oldToDo, @NonNull ToDo newToDo) {
                    return oldToDo.equals(newToDo);
                }
            };

    @Override
    public String toString() {
        return String.format("Title: %s\nDescription: %s\nTags: \nDueDate: \nRemindMe: " +
                        "\nRemindMeDate: ",
                title, description, tags.toString(), dueDate.toString(),
                String.valueOf(remindMe), remindMeDate.toString());
    }

    public long getId(){
        return id;
    }
}
