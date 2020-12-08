package ie.dam.covid19_info.domain;

import java.io.Serializable;

public class Patient implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private boolean sex;
    /**
     * true -> female
     * false -> male
     */
    private Covid19Test covid19Test;


    public Patient(String firstName, String lastName, int age, boolean sex, Covid19Test covid19Test) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.covid19Test = covid19Test;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Covid19Test getCovid19Test() {
        return covid19Test;
    }

    public void setCovid19Test(Covid19Test covid19Test) {
        this.covid19Test = covid19Test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (age != patient.age) return false;
        if (sex != patient.sex) return false;
        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(patient.lastName) : patient.lastName != null)
            return false;
        return covid19Test != null ? covid19Test.equals(patient.covid19Test) : patient.covid19Test == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (sex ? 1 : 0);
        result = 31 * result + (covid19Test != null ? covid19Test.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + age +
                ", sex=" + sex +
                ", covid19Test=" + covid19Test +
                '}';
    }
}
