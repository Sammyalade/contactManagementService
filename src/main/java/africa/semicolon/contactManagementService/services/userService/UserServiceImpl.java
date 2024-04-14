package africa.semicolon.contactManagementService.services.userService;


import africa.semicolon.contactManagementService.datas.models.Contact;
import africa.semicolon.contactManagementService.datas.models.Group;
import africa.semicolon.contactManagementService.datas.models.User;
import africa.semicolon.contactManagementService.datas.repositories.UserRepository;
import africa.semicolon.contactManagementService.dtos.requests.GroupCreationRequest;
import africa.semicolon.contactManagementService.dtos.requests.*;
import africa.semicolon.contactManagementService.dtos.responses.ContactCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.GroupCreationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserRegistrationResponse;
import africa.semicolon.contactManagementService.dtos.responses.UserUpdateResponse;
import africa.semicolon.contactManagementService.exception.*;
import africa.semicolon.contactManagementService.services.contactService.ContactService;
import africa.semicolon.contactManagementService.services.groupService.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static africa.semicolon.contactManagementService.utility.Mapper.*;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private GroupService groupService;

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
        User user = searchUserById(userUpdateRequest.getUserId());
        if(!user.isLocked()) {
            user.setUsername(userUpdateRequest.getUsername());
            userRepository.save(user);
            return map(userUpdateRequest, user.getEmail());
        }
        throw new UserLockedException("User not logged in. Please login and try again");
    }


    @Override
    public void deleteUser(int id) {
        User user = searchUserById(id);
        if(!user.isLocked()) {
            userRepository.delete(user);
        } else throw new UserLockedException("User not logged in. Please login and try again");
    }




    @Override
    public ContactCreationResponse createContact(ContactCreationRequest contactCreationRequest) {
        User user = searchUserById(contactCreationRequest.getUserId());
        if (!user.isLocked()) {
            Contact contact = contactService.createContact(contactCreationRequest);
            if(isEmptyOrNullString(contactCreationRequest.getGroupName())) throw new EmptyStringException("Group name cannot be null or empty");
            Group group = groupService.findByName(contactCreationRequest.getGroupName());
            if (group == null) {
                group = groupService.createGroup(contactCreationRequest.getGroupName());
                group.setContacts((List<Contact>) checkIfListIsNull(group.getContacts()));
            } else {
                group.getContacts().add(contact);
            }
            user.getContacts().add(contact);
            user.getGroups().add(group);
            userRepository.save(user);
            return map(user, contact);
        } else {
            throw new UserLockedException("User is not logged in");
        }
    }




    @Override
    public ContactUpdateResponse updateContact(ContactUpdateRequest contactUpdateRequest){
        User user = searchUserById(contactUpdateRequest.getUserId());
        if(!user.isLocked()) {
            Contact contact = contactService.updateContact(contactUpdateRequest);
            return map(user.getId(), contact.getId(), contact.getName());
        }
        throw new UserLockedException("User is not logged in");
    }

    @Override
    public void deleteContact(ContactDeleteRequest contactDeleteRequest){
        User user = searchUserById(contactDeleteRequest.getUserId());
        if (!user.isLocked()) {
            Optional<Contact> contactOptional = Optional.ofNullable(contactService.getContactById(contactDeleteRequest.getContactId()));
            if (contactOptional.isPresent()) {
                contactService.deleteContact(contactDeleteRequest.getContactId());
            } else {
                throw new ContactNotFoundException("Contact not found");
            }
        } else throw new UserLockedException("User is not logged in");

    }

    @Override
    public List<Contact> findAllContact(int userId){
        if(!searchUserById(userId).isLocked()) return searchUserById(userId).getContacts();
        else throw new UserLockedException("User not logged in");
    }




   @Override
   public GroupCreationResponse createGroup(GroupCreationRequest groupCreationRequest){
        User user = searchUserById(groupCreationRequest.getUserId());
        if (!user.isLocked()) {
            Group group = groupService.createGroup(groupCreationRequest.getGroupName());
            user.getGroups().add(group);
            return map(group.getId(), group.getName(), user.getId());
        }
        throw new UserLockedException("User is not logged in");
   }

   @Override
   public void updateGroup(UpdateGroupRequest updateGroupRequest){
        User user = searchUserById(updateGroupRequest.getUserId());
        if(!user.isLocked()) {
            Group group = groupService.getGroupById(updateGroupRequest.getGroupId());
            if (user.getGroups().contains(group)) {
                groupService.updateGroup(updateGroupRequest);
            } else throw new GroupNotFoundException("Group not found");
        }
        else throw new UserLockedException("User is not logged in");
   }

   @Override
   public void deleteGroup(GroupDeleteRequest groupDeleteRequest){
        User user = searchUserById(groupDeleteRequest.getUserId());
        if (!user.isLocked()) {
            Group group = groupService.getGroupById(groupDeleteRequest.getGroupId());
            if (searchUserById(groupDeleteRequest.getUserId()).getGroups().contains(group)) {
                groupService.deleteGroup(group.getId());
            } else throw new GroupNotFoundException("Group not found");
        }
        else throw new UserLockedException("User is not logged in");
   }

   @Override
   public List<Group> findAllGroup(int userId){
        if(!searchUserById(userId).isLocked()) return searchUserById(userId).getGroups();
        else throw new UserLockedException("User not logged in");
   }

}
