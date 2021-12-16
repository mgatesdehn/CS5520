package edu.neu.khoury.madsea.matthewgatesdehn.data;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import edu.neu.khoury.madsea.matthewgatesdehn.databinding.ToDoItemViewBinding;

public class ToDoItemViewHolder extends RecyclerView.ViewHolder {

    public ToDoItemViewBinding binding;
    public static final String TAG = "ToDoItemViewHolder";

    public ToDoItemViewHolder(ToDoItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
    }

    public void bind(ToDo toDo) {
        binding.setToDo(toDo);
        Log.d(TAG, "toDo: "+toDo.toString());
        binding.executePendingBindings();
    }
}