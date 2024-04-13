package africa.semicolon.contactManagementService.dtos;

import lombok.Data;

@Data
public class AddContactToGroupRequest {

    private int contactId;
    private int groupId;
}
