package africa.semicolon.contactManagementService.exception;

public class ContactNotFoundException extends ContactManagementServiceException{
    public ContactNotFoundException(String message) {
        super(message);
    }
}
