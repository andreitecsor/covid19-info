package ie.dam.covid19_info.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class Covid19Test implements Serializable {
    /**
     * RT-PCR ->  99%
     * Antigen test (test rapid) -> 50%
     * Antibody test (test anticorpi/test rapid) 30% la o saptamana dupa primele simptome
     */
    private TestType type;
    private LocalDate date;
    private boolean result;


}
