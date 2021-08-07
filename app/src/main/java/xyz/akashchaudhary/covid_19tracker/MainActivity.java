package xyz.akashchaudhary.covid_19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.akashchaudhary.covid_19tracker.api.ApiUtilities;
import xyz.akashchaudhary.covid_19tracker.api.CountryData;

public class MainActivity extends AppCompatActivity {


    private TextView totalConfirm, totalActive, totalRecovered,  totalDeath, totalTest;
    private TextView todayConfirm, todayRecovered, todayDeath;
    private TextView countryName, lastUpdated;
    private PieChart pieChart;
    private ImageView countryFlag;

    private List<CountryData> list;
    String country;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        list = new ArrayList<>();

        if(getIntent().getStringExtra("country") != null){
            country = getIntent().getStringExtra("country");
        }
        else{
            country = "India";
        }

        init();

        countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CountryActivity.class));
            }
        });

        ApiUtilities.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryData>>() {
                    @Override
                    public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                        list.addAll(response.body());

                        //TODO create and dismiss Progressbar here

                        for (int i = 0; i<list.size(); i++){
                            if (list.get(i).getCountryName().equals(country)){
                                int confirm = Integer.parseInt(list.get(i).getTotalCases());
                                int active = Integer.parseInt(list.get(i).getTotalActive());
                                int recovered = Integer.parseInt(list.get(i).getTotalRecovered());
                                int death = Integer.parseInt(list.get(i).getTotalDeaths());
                                int test = Integer.parseInt(list.get(i).getTotalTests());
                                int today_Death = Integer.parseInt(list.get(i).getTodayDeaths());
                                int today_Confirm = Integer.parseInt(list.get(i).getTodayCases());
                                int today_Recovered = Integer.parseInt(list.get(i).getTodayRecovered());
                                /*String country_flag = list.get(i).getFlag();*/


                                countryName.setText(list.get(i).getCountryName());
                                totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                totalDeath.setText(NumberFormat.getInstance().format(death));
                                totalTest.setText(NumberFormat.getInstance().format(test));
                                todayDeath.setText(NumberFormat.getInstance().format(today_Death));
                                todayConfirm.setText(NumberFormat.getInstance().format(today_Confirm));
                                todayRecovered.setText(NumberFormat.getInstance().format(today_Recovered));
                                Glide.with(countryFlag).load(list.get(i).getCountryInfo().get("flag")).into(countryFlag);
                                setText(list.get(i).getUpdated());


                                /*URL newurl = null;
                                Bitmap mIcon_val;
                                try {
                                    newurl = new URL(country_flag);
                                    mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                                    countryFlag.setImageBitmap(mIcon_val);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }*/



                                pieChart.addPieSlice(new PieModel("Confirm", confirm,
                                        getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Active", active,
                                        getResources().getColor(R.color.blue_pie)));
                                pieChart.addPieSlice(new PieModel("Recovered", recovered,
                                        getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("Death", death,
                                        getResources().getColor(R.color.red_pie)));

                                pieChart.startAnimation();



                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");

        long milliseconds = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        
        lastUpdated.setText("Updated at " + format.format(calendar.getTime()));
    }

    private void init(){
        totalConfirm = findViewById(R.id.totalConfirm);
        totalActive = findViewById(R.id.totalActive);
        totalRecovered = findViewById(R.id.totalRecovered);
        totalDeath = findViewById(R.id.totalDeath);
        totalTest = findViewById(R.id.totalTested);
        todayConfirm = findViewById(R.id.todayConfirm);
        todayRecovered = findViewById(R.id.todayRecovered);
        todayDeath = findViewById(R.id.todayDeath);
        countryName = findViewById(R.id.text_country_name);
        lastUpdated = findViewById(R.id.text_lastUpdated);
        countryFlag = findViewById(R.id.countryFlag);
        pieChart = findViewById(R.id.pieChart);


    }

}