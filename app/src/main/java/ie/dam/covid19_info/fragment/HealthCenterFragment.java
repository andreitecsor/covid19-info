package ie.dam.covid19_info.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.HealthCenter;


public class HealthCenterFragment extends Fragment {
    private List<HealthCenter> healthCenters;

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
            System.out.println("OnCreate");
            System.out.println(healthCenters);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_health_center, container, false);
        System.out.println("OnCreateView");
        System.out.println(healthCenters);
        return view;
    }
}