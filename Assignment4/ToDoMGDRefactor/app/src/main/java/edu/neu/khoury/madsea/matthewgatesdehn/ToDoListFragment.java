package edu.neu.khoury.madsea.matthewgatesdehn;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalDateTime;

import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDo;
import edu.neu.khoury.madsea.matthewgatesdehn.data.ToDoRecyclerAdapter;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.FragmentTodolistBinding;
import edu.neu.khoury.madsea.matthewgatesdehn.listeners.OnToDoClickListener;

public class ToDoListFragment extends Fragment implements OnToDoClickListener {

    private FragmentTodolistBinding binding;
    private static final String TAG = "ToDoListFragment";
    private ToDoViewModel viewModel;

    protected RecyclerView mRecyclerView;
    protected ToDoRecyclerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ToDoViewModel.Factory factory = new ToDoViewModel.Factory(
                requireActivity().getApplication());

        viewModel = new ViewModelProvider(requireActivity(), factory).get(ToDoViewModel.class);
        binding = FragmentTodolistBinding.inflate(inflater, container, false);
        mRecyclerView = (RecyclerView) binding.recyclerView;

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.scrollToPosition(0);

        mAdapter = new ToDoRecyclerAdapter(this);
        viewModel.getToDoList().observe(getViewLifecycleOwner(), list -> {
            mAdapter.submitList(list);
        });
        mRecyclerView.setAdapter(mAdapter);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.newToDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setCreating();
                NavHostFragment.findNavController(ToDoListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onToDoClick(ToDo toDo) {
        viewModel.setEditing(toDo);
        NavHostFragment.findNavController(ToDoListFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

    @Override
    public void onToDoDeleteClick(ToDo toDo) {
        viewModel.deleteToDo(toDo);
    }

    @Override
    public void onToDoCheckboxClick(ToDo toDo, boolean done) {
        toDo.setDone(done);
        viewModel.updateToDo(toDo);
    }


}