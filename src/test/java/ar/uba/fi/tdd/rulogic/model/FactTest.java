package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

public class FactTest {

    @Test
    public void testFactCreation1() {
        Fact fact = new Fact("varon(juan)");
        Assert.assertTrue(fact.getName().equals("varon"));
        Assert.assertTrue(fact.getValues().get(0).equals("juan"));

    }

    @Test
    public void testFactCreation2() {
        Fact fact = new Fact("padre(juan,maria)");
        Assert.assertTrue(fact.getName().equals("padre"));
        Assert.assertTrue(fact.getValues().get(0).equals("juan"));
        Assert.assertTrue(fact.getValues().get(1).equals("maria"));

    }

    @Test
    public void testFactCreationWithNumbers() {
        Fact fact = new Fact("padre(0,1)");
        Assert.assertTrue(fact.getName().equals("padre"));
        Assert.assertTrue(fact.getValues().get(0).equals("0"));
        Assert.assertTrue(fact.getValues().get(1).equals("1"));

    }

}
