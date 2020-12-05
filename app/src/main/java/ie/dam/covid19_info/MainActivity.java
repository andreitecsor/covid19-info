package ie.dam.covid19_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

import ie.dam.covid19_info.fragment.HealthCenterFragment;
import ie.dam.covid19_info.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabInfo;
    private FloatingActionButton fabHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseComponents(savedInstanceState);

    }

    private void initialiseComponents(Bundle savedInstanceState) {
        setCurrentDate();
        fabInfo = findViewById(R.id.tecsor_andrei_main_fab_info);
        fabHome = findViewById(R.id.tecsor_andrei_main_fab_home);

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