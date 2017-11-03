package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fact {
    private String name;
    private List<String> values;

    /***
     *
     * @param factString example: 'padre(juan,pepe)'
     */
    public Fact(String factString) {
        factString = factString.replace(")","");
        String[] factStringSplitted = factString.split("\\(");
        this.name = factStringSplitted[0];
        this.values = new ArrayList<String>(Arrays.asList(factStringSplitted[1].split(",")));
    }

    public Fact(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }

    public Fact(Fact another) {
        this.name = another.name;
        this.values = new ArrayList<String>();
        this.values.addAll(another.getValues());
    }

    public String getName() {
        return this.name;
    }

    public List<String> getValues() {
        return this.values;
    }

}
