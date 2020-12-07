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

import java.util.List;

import ie.dam.covid19_info.R;
import ie.dam.covid19_info.domain.HealthCenter;

public class HealthCenterAdapter extends ArrayAdapter<HealthCenter> {

    private Context context;
    private List<HealthCenter> healthCenters;
    private LayoutInflater inflater;
    private int resource;

    public HealthCenterAdapter(@NonNull Context context, int resource,
                               @NonNull List<HealthCenter> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.healthCenters = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        HealthCenter healthCenter = healthCenters.get(position);
        if (healthCenter != null) {
            setHealthCenterName(view, healthCenter.getName());
            setHealthCenterAddress(view, healthCenter.getAddress());
            setHealthCenterLocation(view, healthCenter.getLocation());
            setHealthCenterCount(view, healthCenter.getPatients().size());
            setHealthCenterDedicated(view, healthCenter.isCovid19Dedicated());

        }
        return view;
    }

    private void setHealthCenterDedicated(View view, boolean covid19Dedicated) {
        ImageView imageView = view.findViewById(R.id.tecsor_andrei_hc_lv_iv_virus);
        if (covid19Dedicated == false) {
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    private void setHealthCenterCount(View view, int size) {
        TextView tv = view.findViewById(R.id.tecsor_andrei_hc_lv_tv_patients_count);
        tv.setText(String.valueOf(size));
    }

    private void setHealthCenterLocation(View view, String location) {
        TextView tv = view.findViewById(R.id.tecsor_andrei_hc_lv_tv_location);
        if (location != null) {
            tv.setText(location);
        }
    }

    private void setHealthCenterAddress(View view, String address) {
        TextView tv = view.findViewById(R.id.tecsor_andrei_hc_lv_tv_address);
        if (address != null) {
            tv.setText(address);
        }
    }

    private void setHealthCenterName(View view, String name) {
        TextView tv = view.findViewById(R.id.tecsor_andrei_hc_lv_tv_name);
        if (name != null) {
            tv.setText(name);
        }
    }


}
