package africa.semicolon.contactManagementService.dtos.requests;

import africa.semicolon.contactManagementService.datas.models.User;
import lombok.Data;

@Data
public class ContactUpdateRequest {

    private int contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private int userId;
}
