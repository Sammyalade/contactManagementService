package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.requests.*;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.GroupCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserUpdateResponse;
import africa.semicolon.contactManagementService.exception.ContactUpdateResponse;

import java.util.List;

public interface UserService {


    UserRegistrationResponse registerUser(UserCreationRequest userCreationRequest);

    ContactCreationResponse createContact(ContactCreationRequest contactCreationRequest);

    User searchUserById(int userId);

    void loginUser(UserLoginRequest userLoginRequest);

    void logoutUser(int id);

    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest);

    void deleteUser(int id);

    ContactUpdateResponse updateContact(ContactUpdateRequest contactUpdateRequest);

    GroupCreationResponse createGroup(GroupCreationRequest groupCreationRequest);

    void updateGroup(UpdateGroupRequest updateGroupRequest);

    void deleteGroup(GroupDeleteRequest groupDeleteRequest);

    void deleteContact(ContactDeleteRequest contactDeleteRequest);

    List<Contact> findAllContact(int userId);

    List<Group> findAllGroup(int userId);
}
