package ar.uba.fi.tdd.rulogic.model;

public class InvalidDatabaseLineException extends Exception {
    public InvalidDatabaseLineException(String message) {
        super(message);
    }
}
