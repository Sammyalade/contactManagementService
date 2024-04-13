package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.*;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserLoginRequest;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;

import java.util.List;

public interface UserService {


    UserRegistrationResponse registerUser(UserCreationRequest userCreationRequest);

    ContactCreationResponse createContact(ContactCreationRequest contactCreationRequest);

    User searchUserById(int userId);

    void loginUser(UserLoginRequest userLoginRequest);
}
