package com.app.test;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button calculate;
    public EditText entryText;
    public TextView result;
    private DBController db;
    RecyclerView recyclerView;
    ArrayList<String> sentiment_id, sentiment_text, sentiment_label;
    Analysis analysis;
    FloatingActionButton afterPage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("Sentiment");

        calculate = (Button) findViewById(R.id.calculate);
        entryText = (EditText) findViewById(R.id.entryText);
        result = (TextView) findViewById(R.id.result);
        afterPage = (FloatingActionButton) findViewById(R.id.afterPage);

        sentiment_id = new ArrayList<>();
        sentiment_text = new ArrayList<>();
        sentiment_label = new ArrayList<>();

//        // create new DB
//        db = new DBController(MainActivity.this);
//        displayData();

        // for SPINNER(excel, csv, json)
        Spinner spinner_files = (Spinner) findViewById(R.id.spinner_files);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.files, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_files.setAdapter(adapter);

        // for model predict result
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = entryText.getText().toString().trim();
                String label = result.getText().toString().trim();
                if(!text.isEmpty()){
//                    db.addData(text, "positive"); //  db.addData(text, label);
                    result.setText("positive");
                }
            }
        });

        recyclerView = findViewById(R.id.recyclerViewMain);
        System.out.println("sentiment_id: "+sentiment_id);
        System.out.println("sentiment_text: "+sentiment_text);
        System.out.println("sentiment_label: "+sentiment_label);

        analysis = new Analysis(MainActivity.this,
                this, sentiment_id,
                sentiment_text,
                sentiment_label);
        recyclerView.setAdapter(analysis);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // for view datas
        afterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecyclerViewTable.class);
                startActivity(i);
            }
        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setPositiveButton("Text", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // do something here
//            }
//        });

    }

}