package africa.semicolon.contactManagementService.dtos;


import lombok.Data;

@Data
public class UserCreationRequest {

    public String username;
    public String password;
    public String email;
}
