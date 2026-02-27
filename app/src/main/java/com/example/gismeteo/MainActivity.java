package com.example.gismeteo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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
    private ArrayList<CheckBox> choice_arr;

    public String make_url(ArrayList<CheckBox> choice)
    {
        String city_url;
        String time_url;
        String final_url;
        CheckBox city_idx = choice.get(0);
        CheckBox time_idx = choice.get(1);
        String city = city_idx.getText().toString();
        String time = time_idx.getText().toString();
//        Toast.makeText(getApplicationContext(), city, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), time, Toast.LENGTH_SHORT).show();

        if (city.equals("Nizhny Novgorod"))
        {
            city_url = "nizhny-novgorod";
        }
        else if (city.equals("Uren"))
        {
            city_url = "uren";
        }
        else if (city.equals("Zavolgie"))
        {
            city_url = "zavoljie";
        }
        else if (city.equals("Kronshtadt"))
        {
            city_url = "kronstadt";
        }
        else if (city.equals("Yoshkar-Ola"))
        {
            city_url = "yoshkar-ola";
        }
        else
        {
            city_url = "yaroslavl";
        }


        if (time.equals("Today"))
        {
            time_url = "today";
        }
        else if (time.equals("Tomorrow"))
        {
            time_url = "tomorrow";
        }
        else if (time.equals("3 days"))
        {
            time_url = "3-day-weather";
        }
        else
        {
            time_url = "10-day-weather";
        }

        final_url = "https://yandex.ru/pogoda/ru/" + city_url + "/details/" + time_url;

        // url format:  https://yandex.ru/pogoda/ru/nizhny-novgorod/details/today
        return final_url;
    }

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
        choice_arr = new ArrayList<CheckBox>();
        button3 = (Button)findViewById(R.id.button3);


        for (CheckBox cheker : city_arr) {
            cheker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
//                    for (int i = 0; i < city_arr.size(); i++)
//                    {
//                        if (city_arr.get(i).isChecked())
//                        {
//                            choice_arr.add(city_arr.get(i));
//                            for (int k = 0; k < city_arr.size(); k++)
//                            {
//                                if (k != i)
//                                {
//                                    city_arr.get(k).setEnabled(false);
//                                }
//                            }
//                            break;
//                        }
//                    }
                    if (isChecked)
                    {
                        for (CheckBox check : city_arr)
                        {
                            if (check != buttonView)
                            {
                                check.setEnabled(false);
                            }
                            else
                            {
                                choice_arr.add(check);
                            }
                        }
                    }
                    else
                    {
                        for (CheckBox check : city_arr)
                        {
                            choice_arr.remove(check);
                            check.setEnabled(true);
                        }
                    }
                }
            });
        }

        for (CheckBox cheker : time_arr) {
            cheker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
//                    for (int j = 0; j < time_arr.size(); j++)
//                    {
//                        if (time_arr.get(j).isChecked())
//                        {
//                            choice_arr.add(time_arr.get(j));
//                            for (int l = 0; l < time_arr.size(); l++)
//                            {
//                                if (l != j)
//                                {
//                                    time_arr.get(l).setEnabled(false);
//                                }
//                            }
//                            break;
//                        }
//                    }
                    if (isChecked)
                    {
                        for (CheckBox check : time_arr)
                        {
                            if (check != buttonView)
                            {
                                check.setEnabled(false);
                            }
                            else
                            {
                                choice_arr.add(check);
                            }
                        }
                    }
                    else
                    {
                        for (CheckBox check : time_arr)
                        {
                            choice_arr.remove(check);
                            check.setEnabled(true);
                        }
                    }
                }
            });
        }
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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
                    String url = make_url(choice_arr);
                    Intent weather_url = new Intent(Intent.ACTION_VIEW);
                    weather_url.setData(Uri.parse(url));
                    weather_url.addCategory(Intent.CATEGORY_BROWSABLE);
                    if (yandexIntent != null)
                    {
                        startActivity(yandexIntent);
                        startActivity(weather_url);
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
