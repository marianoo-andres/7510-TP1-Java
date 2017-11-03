package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnowledgeBase {
	private List<Fact> facts;
	private List<Rule> rules;

	public KnowledgeBase() {
	    this.facts = new ArrayList<Fact>();
	    this.rules = new ArrayList<Rule>();
    }

    public void addFact(Fact fact) {
	    this.facts.add(fact);
    }

    public void addRule(Rule rule) {
	    this.rules.add(rule);
    }

    public List<Fact> getFacts() {
	    return facts;
    }

    public List<Rule> getRules() {
	    return rules;
    }

    public boolean answer(String queryString) throws InvalidQueryException {
        queryString = queryString.replace(" ","").replace("\t","").replace("\n","").replace(".","");
        validateQueryString(queryString);
        String [] splitted = queryString.replace(")","").split("\\(");
        String name = splitted[0];
        List<String> values = new ArrayList<String>(Arrays.asList(splitted[1].split(",")));
        Fact fact = new Fact(name, values);
        if (queryFact(fact)) return true;
        return queryRule(name, values);

    }

    private Boolean queryFact(Fact fact) {
        for (Fact factStored : this.facts) {
            if (!fact.getName().equals(factStored.getName())) {continue;}
            List<String> factStoredValues = factStored.getValues();
            if (factStoredValues.size() != fact.getValues().size()) {continue;}
            Boolean equal = true;
            for (int i = 0; i < factStoredValues.size(); i++) {
                if (!factStoredValues.get(i).equals(fact.getValues().get(i))) {
                    equal = false;
                    break;
                }
            }
            if (equal) {return true;}
        }
        return false;
    }

    private Boolean queryRule(String name, List<String> values) {
	    for (Rule rule : this.rules) {
	        if (!name.equals(rule.getName())) continue;
	        if (values.size() != rule.getParameterCount()) continue;
	        List<Fact> ruleFactsBinded = rule.bind(values);
	        Boolean equal = true;
	        for (Fact fact : ruleFactsBinded) {
	            if (!this.queryFact(fact)) {
	                equal = false;
                    break;
                }
            }
            if (equal) {
	            return true;
	        }
        }
        return false;
    }

    /***
     * Uses regex to validate query string
     */
    private void validateQueryString(String queryString) throws InvalidQueryException {
        String regex = "^[a-zA-Z]+\\([a-zA-Z]+(,[a-zA-Z]+)*\\)$";
        if (!queryString.matches(regex)) {
            throw new InvalidQueryException("The query is invalid");
        }
    }
}
