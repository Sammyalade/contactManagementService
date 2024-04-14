package africa.semicolon.contactManagementService.dtos.responses;

import lombok.Data;

@Data
public class UserUpdateResponse {

    private int userId;
    private String username;
    private String email;
}
