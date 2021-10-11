package edu.neu.khoury.madsea.matthewgatesdehn.listeners;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;

public interface OnToDoClickListener {
    public void onToDoClick(ToDo toDo);
    public void onToDoDeleteClick(ToDo toDo);
    public void onToDoCheckboxClick(ToDo toDo, boolean done);
}
