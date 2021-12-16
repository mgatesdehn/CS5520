package edu.neu.khoury.madsea.matthewgatesdehn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.neu.khoury.madsea.matthewgatesdehn.databinding.ActivityMainBinding;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.DateTimePickerBinding;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ToDoViewModel viewModel;
    private MenuItem filterMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ToDoViewModel.Factory factory = new ToDoViewModel.Factory(this.getApplication());
        viewModel = new ViewModelProvider(this, factory).get(ToDoViewModel.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        filterMenuItem = menu.getItem(1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_filter) {
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void showFilterDialog() {
        CharSequence[] tags = new String[viewModel.getFilteredTags().size()+1];
        boolean[] filtered = new boolean[viewModel.getFilteredTags().size()+1];

        tags[0] = "Show All";
        filtered[0] = viewModel.getShowAll();
        int i = 1;
        for (String tag: viewModel.getFilteredTags().keySet()
             ) {
            tags[i] = tag;
            filtered[i] = viewModel.getFilteredTags().get(tag);
            i++;
        }
        Log.d(TAG, viewModel.getFilteredTags().values().toString());

        Resources res = getApplicationContext().getResources();
        new AlertDialog.Builder(this)
                .setTitle(res.getString(R.string.filter_dialog_title))
                .setNeutralButton(res.getString(R.string.cancel), (dialog, which) ->{

                })
        .setPositiveButton(res.getString(R.string.ok), (dialog, which) ->{
            viewModel.setShowAll(filtered[0]);

            for (int j = 1; j < tags.length ; j++ ) {
                viewModel.getFilteredTags().put((String) tags[j], filtered[j]);
            }
            viewModel.filterToDoList();

            if (viewModel.getShowAll()){
                filterMenuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_filter_list_24));
            }
            else{
                filterMenuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_baseline_filter_list_off_24));
            }
        })
        .setMultiChoiceItems(tags,  filtered, new DialogSelectionClickHandler())
        .show();
    }

    public class DialogSelectionClickHandler implements
            DialogInterface.OnMultiChoiceClickListener {
        public void onClick(DialogInterface dialog, int clicked,
                            boolean selected) {
            // Log.i("ME", _options[clicked] + " selected: " + selected);
        }
    }
}