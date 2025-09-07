package a2_2101040097;

import utils.NotPossibleException;
import java.util.Objects;

public class PC {
    private String model;
    private final int year;
    private final String manufacturer;
    private Set<String> comps;

    public PC(String model, int year, String manufacturer, Set<String> comps) {
        if (validateModel(model)
                && validateYear(year)
                && validateManufacturer(manufacturer)) {
            this.model = model;
            this.year = year;
            this.manufacturer = manufacturer;
            this.comps = comps;
        } else {
            throw new NotPossibleException("Object's creation failed");
        }
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Set<String> getComps() {
        return comps;
    }

    public void setComps(Set<String> comps) {
        this.comps = comps;
    }

    private boolean validateModel(String model) {
        return model != null && model.length() <= 25; // Corrected length to 25
    }

    private boolean validateYear(int year) {
        return year >= 1984;
    }

    private boolean validateManufacturer(String manufacturer) {
        return manufacturer != null && manufacturer.length() <= 15;
    }

    @Override
    public String toString() {
        return String.format("PC<%s,%d,%s,%s>", model, year, manufacturer, comps.toString()); // Corrected toString()
    }

    @Override
    public boolean equals(Object comparedPC) {
        // Changed parameter name to comparedPC
        if (this == comparedPC) return true;
        if (comparedPC == null || getClass() != comparedPC.getClass()) return false;
        PC comparedpc = (PC) comparedPC; // Changed variable name to comparedpc
        return year == comparedpc.year
                && model.equals(comparedpc.model)
                && manufacturer.equals(comparedpc.manufacturer)
                && comps.equals(comparedpc.comps);
    }
}