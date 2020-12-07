package ie.dam.covid19_info.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.util.adapters.PatientAdapter;


public class PatientFragment extends Fragment {
    private static final String HC_ELEMENT = "SELECTED_HC";
    private HealthCenter healthCenter;
    private ListView patientsListView;

    public PatientFragment() {
    }

    public static PatientFragment newInstance(HealthCenter healthCenter) {
        PatientFragment fragment = new PatientFragment();
        Bundle args = new Bundle();
        args.putSerializable(HC_ELEMENT, healthCenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            healthCenter = (HealthCenter) getArguments().get(HC_ELEMENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        initialiseComponents(view);
        return view;
    }

    private void initialiseComponents(View view) {
        patientsListView = view.findViewById(R.id.tecsor_andrei_patients_lv);
        PatientAdapter patientAdapter = new PatientAdapter(view.getContext(), R.layout.fragment_patient_lv, healthCenter.getPatients(), getLayoutInflater());
        patientsListView.setAdapter(patientAdapter);
    }
}