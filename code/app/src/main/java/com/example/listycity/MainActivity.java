package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selected = -1;
    EditText cityInput;
    Button addButton;
    Button deleteButton;
    Button confirmButton;
    boolean isAdding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = position;
            }
        });

        cityInput = findViewById(R.id.city_input);
        addButton = findViewById(R.id.add_button);
        confirmButton = findViewById(R.id.confirm_button);
        //the implementation of the visibility of entertext and confirmbutton is from ChatGPT, "How to make the input and confirmButton hide and show when click ADD CITY", 2026-1-15
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAdding = true;
                cityInput.setText("");
                cityInput.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                cityInput.requestFocus();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdding){
                    String cityName = cityInput.getText().toString();
                    if (!cityName.isEmpty()){
                        dataList.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                    }
                    isAdding = false;
                    cityInput.setVisibility(View.GONE);
                    confirmButton.setVisibility(View.GONE);
                }
            }
        });

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != -1){
                    dataList.remove(selected);
                    cityAdapter.notifyDataSetChanged();
                    selected = -1;
                }
            }
        });


    }
}