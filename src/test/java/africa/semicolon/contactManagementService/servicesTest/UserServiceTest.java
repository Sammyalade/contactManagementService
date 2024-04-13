package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.requests.ContactCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.UserCreationRequest;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
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
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;

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

    @Test
    public void createUser_saveContact_contactIsSavedTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        userService.createUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactService.createContact(contactCreationRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(contactRepository.count(), is(1L));
    }

    @Test
    public void createUser_saveContactInUser_contactIsSavedTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        User user = userService.createUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUser(user);
        userService.createContact(contactCreationRequest);
        assertThat(contactRepository.count(), is(1L));
        assertThat(userRepository.count(), is(1L));
        assertThat(user.getContacts().size(), is(1));
    }
}
