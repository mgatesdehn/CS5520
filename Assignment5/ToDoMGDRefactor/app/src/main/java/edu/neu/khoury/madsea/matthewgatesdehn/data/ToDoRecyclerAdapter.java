package edu.neu.khoury.madsea.matthewgatesdehn.data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.neu.khoury.madsea.matthewgatesdehn.listeners.OnToDoClickListener;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.ToDoItemViewBinding;

public class ToDoRecyclerAdapter extends ListAdapter<ToDo, ToDoItemViewHolder> {
    private static final String TAG = "ToDoRecyclerAdapter";
    private OnToDoClickListener listener;

    public ToDoRecyclerAdapter (OnToDoClickListener listener){
        super(ToDo.DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ToDoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Creating new view holder.");
        return new ToDoItemViewHolder(ToDoItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoItemViewHolder holder, int position) {
        final int pos = position;
        holder.bind(getCurrentList().get(pos));
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onToDoClick(new ToDo(getItem(holder.getLayoutPosition())));
            }
        });
        holder.binding.doneCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onToDoCheckboxClick(getItem(holder.getLayoutPosition()), holder.binding.doneCheckBox.isChecked());
            }
        });
        holder.binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onToDoDeleteClick(getItem(holder.getLayoutPosition()));
            }
        });
    }

    @Override
    public void submitList(final List<ToDo> list) {
        super.submitList(list != null ? new ArrayList<ToDo>(list) : null);
    }
}

