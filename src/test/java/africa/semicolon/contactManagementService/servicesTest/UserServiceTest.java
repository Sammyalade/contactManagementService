package africa.semicolon.contactManagementService.servicesTest;

import africa.semicolon.contactManagementService.datas.repositories.ContactRepository;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.requests.*;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.GroupCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;
import africa.semicolon.contactManagementService.exception.EmptyStringException;
import africa.semicolon.contactManagementService.exception.UserLockedException;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import africa.semicolon.contactManagementService.services.userService.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser_saveUser_numberOfUserIsOneTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        userService.registerUser(userCreationRequest);
        assertThat(userRepository.count(), is(1L));
    }

    @Test
    public void createUserWithEmptyString_throwsEmptyStringExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        assertThrows(EmptyStringException.class, ()-> userService.registerUser(userCreationRequest));
    }

    @Test
    public void createUser_saveContact_contactIsSavedTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        userService.registerUser(userCreationRequest);
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
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setGroupName("GroupName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        userService.createContact(contactCreationRequest);
        assertThat(contactRepository.count(), is(1L));
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.searchUserById(user.getUserId()).getContacts().size(), is(1));
    }

    @Test
    public void createUser_saveContactInGroupAndUser_contactIsSavedTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setGroupName("GroupName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        userService.createContact(contactCreationRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(userService.searchUserById(user.getUserId()).getContacts().size(), is(1));
    }

    @Test
    public void createUser_createGroupWithNull_throwsExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.createContact(contactCreationRequest);
        })
                .isInstanceOf(EmptyStringException.class)
                .hasMessageContaining("Group name cannot be null or empty");
    }

    @Test
    public void createUser_createGroupWithEmptyString_throwsExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setGroupName("");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.createContact(contactCreationRequest);
        })
                .isInstanceOf(EmptyStringException.class)
                .hasMessageContaining("Group name cannot be null or empty");
    }

    @Test
    public void createUser_createGroup_createContactLaterAndAddToExistingGroup_contactIsSavedTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        GroupCreationRequest groupCreationRequest = new GroupCreationRequest();
        groupCreationRequest.setGroupName("GroupName");
        groupCreationRequest.setUserId(user.getUserId());
        GroupCreationResponse group = userService.createGroup(groupCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setGroupName(group.getGroupName());
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        userService.createContact(contactCreationRequest);
        assertThat(userService.searchUserById(user.getUserId()).getGroups().size(), is(1));
        assertThat(userService.searchUserById(user.getUserId()).getGroups().getFirst().getContacts().size(), is(1));
    }

    @Test
    public void createUser_loginUser_userIsLoggedInTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUserId(user.getUserId());
        userLoginRequest.setEmail(user.getEmail());
        userService.loginUser(userLoginRequest);
        assertThat(userService.searchUserById(user.getUserId()).isLocked(), is(false));
    }


    @Test
    public void createUser_loginUser_logoutUser_userIsLoggedOutTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUserId(user.getUserId());
        userService.loginUser(userLoginRequest);
        userService.logoutUser(user.getUserId());
        assertThat(userService.searchUserById(user.getUserId()).isLocked(), is(true));
    }

    @Test
    public void createUser_logoutUser_createContact_throwExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        userService.logoutUser(user.getUserId());
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setGroupName("GroupName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.createContact(contactCreationRequest);
        })
                .isInstanceOf(UserLockedException.class)
                .hasMessageContaining("User is not logged in");
    }

    @Test
    public void createUser_logoutUser_createGroup_throwsExceptionTest(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        userService.logoutUser(user.getUserId());
        GroupCreationRequest groupCreationRequest = new GroupCreationRequest();
        groupCreationRequest.setGroupName("GroupName");
        groupCreationRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.createGroup(groupCreationRequest);
        })
                .isInstanceOf(UserLockedException.class)
                .hasMessageContaining("User is not logged in");
    }

    @Test
    public void createUser_logoutUser_updateContact_throwException(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        ContactCreationRequest contactCreationRequest = new ContactCreationRequest();
        contactCreationRequest.setFirstName("FirstName");
        contactCreationRequest.setGroupName("GroupName");
        contactCreationRequest.setLastName("LastName");
        contactCreationRequest.setEmail("email@email.com");
        contactCreationRequest.setPhoneNumber("1234567890");
        contactCreationRequest.setUserId(user.getUserId());
        ContactCreationResponse contact = userService.createContact(contactCreationRequest);
        userService.logoutUser(user.getUserId());
        ContactUpdateRequest contactUpdateRequest = new ContactUpdateRequest();
        contactUpdateRequest.setFirstName("FirstName");
        contactUpdateRequest.setLastName("LastName");
        contactUpdateRequest.setEmail("email@email.com");
        contactUpdateRequest.setPhoneNumber("1234567890");
        contactUpdateRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.updateContact(contactUpdateRequest);
        })
                .isInstanceOf(UserLockedException.class)
                .hasMessageContaining("User is not logged in");
    }

    @Test
    public void createUser_logoutUser_createGroup_throwException(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        GroupCreationRequest groupCreationRequest = new GroupCreationRequest();
        groupCreationRequest.setGroupName("GroupName");
        groupCreationRequest.setUserId(user.getUserId());
        userService.logoutUser(user.getUserId());
        assertThatThrownBy(()->{
            userService.createGroup(groupCreationRequest);
        })
                .isInstanceOf(UserLockedException.class)
                .hasMessageContaining("User is not logged in");

    }

    @Test
    public void createUser_createGroup_logoutUser_updateGroup_throwException(){
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername("Username");
        userCreationRequest.setPassword("Password");
        userCreationRequest.setEmail("email@email.com");
        UserRegistrationResponse user = userService.registerUser(userCreationRequest);
        GroupCreationRequest groupCreationRequest = new GroupCreationRequest();
        groupCreationRequest.setGroupName("GroupName");
        groupCreationRequest.setUserId(user.getUserId());
        GroupCreationResponse group = userService.createGroup(groupCreationRequest);
        userService.logoutUser(user.getUserId());
        UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest();
        updateGroupRequest.setGroupName("GroupName");
        updateGroupRequest.setGroupId(group.getGroupId());
        updateGroupRequest.setUserId(user.getUserId());
        assertThatThrownBy(()->{
            userService.updateGroup(updateGroupRequest);
        })
                .isInstanceOf(UserLockedException.class)
                .hasMessageContaining("User is not logged in");

    }


}
