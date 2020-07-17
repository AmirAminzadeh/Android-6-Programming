package com.example.todolist;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoListManager listManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView todoList = findViewById(R.id.todo_list);
        ImageButton addButton = findViewById(R.id.add_item);
        ImageButton deleteButton = findViewById(R.id.delete_item);

        listManager = new ToDoListManager();
        ToDoItemAdapter adapter = new ToDoItemAdapter(this, listManager.getItems());
        todoList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onAddButtonClick();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onDeleteButtonClick();
            }
        });
    }

    private void onDeleteButtonClick() {
        ImageButton closeButton = findViewById(R.id.closeItemInRow);

        closeButton.setVisibility(View.VISIBLE);
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageButton closeButton = findViewById(R.id.closeItemInRow);

                    listManager.removeItem();
                    closeButton.setVisibility(View.INVISIBLE);
                }
            });
    }

    private void onAddButtonClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_item);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setNegativeButton(
                android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );

        builder.setPositiveButton(
                android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToDoItem item = new ToDoItem(
                                input.getText().toString(),
                                false,
                                1
                        );
                        listManager.addItem(item);
                    }
                }
        );

        builder.show();
    }

    private class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

        private Context context;
        private List<ToDoItem> items;

        private ToDoItemAdapter(
                Context context,
                List<ToDoItem> items
        ) {
            super(context, -1, items);

            this.context = context;
            this.items = items;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            if(convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.to_do_item_layout,
                        parent,
                        false
                );
            }

            TextView itemTextView = convertView.findViewById(R.id.itemTextView);
            CheckBox completedCheckBox = convertView.findViewById(R.id.completedCheckBox);
            ImageButton closeItemInRow = convertView.findViewById(R.id.closeItemInRow);

            itemTextView.setText(items.get(position).getDescription());
            completedCheckBox.setChecked(items.get(position).isComplete());
            closeItemInRow.setBottom(items.get(position).isClicked());


            convertView.setTag(items.get(position));
            completedCheckBox.setTag(items.get(position));
            closeItemInRow.setTag(items.get(position));

            View.OnClickListener onClickListener = new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    ToDoItem item = (ToDoItem) view.getTag();
                    item.toggleComplete();
                    notifyDataSetChanged();
                }
            };
            convertView.setOnClickListener(onClickListener);

            completedCheckBox.setOnClickListener(onClickListener);

            //closeItemInRow.setOnClickListener(onClickListener);

            return convertView;
        }
    }
}

