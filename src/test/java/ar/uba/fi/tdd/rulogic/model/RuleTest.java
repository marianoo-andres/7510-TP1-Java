package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RuleTest {

    @Test
    public void testRuleCreation1() {
        Rule rule = new Rule("rule(X):-fact(X)");
        List<Fact> facts = rule.getFacts();

        Assert.assertTrue(rule.getName().equals("rule"));
        Assert.assertTrue(rule.getParameterCount() == 1);
        Assert.assertTrue(facts.get(0).getName().equals("fact"));
        Assert.assertTrue(facts.get(0).getValues().get(0).equals("0"));
    }

    @Test
    public void testRuleCreation2() {
        Rule rule = new Rule("hijo(X,Y):-varon(X),padre(Y,X)");
        List<Fact> facts = rule.getFacts();

        Assert.assertTrue(rule.getName().equals("hijo"));
        Assert.assertTrue(rule.getParameterCount() == 2);
        Assert.assertTrue(facts.get(0).getName().equals("varon"));
        Assert.assertTrue(facts.get(0).getValues().get(0).equals("0"));
        Assert.assertTrue(facts.get(1).getName().equals("padre"));
        Assert.assertTrue(facts.get(1).getValues().get(0).equals("1"));
        Assert.assertTrue(facts.get(1).getValues().get(1).equals("0"));

    }

    @Test
    public void testRuleBind() {
        Rule rule = new Rule("hijo(X,Y):-varon(X),padre(Y,X)");
        List<String> parameters = new ArrayList<String>();
        parameters.add("pepe");
        parameters.add("juan");
        List<Fact> bindedFacts = rule.bind(parameters);

        Assert.assertTrue(rule.getName().equals("hijo"));
        Assert.assertTrue(rule.getParameterCount() == 2);
        Assert.assertTrue(bindedFacts.get(0).getName().equals("varon"));
        Assert.assertTrue(bindedFacts.get(0).getValues().get(0).equals("pepe"));
        Assert.assertTrue(bindedFacts.get(1).getName().equals("padre"));
        Assert.assertTrue(bindedFacts.get(1).getValues().get(0).equals("juan"));
        Assert.assertTrue(bindedFacts.get(1).getValues().get(1).equals("pepe"));

    }
}
