package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rule {
    private String name;
    private List<Fact> facts;
    private int parameterCount;

    /***
     *
     * @param ruleString example: hijo(X,Y):-varon(X),padre(Y,X)
     */
    public Rule(String ruleString) {
        // ['hijo(X,Y)', 'varon(X),padre(Y,X)']
        String [] ruleStringSplitted = ruleString.split(":-");
        // ['hijo', 'X,Y']
        String [] ruleStringSplittedFirstSplitted = ruleStringSplitted[0].replace(")","").split("\\(");
        // ['X','Y']
        String [] variables = ruleStringSplittedFirstSplitted[1].split(",");
        // ['varon(X)','padre(Y,X)']
        String [] factStrings = ruleStringSplitted[1].split("\\),");
        this.name = ruleStringSplittedFirstSplitted[0];
        this.facts = new ArrayList<Fact>();
        this.parameterCount = variables.length;
        this.mapFactString(variables,factStrings);
        this.setFacts(factStrings);
    }

    /***
     * Maps the variables of rule to position numbers of variables.
     * Example: variables ['X', 'Y'] factString ['varon(X)', 'padre(Y,X)'] maps to
     * ['varon(0)','padre(1,0)']
     ***/
    private void mapFactString(String [] variables, String [] factStrings) {
        for (int i = 0; i < variables.length; i++) {
            for (int j = 0; j < factStrings.length; j++) {
                factStrings[j] = factStrings[j].replace(variables[i], String.valueOf(i));
            }
        }
    }
    private void setFacts(String [] factStrings) {
        for (String factString : factStrings) {
            this.facts.add(new Fact(factString));
        }
    }

    public String getName() {
        return this.name;
    }

    public List<Fact> getFacts() {
        return this.facts;
    }

    public int getParameterCount() {
        return this.parameterCount;
    }

    /***
     *
     * @param parameters to bind
     * Binds the facts values of the rule to the parameters and returns them
     */
    public List<Fact> bind(List<String> parameters) {
        List<Fact> factsToBind = new ArrayList<Fact>();
        for (Fact fact : this.facts) {
            factsToBind.add(new Fact(fact));
        }
        for (int i = 0; i < this.parameterCount; i++) {
            for (Fact factToBind : factsToBind) {
                List<String> factToBindValues = factToBind.getValues();
                for (int j = 0; j < factToBindValues.size(); j++) {
                    if (factToBindValues.get(j).equals(String.valueOf(i))) {
                        factToBindValues.set(j, parameters.get(i));
                    }
                }
            }
        }
        return factsToBind;
    }
}
