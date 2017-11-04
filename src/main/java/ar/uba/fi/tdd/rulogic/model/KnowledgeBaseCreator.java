package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KnowledgeBaseCreator {

    /***
     *
     * @param databaseFilePath path to rules.db
     * Reads file, validates it and returns database.
     */
    public KnowledgeBase create(String databaseFilePath) throws InvalidDatabaseLineException {
        String databaseString = getDatabaseString(databaseFilePath);
        List<String> databaseStringList =  Arrays.asList(databaseString.split("\n"));
        validateDatabaseStringList(databaseStringList);

        return getDatabase(databaseStringList);
    }

    /***
        Reads the rules.db file and returns the database string
     ***/
    private String getDatabaseString(String databaseFilePath) {
        String databaseString = null;
        try {
            databaseString = this.fileToString(databaseFilePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return databaseString.replace(" ", "").replace("\t", "").replace(".","");
    }

    /***
        Aux method to read file
     ***/
    private String fileToString(String filePath) throws IOException {
        BufferedReader  bufferedReader= new BufferedReader(new FileReader(filePath));

        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    /***
     *
     * @param databaseStringList example: ['fact(val1)' , 'rule(X,Y):-fact1(X),fact2(Y)']
     * Builds and returns the database
     */
    private KnowledgeBase getDatabase(List<String> databaseStringList) {
        KnowledgeBase knowledgeBase = new KnowledgeBase();
         for (String line : databaseStringList) {
             if (isRuleString(line)) {
                 Rule rule = new Rule(line);
                 knowledgeBase.addRule(rule);
             }

             else {
                 Fact fact = new Fact(line);
                 knowledgeBase.addFact(fact);
             }
         }

         return knowledgeBase;
    }

    private boolean isRuleString(String line) {
        return line.contains(":-");
    }

    /***
        If any element on database string list is invalid throws exception
        with message of invalid rows
     ***/
    private void validateDatabaseStringList(List<String> databaseStringList) throws InvalidDatabaseLineException {
        List<Integer> invalidLineRows = new ArrayList<Integer>();
        int rowCount = 0;
        for (String line : databaseStringList) {
            if (!validateLine(line)) {
                invalidLineRows.add(rowCount);
            }
            rowCount += 1;
        }

        if (invalidLineRows.size() > 0) {
            String message = "The following rows are invalid: ";
            for (int i = 0; i < invalidLineRows.size() - 1; i++) {
                message = message.concat(String.valueOf(invalidLineRows.get(i)) + ",");
            }
            message = message.concat(String.valueOf(invalidLineRows.get(invalidLineRows.size()-1)));
            throw new InvalidDatabaseLineException(message);

        }
    }

    /***
        Use regex to validate the string. Returns true if is a valid fact or rule definition
     ***/
    private Boolean validateLine(String databaseStringLine) {
        return isValidFactString(databaseStringLine) || isValidRuleString(databaseStringLine);
    }

    private Boolean isValidFactString(String databaseStringLine) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)";
        return databaseStringLine.matches(regex);
    }

    private Boolean isValidRuleString(String databaseStringLine) {
        String regex = "^[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\):-[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\)(,[a-zA-Z]*\\([a-zA-Z]+(,[a-zA-Z]*)*\\))*";
        return databaseStringLine.matches(regex);
    }


}
