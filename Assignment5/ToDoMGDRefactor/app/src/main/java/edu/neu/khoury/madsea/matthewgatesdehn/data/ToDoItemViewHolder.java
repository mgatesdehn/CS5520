package edu.neu.khoury.madsea.matthewgatesdehn.data;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import edu.neu.khoury.madsea.matthewgatesdehn.R;
import edu.neu.khoury.madsea.matthewgatesdehn.ToDoListFragment;
import edu.neu.khoury.madsea.matthewgatesdehn.ToDoViewModel;
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