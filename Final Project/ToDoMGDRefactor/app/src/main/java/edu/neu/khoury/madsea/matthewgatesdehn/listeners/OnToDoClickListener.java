package edu.neu.khoury.madsea.matthewgatesdehn.listeners;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;

public interface OnToDoClickListener {
    void onToDoClick(ToDo toDo);
    void onToDoDeleteClick(ToDo toDo);
    void onToDoCheckboxClick(ToDo toDo, boolean done);
    void onItemMove(int fromPosition, int toPosition);
}
