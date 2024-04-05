package africa.semicolon.contactManagementService.services.userService;


import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.contactManagementService.utility.Mapper.map;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserCreationRequest userCreationRequest){
        userRepository.save(map(userCreationRequest ));
    }

}
