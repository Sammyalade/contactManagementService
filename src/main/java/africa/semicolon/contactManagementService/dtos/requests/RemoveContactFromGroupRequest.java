package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class RemoveContactFromGroupRequest {

    private int contactId;
    private int groupId;
}
