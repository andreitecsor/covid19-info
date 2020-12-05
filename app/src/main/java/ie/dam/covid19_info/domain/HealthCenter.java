package ie.dam.covid19_info.domain;

import java.io.Serializable;
import java.util.List;

public class HealthCenter implements Serializable {
    private String name;
    private String city;
    private String county;
    private List<Patient> patients;

    public HealthCenter(String name, String city, String county, List<Patient> patients) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.patients = patients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HealthCenter that = (HealthCenter) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (county != null ? !county.equals(that.county) : that.county != null) return false;
        return patients != null ? patients.equals(that.patients) : that.patients == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (county != null ? county.hashCode() : 0);
        result = 31 * result + (patients != null ? patients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HealthCenter{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", patients=" + patients +
                '}';
    }
}
