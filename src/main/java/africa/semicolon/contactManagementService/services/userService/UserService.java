package africa.semicolon.contactManagementService.services.userService;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.dtos.*;

import java.util.List;

public interface UserService {

    User createUser(UserCreationRequest userCreationRequest);

    void createContact(ContactCreationRequest contactCreationRequest);
    void loginUser(UserLoginRequest userLoginRequest);
    void logoutUser(String username);
    void updateUser(UserUpdateRequest userUpdateRequest);
    void deleteUser(UserDeleteRequest userDeleteRequest);
    List<User> getAllUsers();
    void changePassword(UserPasswordChangeRequest userPasswordChangeRequest);
    void resetPassword(UserPasswordResetRequest userPasswordResetRequest);

}
