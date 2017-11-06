package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.List;

public class KnowledgeBaseCreatorTest {


    @Test
    public void testValidDatabaseCreation() throws InvalidDatabaseLineException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB =  new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "small.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);
        List<Fact> facts = knowledgeBase.getFacts();
        List<Rule> rules = knowledgeBase.getRules();

        Assert.assertTrue(facts.get(0).getName().equals("varon"));
        Assert.assertTrue(facts.get(0).getValues().get(0).equals("juan"));
        Assert.assertTrue(facts.get(1).getName().equals("varon"));
        Assert.assertTrue(facts.get(1).getValues().get(0).equals("pepe"));

        Assert.assertTrue(rules.get(0).getName().equals("hijo"));
        Assert.assertTrue(rules.get(0).getParameterCount() == 2);
        Assert.assertTrue(rules.get(0).getFacts().get(0).getName().equals("varon"));
        Assert.assertTrue(rules.get(0).getFacts().get(0).getValues().get(0).equals("0"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getName().equals("padre"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getValues().get(0).equals("1"));
        Assert.assertTrue(rules.get(0).getFacts().get(1).getValues().get(1).equals("0"));
    }

    @Test
    public void testInvalidDatabaseCreation() {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB =  new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "invalid.db");
        try {
            knowledgeBaseCreator.create(pathToDB);
            Assert.assertTrue(false);
        } catch (InvalidDatabaseLineException e) {
            Assert.assertTrue(e.getMessage().equals("The following rows are invalid: 1,2,3,5,7,8,12,13,16,17,18"));
        }
    }
}
