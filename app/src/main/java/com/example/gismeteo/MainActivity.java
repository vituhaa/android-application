package com.example.gismeteo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button3;
    private RadioGroup choose_1;
    private RadioGroup choose_2;

    private CheckBox city1;
    private CheckBox city2;
    private CheckBox city3;
    private CheckBox city4;
    private CheckBox city5;
    private CheckBox city6;
    private CheckBox time1;
    private CheckBox time2;
    private CheckBox time3;
    private CheckBox time4;
    private List<CheckBox> city_arr;
    private List<CheckBox> time_arr;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        city1 = findViewById(R.id.city1);
        city2 = findViewById(R.id.city2);
        city3 = findViewById(R.id.city3);
        city4 = findViewById(R.id.city4);
        city5 = findViewById(R.id.city5);
        city6 = findViewById(R.id.city6);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);

        city_arr = new ArrayList<>();
        city_arr.add(city1);
        city_arr.add(city2);
        city_arr.add(city3);
        city_arr.add(city4);
        city_arr.add(city5);
        city_arr.add(city6);

        time_arr = new ArrayList<>();
        time_arr.add(time1);
        time_arr.add(time2);
        time_arr.add(time3);
        time_arr.add(time4);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                if (city1.isChecked())
//                {
//                    Toast.makeText(MainActivity.this, "Выбран город", Toast.LENGTH_SHORT).show();
//
//                }
                int count = 0;
                for (int i = 0; i < city_arr.size(); i++)
                {
                    if (city_arr.get(i).isChecked())
                    {
                        count += 1;
                    }
                }

                for (int j = 0; j < time_arr.size(); j++)
                {
                    if (time_arr.get(j).isChecked())
                    {
                        count += 1;
                    }
                }

                if (count != 2)
                {
                    Toast.makeText(MainActivity.this, "Choose city and time", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PackageManager manager = getPackageManager();
                    Intent yandexIntent = manager.getLaunchIntentForPackage("com.yandex.browser");
                    Intent chromeIntent = manager.getLaunchIntentForPackage("com.android.chrome");
                    if (yandexIntent != null)
                    {
                        startActivity(yandexIntent);
                    }
                    else if (chromeIntent != null)
                    {
                        startActivity(chromeIntent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No app", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}