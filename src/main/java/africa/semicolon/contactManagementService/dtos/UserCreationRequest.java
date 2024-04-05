package africa.semicolon.contactManagementService.dtos;


import lombok.Data;

@Data
public class UserCreationRequest {

    private String username;
    private String password;
    private String email;
}
