package africa.semicolon.contactManagementService.dtos.responses;

import lombok.Data;

@Data
public class ContactCreationResponse {

    private int userId;
    private int contactId;
    private String username;
    private String contactName;
}
