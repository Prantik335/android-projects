package me.prantik.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHelper { ;
    public static final String FILE_NAME = "todos.dat";

    public static void writeData(List<Map<String, String>> todos, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream oas = new ObjectOutputStream(fos);
            oas.writeObject(todos);
            oas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, String>> readItems(Context context) {
        List<Map<String, String>> todos = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            todos = (List<Map<String, String>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return todos;
    }

}
