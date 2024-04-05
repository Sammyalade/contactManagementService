package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.UserCreationRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.services.userService.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser_saveUser_numberOfUserIsOneTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        userService.createUser(userCreationRequest);
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void createUserWithEmptyString_throwsEmptyStringExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        assertThrows(EmptyStringException.class, ()-> userService.createUser(userCreationRequest));
    }
}
