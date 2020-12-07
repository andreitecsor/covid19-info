package ie.dam.covid19_info.util.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.Covid19Test;
import ie.dam.covid19_info.domain.Patient;

public class PatientAdapter extends ArrayAdapter<Patient> {

    private Context context;
    private List<Patient> patients;
    private LayoutInflater inflater;
    private int resource;

    public PatientAdapter(@NonNull Context context, int resource, @NonNull List<Patient> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.patients = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Patient patient = patients.get(position);
        if (patient != null) {
            setPatientSex(view, patient.isSex());
            setPatientName(view, patient);
            setPatientBirthDate(view, patient.getAge());
            setPatientTest(view, patient.getCovid19Test());
        }
        return view;
    }

    private void setPatientTest(View view, Covid19Test covid19Test) {
        TextView testType = view.findViewById(R.id.tecsor_andrei_patient_tv_test);
        TextView testDate = view.findViewById(R.id.tecsor_andrei_patient_tv_test_date);

        String type = testType.getText().toString();
        type = type.replace("Type", covid19Test.getType().getType());
        testType.setText(type);

        String date = testDate.getText().toString();
        date = date.replace("Date", String.valueOf(covid19Test.getDate()));
        testDate.setText(date);

        ConstraintLayout cl = view.findViewById(R.id.tecsor_andrei_patient_lv_cl);
        if (covid19Test.isResult()) {
            cl.setBackground(view.getResources().getDrawable(R.drawable.lv_row_patient_positive));
        }
    }

    private void setPatientBirthDate(View view, int age) {
        TextView tv = view.findViewById(R.id.tecsor_andrei_patient_tv_age);
        String years = tv.getText().toString();
        years = years.replace("Age", String.valueOf(age));
        tv.setText(years);
    }


    private void setPatientSex(View view, boolean sex) {
        ImageView boy = view.findViewById(R.id.tecsor_andrei_patient_iv_boy);
        ImageView girl = view.findViewById(R.id.tecsor_andrei_patient_iv_girl);
        if (sex) {
            boy.setVisibility(View.INVISIBLE);
        } else {
            girl.setVisibility(View.INVISIBLE);
        }
    }

    private void setPatientName(View view, Patient patient) {
        if (patient.getFirstName() != null && patient.getLastName() != null) {
            TextView tv = view.findViewById(R.id.tecsor_andrei_patient_tv_name);
            String fullName = tv.getText().toString();
            fullName = fullName.replace("firstName", patient.getFirstName());
            fullName = fullName.replace("lastName", patient.getLastName());
            tv.setText(fullName);
        }
    }


}
