package by.academy.it.dao.exceptions;

public class DaoException extends Throwable {

    private Exception exception;

    public DaoException(Exception exception) {
        this.exception = exception;
    }

    public DaoException() {

    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
