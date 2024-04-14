package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class GroupCreationRequest {

    private int userId;
    private String groupName;
}
