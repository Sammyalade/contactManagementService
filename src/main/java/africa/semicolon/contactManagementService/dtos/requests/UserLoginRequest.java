package africa.semicolon.contactManagementService.dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    private int userId;
    private String email;

}
