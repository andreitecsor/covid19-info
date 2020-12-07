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

import ie.dam.covid19_info.domain.Covid19Test;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.domain.Patient;
import ie.dam.covid19_info.domain.TestType;
import ie.dam.covid19_info.fragment.HealthCenterFragment;
import ie.dam.covid19_info.fragment.InfoFragment;
import ie.dam.covid19_info.fragment.PatientFragment;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_HC_REQUEST = 112;

    private Fragment currentFragment;
    private FloatingActionButton fabInfo;
    private FloatingActionButton fabHome;
    private FloatingActionButton fabAdd;
    private List<HealthCenter> healthCenters = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseComponents(savedInstanceState);
        //TestType type, LocalDate date, boolean result
        Covid19Test c1 = new Covid19Test(TestType.PCR, LocalDate.now(), true);
        Covid19Test c2 = new Covid19Test(TestType.ANTIBODY, LocalDate.now(), false);
        Covid19Test c3 = new Covid19Test(TestType.ANTIGEN, LocalDate.now(), false);

        Patient p1 = new Patient("Nicu ", "Muie", 32, true, c1);
        Patient p2 = new Patient("Niculina", "Muie", 23, false, c2);
        Patient p3 = new Patient("Niculisor", "MuieLuAURsiPSD", 21, false, c3);
        Patient p4 = new Patient("Ardiana", "Barosanca", 45, true, c2);
        Patient p5 = new Patient("Ciucalata", "Florin", 69, false, c1);

        List<Patient> patients = new ArrayList<>();
        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patients.add(p5);
        HealthCenter hc1 = new HealthCenter("Muiomed", "Str. Muie Aur", "Muie, Psd", true, patients);
        healthCenters.add(hc1);
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

    public void openPatientFragment(HealthCenter healthCenter){
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