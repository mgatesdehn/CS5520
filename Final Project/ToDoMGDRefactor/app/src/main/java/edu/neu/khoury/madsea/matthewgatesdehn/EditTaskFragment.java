package edu.neu.khoury.madsea.matthewgatesdehn;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;
import java.time.LocalDateTime;

import edu.neu.khoury.madsea.matthewgatesdehn.databinding.EditPillButtonBinding;
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
        Log.d(TAG, "tags: "+viewModel.editToDo.getValue().getTags());

        for (String tag: viewModel.editToDo.getValue().getTags()
             ) {
            EditPillButtonBinding button = EditPillButtonBinding.inflate(getLayoutInflater(), binding.tagHolder, false);
            button.setName(tag);
            button.pillButton.setOnClickListener((View v)->{
                String del_tag = ((Button) v).getText().toString();
                viewModel.editToDo.getValue().removeTag(del_tag);
                ViewGroup parent = (ViewGroup) v.getParent();
                parent.removeView(v);
            });
            binding.tagHolder.addView(button.getRoot());
        }

        binding.addTagButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                binding.titleEditText.clearFocus();
                binding.descriptionEditText.clearFocus();
                binding.remindMeCheckbox.clearFocus();
                binding.locationEditText.clearFocus();


                String newTag = binding.addTagEditText.getText().toString();

                if (TextUtils.isEmpty(newTag)){
                    return;
                }
                if (viewModel.editToDo.getValue().getTags().contains(newTag)){
                    return;
                }

                viewModel.editToDo.getValue().addTag(newTag);
                EditPillButtonBinding button = EditPillButtonBinding.inflate(getLayoutInflater(), binding.tagHolder, false);
                button.setName(newTag);
                button.pillButton.setOnClickListener((View v)->{
                    String del_tag = ((Button) v).getText().toString();
                    viewModel.editToDo.getValue().removeTag(del_tag);
                    ViewGroup parent = (ViewGroup) v.getParent();
                    parent.removeView(v);
                });
                binding.tagHolder.addView(button.getRoot());
                binding.addTagEditText.setText("");
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.titleEditText.clearFocus();
                binding.descriptionEditText.clearFocus();
                binding.remindMeCheckbox.clearFocus();
                binding.locationEditText.clearFocus();
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

        binding.mapsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                binding.titleEditText.clearFocus();
                binding.descriptionEditText.clearFocus();
                binding.remindMeCheckbox.clearFocus();
                binding.locationEditText.clearFocus();

                String loc = viewModel.editToDo.getValue().getLocation();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q=" + loc));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
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
        binding.locationEditText.clearFocus();
        viewModel.editToDo.getValue().setRemindMe(binding.remindMeCheckbox.isChecked());
        binding.remindMeDateEditText.setEnabled(binding.remindMeCheckbox.isChecked());
    }

    public void showDueDatePicker(View v) {
        binding.titleEditText.clearFocus();
        binding.descriptionEditText.clearFocus();
        binding.remindMeCheckbox.clearFocus();
        binding.locationEditText.clearFocus();
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