package africa.semicolon.contactManagementService.dtos.requests;


import lombok.Data;

@Data
public class UserCreationRequest {

    private String username;
    private String email;
}
