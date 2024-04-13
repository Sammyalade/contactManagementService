package africa.semicolon.contactManagementService.services.userService;


import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserLoginRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserUpdateRequest;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;
import africa.semicolon.contactManagementService.exception.EmailAlreadyRegisteredException;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.exception.UserNotFoundException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static africa.semicolon.contactManagementService.utility.Mapper.*;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;

    @Override
    public UserRegistrationResponse registerUser(UserCreationRequest userCreationRequest) {
        if (isEmptyOrNullString(userCreationRequest.getUsername())) throw new EmptyStringException("Username cannot be null or empty");
        if(userRepository.findByEmail(userCreationRequest.getEmail()) != null)
            throw new EmailAlreadyRegisteredException("Email already registered. Please login instead");
        User user = map(userCreationRequest);
        userRepository.save(user);
        return map(user);
    }

    @Override
    public ContactCreationResponse createContact(ContactCreationRequest contactCreationRequest) {
        User user = searchUserById(contactCreationRequest.getUserId());
        Contact contact = contactService.createContact(contactCreationRequest);
        user.setContacts((List<Contact>) checkIfListIsNull(user.getContacts()));
        user.getContacts().add(contact);
        userRepository.save(user);
        return map(user, contact);
    }


    @Override
    public User searchUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserNotFoundException("User not found");
    }


    @Override
    public void loginUser(UserLoginRequest userLoginRequest) {
        User user = searchUserById(userLoginRequest.getUserId());
        if(user.getEmail().equals(userLoginRequest.getEmail())) user.setLocked(false);
    }

    @Override
    public void logoutUser(int id) {
        User user = searchUserById(id);
        user.setLocked(true);
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User user = searchUserById(userUpdateRequest.getId());
        if(!user.isLocked()) {
            if(userUpdateRequest.getUsername() != null) user.setUsername(userUpdateRequest.getUsername());
            if(userUpdateRequest.getEmail() != null) user.setEmail(userUpdateRequest.getEmail());
            userRepository.save(user);
            return map(userUpdateRequest);
        }
        throw new UserNotLoggedInException("User not logged in. Please login and try again");
    }



    @Override
    public void deleteUser(int id) {
        User user = searchUserById(id);
        if(!user.isLocked()) {
            userRepository.delete(user);
        } else throw new UserNotLoggedInException("User not logged in. Please login and try again");
    }

}
