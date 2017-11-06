package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class KnowledgeBaseTest {

    @Test
    public void testInvalidQueries() throws InvalidDatabaseLineException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB = new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "big.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);

        try {
            knowledgeBase.answer("varonjavier).");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
        try {
            knowledgeBase.answer("varon().");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
        try {
            knowledgeBase.answer("varon(,)).");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
        try {
            knowledgeBase.answer("12312");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
        try {
            knowledgeBase.answer(").");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
        try {
            knowledgeBase.answer("");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }

        try {
            knowledgeBase.answer("varon");
            Assert.assertTrue(false);
        } catch (InvalidQueryException e) {
        }
    }

    @Test
    public void testValidFactQueriesBigDB() throws InvalidDatabaseLineException, InvalidQueryException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB = new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "big.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);

        Assert.assertTrue(knowledgeBase.answer("varon(juan)."));
        Assert.assertTrue(knowledgeBase.answer("varon(pepe)."));
        Assert.assertTrue(knowledgeBase.answer("varon(hector)."));
        Assert.assertTrue(knowledgeBase.answer("varon(roberto)."));
        Assert.assertTrue(knowledgeBase.answer("varon(alejandro)."));
        Assert.assertTrue(knowledgeBase.answer("varon(nicolas)."));
        Assert.assertTrue(knowledgeBase.answer("mujer(maria)."));
        Assert.assertTrue(knowledgeBase.answer("mujer(cecilia)."));
        Assert.assertTrue(knowledgeBase.answer("padre(juan,pepe)."));
        Assert.assertTrue(knowledgeBase.answer("padre(juan,pepa)."));
        Assert.assertTrue(knowledgeBase.answer("padre(hector,maria)."));
        Assert.assertTrue(knowledgeBase.answer("padre(roberto,alejandro)."));
        Assert.assertTrue(knowledgeBase.answer("padre(roberto,cecilia)."));
        Assert.assertTrue(knowledgeBase.answer("hermano(nicolas,roberto)."));
        Assert.assertTrue(knowledgeBase.answer("hermano(roberto,nicolas)."));

        Assert.assertFalse(knowledgeBase.answer("varon(carlitos)."));
        Assert.assertFalse(knowledgeBase.answer("varon(julieta)."));
        Assert.assertFalse(knowledgeBase.answer("mujer(jose)."));
        Assert.assertFalse(knowledgeBase.answer("mujer(valentina)."));
        Assert.assertFalse(knowledgeBase.answer("padre(hector,juan)."));
        Assert.assertFalse(knowledgeBase.answer("hermano(nicolas, juan)."));

    }

    @Test
    public void testValidRuleQueriesBigDB() throws InvalidDatabaseLineException, InvalidQueryException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB = new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "big.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);

        Assert.assertTrue(knowledgeBase.answer("hijo(pepe,juan)."));
        Assert.assertTrue(knowledgeBase.answer("hijo(alejandro,roberto)."));
        Assert.assertTrue(knowledgeBase.answer("hija(cecilia,roberto)."));
        Assert.assertTrue(knowledgeBase.answer("tio(nicolas,alejandro,roberto)."));
        Assert.assertTrue(knowledgeBase.answer("tio(nicolas,cecilia,roberto)."));

        Assert.assertFalse(knowledgeBase.answer("hija(pepa,juan)."));
        Assert.assertFalse(knowledgeBase.answer("tio(pepa,juan,carlos)."));
        Assert.assertFalse(knowledgeBase.answer("hijo(juan,pepa)."));

    }

    @Test
    public void testValidFactQueriesNumbersDB() throws InvalidDatabaseLineException, InvalidQueryException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB = new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "numbers.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);

        Assert.assertTrue(knowledgeBase.answer("add(zero,zero,zero)."));
        Assert.assertTrue(knowledgeBase.answer("add(zero,one,one)."));
        Assert.assertFalse(knowledgeBase.answer("add(one,two,three)."));
        Assert.assertFalse(knowledgeBase.answer("NotAFact(zero,zero,zero)."));
    }

    @Test
    public void testValidRuleQueriesNumbersDB() throws InvalidDatabaseLineException, InvalidQueryException {
        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        String pathToDB = new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "test"
                + File.separator + "resources" + File.separator + "numbers.db");
        KnowledgeBase knowledgeBase = (KnowledgeBase) knowledgeBaseCreator.create(pathToDB);

        Assert.assertFalse(knowledgeBase.answer("subtract(one,one,two)."));
        Assert.assertTrue(knowledgeBase.answer("subtract(two,one,one)."));
    }
}
