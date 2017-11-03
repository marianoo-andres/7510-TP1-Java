package ar.uba.fi.tdd.rulogic.model;

public class InvalidQueryException extends Exception {
    public InvalidQueryException(String message) {
        super(message);
    }
}
