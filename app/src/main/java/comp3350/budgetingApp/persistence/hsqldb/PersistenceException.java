package comp3350.budgetingApp.persistence.hsqldb;

public class PersistenceException extends RuntimeException{
    public PersistenceException(final Exception cause) {
        super(cause);
    }
}
