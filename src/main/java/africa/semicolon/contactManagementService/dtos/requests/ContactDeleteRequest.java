package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class ContactDeleteRequest {

    private int contactId;
    private int userId;
}
