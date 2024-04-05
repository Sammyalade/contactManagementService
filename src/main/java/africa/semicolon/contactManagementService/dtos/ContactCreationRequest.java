package africa.semicolon.contactManagementService.dtos;

import lombok.Data;

@Data
public class ContactCreationRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
