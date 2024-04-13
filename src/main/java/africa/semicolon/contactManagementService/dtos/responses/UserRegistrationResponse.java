package africa.semicolon.contactManagementService.dtos.responses;

import lombok.Data;

@Data
public class UserRegistrationResponse {

    private int userId;
    private String username;
    private String email;
}
