package ie.dam.covid19_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import ie.dam.covid19_info.domain.HealthCenter;

public class AddHCActivity extends AppCompatActivity {
    public static final String HC_KEY = "new_hc";

    private TextInputEditText tietName;
    private TextInputEditText tietAddress;
    private TextInputEditText tietLocation;
    private Switch switchDedicated;
    private Button btnSave;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hc);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        intent = getIntent();
        initialiseComponents();
        btnSave.setOnClickListener(saveClick());
    }

    private View.OnClickListener saveClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    HealthCenter healthCenter = new HealthCenter(tietName.getText().toString(), tietAddress.getText().toString(), tietLocation.getText().toString(), switchDedicated.isChecked());
                    intent.putExtra(HC_KEY, healthCenter);
                    setResult(RESULT_OK, intent);
                    finish();
                    overridePendingTransition(R.anim.top_to_bot_in, R.anim.top_to_bot_out);

                }
            }
        };
    }

    private boolean validateInputs() {
        if (tietName.getText().toString() == null || tietName.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(), R.string.error_name, Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (tietAddress.getText().toString() == null || tietAddress.getText().toString().trim().length() < 4) {
            Toast.makeText(getApplicationContext(), R.string.invalid_address, Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (tietLocation.getText().toString() == null || tietLocation.getText().toString().trim().split(",").length != 2 || tietLocation.getText().toString().trim().length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.invalid_location, Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }


    private void initialiseComponents() {
        tietName = findViewById(R.id.tecsor_andrei_add_tiet_name);
        tietAddress = findViewById(R.id.tecsor_andrei_add_tiet_address);
        tietLocation = findViewById(R.id.tecsor_andrei_add_tiet_location);
        btnSave = findViewById(R.id.tecsor_andrei_add_button);
        switchDedicated = findViewById(R.id.tecsor_andrei_add_switch);
    }
}