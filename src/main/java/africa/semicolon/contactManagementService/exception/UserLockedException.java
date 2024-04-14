package africa.semicolon.contactManagementService.exception;


public class UserLockedException extends ContactManagementServiceException {
    public UserLockedException(String message) {
        super(message);
    }
}
