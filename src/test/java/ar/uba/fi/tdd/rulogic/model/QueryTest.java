package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

public class QueryTest {

    @Test
    public void testQueryCreation1() throws InvalidQueryException {
        Query query = new Query("query(x).");
        Assert.assertTrue(query.getName().equals("query"));
        Assert.assertTrue(query.getParameters().get(0).equals("x"));
    }

    @Test
    public void testQueryCreation2() throws InvalidQueryException {
        Query query = new Query("query(x,y,z).");
        Assert.assertTrue(query.getName().equals("query"));
        Assert.assertTrue(query.getParameters().get(0).equals("x"));
        Assert.assertTrue(query.getParameters().get(1).equals("y"));
        Assert.assertTrue(query.getParameters().get(2).equals("z"));

    }

    @Test
    public void testInvalidQueryCreation1() {
        try {
            new Query("");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testInvalidQueryCreation2() {
        try {
            new Query("varon");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testInvalidQueryCreation3() {
        try {
            new Query("1234");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testInvalidQueryCreation4() {
        try {
            new Query("varon()");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testInvalidQueryCreation5() {
        try {
            new Query("varon,)");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testInvalidQueryCreation6() {
        try {
            new Query("varon),");
            Assert.assertTrue(false);
        }
        catch (InvalidQueryException e) {
        }
    }

}
