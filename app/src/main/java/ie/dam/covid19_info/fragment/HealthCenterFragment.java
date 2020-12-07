package ie.dam.covid19_info.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.List;

import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.util.adapters.HealthCenterAdapter;


public class HealthCenterFragment extends Fragment {
    private List<HealthCenter> healthCenters;
    private ListView listView;

    public HealthCenterFragment() {
    }

    public static HealthCenterFragment newInstance(List<HealthCenter> list) {
        HealthCenterFragment fragment = new HealthCenterFragment();
        Bundle args = new Bundle();
        args.putSerializable("HC_list", (Serializable) list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            healthCenters = (List<HealthCenter>) getArguments().get("HC_list");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_center, container, false);
        initialiseComponents(view);
        return view;
    }

    private void initialiseComponents(View view) {
        listView = view.findViewById(R.id.tecsor_andrei_hc_lv);
        HealthCenterAdapter hcAdapter = new HealthCenterAdapter(view.getContext(), R.layout.fragment_health_center_lv, healthCenters, getLayoutInflater());
        listView.setAdapter(hcAdapter);
    }

}