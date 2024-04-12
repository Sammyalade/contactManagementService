package africa.semicolon.contactManagementService.dtos;

import lombok.Data;

@Data
public class UpdateGroupRequest {

    private int groupId;
    private String groupName;
}
