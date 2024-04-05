package africa.semicolon.contactManagementService.services.userService;


import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.contactManagementService.utility.Mapper.checkEmptyStringUsername;
import static africa.semicolon.contactManagementService.utility.Mapper.map;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest userCreationRequest){
        if(checkEmptyStringUsername(userCreationRequest.getUsername()))
            throw new EmptyStringException("Username cannot be null");
        User newUser = map(userCreationRequest);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public void createContact(ContactCreationRequest contactCreationRequest) {

    }


}
