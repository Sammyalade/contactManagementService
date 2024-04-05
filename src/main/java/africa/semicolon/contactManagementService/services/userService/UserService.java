package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;

public interface UserService {

    User createUser(UserCreationRequest userCreationRequest);

    void createContact(ContactCreationRequest contactCreationRequest);
}
