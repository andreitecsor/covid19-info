package ie.dam.covid19_info.domain;

import java.io.Serializable;
import java.util.List;

public class HealthCenter implements Serializable {
    private String name;
    private String city;
    private String county;
    private List<Patient> patients;
}
