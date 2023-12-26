package com.app.test;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class RecyclerViewTable extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> sentiment_id, sentiment_text, sentiment_label;
    Analysis analysis;
    FloatingActionButton turnBack;
    FloatingActionButton afterPage;
    private DBController db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);
        recyclerView = findViewById(R.id.recyclerView);
        sentiment_id = new ArrayList<>();
        sentiment_text = new ArrayList<>();
        sentiment_label = new ArrayList<>();
        db = new DBController(RecyclerViewTable.this);
        displayData();
        analysis = new Analysis(RecyclerViewTable.this, this, sentiment_id, sentiment_text, sentiment_label);
        recyclerView.setAdapter(analysis);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewTable.this));

        // for turn back
        turnBack = findViewById(R.id.turnBack);
        turnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecyclerViewTable.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void displayData(){
        Cursor cursor = db.getAllDatas();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Veri bulunamadı.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()){
                System.out.println("cursor.getString(0): "+cursor.getString(0));
                System.out.println("cursor.getString(1): "+cursor.getString(1));
                System.out.println("cursor.getString(2): "+cursor.getString(2));
                sentiment_id.add(cursor.getString(0));
                sentiment_text.add(cursor.getString(1));
                sentiment_label.add(cursor.getString(2)); // cursor.getString(2)
            }
        }
    }
    public void showData(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        sentiment_id = new ArrayList<>();
        sentiment_text = new ArrayList<>();
        sentiment_label = new ArrayList<>();
        analysis = new Analysis(RecyclerViewTable.this, this, sentiment_id, sentiment_text, sentiment_label);
        recyclerView.setAdapter(analysis);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewTable.this));
    }
}
