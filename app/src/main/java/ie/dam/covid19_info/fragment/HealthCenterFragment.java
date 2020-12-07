package ie.dam.covid19_info.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import ie.dam.covid19_info.MainActivity;
import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.util.adapters.HealthCenterAdapter;


public class HealthCenterFragment extends Fragment {
    private static final String LIST_KEY = "HC_list";
    private List<HealthCenter> healthCenters;
    private ListView hcListView;

    public HealthCenterFragment() {
    }

    public static HealthCenterFragment newInstance(List<HealthCenter> list) {
        HealthCenterFragment fragment = new HealthCenterFragment();
        Bundle args = new Bundle();
        args.putSerializable(LIST_KEY, (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            healthCenters = (List<HealthCenter>) getArguments().get(LIST_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_center, container, false);
        initialiseComponents(view);
        return view;
    }

    private void initialiseComponents(View view) {
        hcListView = view.findViewById(R.id.tecsor_andrei_hc_lv);
        HealthCenterAdapter hcAdapter = new HealthCenterAdapter(view.getContext(), R.layout.fragment_health_center_lv, healthCenters, getLayoutInflater());
        hcListView.setAdapter(hcAdapter);
        hcListView.setOnItemClickListener(openPatientFragment());
    }

    private AdapterView.OnItemClickListener openPatientFragment() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if (healthCenters.get(position).getPatients().size() == 0) {
                    Toast.makeText(mainActivity, "This health center doesn't have any patients",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                mainActivity.openPatientFragment(healthCenters.get(position));
            }
        };
    }

}