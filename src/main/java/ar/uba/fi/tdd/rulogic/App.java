package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.InvalidDatabaseLineException;
import ar.uba.fi.tdd.rulogic.model.InvalidQueryException;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.model.KnowledgeBaseCreator;

import java.io.File;
import java.util.Scanner;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
	    App app = new App();

        KnowledgeBaseCreator knowledgeBaseCreator = new KnowledgeBaseCreator();
        KnowledgeBase knowledgeBase = null;
        try {
            knowledgeBase = knowledgeBaseCreator.create(app.getDBPathFile());
        } catch (InvalidDatabaseLineException e) {
            System.out.println("Check your databasee file!");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        app.answerQueries(knowledgeBase);


    }

    private String getDBPathFile() {
	    return new File("").getAbsolutePath().concat(File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "rules.db");
    }

    private void answerQueries(KnowledgeBase knowledgeBase) {
	    String EXIT_WORD = "exit";
        System.out.println("I shall answer all your questions!");
        System.out.println("Type exit to terminate");
        String queryString;
        Scanner scan = new Scanner(System.in);
	    while (true) {
	        queryString = scan.nextLine();
	        if (EXIT_WORD.equals(queryString)) break;

            try {
                if (knowledgeBase.answer(queryString)) {
                    System.out.println("SI");
                }
                else {
                    System.out.println("NO");
                }
            }
            catch (InvalidQueryException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
