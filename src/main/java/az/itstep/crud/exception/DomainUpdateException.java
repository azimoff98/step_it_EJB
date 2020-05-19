package az.itstep.crud.exception;

public class DomainUpdateException extends RuntimeException {

    public DomainUpdateException() {
        super();
    }

    public DomainUpdateException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
