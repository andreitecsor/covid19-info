package ie.dam.covid19_info.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HealthCenter implements Serializable {
    private String name;
    private String address;
    private String location;
    private boolean covid19Dedicated;
    private List<Patient> patients;

    public HealthCenter(String name, String address, String location, boolean covid19Dedicated, List<Patient> patients) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.covid19Dedicated = covid19Dedicated;
        this.patients = patients;
    }

    public HealthCenter(String name, String address, String location, boolean covid19Dedicated) {
        this.name = name;
        this.address = address;
        this.location = location;
        this.covid19Dedicated = covid19Dedicated;
        this.patients = new ArrayList<>();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCovid19Dedicated(boolean covid19Dedicated) {
        this.covid19Dedicated = covid19Dedicated;
    }

    public boolean isCovid19Dedicated() {
        return covid19Dedicated;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

        if (covid19Dedicated != that.covid19Dedicated) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null)
            return false;
        return patients != null ? patients.equals(that.patients) : that.patients == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (covid19Dedicated ? 1 : 0);
        result = 31 * result + (patients != null ? patients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HealthCenter{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", covid19Dedicated=" + covid19Dedicated +
                ", patients=" + patients +
                '}';
    }
}
