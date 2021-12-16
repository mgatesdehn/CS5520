package edu.neu.khoury.madsea.matthewgatesdehn.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.neu.khoury.madsea.matthewgatesdehn.R;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.ListPillButtonBinding;
import edu.neu.khoury.madsea.matthewgatesdehn.listeners.OnStartDragListener;
import edu.neu.khoury.madsea.matthewgatesdehn.listeners.OnToDoClickListener;
import edu.neu.khoury.madsea.matthewgatesdehn.databinding.ToDoItemViewBinding;

public class ToDoRecyclerAdapter extends ListAdapter<ToDo, ToDoItemViewHolder> implements ItemTouchHelperAdapter{
    private static final String TAG = "ToDoRecyclerAdapter";
    private OnToDoClickListener listener;
    private OnStartDragListener dragListener;
    private int fromPosition;
    private int toPosition;
    private final Context context;

    public ToDoRecyclerAdapter(OnToDoClickListener listener, OnStartDragListener dragListener, Context context) {
        super(ToDo.DIFF_CALLBACK);
        this.context = context;
        this.listener = listener;
        this.dragListener = dragListener;
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
        ToDo todo = holder.binding.getToDo();
        holder.binding.tagHolder.removeAllViews();

        for (String tag : todo.getTags()
        ) {
            ListPillButtonBinding button = ListPillButtonBinding.inflate(
                    LayoutInflater.from(holder.itemView.getContext()), holder.binding.tagHolder, false);
            button.setName(tag);
            button.pillButton.setClickable(false);
            holder.binding.tagHolder.addView(button.getRoot());
        }

        TextView dueDateTextView = holder.binding.taskDueDate;
        LocalDateTime now = LocalDateTime.now();
        if (todo.getDueDate().compareTo(now)<0){
            dueDateTextView.setTextColor(ContextCompat.getColor(context, R.color.past_due));
        }
        else{
            dueDateTextView.setTextColor(ContextCompat.getColor(context, R.color.not_past_due));
        }

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
        holder.binding.dragHandle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public void submitList(final List<ToDo> list) {
        super.submitList(list != null ? new ArrayList<ToDo>(list) : null);
    }

    @Override
    public void onItemDismiss(int position) {
        listener.onToDoDeleteClick(getItem(position));
        notifyItemRemoved(position);
    }

    @Override
    public void onItemDropped() {
        Log.d(TAG, "Item Dropped With Positions: from="+fromPosition+" to="+toPosition);
        if (fromPosition != toPosition) {
            listener.onItemMove(fromPosition, toPosition);
        }
    }

    @Override
    public void onSelectedChanged(int layoutPosition) {
        fromPosition = getItem(layoutPosition).getPosition();
        toPosition = fromPosition;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //listener.onItemMove(getItem(fromPosition).getPosition(),getItem(toPosition).getPosition());
        this.toPosition = getItem(toPosition).getPosition();
        Log.d(TAG, "Move Queued: from="+this.fromPosition+" to="+this.toPosition);
        notifyItemMoved(fromPosition, toPosition);

    }
}

