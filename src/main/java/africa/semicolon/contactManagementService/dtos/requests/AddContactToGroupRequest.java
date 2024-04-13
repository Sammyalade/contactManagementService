package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class AddContactToGroupRequest {

    private int contactId;
    private int groupId;
}
