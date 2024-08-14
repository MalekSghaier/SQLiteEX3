package edu.a.mpswmsqlite;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDBAdapter dbAdapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.epifirstdatabase);
        final Spinner faculties =(Spinner)findViewById(R.id.spinner);
        final EditText studentName =(EditText)findViewById(R.id.editText);
        Button add = (Button)findViewById(R.id.add);
        Button delete= (Button)findViewById(R.id.delete);
        list = (ListView)findViewById(R.id.list);

        dbAdapter = new MyDBAdapter(MainActivity.this);
        dbAdapter.open();
        String[] allFaculties = {"EPI-TEC","EPI-MA"};
        faculties.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,allFaculties));
        loadList();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.insertStudent(studentName.getText().toString(), faculties.getSelectedItemPosition()+1);
                loadList();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteAllEpiTec();
                loadList();
            }
        });
    }
    private void loadList(){
        ArrayList<String> allStudents = new ArrayList<String>();
        allStudents = dbAdapter.selectAllStudents();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,allStudents);
        list.setAdapter(adapter);
    }
}
