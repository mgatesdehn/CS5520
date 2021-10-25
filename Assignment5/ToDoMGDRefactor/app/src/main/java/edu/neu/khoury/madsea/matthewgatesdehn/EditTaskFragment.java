package edu.neu.khoury.madsea.matthewgatesdehn;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalDateTime;
import java.util.Date;

import edu.neu.khoury.madsea.matthewgatesdehn.databinding.FragmentEdittaskBinding;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.DateTimePickerBinding;

public class EditTaskFragment extends Fragment {

    private static final String TAG = "EditTaskFragment";
    private FragmentEdittaskBinding binding;
    private DateTimePickerBinding dateTimePickerBinding;
    private ToDoViewModel viewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentEdittaskBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToDoViewModel.Factory factory = new ToDoViewModel.Factory(
                requireActivity().getApplication());

        viewModel = new ViewModelProvider(requireActivity(), factory).get(ToDoViewModel.class);
        binding.setViewmodel(viewModel);
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.titleEditText.clearFocus();
                binding.descriptionEditText.clearFocus();
                binding.remindMeCheckbox.clearFocus();
                viewModel.saveTask();

                NavHostFragment.findNavController(EditTaskFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        binding.dueDateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked on Due Date Edit Text.");
                showDueDatePicker(view);
            }
        });
        binding.remindMeDateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked on Remind Me Date Edit Text.");
                showRemindMeDatePicker(view);
            }
        });

        binding.remindMeCheckbox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Clicked on Remind Me checkbox.");
                remindMeClicked(view);
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditTaskFragment.this)
                        .navigate(R.id.action_SecondFragment_pop);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void remindMeClicked(View v){
        binding.titleEditText.clearFocus();
        binding.descriptionEditText.clearFocus();
        binding.remindMeCheckbox.clearFocus();
        viewModel.editToDo.getValue().setRemindMe(binding.remindMeCheckbox.isChecked());
        binding.remindMeDateEditText.setEnabled(binding.remindMeCheckbox.isChecked());
    }

    public void showDueDatePicker(View v) {
        binding.titleEditText.clearFocus();
        binding.descriptionEditText.clearFocus();
        binding.remindMeCheckbox.clearFocus();
        dateTimePickerBinding = DateTimePickerBinding.inflate(requireActivity().getLayoutInflater());
        //final View dialogView = View.inflate(requireActivity(), R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();

        LocalDateTime dateTime = viewModel.editToDo.getValue().getDueDate();

        dateTimePickerBinding.datePicker.updateDate(dateTime.getYear(),dateTime.getMonthValue()-1,dateTime.getDayOfMonth());
        dateTimePickerBinding.timePicker.setHour(dateTime.getHour());
        dateTimePickerBinding.timePicker.setMinute(dateTime.getMinute());

        dateTimePickerBinding.dateTimeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = dateTimePickerBinding.datePicker;
                TimePicker timePicker = dateTimePickerBinding.timePicker;

                LocalDateTime dateTime = LocalDateTime.of(datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth(),timePicker.getHour(),timePicker.getMinute());

                viewModel.editToDo.getValue().setDueDate(dateTime);
                viewModel.editToDo.setValue(viewModel.editToDo.getValue());

                alertDialog.dismiss();
            }});
        dateTimePickerBinding.dateTimeCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dateTimePickerBinding.getRoot());
        alertDialog.show();
    }

    public void showRemindMeDatePicker(View v) {
        dateTimePickerBinding = DateTimePickerBinding.inflate(requireActivity().getLayoutInflater());
        //final View dialogView = View.inflate(requireActivity(), R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(requireActivity()).create();

        LocalDateTime dateTime = viewModel.editToDo.getValue().getRemindMeDate();

        dateTimePickerBinding.datePicker.updateDate(dateTime.getYear(),dateTime.getMonthValue()-1,dateTime.getDayOfMonth());
        dateTimePickerBinding.timePicker.setHour(dateTime.getHour());
        dateTimePickerBinding.timePicker.setMinute(dateTime.getMinute());

        dateTimePickerBinding.dateTimeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = dateTimePickerBinding.datePicker;
                TimePicker timePicker = dateTimePickerBinding.timePicker;

                LocalDateTime dateTime = LocalDateTime.of(datePicker.getYear(),datePicker.getMonth()+1,datePicker.getDayOfMonth(),timePicker.getHour(),timePicker.getMinute());

                viewModel.editToDo.getValue().setRemindMeDate(dateTime);
                viewModel.editToDo.setValue(viewModel.editToDo.getValue());

                alertDialog.dismiss();
            }});
        dateTimePickerBinding.dateTimeCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setView(dateTimePickerBinding.getRoot());
        alertDialog.show();
    }




}