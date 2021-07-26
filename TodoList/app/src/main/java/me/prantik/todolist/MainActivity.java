package me.prantik.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    SimpleAdapter adapter;
    List<Map<String, String>> todos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        todos = FileHelper.readItems(this);

        adapter = new SimpleAdapter(this, todos,
                android.R.layout.simple_list_item_2,
                new String[]{"todo", "timestamp"},
                new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);

        add.setOnClickListener(view -> addItemToList());

        listView.setOnItemClickListener((adapterView, view, pos, l) -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Delete");
            alert.setMessage("Do you want to delete '" + todos.get(pos).get("todo") + "' from the list?");
            alert.setCancelable(false);
            alert.setNegativeButton("No", (dialogInterface, i1) -> {
                dialogInterface.cancel();
            });
            alert.setPositiveButton("Yes", (dialogInterface, i1) -> {
                removeTodoFromTodos(pos);
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        });

    }

    private void addItemToList() {
        String itemName = item.getText().toString();

        if (itemName.isEmpty()) {
//            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
        }
        {
            Map<String, String> todo = new HashMap<>(2);
            SimpleDateFormat format = new SimpleDateFormat("h:mm a, d'''th' MMM yyy", Locale.getDefault());
            String currentDateAndTime = format.format(new Date());
            todo.put("todo", itemName);
            todo.put("timestamp", currentDateAndTime);
            todos.add(todo);

            item.setText("");

            adapter.notifyDataSetChanged();
            FileHelper.writeData(todos, this);
        }
    }

    private void removeTodoFromTodos(int pos) {
        todos.remove(pos);
        adapter.notifyDataSetChanged();
        FileHelper.writeData(todos, this);
    }
}