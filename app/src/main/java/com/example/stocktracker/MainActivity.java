package com.example.stocktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddStock;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_stocks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddStock = findViewById(R.id.floating_button_add);
        buttonAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(MainActivity.this, AddStockActivity.class);
                startActivity(intent);
                 */
            }
        });

        // getStocks();

    }

    private void getStocks() {
        class GetStocks extends AsyncTask<Void, Void, List<Stock>> {

            @Override
            protected List<Stock> doInBackground(Void... voids) {
                List<Stock> stockList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .stockDao()
                        .getAll();
                return stockList;
            }

            @Override
            protected void onPostExecute(List<Stock> tasks) {
                super.onPostExecute(tasks);
                //TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                //recyclerView.setAdapter(adapter);
            }
        }
    }


}