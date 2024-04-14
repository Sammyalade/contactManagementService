package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private int userId;
    private String username;
}
