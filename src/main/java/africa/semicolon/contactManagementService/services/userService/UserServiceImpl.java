package africa.semicolon.contactManagementService.services.userService;


import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.*;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.contactManagementService.utility.Mapper.*;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;

    public User createUser(UserCreationRequest userCreationRequest){
        if(checkEmptyStringUsername(userCreationRequest.getUsername()))
            throw new EmptyStringException("Username cannot be null");
        User newUser = map(userCreationRequest);
        userRepository.save(newUser);
        return newUser;
    }


    @Override
    public void createContact(ContactCreationRequest contactCreationRequest) {
        Contact contact = contactService.createContact(contactCreationRequest);
        User user = contactCreationRequest.getUser();
        user.setContacts((List<Contact>) checkIfListIsNull(user.getContacts()));
        user.getContacts().add(contact);
    }

    @Override
    public void loginUser(UserLoginRequest userLoginRequest) {

    }

    @Override
    public void logoutUser(String username) {

    }

    @Override
    public void updateUser(UserUpdateRequest userUpdateRequest) {

    }

    @Override
    public void deleteUser(UserDeleteRequest userDeleteRequest) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void changePassword(UserPasswordChangeRequest userPasswordChangeRequest) {

    }

    @Override
    public void resetPassword(UserPasswordResetRequest userPasswordResetRequest) {

    }


}
