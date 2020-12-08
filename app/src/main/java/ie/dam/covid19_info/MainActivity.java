package ie.dam.covid19_info;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import ie.dam.covid19_info.domain.Covid19Test;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.domain.Patient;
import ie.dam.covid19_info.domain.TestType;
import ie.dam.covid19_info.fragment.HealthCenterFragment;
import ie.dam.covid19_info.fragment.InfoFragment;
import ie.dam.covid19_info.fragment.PatientFragment;
import ie.dam.covid19_info.util.JsonParser;
import ie.dam.covid19_info.util.async_task.AsyncTaskRunner;
import ie.dam.covid19_info.util.async_task.Callback;
import ie.dam.covid19_info.util.network.HttpManager;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_HC_REQUEST = 112;
    private static final String JSON_URL = "https://jsonkeeper.com/b/KZ7L";

    private Fragment currentFragment;
    private FloatingActionButton fabInfo;
    private FloatingActionButton fabHome;
    private FloatingActionButton fabAdd;
    private List<HealthCenter> healthCenters = new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseComponents(savedInstanceState);
        getHCFromHttp();
    }

    private void getHCFromHttp() {
        Callable<String> asyncOperation = new HttpManager(JSON_URL);
        Callback<String> mainThreadOperation = receiveHCsFromHttp();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveHCsFromHttp() {
        return new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void runResultOnUiThread(String result) {
                healthCenters.addAll(JsonParser.fromJson(result));
                openHCFragment(healthCenters);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_HC_REQUEST && resultCode == RESULT_OK && data != null) {
            HealthCenter healthCenter = (HealthCenter) data.getSerializableExtra(AddHCActivity.HC_KEY);
            if (healthCenter != null) {
                Toast.makeText(getApplicationContext(), R.string.new_HC_added,
                        Toast.LENGTH_LONG).show();
                healthCenters.add(healthCenter);
                openHCFragment(healthCenters);
            }
        }
    }

    private void initialiseComponents(Bundle savedInstanceState) {
        setCurrentDate();
        //Start-up fragment
        if (savedInstanceState == null) {
            openHCFragment(healthCenters);
        }

        fabInfo = findViewById(R.id.tecsor_andrei_main_fab_info);
        fabHome = findViewById(R.id.tecsor_andrei_main_fab_home);
        fabAdd = findViewById(R.id.tecsor_andrei_main_fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddHCActivity.class);
                startActivityForResult(intent, NEW_HC_REQUEST);
            }
        });

        fabInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment = new InfoFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl,
                        currentFragment).commit();
            }
        });
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHCFragment(healthCenters);
            }
        });
    }

    private void openHCFragment(List<HealthCenter> list) {
        currentFragment = HealthCenterFragment.newInstance(list);
        getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl, currentFragment).commit();
    }

    public void openPatientFragment(HealthCenter healthCenter) {
        currentFragment = PatientFragment.newInstance(healthCenter);
        getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl, currentFragment).commit();
    }

    private void setCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        TextView currentDate = findViewById(R.id.tecsor_andrei_main_tv_current_date);
        currentDate.setText(formatter.format(date));
    }
}