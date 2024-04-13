package africa.semicolon.contactManagementService.dtos;

import lombok.Data;

@Data
public class RemoveContactFromGroupRequest {

    private int contactId;
    private int groupId;
}
