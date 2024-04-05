package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.dtos.UserCreationRequest;

public interface UserService {

    void createUser(UserCreationRequest userCreationRequest);
}
