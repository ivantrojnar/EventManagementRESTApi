package hr.itrojnar.eventmanagementrestapi.service.security;

public class TokenExpiredException extends RuntimeException {

    private final String errorMessage;

    public TokenExpiredException(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
