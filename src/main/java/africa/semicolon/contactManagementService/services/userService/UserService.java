package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;

public interface UserService {

    User createUser(UserCreationRequest userCreationRequest);
}
