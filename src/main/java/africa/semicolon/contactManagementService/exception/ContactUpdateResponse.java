package africa.semicolon.contactManagementService.exception;

import lombok.Data;

@Data
public class ContactUpdateResponse {

    private int contactId;
    private String contactName;
    private int userId;
}
