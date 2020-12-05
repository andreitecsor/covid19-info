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

    public Covid19Test(TestType type, LocalDate date, boolean result) {
        this.type = type;
        this.date = date;
        this.result = result;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Covid19Test that = (Covid19Test) o;

        if (result != that.result) return false;
        if (type != that.type) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result1 = type != null ? type.hashCode() : 0;
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + (result ? 1 : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "Covid19Test{" +
                "type=" + type +
                ", date=" + date +
                ", result=" + result +
                '}';
    }
}
