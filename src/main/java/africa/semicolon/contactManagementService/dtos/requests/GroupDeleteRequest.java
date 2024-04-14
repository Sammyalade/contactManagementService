package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class GroupDeleteRequest {

    private int groupId;
    private int userId;
}
