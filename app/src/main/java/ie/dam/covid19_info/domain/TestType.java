package ie.dam.covid19_info.domain;

import java.util.HashMap;
import java.util.Map;

public enum TestType {
    PCR("RT-PCR", 0.99),
    ANTIGEN("Antigen", 0.5),
    ANTIBODY("Antibody", 0.3);

    private static final Map<String, TestType> TYPE_MAP = new HashMap<>();
    private static final Map<Double, TestType> ACCURACY_MAP = new HashMap<>();

    static {
        for (TestType testType : values()) {
            TYPE_MAP.put(testType.type, testType);
            ACCURACY_MAP.put(testType.accuracy, testType);
        }
    }

    private String type;
    private double accuracy;

    TestType(String type, double accuracy) {
        this.type = type;
        this.accuracy = accuracy;
    }

    public static TestType getByType(String type) {
        return TYPE_MAP.get(type);
    }

    public static TestType getByAccuracy(Double accuracy) {
        return ACCURACY_MAP.get(accuracy);
    }

}
