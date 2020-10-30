package com.bcit.lab7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference taskReference = database.getReference("tasks");
    public ValueEventListener taskReferenceListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onResume() {
        super.onResume();
        taskReferenceListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ListView listView = findViewById(R.id.ListViewTasks);

                List<ToDoList> toDoLists = new ArrayList<>();
                for(DataSnapshot ds: snapshot.getChildren()){
                    ToDoList t = ds.getValue(ToDoList.class);
                    toDoLists.add(t);
                }

                ArrayAdapter<ToDoList> adapter = new ArrayAdapter<ToDoList>(getApplicationContext(), android.R.layout.simple_list_item_1, toDoLists);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        taskReference.addValueEventListener(taskReferenceListener);
    }

    public void createTaskAndUpload(View view) {


        Calendar cal = Calendar.getInstance();

        EditText editTaskText = findViewById(R.id.editTextTask);
        EditText editPersonText = findViewById(R.id.editTextPersonName);
        DatePicker datepicker = findViewById(R.id.datePicker);
        CheckBox checkBoxDone = findViewById(R.id.checkBoxDone);

        String task = editTaskText.getText().toString();
        String name = editPersonText.getText().toString();


        if(!task.isEmpty() && !name.isEmpty()){
            String key = taskReference.push().getKey();
            cal.set(datepicker.getYear(), datepicker.getMonth(), datepicker.getDayOfMonth());
            ToDoList list = new ToDoList(task, name, cal.getTime(), checkBoxDone.isChecked());
            taskReference.child(key).setValue(list);
        }


    }
}