package africa.semicolon.contactManagementService.dtos.responses;

import lombok.Data;

@Data
public class GroupCreationResponse {

    private int groupId;
    private String groupName;
    private int userId;
}
