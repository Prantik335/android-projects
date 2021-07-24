package me.prantik.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText item;
    Button add;
    ListView listView;
    ArrayList<String> itemList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        item = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        listView = findViewById(R.id.list);

        itemList = FileHelper.readData(this);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, itemList);

        listView.setAdapter(arrayAdapter);

        add.setOnClickListener(view -> addItemToList());

        listView.setOnItemClickListener((adapterView, view, pos, l) -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Delete");
            alert.setMessage("Do you want to delete '" + itemList.get(pos) + "' from the list?");
            alert.setCancelable(false);
            alert.setNegativeButton("No", (dialogInterface, i1) -> {
                dialogInterface.cancel();
            });
            alert.setPositiveButton("Yes", (dialogInterface, i1) -> {
                removeItemToList(pos);
            });
            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        });

    }

    private void addItemToList() {
        String itemName = item.getText().toString();
        itemList.add(itemName);
        item.setText("");
        arrayAdapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, this);
    }

    private void removeItemToList(int pos) {
        itemList.remove(pos);
        arrayAdapter.notifyDataSetChanged();
        FileHelper.writeData(itemList, this);
    }
}