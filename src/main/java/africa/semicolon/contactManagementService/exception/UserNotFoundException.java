package africa.semicolon.contactManagementService.exception;

public class UserNotFoundException extends ContactManagementServiceException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
