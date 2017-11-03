package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBaseCreator;

import java.io.File;
import java.io.IOException;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws IOException {
		System.out.println("I shall answer all your questions!");

        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
    }

    private static String getDBPathFile() {
	    return new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "rules.db");
    }
}
