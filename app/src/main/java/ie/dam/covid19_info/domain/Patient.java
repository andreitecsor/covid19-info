package ie.dam.covid19_info.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean sex;
    /**
     * true -> female
     * false -> male
     */
    private Covid19Test covid19Test;


    public Patient() {
    }


}
