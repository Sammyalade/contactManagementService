package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class UpdateGroupRequest {

    private int groupId;
    private String groupName;
}
