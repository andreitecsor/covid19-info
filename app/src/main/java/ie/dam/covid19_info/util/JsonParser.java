package ie.dam.covid19_info.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ie.dam.covid19_info.domain.Covid19Test;
import ie.dam.covid19_info.domain.HealthCenter;
import ie.dam.covid19_info.domain.Patient;
import ie.dam.covid19_info.domain.TestType;

@RequiresApi(api = Build.VERSION_CODES.O)
public class JsonParser {

    public static final String HC_NAME = "name";
    public static final String HC_ADDRESS = "address";
    public static final String HC_LOCATION = "location";
    public static final String HC_COVID19_DEDICATED = "covid19Dedicated";

    public static final String PATIENTS = "patients";
    public static final String PATIENT_F_NAME = "firstName";
    public static final String PATIENT_L_NAME = "lastName";
    public static final String PATIENT_AGE = "age";
    public static final String PATIENT_SEX = "sex";

    public static final String C19TEST = "covid19Test";
    public static final String C19TEST_TYPE = "type";
    public static final String C19TEST_DATE = "date";
    public static final String C19TEST_RESULT = "result";
    public static final String C19TEST_ACCURACY = "accuracy";


    public static List<HealthCenter> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return readHCs(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<HealthCenter> readHCs(JSONArray array) throws JSONException {
        List<HealthCenter> healthCenters = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            HealthCenter healthCenter = readHC(array.getJSONObject(i));
            healthCenters.add(healthCenter);
        }
        return healthCenters;
    }

    private static HealthCenter readHC(JSONObject object) throws JSONException {
        String name = object.getString(HC_NAME);
        String address = object.getString(HC_ADDRESS);
        String location = object.getString(HC_LOCATION);
        boolean covid19Dedicated = object.getBoolean(HC_COVID19_DEDICATED);

        JSONArray jsonArray = object.getJSONArray(PATIENTS);
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Patient patient = readPatient(jsonArray.getJSONObject(i));
            if (patient.getCovid19Test() != null) {
                patients.add(patient);
            }
        }
        return new HealthCenter(name, address, location, covid19Dedicated, patients);
    }


    private static Patient readPatient(JSONObject object) throws JSONException {
        String firstName = object.getString(PATIENT_F_NAME);
        String lastName = object.getString(PATIENT_L_NAME);
        int age = object.getInt(PATIENT_AGE);
        boolean sex = object.getBoolean(PATIENT_SEX);
        Covid19Test covid19Test = readCovid19Test(object.getJSONObject(C19TEST));
        return new Patient(firstName, lastName, age, sex, covid19Test);
    }


    private static Covid19Test readCovid19Test(JSONObject object) throws JSONException {
        String type = object.getString(C19TEST_TYPE);
        double accuracy = object.getDouble(C19TEST_ACCURACY);
        if (!TestType.getByAccuracy(accuracy).equals(TestType.getByType(type))) {
            return null;
        }
        String date = object.getString(C19TEST_DATE);
        String[] dateValues = date.split("-");
        LocalDate testDate = LocalDate.of(Integer.parseInt(dateValues[2]),
                Integer.parseInt(dateValues[1]), Integer.parseInt(dateValues[0]));
        boolean result = object.getBoolean(C19TEST_RESULT);
        return new Covid19Test(TestType.getByType(type), testDate, result);
    }


}