package africa.semicolon.contactManagementService.exception;

public class EmailAlreadyRegisteredException extends ContactManagementServiceException {
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
