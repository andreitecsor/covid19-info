package ie.dam.covid19_info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.fragment.HealthCenterFragment;
import ie.dam.covid19_info.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_HC_REQUEST = 112;

    private FloatingActionButton fabInfo;
    private FloatingActionButton fabHome;
    private FloatingActionButton fabAdd;

    private List<HealthCenter> healthCenters = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseComponents(savedInstanceState);

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

                HealthCenterFragment fragment = HealthCenterFragment.newInstance(healthCenters);
                getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl, fragment).commit();
            }
        }
    }


    private void initialiseComponents(Bundle savedInstanceState) {
        setCurrentDate();
        //Start-up fragment
        if (savedInstanceState == null) {
            HealthCenterFragment fragment = HealthCenterFragment.newInstance(healthCenters);
            getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl, fragment).commit();
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
                getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl,
                        new InfoFragment()).commit();
            }
        });
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.tecsor_andrei_main_fl,
                        new HealthCenterFragment()).commit();
            }
        });

    }

    private void setCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        TextView currentDate = findViewById(R.id.tecsor_andrei_main_tv_current_date);
        currentDate.setText(formatter.format(date));
    }
}